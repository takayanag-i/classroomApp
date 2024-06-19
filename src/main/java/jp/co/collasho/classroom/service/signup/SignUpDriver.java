package jp.co.collasho.classroom.service.signup;

import java.sql.Connection;
import java.sql.SQLException;
import jp.co.collasho.classroom.common.ConnectionManager;
import jp.co.collasho.classroom.dao.StudentDao;
import jp.co.collasho.classroom.entity.StudentEntity;

public class SignUpDriver {
    public static void main(String[] args) {
        ConnectionManager connectionManager = new ConnectionManager();

        try (Connection connection = connectionManager.getConnection()) {
            StudentDao studentDao = new StudentDao(connection);
            StudentEntity studentEntity =
                    new StudentEntity("1802", "山口", "mei2@example.com", "0403");
            studentDao.insert(studentEntity);
            connectionManager.commit();
            System.out.println("サインアップに成功してコネクションを切断しました");
        } catch (SQLException e) {
            connectionManager.rollback();
            System.out.println("サインアップに失敗してコネクションを切断しました");
        }
    }
}
