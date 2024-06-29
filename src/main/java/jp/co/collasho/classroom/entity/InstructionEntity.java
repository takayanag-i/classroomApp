package jp.co.collasho.classroom.entity;

/**
 * InstructionテーブルのEntityクラス
 */
public class InstructionEntity {
    /** コースID */
    private String courseId;
    /** 教員名 */
    private String instructor;

    /*
     * ゲッタ・セッタ
     */
    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public String getInstructor() {
        return instructor;
    }

    public void setInstructor(String instructor) {
        this.instructor = instructor;
    }


}
