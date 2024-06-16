package jp.co.collasho.classroom.test;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import jp.co.collasho.classroom.common.ConnectionManager;
import jp.co.collasho.classroom.dao.StudentDao;
import jp.co.collasho.classroom.entity.StudentEntity;

public class Test {

    public static void main(String[] args) {
        // モックデータを作成
        StudentEntity student = new StudentEntity();
        student.setStudentId(1);
        student.setName("John Doe");
        student.setEmail("john.doe@example.com");
        student.setPassword("password123");

        // DB接続もどき
        ConnectionManager connectionManager = new ConnectionManager();

        try (Connection connection = connectionManager.getConnection();) {
            StudentDao studentDao = new StudentDao(connection);
            // insertStubメソッドを実行
            studentDao.insertStub(student);
        } catch (IOException e) {
            throw new RuntimeException("登録に失敗しました。");
        } catch (SQLException e) {
            throw new RuntimeException("登録に失敗しました。");
        }
        System.out.println("コネクションを切断しました。");
    }
}
