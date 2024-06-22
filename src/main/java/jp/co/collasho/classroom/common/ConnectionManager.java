package jp.co.collasho.classroom.common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionManager {
    /** コネクション */
    private Connection connection;
    /** JDBCURL */
    private String jdbcUrl = "jdbc:mysql://localhost:13306/training_db";
    /** ユーザ名 */
    private String user = "root";
    /** パスワード */
    private String password = "password";
    // stub実行用のjdbcUrlもどき
    // private String jdbcUrlStub = "jdbc:h2:mem:test;DB_CLOSE_DELAY=-1";

    /**
     * コンストラクタ
     */
    public ConnectionManager() {

    }

    /**
     * データベース接続
     * 
     * @throws RuntimeException
     * @throws ClassNotFoundException
     */
    public Connection getConnection() throws RuntimeException {
        if (this.connection == null) {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                this.connection =
                        DriverManager.getConnection(this.jdbcUrl, this.user, this.password);
                this.connection.setAutoCommit(false);
                System.out.println("データベースに接続しました。");
            } catch (SQLException e) {
                throw new RuntimeException("データベースの接続に失敗しました。", e);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException("ドライバが見つからないためデータベースの接続に失敗しました。");
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
