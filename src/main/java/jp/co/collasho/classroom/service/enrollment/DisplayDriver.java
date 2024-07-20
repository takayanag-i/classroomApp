package jp.co.collasho.classroom.service.enrollment;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import jp.co.collasho.classroom.common.ConnectionManager;
import jp.co.collasho.classroom.common.DayOfWeek;
import jp.co.collasho.classroom.constants.ErrorMessages;
import jp.co.collasho.classroom.dao.CourseDao;
import jp.co.collasho.classroom.dao.InstructionDao;
import jp.co.collasho.classroom.dto.CourseDto;
import jp.co.collasho.classroom.entity.CourseEntity;
import jp.co.collasho.classroom.entity.InstructionEntity;
import jp.co.collasho.classroom.logic.multipleInstructorsLogic;
import jp.co.collasho.classroom.util.MatrixUtil;

/**
 * 登録講座一覧を表示する処理のドライバ
 */
public class DisplayDriver {
    /** コネクションマネージャ */
    private ConnectionManager connectionManager = new ConnectionManager();

    /**
     * 時間割表のマトリクスを作成する
     * 
     * @param courses
     * @return List<List<CourseDto>>
     */
    public List<List<CourseDto>> getCourseMatrix(List<CourseDto> courses) {

        // 空きコマ用のDTOで初期化されたmatrixを生成
        List<List<CourseDto>> matrix = this.initialiseMatrix();

        // matrixにCourseDTOをセットする
        for (CourseDto course : courses) {
            course.setFormAction("PreDeleteServlet");

            int day = course.getDayOfWeek().getInt();
            int period = Integer.parseInt(course.getPeriod());
            matrix.get(day - 1).set(period - 1, course);
        }

        // セル結合のための処理
        for (int i = 0; i < matrix.size(); i++) {
            List<CourseDto> vector = matrix.get(i);
            List<CourseDto> newVector = this.consolidateConsecutiveCourses(vector);

            // matrixの行を更新
            matrix.set(i, newVector);
        }

        return MatrixUtil.transpose(matrix);
    }

    /**
     * 空きコマ用のDTOで初期化したmatrixを生成する
     * 
     * @return List<List<CourseDto>> matrix
     */
    List<List<CourseDto>> initialiseMatrix() {
        List<List<CourseDto>> matrix = new ArrayList<>();

        for (int i = 0; i < 5; i++) { // day
            List<CourseDto> vector = new ArrayList<>();
            for (int j = 0; j < 5; j++) { // period
                CourseDto emptyDto = new CourseDto();
                emptyDto.setCourseId("");
                emptyDto.setDayOfWeek(DayOfWeek.fromNum(i + 1));
                emptyDto.setPeriod(String.valueOf(j + 1));
                emptyDto.setFormAction("SearchServlet");
                vector.add(emptyDto);
            }
            matrix.add(vector);
        }

        return matrix;
    }

    /**
     * 連続した複数時限にまたがる講義を統合する
     * 
     * @param vector 1日分の時間割ベクトル
     * @return 処理された1日分の時間割ベクトル
     */
    private List<CourseDto> consolidateConsecutiveCourses(List<CourseDto> vector) {
        Map<String, CourseDto> map = new LinkedHashMap<>();

        for (CourseDto dto : vector) {
            String courseId = dto.getCourseId();

            if (courseId == "") {
                map.put(UUID.randomUUID().toString(), dto); // 空のセルもマップに追加
                continue;
            }

            if (map.containsKey(courseId)) {
                CourseDto val = map.get(courseId);
                val.setRowspan(val.getRowspan() + 1);
            } else {
                map.put(courseId, dto);
            }
        }

        return new ArrayList<>(map.values());
    }

    /**
     * DBから講座リストを取得する
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
