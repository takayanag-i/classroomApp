package jp.co.collasho.classroom.service.login;

import java.sql.Connection;
import java.sql.SQLException;
import jp.co.collasho.classroom.common.ConnectionManager;
import jp.co.collasho.classroom.dao.StudentDao;
import jp.co.collasho.classroom.dto.LoginStudentDto;
import jp.co.collasho.classroom.entity.StudentEntity;
import jp.co.collasho.classroom.exception.LoginFailedException;

/**
 * ログインサービスのドライバ
 */
public class LoginDriver {
    /** コネクションマネージャ */
    ConnectionManager connectionManager = new ConnectionManager();

    public LoginStudentDto drive(String studentId, String password) throws LoginFailedException {
        try (Connection connection = this.connectionManager.getConnection()) {
            StudentDao studentDao = new StudentDao(connection);

            StudentEntity student = studentDao.getStudentEntity(studentId, password);
            String name = student.getName();
            String email = student.getEmail();

            return new LoginStudentDto(studentId, name, email);

        } catch (SQLException e) {
            throw new RuntimeException("予期しないログインエラーが発生しました", e);
        }
    }
}
