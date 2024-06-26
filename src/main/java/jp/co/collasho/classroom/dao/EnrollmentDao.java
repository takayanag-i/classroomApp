package jp.co.collasho.classroom.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import jp.co.collasho.classroom.entity.EnrollmentEntity;

public class EnrollmentDao {
    /** コネクション */
    Connection connection;

    /**
     * コンストラクタ
     * 
     * @param connection
     */
    public EnrollmentDao(Connection connection) {
        this.connection = connection;
    }

    /**
     * 履修の「登録」
     * 
     * @param enrollment
     */
    public void insertEnrollment(EnrollmentEntity enrollment) {
        String query =
                "insert into Enrollments (student_id, course_id, enrollment_date) values (?, ?, ?);";
        try (PreparedStatement pStmt = connection.prepareStatement(query)) {
            // TODO
        } catch (SQLException e) {
            throw new RuntimeException("INSERTクエリの実行時に予期しないエラーが発生しました。", e);
        }
    }

    /**
     * 履修の「削除」
     * 
     * 登録期間中は削除することができる
     * 
     * @param courseId
     */
    public void deleteEnrollment(String courseId) {
        // TODO
    }

    /**
     * 履修の「取消」
     * 
     * 登録期間後，1週間だけ実行できる
     * 
     * @param courseId
     */
    public void updateCancelFlug(String courseId) {
        // TODO
    }
}
