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
    private Connection connection;

    /**
     * コンストラクタ
     * 
     * @param connection
     */
    public StudentDao(Connection connection) {
        this.connection = connection;
    }


    /**
     * insert文の実行
     * 
     * @param student
     */
    public void insert(StudentEntity student) {

        String query =
                "INSERT INTO Students (student_id, name, email, password) VALUES (?, ?, ?, ?);";

        try (PreparedStatement pStmt = this.connection.prepareStatement(query)) {
            pStmt.setString(1, student.getStudentId());
            pStmt.setString(2, student.getName());
            pStmt.setString(3, student.getEmail());
            pStmt.setString(4, student.getPassword());
            pStmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("INSERTクエリの実行に失敗してステートメントを解放しました。", e);
        }
    }

    public List<StudentEntity> getAll() {
        List<StudentEntity> allStudents = new ArrayList<>();
        String query = "SELECT * FROM Students;";

        try (PreparedStatement pStmt = this.connection.prepareStatement(query)) {
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

    public StudentEntity getStudentEntity(String studentId, String password)
            throws LoginFailedException {
        String query = "SELECT * FROM Students WHERE student_id = ? AND password = ?";

        try (PreparedStatement pStmt = this.connection.prepareStatement(query)) {
            pStmt.setString(1, studentId);
            pStmt.setString(2, password);
            ResultSet resultSet = pStmt.executeQuery();

            if (resultSet.next()) {
                String name = resultSet.getString("name");
                String email = resultSet.getString("email");

                return new StudentEntity(studentId, name, email);

            } else {
                throw new LoginFailedException("ログインに失敗しました");
            }

        } catch (SQLException e) {
            throw new RuntimeException("SELECTクエリの実行に失敗してステートメントを解放しました。");
        }
    }
}
