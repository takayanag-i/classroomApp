package jp.co.collasho.classroom.test;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import jp.co.collasho.classroom.common.Constants;
import jp.co.collasho.classroom.entity.StudentEntity;

public class StubStudentDao {

    /** コネクション */
    protected Connection connection;
    /** insert文 */
    protected String insertQuery =
            "INSERT INTO Students (student_id, name, email, password) VALUES (?, ?, ?, ?)";

    /**
     * コンストラクタ：コネクションとクエリをフィールド
     * 
     * @param connection
     */
    public StubStudentDao(Connection connection) {
        this.connection = connection;
    }

    /**
     * insert文を実行したフリをする
     * 
     * @param studentEntity
     * @throws IOException
     */
    public void insertStub(StudentEntity studentEntity) throws IOException {
        String tableName = this.getTableName();
        String csvFilePath = Constants.CSV_DIR_PATH + tableName + ".csv";
        int columnCount = studentEntity.getClass().getDeclaredFields().length;

        try (BufferedWriter csvWriter = new BufferedWriter(new FileWriter(csvFilePath, true))) {
            String[] pStmtStub = new String[columnCount];
            pStmtStub[0] = studentEntity.getStudentId();
            pStmtStub[1] = studentEntity.getName();
            pStmtStub[2] = studentEntity.getEmail();
            pStmtStub[3] = studentEntity.getPassword();

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

    private String getTableName() {
        return this.insertQuery.split(" ")[2].trim();
    }
}
