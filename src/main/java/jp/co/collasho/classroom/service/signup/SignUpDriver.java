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
     * @param dto 学生DTO
     * @throws SignUpFailedException 新規学生登録に失敗したときにスローされる例外
     */
    public void signUp(StudentDto dto) throws SignUpFailedException {

        try (Connection conn = this.connectionManager.getConnection()) {
            StudentDao studentDao = new StudentDao(conn);
            StudentEntity entity = this.convertDtoToEntity(dto);

            // 重複ユーザチェック
            List<StudentEntity> allEntities = studentDao.selectAll();
            this.CheckIdAndEmail(entity, allEntities);

            // インサート
            studentDao.insert(entity);
            this.connectionManager.commit();
        } catch (SQLException e) {
            connectionManager.rollback();
            throw new RuntimeException("登録の予期しないエラーが起こりました", e);
        }
    }

    /**
     * Enrollmentの変換 (DTO→Entity)
     * 
     * @param dto 学生DTO
     * @return 学生エンティティ
     */
    private StudentEntity convertDtoToEntity(StudentDto dto) {
        StudentEntity entity = new StudentEntity();

        entity.setStudentId(dto.getStudentId());
        entity.setName(dto.getName());
        entity.setEmail(dto.getEmail());
        entity.setPassword(dto.getPassword());

        return entity;
    }

    /**
     * 重複するIDとメールアドレスをチェックする
     * 
     * @param target チェックされる学生エンティティ
     * @param entities 登録されている全学生エンティティ
     * @throws SignUpFailedException 新規登録に失敗したときにスローされる例外
     */
    private void CheckIdAndEmail(StudentEntity target, List<StudentEntity> entities)
            throws SignUpFailedException {
        for (StudentEntity entity : entities) {
            if (target.getStudentId().equals(entity.getStudentId())) {
                throw new SignUpFailedException("重複するIDです");
            } else if (target.getEmail().equals(entity.getEmail())) {
                throw new SignUpFailedException("重複するメールアドレスです");
            }
        }
    }
}
