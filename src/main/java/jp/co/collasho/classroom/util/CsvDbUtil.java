package jp.co.collasho.classroom.util;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class CsvDbUtil {

    private String csvDirPath;

    public CsvDbUtil(String csvDirPath) {
        this.csvDirPath = csvDirPath;
    }

    public void execute(Connection conn, String insertSQL) {
        try (Statement stmt = conn.createStatement()) {
            // テーブル名を取得
            String tableName = getTableNameFromInsertSQL(insertSQL);

            // テーブルが存在しない場合に作成
            createTableIfNotExists(stmt, tableName);

            // 新しいデータを挿入
            stmt.execute(insertSQL);

            // 挿入された行をクエリして取得
            String selectSQL = generateSelectSQLFromInsert(insertSQL);
            ResultSet rs = stmt.executeQuery(selectSQL);

            // 挿入された1行のデータをCSVファイルに追加
            if (rs.next()) {
                String csvFilePath = csvDirPath + "/" + tableName + ".csv";
                try (BufferedWriter csvWriter = new BufferedWriter(new FileWriter(csvFilePath, true))) {
                    String[] columns = getColumnsFromInsertSQL(insertSQL);
                    for (int i = 0; i < columns.length; i++) {
                        if (i > 0)
                            csvWriter.append(",");
                        csvWriter.append(rs.getString(columns[i].trim()));
                    }
                    csvWriter.append("\n");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("ステートメントを解放しました。");
        } catch (SQLException e) {
            throw new RuntimeException("ステートメントの解放に失敗しました");
        }
    }

    private void createTableIfNotExists(Statement stmt, String tableName) throws SQLException {
        String createTableSQL = "CREATE TABLE IF NOT EXISTS " + tableName + " AS SELECT * FROM CSVREAD('" + csvDirPath
                + "/" + tableName + ".csv')";
        stmt.execute(createTableSQL);
    }

    private static String generateSelectSQLFromInsert(String insertSQL) {
        String[] parts = insertSQL.split("VALUES");
        String columnsPart = parts[0].substring(parts[0].indexOf("(") + 1, parts[0].indexOf(")")).trim();
        String valuesPart = parts[1].substring(parts[1].indexOf("(") + 1, parts[1].lastIndexOf(")")).trim();

        String[] columns = columnsPart.split(",");
        String[] values = valuesPart.split(",");

        StringBuilder selectSQL = new StringBuilder("SELECT * FROM ");
        selectSQL.append(insertSQL.split(" ")[2].trim()); // テーブル名を取得

        selectSQL.append(" WHERE ");
        // 値をエスケープしてシングルクォートで囲む
        for (int i = 0; i < columns.length; i++) {
            String value = values[i].trim();
            if (value.startsWith("'") && value.endsWith("'")) {
                value = value.substring(1, value.length() - 1).replace("'", "''");
                value = "'" + value + "'";
            }
            selectSQL.append(columns[i].trim()).append(" = ").append(value);
            if (i < columns.length - 1) {
                selectSQL.append(" AND ");
            }
        }

        return selectSQL.toString();
    }

    private static String getTableNameFromInsertSQL(String insertSQL) {
        return insertSQL.split(" ")[2].trim(); // テーブル名を取得
    }

    private static String[] getColumnsFromInsertSQL(String insertSQL) {
        String columnsPart = insertSQL.split("VALUES")[0];
        columnsPart = columnsPart.substring(columnsPart.indexOf("(") + 1, columnsPart.indexOf(")")).trim();
        return columnsPart.split(",");
    }
}
