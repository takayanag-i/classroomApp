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
     * @param student
     */
    public void insert(StudentEntity student) {

        String query =
                "INSERT INTO Students (student_id, name, email, password) VALUES (?, ?, ?, ?);";

        try (PreparedStatement pStmt = this.conn.prepareStatement(query)) {
            pStmt.setString(1, student.getStudentId());
            pStmt.setString(2, student.getName());
            pStmt.setString(3, student.getEmail());
            pStmt.setString(4, student.getPassword());
            pStmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("INSERTクエリの実行に失敗してステートメントを解放しました。", e);
        }
    }

    /**
     * SELECT（全レコード）
     * 
     * @return StudentEntityのリスト
     */
    public List<StudentEntity> select() {
        List<StudentEntity> allStudents = new ArrayList<>();
        String query = "SELECT * FROM Students;";

        try (PreparedStatement pStmt = this.conn.prepareStatement(query)) {
            ResultSet rs = pStmt.executeQuery();
            while (rs.next()) {
                StudentEntity student = new StudentEntity();
                student.setStudentId(rs.getString("student_id"));
                student.setName(rs.getString("name"));
                student.setEmail(rs.getString("email"));
                student.setPassword(rs.getString("password"));

                allStudents.add(student);
            }
        } catch (SQLException e) {
            throw new RuntimeException("SELECTクエリの実行に失敗してステートメントを解放しました。");
        }

        return allStudents;
    }

    /**
     * SELECT（出席番号, パスワード指定）
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
                StudentEntity student = new StudentEntity();
                student.setStudentId(studentId);
                student.setName(rs.getString("name"));
                student.setEmail(rs.getString("email"));

                return student;

            } else {
                return null;
            }

        } catch (SQLException e) {
            throw new RuntimeException("SELECTクエリの実行に失敗してステートメントを解放しました。");
        }
    }
}
