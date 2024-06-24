package jp.co.collasho.classroom.dao;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import jp.co.collasho.classroom.entity.CourseEntity;
import jp.co.collasho.classroom.entity.InstructionEntity;

public class InstructionDao {
    /** コネクション */
    private Connection connection;

    /**
     * コンストラクタ
     * 
     * @param connection
     */
    public InstructionDao(Connection connection) {
        this.connection = connection;
    }

    public List<InstructionEntity> getInstructions(List<CourseEntity> enrollments) {
        List<InstructionEntity> instructions = new ArrayList<>();
        StringBuilder sb = new StringBuilder();

        String quelette =
                "select n.course_id, r.name from Instructions as n inner join Instructors as r on r.instructor_id = n.instructor_id where 1=0";
        sb.append(quelette);

        for (CourseEntity enrollment : enrollments) {
            String courseId = enrollment.getCourseId();
            sb.append(" or n.course_id = '");
            sb.append(courseId);
            sb.append("'");
        }

        sb.append(";");
        String query = sb.toString();

        try (Statement stmt = connection.createStatement()) {
            ResultSet resultSet = stmt.executeQuery(query);

            while (resultSet.next()) {
                // ResuleSetから結果を取得
                String courseId = resultSet.getString("course_id");
                String instructor = resultSet.getString("name");

                // 履修登録エンティティの生成
                InstructionEntity instruction = new InstructionEntity(courseId, instructor);

                // リストに追加
                instructions.add(instruction);
            }

        } catch (SQLException e) {
            throw new RuntimeException("SELECTクエリの実行に失敗してステートメントを解放しました。");
        }
        return instructions;
    }
}
