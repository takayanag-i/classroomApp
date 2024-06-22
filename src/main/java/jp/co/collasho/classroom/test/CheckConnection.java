package jp.co.collasho.classroom.test;

import java.sql.Connection;
import jp.co.collasho.classroom.common.ConnectionManager;

public class CheckConnection {
    public static void main(String[] args) throws ClassNotFoundException {
        Class.forName("com.mysql.jdbc.Driver");

        ConnectionManager connectionManager = new ConnectionManager();

        try (Connection connection = connectionManager.getConnection()) {
            System.out.println("成功");
        } catch (Exception e) {
            System.out.println("失敗");
        }
    }
}
