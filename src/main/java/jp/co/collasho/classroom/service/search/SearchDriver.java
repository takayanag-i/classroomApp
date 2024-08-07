package jp.co.collasho.classroom.service.search;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import jp.co.collasho.classroom.common.ConnectionManager;
import jp.co.collasho.classroom.constants.ErrorMessages;
import jp.co.collasho.classroom.dao.CourseDao;
import jp.co.collasho.classroom.dao.InstructionDao;
import jp.co.collasho.classroom.dto.CourseDto;
import jp.co.collasho.classroom.dto.SearchCriteriaDto;
import jp.co.collasho.classroom.entity.CourseEntity;
import jp.co.collasho.classroom.entity.InstructionEntity;
import jp.co.collasho.classroom.logic.multipleInstructorsLogic;

/**
 * 講座検索処理のドライバ
 */
public class SearchDriver {
    /** コネクションマネージャ */
    ConnectionManager connectionManager = new ConnectionManager();

    /**
     * 表示用の講座リストを取得する
     * 
     * @param criteriaDto 検索条件
     * @return 講座オブジェクトのリスト
     */
    public List<CourseDto> getCourses(SearchCriteriaDto criteriaDto) {
        List<CourseDto> courseDtos = new ArrayList<>();

        try (Connection conn = connectionManager.getConnection()) {
            CourseDao enrollmentDao = new CourseDao(conn);
            InstructionDao instructionDao = new InstructionDao(conn);

            // 講座エンティティ，講座-教員対応エンティティの取得
            List<CourseEntity> courseEntities = enrollmentDao.selectByCriteria(criteriaDto);
            List<InstructionEntity> instrucionEntities = instructionDao.select(criteriaDto);

            // 講座DTOの作成―複数教員に注意しながら―
            multipleInstructorsLogic logic = new multipleInstructorsLogic();
            for (CourseEntity courseEntity : courseEntities) {
                CourseDto courseDto = logic.merge(courseEntity, instrucionEntities);
                if (courseDto == null) {
                    continue;
                }
                courseDtos.add(courseDto);
            }

        } catch (SQLException e) {
            throw new RuntimeException(ErrorMessages.DRIVER_SEARCH_ERROR, e);
        }

        return courseDtos;
    }
}
