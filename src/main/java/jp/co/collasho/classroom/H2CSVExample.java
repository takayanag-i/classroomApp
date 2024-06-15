package jp.co.collasho.classroom;

import java.sql.Connection;
import java.sql.SQLException;

import jp.co.collasho.classroom.common.ConnectionManager;
import jp.co.collasho.classroom.util.CsvDbUtil;

public class H2CSVExample {
    public static void main(String[] args) {

        ConnectionManager connectionManager = new ConnectionManager();
        CsvDbUtil csvDbUtil = new CsvDbUtil("classroom/src/main/resources");

        try (Connection conn = connectionManager.getConnection();) {

            // 新しいデータを挿入
            String insertSQL = "INSERT INTO students (student_id, name, email, password) VALUES (1420, 'akari', 'stu20231420@sry.collasho.jp', '0000000')";
            csvDbUtil.execute(conn, insertSQL); // モック

            // Statement stmt = conn.createStatement();
            // stmt.execute(insertSQL);
            // connectionManager.commit();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
