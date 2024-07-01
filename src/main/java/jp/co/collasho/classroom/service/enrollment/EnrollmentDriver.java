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

public class EnrollmentDriver {

    /** コネクションマネージャ */
    ConnectionManager connectionManager = new ConnectionManager();

    public void enroll(EnrollmentDto enrollment) {
        try (Connection conn = this.connectionManager.getConnection()) {
            EnrollmentDao enrollmentDao = new EnrollmentDao(conn);
            CourseDao courseDao = new CourseDao(conn);
            EnrollmentEntity entity = this.convert(enrollment);

            // 重複チェック
            List<CourseEntity> enrolledCourses = courseDao.select(enrollment.getStudentId());


            // インサート
            enrollmentDao.insert(entity);
            this.connectionManager.commit();

        } catch (SQLException e) {
            this.connectionManager.rollback();
            throw new RuntimeException("履修登録における予期しないエラーが発生しました。", e);
        }
    }


    /**
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

    private boolean isValidEnrollment(EnrollmentDto d, List<CourseEntity> courses) {

        for (CourseEntity course : courses) {
            String dayOfweekString = course.getDayOfWeekString();
        }

        return false;
    }
}
