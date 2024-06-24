package jp.co.collasho.classroom.logic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import jp.co.collasho.classroom.common.DayOfWeek;
import jp.co.collasho.classroom.dto.CourseDto;
import jp.co.collasho.classroom.entity.CourseEntity;
import jp.co.collasho.classroom.entity.InstructionEntity;

public class InstructionLogic {

    /** 担当教員マップ */
    private Map<String, List<String>> instructorsMap;

    /**
     * コンストラクタ
     * 
     * @param instructions
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

    public CourseDto getEnrollmentDto(CourseEntity entity) {
        DayOfWeek day = DayOfWeek.fromAbbreviation(entity.getDayOfWeekString());
        List<String> instructors = this.instructorsMap.get(entity.getCourseId());

        CourseDto dto = new CourseDto();
        dto.setDayOfWeek(day);
        dto.setPeriod(entity.getPeriod());
        dto.setCourseId(entity.getCourseId());
        dto.setCourseName(entity.getCourseName());
        dto.setInstructors(instructors);

        return dto;
    }
}
