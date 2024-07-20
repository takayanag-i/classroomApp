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

/**
 * ログインをチェックするフィルタ
 */
@WebFilter(urlPatterns = {PathConstants.HOME_SERVLET_ANNT, PathConstants.ENROLLMENT_SERVLET_ANNT,
        PathConstants.SEARCH_SERVLET_ANNT, PathConstants.PREDELETE_SERVLET_ANNT,
        PathConstants.DELETE_SERVLET_ANNT, PathConstants.LOGOUT_SERVLET_ANNT})
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

        chain.doFilter(req, res);

    }

}
