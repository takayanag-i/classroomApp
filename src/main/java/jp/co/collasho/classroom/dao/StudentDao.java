package jp.co.collasho.classroom.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import jp.co.collasho.classroom.entity.AbstractEntity;
import jp.co.collasho.classroom.entity.StudentEntity;

public class StudentDao extends AbstractInsertDao {

    /**
     * コンストラクタ：コネクションとクエリをフィールド
     * 
     * @param connection
     */
    public StudentDao(Connection connection) {
        super(connection);
        this.insertQuery = "INSERT INTO students (student_id, name, email, password) VALUES (?, ?, ?, ?)";
    }

    /**
     * プリペアドステートメントにエンティティのフィールドをセットする
     * 
     * @param pStmt  プリペアドステートメント
     * @param entity エンティティ（studentエンティティにキャストされる）
     */
    @Override
    protected void setParameters(PreparedStatement pStmt, AbstractEntity entity) throws SQLException {

        if (entity instanceof StudentEntity) {

            StudentEntity studentEntity = (StudentEntity) entity;

            pStmt.setInt(1, studentEntity.getStudentId());
            pStmt.setString(2, studentEntity.getName());
            pStmt.setString(3, studentEntity.getEmail());
            pStmt.setString(4, studentEntity.getPassword());
        } else {
            throw new IllegalArgumentException("エンティティの型がStudentEntityではありません。");
        }
    }

    /**
     * プリペアドステートメントにエンティティのフィールドをセットするフリをする
     * 
     * @param pStmtStub プリペアドステートメントもどき配列
     * @param entity    エンティティ（studentエンティティにキャストされる）
     */
    @Override
    protected void setParametersStub(String[] pStmtStub, AbstractEntity entity) {
        if (entity instanceof StudentEntity) {
            StudentEntity studentEntity = (StudentEntity) entity;

            pStmtStub[0] = String.valueOf(studentEntity.getStudentId());
            pStmtStub[1] = studentEntity.getName();
            pStmtStub[2] = studentEntity.getEmail();
            pStmtStub[3] = studentEntity.getPassword();
        } else {
            throw new IllegalArgumentException("エンティティの型がStudentEntityではありません。");
        }
    }
}
