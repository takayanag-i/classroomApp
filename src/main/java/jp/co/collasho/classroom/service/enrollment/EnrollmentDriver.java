package jp.co.collasho.classroom.service.enrollment;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import jp.co.collasho.classroom.common.ConnectionManager;
import jp.co.collasho.classroom.constants.ErrorMessages;
import jp.co.collasho.classroom.dao.CourseDao;
import jp.co.collasho.classroom.dao.EnrollmentDao;
import jp.co.collasho.classroom.dto.EnrollmentDto;
import jp.co.collasho.classroom.entity.CourseEntity;
import jp.co.collasho.classroom.entity.EnrollmentEntity;
import jp.co.collasho.classroom.exception.InValidEnrollmentException;

/**
 * 履修登録処理のドライバ
 */
public class EnrollmentDriver {

    /** コネクションマネージャ */
    ConnectionManager connectionManager = new ConnectionManager();

    /**
     * 履修登録する
     * 
     * @param dto EnrollmentDTO
     * @throws InValidEnrollmentException 不正な履修登録があったときにスローされる例外
     */
    public void enroll(EnrollmentDto dto) throws InValidEnrollmentException {
        try (Connection conn = this.connectionManager.getConnection()) {
            EnrollmentDao enrollmentDao = new EnrollmentDao(conn);
            CourseDao courseDao = new CourseDao(conn);
            EnrollmentEntity entity = this.convert(dto);

            // 重複チェック
            CourseEntity targetCourse = courseDao.selectByCourseId(dto.getCourseId());
            List<CourseEntity> enrolledCourses = courseDao.selectByStudentId(dto.getStudentId());
            if (!this.isValidEnrollment(targetCourse, enrolledCourses)) {
                throw new InValidEnrollmentException(ErrorMessages.DUPLICATE_ENROLLMENT);
            }

            // インサート
            enrollmentDao.insert(entity);
            this.connectionManager.commit();

        } catch (SQLException e) {
            this.connectionManager.rollback();
            throw new RuntimeException(ErrorMessages.DRIVER_ENROLLMENT_ERROR, e);
        }
    }


    /**
     * Enrollmentの変換 (DTO→Entity)
     * 
     * @param dto 履修登録DTO
     * @return 履修登録Entity
     */
    private EnrollmentEntity convert(EnrollmentDto dto) {
        EnrollmentEntity entity = new EnrollmentEntity();

        entity.setStudentId(dto.getStudentId());
        entity.setCourseId(dto.getCourseId());
        entity.setEnrollmentDate(dto.getEnrollmentDate());

        return entity;
    }

    private boolean isValidEnrollment(CourseEntity target, List<CourseEntity> entities) {

        for (CourseEntity entity : entities) {
            String day = entity.getDayOfWeekNum();
            String period = entity.getPeriod();

            if (day.equals(target.getDayOfWeekNum()) && period.equals(target.getPeriod())) {
                return false;
            }
        }
        return true;
    }
}
