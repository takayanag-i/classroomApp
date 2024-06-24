package jp.co.collasho.classroom.entity;

public class InstructionEntity {
    /** コースID */
    private String courseId;
    /** 教員名 */
    private String instructor;

    /**
     * コンストラクタ
     */
    public InstructionEntity() {}

    /**
     * コンストラクタ
     * 
     * @param courseId
     * @param instructorId
     * @param instructorName
     */
    public InstructionEntity(String courseId, String instructor) {
        this.courseId = courseId;
        this.instructor = instructor;
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

    public String getInstructor() {
        return instructor;
    }

    public void setInstructor(String instructor) {
        this.instructor = instructor;
    }


}
