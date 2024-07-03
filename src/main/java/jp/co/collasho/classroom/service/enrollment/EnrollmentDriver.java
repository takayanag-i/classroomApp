package jp.co.collasho.classroom.service.enrollment;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import jp.co.collasho.classroom.common.ConnectionManager;
import jp.co.collasho.classroom.dao.CourseDao;
import jp.co.collasho.classroom.dao.EnrollmentDao;
import jp.co.collasho.classroom.dto.EnrollmentDto;
import jp.co.collasho.classroom.entity.CourseEntity;
import jp.co.collasho.classroom.entity.EnrollmentEntity;
import jp.co.collasho.classroom.exception.InValidEnrollmentException;

public class EnrollmentDriver {

    /** コネクションマネージャ */
    ConnectionManager connectionManager = new ConnectionManager();

    public void enroll(EnrollmentDto enrollment) throws InValidEnrollmentException {
        try (Connection conn = this.connectionManager.getConnection()) {
            EnrollmentDao enrollmentDao = new EnrollmentDao(conn);
            CourseDao courseDao = new CourseDao(conn);
            EnrollmentEntity entity = this.convert(enrollment);

            // 重複チェック
            CourseEntity targetCourse = courseDao.selectByCourseId(enrollment.getCourseId());
            List<CourseEntity> enrolledCourses =
                    courseDao.selectByStudentId(enrollment.getStudentId());
            if (!this.isValidEnrollment(targetCourse, enrolledCourses)) {
                throw new InValidEnrollmentException("曜日・時限が重複しています。");
            }

            // インサート
            enrollmentDao.insert(entity);
            this.connectionManager.commit();

        } catch (SQLException e) {
            this.connectionManager.rollback();
            throw new RuntimeException("履修登録における予期しないエラーが発生しました。", e);
        }
    }


    /**
     * Enrollmentの変換 (DTO→Entity)
     * 
     * @param d EnrollmentDTO
     * @return EnrollmentEntity
     */
    private EnrollmentEntity convert(EnrollmentDto d) {
        EnrollmentEntity e = new EnrollmentEntity();

        e.setStudentId(d.getStudentId());
        e.setCourseId(d.getCourseId());
        e.setEnrollmentDate(d.getEnrollmentDate());

        return e;
    }

    private boolean isValidEnrollment(CourseEntity target, List<CourseEntity> courses) {

        for (CourseEntity course : courses) {
            String day = course.getDayOfWeekNum();
            String period = course.getPeriod();

            if (day.equals(target.getDayOfWeekNum()) && period.equals(target.getPeriod())) {
                return false;
            }
        }
        return true;
    }
}
