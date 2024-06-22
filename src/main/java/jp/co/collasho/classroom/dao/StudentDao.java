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
    private Connection connection;
    /** insert文 */
    private String insertQuery =
            "INSERT INTO Students (student_id, name, email, password) VALUES (?, ?, ?, ?);";
    private String selectQuery = "SELECT * FROM Students;";

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
        try (PreparedStatement pStmt = this.connection.prepareStatement(this.insertQuery)) {
            pStmt.setString(1, student.getStudentId());
            pStmt.setString(2, student.getName());
            pStmt.setString(3, student.getEmail());
            pStmt.setString(4, student.getPassword());
            pStmt.executeUpdate();

            System.out.println("insertクエリを実行してステートメントを解放しました。");

        } catch (SQLException e) {
            throw new RuntimeException("insertクエリの実行に失敗してステートメントを解放しました。", e);
        }
    }

    public List<StudentEntity> getAll() {
        List<StudentEntity> allStudents = new ArrayList<>();
        try (PreparedStatement pStmt = this.connection.prepareStatement(this.selectQuery)) {
            ResultSet resultSet = pStmt.executeQuery();
            while (resultSet.next()) {
                String studentId = resultSet.getString("student_id");
                String name = resultSet.getString("name");
                String email = resultSet.getString("email");
                String password = resultSet.getString("password");

                StudentEntity student = new StudentEntity();
                student.setStudentId(studentId);
                student.setName(name);
                student.setEmail(email);
                student.setPassword(password);

                allStudents.add(student);
            }

            System.out.println("selectクエリを実行してステートメントを解放しました。");
        } catch (SQLException e) {
            throw new RuntimeException("selectクエリの実行に失敗してステートメントを解放しました。");
        }

        return allStudents;
    }
}
