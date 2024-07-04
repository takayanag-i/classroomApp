package jp.co.collasho.classroom.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
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
     * 履修の「登録」
     * 
     * @param entity 履修登録エンティティ
     */
    public void insert(EnrollmentEntity entity) {
        String query =
                "insert into Enrollments (student_id, course_id, enrollment_date) values (?, ?, ?);";
        try (PreparedStatement pStmt = conn.prepareStatement(query)) {
            pStmt.setString(1, entity.getStudentId());
            pStmt.setString(2, entity.getCourseId());
            pStmt.setTimestamp(3, new Timestamp(System.currentTimeMillis()));

            pStmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("INSERTクエリの実行時に予期しないエラーが発生しました。", e);
        }
    }

    /**
     * 履修の「抹消」
     * 
     * 登録期間中は抹消することができる
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
            throw new RuntimeException("DELETEクエリの実行時に予期しないエラーが発生しました。", e);
        }
    }
}
