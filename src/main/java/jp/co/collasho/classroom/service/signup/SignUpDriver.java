package jp.co.collasho.classroom.service.signup;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import jp.co.collasho.classroom.common.ConnectionManager;
import jp.co.collasho.classroom.dao.StudentDao;
import jp.co.collasho.classroom.dto.StudentDto;
import jp.co.collasho.classroom.entity.StudentEntity;
import jp.co.collasho.classroom.exception.SignUpException;

/**
 * ユーザ登録のドライバ
 */
public class SignUpDriver {
    /** コネクションマネージャ */
    private ConnectionManager connectionManager = new ConnectionManager();

    /**
     * ユーザ登録を実行する
     * 
     * @param studentEntity
     * @throws ClassNotFoundException
     */
    public void drive(StudentDto studentDto) throws SignUpException {

        try (Connection connection = this.connectionManager.getConnection()) {
            StudentEntity student = this.convertDtoToEntity(studentDto);

            // 重複ユーザチェック
            StudentDao studentDao = new StudentDao(connection);
            List<StudentEntity> allStudents = studentDao.getAll();
            this.CheckIdAndEmail(student, allStudents);

            // インサート
            studentDao.insert(student);
            this.connectionManager.commit();
        } catch (SQLException e) {
            connectionManager.rollback();
        }
    }

    private StudentEntity convertDtoToEntity(StudentDto dto) {
        StudentEntity entity = new StudentEntity();

        entity.setStudentId(dto.getStudentId());
        entity.setName(dto.getName());
        entity.setEmail(dto.getEmail());
        entity.setPassword(dto.getPassword());

        return entity;
    }

    private void CheckIdAndEmail(StudentEntity candidate, List<StudentEntity> allStudents)
            throws SignUpException {
        for (StudentEntity student : allStudents) {
            if (candidate.getStudentId().equals(student.getStudentId())) {
                throw new SignUpException("重複するIDです");
            } else if (candidate.getEmail().equals(student.getEmail())) {
                throw new SignUpException("重複するメールアドレスです");
            }
        }
    }
}
