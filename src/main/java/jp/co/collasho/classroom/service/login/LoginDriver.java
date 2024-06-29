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

    /**
     * ログイン処理を実行する
     * 
     * @param studentId 出席番号
     * @param password パスワード
     * @return ログインする学生のエンティティ
     * @throws LoginFailedException ID・passでログインできる学生がいない場合
     */
    public LoginStudentDto getStudentToLogin(String studentId, String password)
            throws LoginFailedException {
        try (Connection conn = this.connectionManager.getConnection()) {
            StudentDao studentDao = new StudentDao(conn);

            StudentEntity student = studentDao.select(studentId, password);

            if (student == null) {
                // ログイン失敗
                throw new LoginFailedException("ログインに失敗しました。");
            }

            // ログイン成功
            String name = student.getName();
            String email = student.getEmail();

            return new LoginStudentDto(studentId, name, email);

        } catch (SQLException e) {
            throw new RuntimeException("予期しないログインエラーが発生しました", e);
        }
    }
}
