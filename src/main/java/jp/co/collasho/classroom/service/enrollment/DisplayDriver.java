package jp.co.collasho.classroom.service.enrollment;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import jp.co.collasho.classroom.common.ConnectionManager;
import jp.co.collasho.classroom.dao.EnrolledCourseDao;
import jp.co.collasho.classroom.dao.InstructionDao;
import jp.co.collasho.classroom.dto.CourseDto;
import jp.co.collasho.classroom.entity.CourseEntity;
import jp.co.collasho.classroom.entity.InstructionEntity;
import jp.co.collasho.classroom.logic.InstructionLogic;

/**
 * 登録講座一覧を表示する処理のドライバ
 */
public class DisplayDriver {
    /** コネクションマネージャ */
    private ConnectionManager connectionManager = new ConnectionManager();

    /**
     * 表示用の講座リストを取得する
     * 
     * @param studentId 対象とする学生ID
     * @return 対象とする学生が登録している講座のDTOのリスト
     */
    public List<CourseDto> drive(String studentId) {
        List<CourseDto> enrollmentDtos = new ArrayList<>();

        try (Connection connection = this.connectionManager.getConnection()) {
            EnrolledCourseDao enrollmentDao = new EnrolledCourseDao(connection);
            InstructionDao instructionDao = new InstructionDao(connection);

            List<CourseEntity> courses = enrollmentDao.getCourses(studentId);
            List<InstructionEntity> instrucions = instructionDao.getInstructions(courses);

            InstructionLogic logic = new InstructionLogic(instrucions);

            for (CourseEntity enrollmentEntity : courses) {
                CourseDto enrollmentDto = logic.getEnrollmentDto(enrollmentEntity);
                enrollmentDtos.add(enrollmentDto);
            }

        } catch (SQLException e) {
            throw new RuntimeException("履修登録状況の表示に関して予期しないエラーが発生しました。");
        }

        return enrollmentDtos;
    }
}
