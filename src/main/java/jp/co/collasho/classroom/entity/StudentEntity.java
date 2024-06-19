package jp.co.collasho.classroom.entity;

public class StudentEntity extends AbstractEntity {
    private String studentId;
    private String name;
    private String email;
    private String password;

    /**
     * コンストラクタ
     */
    public StudentEntity() {}

    /**
     * コンストラクタ
     * 
     * @param studentId
     * @param name
     * @param email
     * @param password
     */
    public StudentEntity(String studentId, String name, String email, String password) {
        this.studentId = studentId;
        this.name = name;
        this.email = email;
        this.password = password;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
