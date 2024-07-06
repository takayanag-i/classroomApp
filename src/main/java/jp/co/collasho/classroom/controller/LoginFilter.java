package jp.co.collasho.classroom.controller;

import java.io.IOException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jp.co.collasho.classroom.constants.PathConstants;
import jp.co.collasho.classroom.constants.ScopeConstants;
import jp.co.collasho.classroom.dto.LoginStudentDto;

@WebFilter(urlPatterns = {PathConstants.HOME_SERVLET, PathConstants.ENROLLMENT_SERVLET,
        PathConstants.SEARCH_SERVLET, PathConstants.PREDELETE_SERVLET, PathConstants.DELETE_SERVLET,
        PathConstants.LOGOUT_SERVLET})
public class LoginFilter extends HttpFilter {
    private static final long serialVersionUID = 1L;

    protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain)
            throws IOException, ServletException {

        // セッション情報の取得
        HttpSession session = req.getSession();
        LoginStudentDto loginStudent =
                (LoginStudentDto) session.getAttribute(ScopeConstants.LOGIN_STUDENT);

        if (loginStudent == null) {
            req.getRequestDispatcher("WEB-INF/jsp/login.jsp").forward(req, res);
            return;
        }

    }

}
