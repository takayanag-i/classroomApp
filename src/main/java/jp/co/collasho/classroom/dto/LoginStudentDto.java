package jp.co.collasho.classroom.dto;

import java.io.Serializable;

/**
 * ログイン情報のDTOクラス
 */
public class LoginStudentDto implements Serializable {
    /** 出席番号 */
    private String studentId;
    /** 名前 */
    private String name;
    /** メールアドレス */
    private String email;

    /**
     * publicで引数のないコンストラクタ
     */
    public LoginStudentDto() {}

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
