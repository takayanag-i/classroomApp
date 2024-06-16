package jp.co.collasho.classroom.test;

import java.sql.SQLException;
import java.sql.Statement;

import jp.co.collasho.classroom.common.Constants;

public class TestUtil {

    public static void createTableIfNotExists(Statement stmt, String tableName) throws SQLException {
        String createTableSQL = "CREATE TABLE IF NOT EXISTS " + tableName + " AS SELECT * FROM CSVREAD('"
                + Constants.CSV_DIR_PATH
                + "/" + tableName + ".csv')";
        stmt.execute(createTableSQL);
    }

    public static String generateSelectSQLFromInsert(String insertSQL) {
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

    public static String getTableNameFromInsertSQL(String insertSQL) {
        return insertSQL.split(" ")[2].trim(); // テーブル名を取得
    }

    public static String[] getColumnsFromInsertSQL(String insertSQL) {
        String columnsPart = insertSQL.split("VALUES")[0];
        columnsPart = columnsPart.substring(columnsPart.indexOf("(") + 1, columnsPart.indexOf(")")).trim();
        return columnsPart.split(",");
    }
}
