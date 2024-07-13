package jp.co.collasho.classroom.service.enrollment;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import jp.co.collasho.classroom.common.ConnectionManager;
import jp.co.collasho.classroom.constants.ErrorMessages;
import jp.co.collasho.classroom.dao.CourseDao;
import jp.co.collasho.classroom.dao.InstructionDao;
import jp.co.collasho.classroom.dto.CourseCellDto;
import jp.co.collasho.classroom.dto.CourseDto;
import jp.co.collasho.classroom.entity.CourseEntity;
import jp.co.collasho.classroom.entity.InstructionEntity;
import jp.co.collasho.classroom.logic.multipleInstructorsLogic;

/**
 * 登録講座一覧を表示する処理のドライバ
 */
public class DisplayDriver {
    /** コネクションマネージャ */
    private ConnectionManager connectionManager = new ConnectionManager();

    public CourseCellDto[][] getCourseTable(List<CourseDto> courses) {
        CourseCellDto[][] courseTable = new CourseCellDto[5][5];

        for (CourseDto course : courses) {
            int day = Integer.parseInt(course.getDayOfWeek().getNum());
            int period = Integer.parseInt(course.getPeriod());
            CourseCellDto dto = new CourseCellDto();
            dto.setCourse(course);
            dto.setEmpty(false);
            dto.setFormAction("/PreDeleteServlet");
            courseTable[period - 1][day - 1] = dto;
        }

        for (int i = 0; i < courseTable.length; i++) {
            CourseCellDto[] row = courseTable[i];
            for (int j = 0; j < row.length; j++) {
                if (row[j] == null) {
                    row[j] = new CourseCellDto();
                    row[j].setFormAction("/SearchServlet");
                }
            }
        }

        return courseTable;

    }

    /**
     * 表示用の講座リストを取得する
     * 
     * @param studentId 出席番号
     * @return 学生が登録している講座のDTOのリスト
     */
    public List<CourseDto> getCourses(String studentId) {
        List<CourseDto> courseDtos = new ArrayList<>();

        try (Connection conn = this.connectionManager.getConnection()) {
            CourseDao courseDao = new CourseDao(conn);
            InstructionDao instructionDao = new InstructionDao(conn);

            // 講座エンティティ，講座-教員対応エンティティの取得
            List<CourseEntity> courseEntities = courseDao.selectByStudentId(studentId);
            List<InstructionEntity> instrucionEntities = instructionDao.select(courseEntities);

            // 講座DTOの作成―複数教員に注意しながら―
            multipleInstructorsLogic logic = new multipleInstructorsLogic();
            for (CourseEntity courseEntity : courseEntities) {
                CourseDto courseDto = logic.merge(courseEntity, instrucionEntities);
                courseDtos.add(courseDto);
            }

        } catch (SQLException e) {
            throw new RuntimeException(ErrorMessages.DRIVER_DISPLAY_ERROR, e);
        }

        return courseDtos;
    }
}
