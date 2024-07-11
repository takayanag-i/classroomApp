package jp.co.collasho.classroom.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import jp.co.collasho.classroom.constants.DbConstants;
import jp.co.collasho.classroom.constants.ErrorMessages;
import jp.co.collasho.classroom.entity.StudentEntity;
import jp.co.collasho.classroom.exception.DaoException;

/**
 * Studentテーブル用DAO
 */
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
     * @param student 学生エンティティ
     */
    public void insert(StudentEntity student) throws DaoException {

        String query =
                "INSERT INTO Students (student_id, name, email, password) VALUES (?, ?, ?, ?);";

        try (PreparedStatement pStmt = this.conn.prepareStatement(query)) {
            pStmt.setString(1, student.getStudentId());
            pStmt.setString(2, student.getName());
            pStmt.setString(3, student.getEmail());
            pStmt.setString(4, student.getPassword());
            pStmt.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    /**
     * SELECT 全レコード取得する
     * 
     * @return 学生Entityのリスト
     */
    public List<StudentEntity> selectAll() {
        List<StudentEntity> allStudents = new ArrayList<>();
        String query = "SELECT * FROM Students;";

        try (PreparedStatement pStmt = this.conn.prepareStatement(query)) {
            ResultSet rs = pStmt.executeQuery();
            while (rs.next()) {
                StudentEntity student = this.getEntityFromResult(rs);
                allStudents.add(student);
            }
        } catch (SQLException e) {
            throw new RuntimeException(ErrorMessages.DAO_SELECT_ERROR, e);
        }

        return allStudents;
    }

    /**
     * SELECT 出席番号, パスワードを指定して取得する
     * 
     * @param studentId 出席番号 PK
     * @param password パスワード
     * @return 学生Entity
     * @return null（該当値なしの場合）
     */
    public StudentEntity selectByIdAndPassword(String studentId, String password) {
        String query = "SELECT * FROM Students WHERE student_id = ? AND password = ?";

        try (PreparedStatement pStmt = this.conn.prepareStatement(query)) {
            pStmt.setString(1, studentId);
            pStmt.setString(2, password);
            ResultSet rs = pStmt.executeQuery();

            if (rs.next()) {
                StudentEntity student = this.getEntityFromResult(rs);
                return student;

            } else {
                return null;
            }

        } catch (SQLException e) {
            throw new RuntimeException(ErrorMessages.DAO_SELECT_ERROR, e);
        }
    }

    /**
     * ResultSetから学生エンティティを生成
     * 
     * @param rs java.sql.ResultSet
     * @return 学生エンティティ
     * @throws SQLException
     */
    private StudentEntity getEntityFromResult(ResultSet rs) throws SQLException {
        StudentEntity student = new StudentEntity();

        student.setStudentId(rs.getString(DbConstants.STUDENT_ID));
        student.setName(rs.getString(DbConstants.STUDENT_NAME));
        student.setEmail(rs.getString(DbConstants.STUDENT_EMAIL));
        student.setPassword(rs.getString(DbConstants.STUDENT_PASSWORD));

        return student;
    }
}
