package jp.co.collasho.classroom.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import jp.co.collasho.classroom.constants.DbConstants;
import jp.co.collasho.classroom.constants.ErrorMessages;
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
     * @param courses 講座エンティティのリスト
     * @return 講座-教員対応エンティティのリスト
     */
    public List<InstructionEntity> select(List<CourseEntity> courses) {
        List<InstructionEntity> instructions = new ArrayList<>();

        String query = this.getQueryWithInClause(courses);

        try (PreparedStatement pStmt = conn.prepareStatement(query)) {
            int i = 1;
            for (CourseEntity course : courses) {
                pStmt.setString(i++, course.getCourseId());
            }
            ResultSet rs = pStmt.executeQuery();

            while (rs.next()) {
                InstructionEntity instruction = this.getEntityFromResult(rs);
                instructions.add(instruction);
            }

        } catch (SQLException e) {
            throw new RuntimeException(ErrorMessages.UNEXPECTED_SELECT_ERROR, e);
        }
        return instructions;
    }

    /**
     * 検索条件の教員名から，講座と教員の対応づけを取得
     * 
     * @param criteria
     * @return 講座-教員対応エンティティのリスト
     */
    public List<InstructionEntity> select(SearchCriteriaDto criteria) {
        List<InstructionEntity> instructions = new ArrayList<>();

        String query =
                "select n.course_id, r.name from Instructions as n inner join Instructors as r on r.instructor_id = n.instructor_id where r.name like ?";

        try (PreparedStatement pStmt = conn.prepareStatement(query)) {
            pStmt.setString(1, "%" + criteria.getInstructorName() + "%");

            ResultSet rs = pStmt.executeQuery();

            while (rs.next()) {
                InstructionEntity instruction = this.getEntityFromResult(rs);
                instructions.add(instruction);
            }

        } catch (SQLException e) {
            throw new RuntimeException(ErrorMessages.UNEXPECTED_SELECT_ERROR, e);
        }

        return instructions;
    }

    /**
     * ResultSetから講座-教員対応エンティティを生成
     * 
     * @param rs java.sql.ResultSet
     * @return 講座-教員対応エンティティ
     * @throws SQLException
     */
    private InstructionEntity getEntityFromResult(ResultSet rs) throws SQLException {
        InstructionEntity instruction = new InstructionEntity();

        instruction.setCourseId(rs.getString(DbConstants.COURSE_ID));
        instruction.setInstructor(rs.getString(DbConstants.INSTRUCTOR_NAME));

        return instruction;
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
