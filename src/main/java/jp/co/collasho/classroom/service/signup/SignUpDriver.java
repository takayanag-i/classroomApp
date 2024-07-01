package jp.co.collasho.classroom.service.signup;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import jp.co.collasho.classroom.common.ConnectionManager;
import jp.co.collasho.classroom.dao.StudentDao;
import jp.co.collasho.classroom.dto.StudentDto;
import jp.co.collasho.classroom.entity.StudentEntity;
import jp.co.collasho.classroom.exception.SignUpFailedException;

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
     * @throws SignUpFailedException
     */
    public void drive(StudentDto studentDto) throws SignUpFailedException {

        try (Connection conn = this.connectionManager.getConnection()) {
            StudentDao studentDao = new StudentDao(conn);
            StudentEntity studentEntity = this.convertDtoToEntity(studentDto);

            // 重複ユーザチェック
            List<StudentEntity> allStudentEntities = studentDao.select();
            this.CheckIdAndEmail(studentEntity, allStudentEntities);

            // インサート
            studentDao.insert(studentEntity);
            this.connectionManager.commit();
        } catch (SQLException e) {
            connectionManager.rollback();
            throw new RuntimeException("登録の予期しないエラーが起こりました", e);
        }
    }

    private StudentEntity convertDtoToEntity(StudentDto d) {
        StudentEntity e = new StudentEntity();

        e.setStudentId(d.getStudentId());
        e.setName(d.getName());
        e.setEmail(d.getEmail());
        e.setPassword(d.getPassword());

        return e;
    }

    private void CheckIdAndEmail(StudentEntity candidate, List<StudentEntity> allStudents)
            throws SignUpFailedException {
        for (StudentEntity student : allStudents) {
            if (candidate.getStudentId().equals(student.getStudentId())) {
                throw new SignUpFailedException("重複するIDです");
            } else if (candidate.getEmail().equals(student.getEmail())) {
                throw new SignUpFailedException("重複するメールアドレスです");
            }
        }
    }
}
