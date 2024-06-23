package jp.co.collasho.classroom.dto;

import java.io.Serializable;

public class LoginStudentDto implements Serializable {
    private String studentId;
    private String name;
    private String email;

    /**
     * コンストラクタ
     */
    public LoginStudentDto() {}

    /**
     * コンストラクタ
     * 
     * @param studentId
     * @param name
     * @param email
     * @param password
     */
    public LoginStudentDto(String studentId, String name, String email) {
        this.studentId = studentId;
        this.name = name;
        this.email = email;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
