package jp.co.collasho.classroom.logic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import jp.co.collasho.classroom.common.DayOfWeek;
import jp.co.collasho.classroom.dto.CourseDto;
import jp.co.collasho.classroom.entity.CourseEntity;
import jp.co.collasho.classroom.entity.InstructionEntity;

/**
 * 複数教員に対応するためのロジック
 */
public class InstructionLogic {

    /** 担当教員マップ */
    private Map<String, List<String>> instructorsMap;

    /**
     * コンストラクタ
     * 
     * フィールドの担当教員マップを初期化する
     * 
     * @param instructions 講座-教員対応エンティティ
     */
    public InstructionLogic(List<InstructionEntity> instructions) {
        Map<String, List<String>> instructorsMap = new HashMap<>();

        for (InstructionEntity instruction : instructions) {
            String courseId = instruction.getCourseId();
            String instructor = instruction.getInstructor();

            // 新しいコースIdが来たらkeyにして，valueとして新しいArrayListをnewする
            instructorsMap.computeIfAbsent(courseId, k -> new ArrayList<>());

            // ArrayListに教員を追加する
            instructorsMap.get(courseId).add(instructor);
        }

        this.instructorsMap = instructorsMap;
    }

    /**
     * 表示用の講座オブジェクトを取得する
     * 
     * @param entity 講座エンティティ（教員情報なし）
     * @return 講座オブジェクト
     * @return null 過剰の講座エンティティに対してはnullを返却する
     */
    public CourseDto getCourseDto(CourseEntity entity) {
        List<String> instructors = this.instructorsMap.get(entity.getCourseId());
        if (instructors == null) {
            return null;
        }

        CourseDto dto = new CourseDto();
        DayOfWeek day = DayOfWeek.fromAbbreviation(entity.getDayOfWeekString());
        dto.setDayOfWeek(day);
        dto.setPeriod(entity.getPeriod());
        dto.setCourseId(entity.getCourseId());
        dto.setCourseName(entity.getCourseName());
        dto.setInstructors(instructors);

        return dto;
    }
}
