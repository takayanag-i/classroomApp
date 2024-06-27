package jp.co.collasho.classroom.service.enrollment;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import jp.co.collasho.classroom.common.ConnectionManager;
import jp.co.collasho.classroom.dao.EnrollmentDao;
import jp.co.collasho.classroom.dto.EnrollmentDto;
import jp.co.collasho.classroom.entity.EnrollmentEntity;

public class EnrollmentDriver {

    /** コネクションマネージャ */
    ConnectionManager connectionManager = new ConnectionManager();

    public void drive(List<EnrollmentDto> dtos) {
        try (Connection connection = this.connectionManager.getConnection()) {
            EnrollmentDao enrollmentDao = new EnrollmentDao(connection);

            for (EnrollmentDto dto : dtos) {
                EnrollmentEntity entity = this.convertEntityToDto(dto);
                enrollmentDao.insertEnrollment(entity);
            }

            this.connectionManager.commit();

        } catch (SQLException e) {
            this.connectionManager.rollback();
            throw new RuntimeException("履修登録における予期しないエラーが発生しました。", e);
        }
    }

    private EnrollmentEntity convertEntityToDto(EnrollmentDto dto) {
        EnrollmentEntity entity = new EnrollmentEntity();

        entity.setStudentId(dto.getStudentId());
        entity.setCourseId(dto.getCourseId());
        entity.setEnrollmentDate(dto.getEnrollmentDate());

        return entity;
    }
}
