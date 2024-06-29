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
 * 複数教員情報をもつ講座DTOを作成するクラス
 */
public class multipleInstructorsLogic {

    /** 講座・教員対応エンティティ */
    List<InstructionEntity> instructions;

    /** コンストラクタ */
    public multipleInstructorsLogic(List<InstructionEntity> instructions) {
        this.instructions = instructions;
    }

    /**
     * 表示用の講座オブジェクトを取得する
     * 
     * @param e 講座エンティティ（教員情報なし）
     * @return 講座オブジェクト
     * @return null 過剰の講座エンティティに対してはnullを返却する
     */
    public CourseDto convertEntityToDto(CourseEntity e) {
        Map<String, List<String>> instructionMap = this.getInstructionMap();
        List<String> instructors = instructionMap.get(e.getCourseId());

        if (instructors == null) {
            // 教員名で検索があった場合などは
            // 講座エンティティの数 > 講座・教員対応エンティティの数
            return null;
        }

        CourseDto d = new CourseDto();
        d.setDayOfWeek(DayOfWeek.fromAbbreviation(e.getDayOfWeekString()));
        d.setPeriod(e.getPeriod());
        d.setCourseId(e.getCourseId());
        d.setCourseName(e.getCourseName());
        d.setInstructors(instructors);

        return d;
    }

    private Map<String, List<String>> getInstructionMap() {
        Map<String, List<String>> instructionMap = new HashMap<>();

        for (InstructionEntity instruction : instructions) {
            String courseId = instruction.getCourseId();
            String instructor = instruction.getInstructor();

            // 新しいコースIdが来たらkeyにして，valueとして新しいArrayListをnewする
            instructionMap.computeIfAbsent(courseId, k -> new ArrayList<>());

            // ArrayListに教員を追加する
            instructionMap.get(courseId).add(instructor);
        }

        return instructionMap;
    }
}
