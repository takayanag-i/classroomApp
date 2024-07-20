package jp.co.collasho.classroom.service.signup;

import java.sql.Connection;
import java.sql.SQLException;
import jp.co.collasho.classroom.common.ConnectionManager;
import jp.co.collasho.classroom.constants.ErrorMessages;
import jp.co.collasho.classroom.dao.StudentDao;
import jp.co.collasho.classroom.dto.StudentDto;
import jp.co.collasho.classroom.entity.StudentEntity;
import jp.co.collasho.classroom.exception.DaoException;
import jp.co.collasho.classroom.exception.SignUpFailedException;
import jp.co.collasho.classroom.util.DbErrorUtil;

/**
 * ユーザ登録のドライバ
 */
public class SignUpDriver {
    /** コネクションマネージャ */
    private ConnectionManager connectionManager = new ConnectionManager();

    /**
     * ユーザ登録を実行する
     * 
     * @param dto StudentDto
     * @throws SignUpFailedException 新規学生登録に失敗したときにスローされる例外
     */
    public void signUp(StudentDto dto) throws SignUpFailedException {

        StudentEntity entity = this.convertDtoToEntity(dto);

        try (Connection conn = this.connectionManager.getConnection()) {
            StudentDao studentDao = new StudentDao(conn);

            // INSERT
            studentDao.insert(entity);
            this.connectionManager.commit();

        } catch (DaoException e) {
            // DAO起因のエラー connectionが閉じてrollback
            if (DbErrorUtil.isDuplicateError(e, "Students.PRIMARY")) {
                // 重複する出席番号
                throw new SignUpFailedException(ErrorMessages.DUPLICATE_STUDENT_ID);

            } else if (DbErrorUtil.isDuplicateError(e, "Students.email")) {
                // 重複するメールアドレス
                throw new SignUpFailedException(ErrorMessages.DUPLICATE_EMAIL);

            } else {
                throw new RuntimeException(e.getMessage(), e);
            }

        } catch (SQLException e) {
            // 他のエラー connectionが閉じてrollback
            throw new RuntimeException(ErrorMessages.DRIVER_SIGNUP_ERROR, e);
        }
    }

    /**
     * Enrollmentの変換 (DTO→Entity)
     * 
     * @param dto StudetnDto
     * @return StudentEtnity
     */
    private StudentEntity convertDtoToEntity(StudentDto dto) {
        StudentEntity entity = new StudentEntity();

        entity.setStudentId(dto.getStudentId());
        entity.setName(dto.getName());
        entity.setEmail(dto.getEmail());
        entity.setPassword(dto.getPassword());

        return entity;
    }
}
