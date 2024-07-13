package jp.co.collasho.classroom.dto;

public class CourseCellDto {
    /** 空かどうか */
    private boolean isEmpty = true;
    /** 講座DTO */
    private CourseDto course;
    /** ポストリクエスト先 */
    private String formAction;

    public CourseCellDto() {

    }

    public boolean isEmpty() {
        return isEmpty;
    }

    public void setEmpty(boolean isEmpty) {
        this.isEmpty = isEmpty;
    }

    public CourseDto getCourse() {
        return course;
    }

    public void setCourse(CourseDto course) {
        this.course = course;
    }

    public String getFormAction() {
        return formAction;
    }

    public void setFormAction(String formAction) {
        this.formAction = formAction;
    }

}
