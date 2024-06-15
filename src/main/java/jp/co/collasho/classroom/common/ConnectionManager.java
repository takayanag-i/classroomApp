package jp.co.collasho.classroom.common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionManager {
    /** コネクション */
    private Connection connection;
    /** JDBCURL */
    private String jdbcUrl = "jdbc:h2:mem:test;DB_CLOSE_DELAY=-1";

    /**
     * コンストラクタ
     */
    public ConnectionManager() {

    }

    /**
     * データベース接続
     * 
     * @throws RuntimeException
     */
    public Connection getConnection() throws RuntimeException {
        if (this.connection == null) {
            try {
                this.connection = DriverManager.getConnection(this.jdbcUrl);
                this.connection.setAutoCommit(false);
            } catch (SQLException e) {
                throw new RuntimeException("データベースの接続に失敗しました。", e);
            }
        }
        return this.connection;
    }

    /**
     * データベース切断
     * 
     * @throws RuntimeException
     */
    public void closeConnection() throws RuntimeException {
        try {
            if (this.connection != null) {
                this.connection.close();
            }
        } catch (SQLException e) {
            throw new RuntimeException("データベースの切断に失敗しました。", e);
        } finally {
            this.connection = null;
        }
    }

    /**
     * コミット
     * 
     * @throws RuntimeException
     */
    public void commit() throws RuntimeException {
        try {
            if (this.connection != null) {
                this.connection.commit();
            }
        } catch (SQLException e) {
            throw new RuntimeException("トランザクションのコミットに失敗しました。", e);
        }
    }

    /**
     * ロールバック
     * 
     * @throws RuntimeException
     */
    public void rollback() throws RuntimeException {
        try {
            if (this.connection != null) {
                this.connection.rollback();
            }
        } catch (SQLException e) {
            throw new RuntimeException("トランザクションのロールバックに失敗しました。", e);
        }
    }

}
