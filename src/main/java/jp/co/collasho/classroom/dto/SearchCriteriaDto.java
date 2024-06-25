package jp.co.collasho.classroom.dto;

import jp.co.collasho.classroom.common.DayOfWeek;

/**
 * 検索条件オブジェクト
 */
public class SearchCriteriaDto {
    /** コースID */
    private String courseId;
    /** コース名 */
    private String courseName;
    /** 教員名 */
    private String instructorName;
    /** 曜日 */
    private DayOfWeek dayOfWeek;
    /** 時限 */
    private String period;

    /*
     * ゲッタ・セッタ
     */
    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getInstructorName() {
        return instructorName;
    }

    public void setInstructorName(String instructorName) {
        this.instructorName = instructorName;
    }

    public DayOfWeek getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(DayOfWeek dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    public String getPeriod() {
        return period;
    }

    public void setPeriod(String period) {
        this.period = period;
    }


}
