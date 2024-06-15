package jp.co.collasho.classroom.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import jp.co.collasho.classroom.entity.StudentEntity;

public class StudentDao {

    private String tableName = "students";
    private Connection conn;

    // コンストラクタでConnectionをフィールドに設定
    public StudentDao(Connection conn) {
        this.conn = conn;
    }

    public void insert(StudentEntity student) throws SQLException {
        String insertSQL = "INSERT INTO students (student_id, name, email, password) VALUES (?, ?, ?, ?)";
        try (PreparedStatement pstmt = conn.prepareStatement(insertSQL)) {
            pstmt.setInt(1, student.getStudentId());
            pstmt.setString(2, student.getName());
            pstmt.setString(3, student.getEmail());
            pstmt.setString(4, student.getPassword());
            pstmt.executeUpdate();
        }
    }
}
