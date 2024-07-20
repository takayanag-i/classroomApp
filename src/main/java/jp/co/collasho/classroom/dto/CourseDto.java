package jp.co.collasho.classroom.dto;

import java.io.Serializable;
import java.util.List;
import jp.co.collasho.classroom.common.DayOfWeek;

/**
 * 講座情報のDTOクラス
 */
public class CourseDto implements Serializable {
    /** 曜日 */
    private DayOfWeek dayOfWeek;
    /** 時限 */
    private String period;
    /** 講座コード */
    private String courseId;
    /** 講座名 */
    private String courseName;
    /** 担当教員リスト */
    private List<String> instructors;
    /** ポストリクエスト先 */
    private String formAction;
    /** rowspan */
    private int rowspan = 1;

    /**
     * publicで引数のないコンストラクタ
     */
    public CourseDto() {}

    /*
     * ゲッタ・セッタ
     */
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

    public List<String> getInstructors() {
        return instructors;
    }

    public void setInstructors(List<String> instructors) {
        this.instructors = instructors;
    }

    public String getFormAction() {
        return formAction;
    }

    public void setFormAction(String formAction) {
        this.formAction = formAction;
    }

    public int getRowspan() {
        return rowspan;
    }

    public void setRowspan(int rowspan) {
        this.rowspan = rowspan;
    }
}
