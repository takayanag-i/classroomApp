package jp.co.collasho.classroom.constants;

/**
 * パスを格納する定数クラス
 */
public class PathConstants {
    public static final String JSPDIR = "WEB-INF/jsp/";

    public static final String HOME_VIEW = JSPDIR + "enrollment.jsp";
    public static final String LOGIN_VIEW = JSPDIR + "login.jsp";
    public static final String PREDELETE_VIEW = JSPDIR + "predelete.jsp";
    public static final String SEARCH_VIEW = JSPDIR + "search.jsp";
    public static final String SIGNUP_VIEW = JSPDIR + "signup.jsp";

    public static final String HEADER_VIEW = JSPDIR + "header.jsp";

    public static final String DELETE_SERVLET = "DeleteServlet";
    public static final String ENROLLMENT_SERVLET = "EnrollmentServlet";
    public static final String HOME_SERVLET = "HomeServlet";
    public static final String LOGIN_SERVLET = "LoginServlet";
    public static final String LOGOUT_SERVLET = "LogoutServlet";
    public static final String PREDELETE_SERVLET = "PreDeleteServlet";
    public static final String SEARCH_SERVLET = "SearchServlet";
    public static final String SIGNUP_SERVLET = "SignUpServlet";

    public static final String DELETE_SERVLET_ANNT = "/" + DELETE_SERVLET;
    public static final String ENROLLMENT_SERVLET_ANNT = "/" + ENROLLMENT_SERVLET;
    public static final String HOME_SERVLET_ANNT = "/" + HOME_SERVLET;
    public static final String LOGIN_SERVLET_ANNT = "/" + LOGIN_SERVLET;
    public static final String LOGOUT_SERVLET_ANNT = "/" + LOGOUT_SERVLET;
    public static final String PREDELETE_SERVLET_ANNT = "/" + PREDELETE_SERVLET;
    public static final String SEARCH_SERVLET_ANNT = "/" + SEARCH_SERVLET;
    public static final String SIGNUP_SERVLET_ANNT = "/" + SIGNUP_SERVLET;


    private PathConstants() {
        throw new IllegalStateException(ErrorMessages.UTIL_NEW_ERROR);
    }

}
