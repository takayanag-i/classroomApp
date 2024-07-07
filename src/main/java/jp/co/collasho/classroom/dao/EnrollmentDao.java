package jp.co.collasho.classroom.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import jp.co.collasho.classroom.constants.ErrorMessages;
import jp.co.collasho.classroom.entity.EnrollmentEntity;

public class EnrollmentDao {
    /** コネクション */
    Connection conn;

    /**
     * コンストラクタ
     * 
     * @param conn
     */
    public EnrollmentDao(Connection conn) {
        this.conn = conn;
    }

    /**
     * INSERT
     * 
     * @param enrollment 履修登録エンティティ
     */
    public void insert(EnrollmentEntity enrollment) {
        String query =
                "insert into Enrollments (student_id, course_id, enrollment_date) values (?, ?, ?);";
        try (PreparedStatement pStmt = conn.prepareStatement(query)) {
            pStmt.setString(1, enrollment.getStudentId());
            pStmt.setString(2, enrollment.getCourseId());
            pStmt.setTimestamp(3, new Timestamp(System.currentTimeMillis()));

            pStmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(ErrorMessages.DAO_INSERT_ERROR, e);
        }
    }

    /**
     * DELETE
     * 
     * @param courseId 講座コード
     */
    public void delete(String studentId, String courseId) {
        String query = "delete from Enrollments where student_id = ? and course_id = ?";
        try (PreparedStatement pStmt = conn.prepareStatement(query)) {
            pStmt.setString(1, studentId);
            pStmt.setString(2, courseId);

            pStmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(ErrorMessages.DAO_DELETE_ERROR, e);
        }
    }
}
