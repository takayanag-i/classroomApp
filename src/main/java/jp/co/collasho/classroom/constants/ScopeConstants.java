package jp.co.collasho.classroom.constants;

public class ScopeConstants {

    // Attributes
    public static final String CRITERIA = "criteria";
    public static final String ENROLLMETNS = "enrollments";
    public static final String ERROR_MESSAGE = "errorMessage";
    public static final String SIGNED_UP_ID = "studentId";
    public static final String LOGIN_STUDENT = "loginStudent";
    public static final String RESULTS = "results";

    // Parameters
    public static final String COURSE_ID = "course_id";
    public static final String COURRSE_NAME = "course_name";
    public static final String DAY_OF_WEEK = "day_of_week";
    public static final String EMAIL = "email";
    public static final String INSTRUCTOR_NAME = "instructor_name";
    public static final String NAME = "name";
    public static final String PASSWORD = "password";
    public static final String PERIOD = "period";
    public static final String SELECTED_COURSE = "selectedCourse";
    public static final String STUDENT_ID = "student_id";

    private ScopeConstants() {
        throw new IllegalStateException(ErrorMessages.UTIL_NEW_ERROR);
    }
}
