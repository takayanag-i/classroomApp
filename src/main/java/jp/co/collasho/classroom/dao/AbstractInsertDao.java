package jp.co.collasho.classroom.dao;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import jp.co.collasho.classroom.common.Constants;
import jp.co.collasho.classroom.entity.AbstractEntity;

public abstract class AbstractInsertDao {

    /** コネクション */
    protected Connection connection;
    /** insert文 */
    protected String insertQuery;

    /**
     * コンストラクタ
     * 
     * @param connection コネクション
     */
    public AbstractInsertDao(Connection connection) {
        this.connection = connection;
    }

    /**
     * insert文の実行
     * 
     * @param entity
     */
    public void insert(AbstractEntity entity) {
        try (PreparedStatement pStmt = this.connection.prepareStatement(this.insertQuery)) {
            PreparedStatement pStmtReady = setParameters(pStmt, entity);
            pStmtReady.executeUpdate();

            System.out.println("insertクエリを実行してステートメントを解放しました。");

        } catch (SQLException e) {
            throw new RuntimeException("insertクエリの実行に失敗してステートメントを解放しました。", e);
        }
    }

    /**
     * insert文を実行したフリをする
     * 
     * @param entity
     * @throws IOException
     */
    public void insertStub(AbstractEntity entity) throws IOException {
        String tableName = this.getTableName();
        String csvFilePath = Constants.CSV_DIR_PATH + tableName + ".csv";
        int columnCount = entity.getClass().getDeclaredFields().length;

        try (BufferedWriter csvWriter = new BufferedWriter(new FileWriter(csvFilePath, true))) {
            String[] pStmtStub = new String[columnCount];
            this.setParametersStub(pStmtStub, entity);

            for (int i = 0; i < pStmtStub.length; i++) {
                if (i > 0) {
                    csvWriter.append(",");
                }
                csvWriter.append(pStmtStub[i]);
            }
        } catch (IOException e) {
            throw new RuntimeException("insertもどきメソッドの入出力エラーが発生しました", e);
        }

    }

    // エンティティのフィールドをpStmtにセッパラ
    protected abstract PreparedStatement setParameters(PreparedStatement pStmt,
            AbstractEntity entity) throws SQLException;

    // setParametersもどき
    protected abstract void setParametersStub(String[] pStmtStub, AbstractEntity entity);

    protected String getTableName() {
        return this.insertQuery.split(" ")[2].trim(); // テーブル名を取得
    }
}
