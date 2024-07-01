package jp.co.collasho.classroom.service.delete;

import java.sql.Connection;
import java.sql.SQLException;
import jp.co.collasho.classroom.common.ConnectionManager;
import jp.co.collasho.classroom.dao.EnrollmentDao;

/**
 * 履修削除処理のドライバ
 */
public class DeleteDriver {
    /** コネクションマネージャ */
    ConnectionManager connectionManager = new ConnectionManager();

    /**
     * 履修登録を抹消する
     */
    public void deleteEnrollment(String studentId, String courseId) {
        try (Connection conn = this.connectionManager.getConnection()) {
            EnrollmentDao enrollmentDao = new EnrollmentDao(conn);
            enrollmentDao.delete(studentId, courseId);

            this.connectionManager.commit();

        } catch (SQLException e) {
            this.connectionManager.rollback();
            throw new RuntimeException("履修の抹消における予期せぬエラーが発生しました。", e);
        }
    }
}
