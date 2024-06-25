package jp.co.collasho.classroom.dto;

import java.io.Serializable;
import java.sql.Timestamp;

public class EnrollmentDto implements Serializable {
    /** 出席番号 */
    private String studentId;
    /** 講座ID */
    private String courseId;
    /** 登録日時 */
    private Timestamp enrollmentDate;

    /**
     * コンストラクタ
     * 
     * @param studentId
     * @param courseId
     * @param enrollmentDate
     */
    public EnrollmentDto(String studentId, String courseId, Timestamp enrollmentDate) {
        this.studentId = studentId;
        this.courseId = courseId;
        this.enrollmentDate = enrollmentDate;
    }

    /*
     * ゲッタ・セッタ
     */
    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public Timestamp getEnrollmentDate() {
        return enrollmentDate;
    }

    public void setEnrollmentDate(Timestamp enrollmentDate) {
        this.enrollmentDate = enrollmentDate;
    }


}
