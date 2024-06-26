package jp.co.collasho.classroom.common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * コネクションマネージャクラス
 */
public class ConnectionManager {
    /** コネクション */
    private Connection conn;
    /** JDBCURL */
    private String jdbcUrl = "jdbc:mysql://localhost:13306/training_db";
    /** ユーザ名 */
    private String user = "root";
    /** パスワード */
    private String password = "password";

    /**
     * データベース接続
     * 
     * @return コネクション
     * @throws RuntimeException
     * @throws ClassNotFoundException
     */
    public Connection getConnection() throws RuntimeException {
        if (this.conn == null) {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                this.conn = DriverManager.getConnection(this.jdbcUrl, this.user, this.password);
                this.conn.setAutoCommit(false);
                System.out.println("データベースに接続しました。");
            } catch (SQLException e) {
                throw new RuntimeException("データベースの接続に失敗しました。", e);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException("ドライバが見つからないためデータベースの接続に失敗しました。");
            }
        }
        return this.conn;
    }

    /**
     * データベース切断
     * 
     * @throws RuntimeException
     */
    public void closeConnection() throws RuntimeException {
        try {
            if (this.conn != null) {
                this.conn.close();
            }
        } catch (SQLException e) {
            throw new RuntimeException("データベースの切断に失敗しました。", e);
        } finally {
            this.conn = null;
        }
    }

    /**
     * コミット
     * 
     * @throws RuntimeException
     */
    public void commit() throws RuntimeException {
        try {
            if (this.conn != null) {
                this.conn.commit();
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
            if (this.conn != null) {
                this.conn.rollback();
            }
        } catch (SQLException e) {
            throw new RuntimeException("トランザクションのロールバックに失敗しました。", e);
        }
    }

}
