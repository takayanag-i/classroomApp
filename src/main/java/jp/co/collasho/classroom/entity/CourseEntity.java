package jp.co.collasho.classroom.entity;

public class CourseEntity {
    /** コースID */
    private String courseId;
    /** コース名 */
    private String courseName;
    /** 曜日略称 */
    private String dayOfWeekString;
    /** 時限 */
    private int period;

    /**
     * コンストラクタ
     */
    public CourseEntity() {}

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

    public String getDayOfWeekString() {
        return dayOfWeekString;
    }

    public void setDayOfWeekString(String dayOfWeekString) {
        this.dayOfWeekString = dayOfWeekString;
    }

    public int getPeriod() {
        return period;
    }

    public void setPeriod(int period) {
        this.period = period;
    }
}
