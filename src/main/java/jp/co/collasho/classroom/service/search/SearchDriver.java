package jp.co.collasho.classroom.service.search;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import jp.co.collasho.classroom.common.ConnectionManager;
import jp.co.collasho.classroom.dao.CourseDao;
import jp.co.collasho.classroom.dao.InstructionDao;
import jp.co.collasho.classroom.dto.CourseDto;
import jp.co.collasho.classroom.dto.SearchCriteriaDto;
import jp.co.collasho.classroom.entity.CourseEntity;
import jp.co.collasho.classroom.entity.InstructionEntity;
import jp.co.collasho.classroom.logic.InstructionLogic;

public class SearchDriver {
    /** コネクションマネージャ */
    ConnectionManager connectionManager = new ConnectionManager();

    /**
     * 検索条件にもとづき講座エンティティのリストと講座-教員対応エンティティのリスト取得して，表示用の講座オブジェクトのリストを作成する
     * 
     * @param criteria
     * @return 講座オブジェクトのリスト
     */
    public List<CourseDto> drive(SearchCriteriaDto criteria) {
        List<CourseDto> results = new ArrayList<>();

        try (Connection connection = connectionManager.getConnection()) {
            CourseDao enrollmentDao = new CourseDao(connection);
            InstructionDao instructionDao = new InstructionDao(connection);

            List<CourseEntity> courseEntities = enrollmentDao.getCoursesByCriteria(criteria);
            List<InstructionEntity> instrucions =
                    instructionDao.getInstructionsByCriteria(criteria);

            InstructionLogic logic = new InstructionLogic(instrucions);

            for (CourseEntity courseEntity : courseEntities) {
                CourseDto courseDto = logic.getCourseDto(courseEntity);
                if (courseDto == null) {
                    continue;
                }
                results.add(courseDto);
            }

        } catch (SQLException e) {
            throw new RuntimeException("検索実行における予期しないエラーが発生しました。", e);
        }

        return results;
    }
}
