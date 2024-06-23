package jp.co.collasho.classroom.service.enrollment;

import java.util.ArrayList;
import java.util.List;
import jp.co.collasho.classroom.common.ConnectionManager;
import jp.co.collasho.classroom.dto.EnrollmentDto;

public class DisplayDriver {
    /** コネクションマネージャ */
    ConnectionManager connectionManager = new ConnectionManager();

    public List<EnrollmentDto> drive(String studentId) {
        List<EnrollmentDto> enrollments = new ArrayList<>();

        // TODO

        return enrollments;
    }
}
