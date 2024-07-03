package jp.co.collasho.classroom.entity;

/**
 * CourseテーブルのEntityクラス
 */
public class CourseEntity {
    /** コースID */
    private String courseId;
    /** コース名 */
    private String courseName;
    /** 曜日番号 */
    private String dayOfWeekNum;
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

    public String getDayOfWeekNum() {
        return dayOfWeekNum;
    }

    public void setDayOfWeekNum(String dayOfWeekNum) {
        this.dayOfWeekNum = dayOfWeekNum;
    }

    public String getPeriod() {
        return period;
    }

    public void setPeriod(String period) {
        this.period = period;
    }
}
