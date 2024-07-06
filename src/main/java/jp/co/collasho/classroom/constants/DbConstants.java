package jp.co.collasho.classroom.constants;

public class DbConstants {
    // Students
    /** テーブル名 */
    public static final String STUDENTS = "Students";
    /** 出席番号 */
    public static final String STUDENT_ID = "student_id";
    /** 名前 */
    public static final String STUDENT_NAME = "name";
    /** メールアドレス */
    public static final String STUDENT_EMAIL = "email";
    /** パスワード */
    public static final String STUDENT_PASSWORD = "password";

    // Instructors
    /** テーブル名 */
    public static final String INSTRUCTORS = "Instructors";
    /** 教員ID */
    public static final String INSTRUCTOR_ID = "instructor_id";
    /** 名前 */
    public static final String INSTRUCTOR_NAME = "name";
    /** メールアドレス */
    public static final String INSTRUCTOR_EMAIL = "email";
    /** パスワード */
    public static final String INSTRUCTOR_PASSWORD = "password";

    // Courses
    public static final String COURSES = "Courses";
    /** 講座コード */
    public static final String COURSE_ID = "course_id";
    /** 講座名 */
    public static final String COURSE_NAME = "course_name";
    /** 曜日 */
    public static final String DAY_OF_WEEK = "day_of_week";
    /** 時限 */
    public static final String PERIOD = "period";

    // Enrollments
    /** テーブル名 */
    public static final String ENROLLMETNS = "Enrollments";
    /** 履修登録日時 */
    public static final String ENROLLMENT_DATE = "enrollment_date";
    /** 取消フラグ */
    public static final String CANCEL_FLAG = "cancel_flag";

    // Instructions
    /** テーブル名 */
    public static final String INSTRUCTIONS = "Instructions";
}
