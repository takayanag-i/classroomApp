package jp.co.collasho.classroom.entity;

public class CourseEntity {
    /** コースID */
    private String courseId;
    /** コース名 */
    private String courseName;
    /** 曜日略称 */
    private String dayOfWeekString;
    /** 時限 */
    private String period;

    /**
     * コンストラクタ
     */
    public CourseEntity() {}

    /**
     * コンストラクタ
     * 
     * @param courseId
     * @param courseName
     * @param dayOfWeekString
     * @param period
     */
    public CourseEntity(String courseId, String courseName, String dayOfWeekString, String period) {
        this.courseId = courseId;
        this.courseName = courseName;
        this.dayOfWeekString = dayOfWeekString;
        this.period = period;
    }

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

    public String getPeriod() {
        return period;
    }

    public void setPeriod(String period) {
        this.period = period;
    }
}
