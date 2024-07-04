package jp.co.collasho.classroom.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import jp.co.collasho.classroom.entity.StudentEntity;

public class StudentDao {

    /** コネクション */
    private Connection conn;

    /**
     * コンストラクタ
     * 
     * @param conn
     */
    public StudentDao(Connection conn) {
        this.conn = conn;
    }


    /**
     * INSERT
     * 
     * @param entity 学生エンティティ
     */
    public void insert(StudentEntity entity) {

        String query =
                "INSERT INTO Students (student_id, name, email, password) VALUES (?, ?, ?, ?);";

        try (PreparedStatement pStmt = this.conn.prepareStatement(query)) {
            pStmt.setString(1, entity.getStudentId());
            pStmt.setString(2, entity.getName());
            pStmt.setString(3, entity.getEmail());
            pStmt.setString(4, entity.getPassword());
            pStmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("INSERTクエリの実行に失敗してステートメントを解放しました。", e);
        }
    }

    /**
     * 全レコード取得する
     * 
     * @return 学生Entityのリスト
     */
    public List<StudentEntity> select() {
        List<StudentEntity> allEntities = new ArrayList<>();
        String query = "SELECT * FROM Students;";

        try (PreparedStatement pStmt = this.conn.prepareStatement(query)) {
            ResultSet rs = pStmt.executeQuery();
            while (rs.next()) {
                StudentEntity entity = new StudentEntity();
                entity.setStudentId(rs.getString("student_id"));
                entity.setName(rs.getString("name"));
                entity.setEmail(rs.getString("email"));
                entity.setPassword(rs.getString("password"));

                allEntities.add(entity);
            }
        } catch (SQLException e) {
            throw new RuntimeException("SELECTクエリの実行に失敗してステートメントを解放しました。");
        }

        return allEntities;
    }

    /**
     * 出席番号, パスワードを指定して取得する
     * 
     * @param studentId 出席番号 PK
     * @param password パスワード
     * @return StudentEntity
     * @return null（該当値なしの場合）
     */
    public StudentEntity select(String studentId, String password) {
        String query = "SELECT * FROM Students WHERE student_id = ? AND password = ?";

        try (PreparedStatement pStmt = this.conn.prepareStatement(query)) {
            pStmt.setString(1, studentId);
            pStmt.setString(2, password);
            ResultSet rs = pStmt.executeQuery();

            if (rs.next()) {
                StudentEntity entity = new StudentEntity();
                entity.setStudentId(studentId);
                entity.setName(rs.getString("name"));
                entity.setEmail(rs.getString("email"));

                return entity;

            } else {
                return null;
            }

        } catch (SQLException e) {
            throw new RuntimeException("SELECTクエリの実行に失敗してステートメントを解放しました。");
        }
    }
}
