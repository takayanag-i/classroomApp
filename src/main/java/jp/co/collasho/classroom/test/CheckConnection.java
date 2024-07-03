package jp.co.collasho.classroom.test;

import java.sql.Connection;
import jp.co.collasho.classroom.common.ConnectionManager;

public class CheckConnection {
    public static void main(String[] args) throws ClassNotFoundException {
        ConnectionManager connectionManager = new ConnectionManager();

        try (Connection conn = connectionManager.getConnection()) {
            System.out.println("成功");
        } catch (Exception e) {
            System.out.println("失敗");
        }
    }
}
