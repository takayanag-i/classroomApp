package jp.co.collasho.classroom.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import jp.co.collasho.classroom.dto.SearchCriteriaDto;
import jp.co.collasho.classroom.entity.CourseEntity;
import jp.co.collasho.classroom.entity.InstructionEntity;

/**
 * 教員データへのアクセス用オブジェクト
 */
public class InstructionDao {
    /** コネクション */
    private Connection conn;

    /**
     * コンストラクタ
     * 
     * @param conn コネクション
     */
    public InstructionDao(Connection conn) {
        this.conn = conn;
    }

    /**
     * 講座リストを受け取り，講座と教員の対応づけを全取得する
     * 
     * @param courseEntities 講座エンティティのリスト
     * @return 講座-教員対応エンティティのリスト
     */
    public List<InstructionEntity> select(List<CourseEntity> courseEntities) {
        List<InstructionEntity> instructionEntities = new ArrayList<>();

        String query = this.getQueryWithInClause(courseEntities);

        try (PreparedStatement pStmt = conn.prepareStatement(query)) {
            int i = 1;
            for (CourseEntity courseEntity : courseEntities) {
                pStmt.setString(i++, courseEntity.getCourseId());
            }
            ResultSet rs = pStmt.executeQuery();

            while (rs.next()) {
                InstructionEntity instructionEntity = this.getEntityFromResult(rs);
                instructionEntities.add(instructionEntity);
            }

        } catch (SQLException e) {
            throw new RuntimeException("SELECTクエリの実行に失敗してステートメントを解放しました。", e);
        }
        return instructionEntities;
    }

    /**
     * 検索条件の教員名から，講座と教員の対応づけを取得
     * 
     * @param criteriaDto
     * @return 講座-教員対応エンティティのリスト
     */
    public List<InstructionEntity> select(SearchCriteriaDto criteriaDto) {
        List<InstructionEntity> instructionsEntities = new ArrayList<>();

        String query =
                "select n.course_id, r.name from Instructions as n inner join Instructors as r on r.instructor_id = n.instructor_id where r.name like ?";

        try (PreparedStatement pStmt = conn.prepareStatement(query)) {
            pStmt.setString(1, "%" + criteriaDto.getInstructorName() + "%");

            ResultSet rs = pStmt.executeQuery();

            while (rs.next()) {
                InstructionEntity instructionEntity = this.getEntityFromResult(rs);
                instructionsEntities.add(instructionEntity);
            }

        } catch (SQLException e) {
            throw new RuntimeException("SELECTクエリの実行に失敗してステートメントを開放しました。", e);
        }

        return instructionsEntities;
    }

    /**
     * ResultSetから講座-教員対応エンティティを生成
     * 
     * @param rs java.sql.ResultSet
     * @return 講座-教員対応エンティティ
     * @throws SQLException
     */
    private InstructionEntity getEntityFromResult(ResultSet rs) throws SQLException {
        InstructionEntity entity = new InstructionEntity();

        entity.setCourseId(rs.getString("course_id"));
        entity.setInstructor(rs.getString("name"));

        return entity;
    }

    /**
     * 講座数だけの?をIN句にもつクエリを生成する
     * 
     * @param entities 講座エンティティのリスト
     * @return クエリ
     */
    private String getQueryWithInClause(List<CourseEntity> entities) {
        StringBuilder sb = new StringBuilder();
        sb.append(
                "select n.course_id, r.name from Instructions as n inner join Instructors as r on r.instructor_id = n.instructor_id where n.course_id in (");

        if (entities.isEmpty()) {
            // 履修登録が1つもされていない場合の処理
            sb.delete(sb.length() - 23, sb.length());
            sb.append(";");

            return sb.toString();
        }

        for (int i = 0; i < entities.size(); i++) {
            sb.append("?, ");
        }
        // 末尾を微調整
        sb.delete(sb.length() - 2, sb.length());
        sb.append(");");

        return sb.toString();
    }
}
