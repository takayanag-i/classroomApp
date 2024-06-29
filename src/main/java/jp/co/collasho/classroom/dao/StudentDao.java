package jp.co.collasho.classroom.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import jp.co.collasho.classroom.entity.StudentEntity;
import jp.co.collasho.classroom.exception.LoginFailedException;

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
     * insert文の実行
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
     * SELECT
     * 
     * @return 学生エンティティ（リスト：WHERE句なし）
     */
    public List<StudentEntity> select() {
        List<StudentEntity> allStudents = new ArrayList<>();
        String query = "SELECT * FROM Students;";

        try (PreparedStatement pStmt = this.conn.prepareStatement(query)) {
            ResultSet resultSet = pStmt.executeQuery();
            while (resultSet.next()) {
                String studentId = resultSet.getString("student_id");
                String name = resultSet.getString("name");
                String email = resultSet.getString("email");
                String password = resultSet.getString("password");

                StudentEntity student = new StudentEntity(studentId, name, email, password);

                allStudents.add(student);
            }
        } catch (SQLException e) {
            throw new RuntimeException("SELECTクエリの実行に失敗してステートメントを解放しました。");
        }

        return allStudents;
    }

    /**
     * SELECT
     * 
     * @param studentId
     * @param password
     * @return 学生エンティティ（単一：studentIdは主キー）
     * @return null（該当値なし）
     * @throws LoginFailedException
     */
    public StudentEntity select(String studentId, String password) {
        String query = "SELECT * FROM Students WHERE student_id = ? AND password = ?";

        try (PreparedStatement pStmt = this.conn.prepareStatement(query)) {
            pStmt.setString(1, studentId);
            pStmt.setString(2, password);
            ResultSet resultSet = pStmt.executeQuery();

            if (resultSet.next()) {
                String name = resultSet.getString("name");
                String email = resultSet.getString("email");

                return new StudentEntity(studentId, name, email);

            } else {
                return null;
            }

        } catch (SQLException e) {
            throw new RuntimeException("SELECTクエリの実行に失敗してステートメントを解放しました。");
        }
    }

    // TODO convert?
}
