package jp.co.collasho.classroom.service.login;

import java.sql.Connection;
import java.sql.SQLException;
import jp.co.collasho.classroom.common.ConnectionManager;
import jp.co.collasho.classroom.constants.ErrorMessages;
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

    /**
     * ログイン処理を実行する
     * 
     * @param studentId 出席番号
     * @param password パスワード
     * @return ログインする学生のエンティティ
     * @throws LoginFailedException ログインに失敗したときにスローされる例外
     */
    public LoginStudentDto getStudentToLogin(String studentId, String password)
            throws LoginFailedException {
        try (Connection conn = this.connectionManager.getConnection()) {
            StudentDao studentDao = new StudentDao(conn);

            StudentEntity entity = studentDao.selectByIdAndPassword(studentId, password);

            if (entity == null) {
                // ログイン失敗
                throw new LoginFailedException(ErrorMessages.LOGIN_FAILED);
            }

            // ログイン成功
            LoginStudentDto dto = new LoginStudentDto();
            dto.setStudentId(studentId);
            dto.setName(entity.getName());
            dto.setEmail(entity.getEmail());

            return dto;

        } catch (SQLException e) {
            throw new RuntimeException(ErrorMessages.DRIVER_LOGIN_ERROR, e);
        }
    }
}
