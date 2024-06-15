package jp.co.collasho.classroom.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Map;

import jp.co.collasho.classroom.entity.AbstractEntity;

public abstract class AbstractInsertDao {

    /** コネクション */
    private Connection conneciton;
    /** テーブル名 */
    String table;

    /**
     * コンストラクタ
     * 
     * @param connection コネクション
     */
    public AbstractInsertDao(Connection connection) {
        this.conneciton = connection;
    }

    // 共通のinsertメソッド
    public void insert(AbstractEntity entity, String insertSQL) {
        // TODO
    }

    // 共通のinsertもどきメソッド
    public void insertStub(AbstractEntity entity, String insertSQL) {
        // TODO
    }

    // エンティティのフィールドをpStmtにセッパラ
    protected abstract void setParameters(PreparedStatement pStmt, AbstractEntity entity);

    /**
     * もしメモリにテーブルがなかったら作成する
     * 
     * @throws SQLException
     */
    private void createTableIfNotExists() throws SQLException {
        StringBuilder createTableSQL = new StringBuilder("CREATE TABLE IF NOT EXISTS" + this.table + " (");

        // TODO Mapを処理する

        createTableSQL.setLength(createTableSQL.length() - 1); // 最後のカンマを削除
        createTableSQL.append(")");
        String query = createTableSQL.toString();

        try (Statement stmt = this.conneciton.createStatement()) {
            stmt.execute(query);
        }
    }

    /**
     * カラム定義を取得する抽象メソッド
     * 
     * @return <カラム名, 型>のマップ
     */
    public abstract Map<String, String> getColumnDefinitions();
}
