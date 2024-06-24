package jp.co.collasho.classroom.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import jp.co.collasho.classroom.entity.CourseEntity;

public class EnrolledCourseDao {
    /** コネクション */
    private Connection connection;

    /**
     * コンストラクタ
     * 
     * @param connection
     */
    public EnrolledCourseDao(Connection connection) {
        this.connection = connection;
    }

    /**
     * 
     * @param studentId
     * @return 登録講座エンティティのリスト
     */
    public List<CourseEntity> getCourses(String studentId) {
        List<CourseEntity> enrollments = new ArrayList<>();

        String query =
                "select e.course_id, c.course_name, c.day_of_week, c.period from Enrollments as e inner join Courses as c on c.course_id = e.course_id where e.student_id = ? ;";

        try (PreparedStatement pStmt = connection.prepareStatement(query)) {
            pStmt.setString(1, studentId);
            ResultSet resultSet = pStmt.executeQuery();

            while (resultSet.next()) {
                // ResuleSetから結果を取得
                String courseId = resultSet.getString("course_id");
                String courseName = resultSet.getString("course_name");
                String dayOfWeekString = resultSet.getString("day_of_week");
                int period = resultSet.getInt("period");

                // 履修登録エンティティの生成
                CourseEntity enrollment = new CourseEntity();

                enrollment.setCourseId(courseId);
                enrollment.setCourseName(courseName);
                enrollment.setDayOfWeekString(dayOfWeekString);
                enrollment.setPeriod(period);

                // リストに追加
                enrollments.add(enrollment);
            }

        } catch (SQLException e) {
            throw new RuntimeException("SELECTクエリの実行に失敗してステートメントを解放しました。");
        }
        return enrollments;
    }
}
