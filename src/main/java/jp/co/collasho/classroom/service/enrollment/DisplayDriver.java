package jp.co.collasho.classroom.service.enrollment;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import jp.co.collasho.classroom.common.ConnectionManager;
import jp.co.collasho.classroom.dao.CourseDao;
import jp.co.collasho.classroom.dao.InstructionDao;
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

    /**
     * 表示用の講座リストを取得する
     * 
     * @param studentId 対象とする学生ID
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
            throw new RuntimeException("履修登録状況の表示に関して予期しないエラーが発生しました。", e);
        }

        return courseDtos;
    }
}
