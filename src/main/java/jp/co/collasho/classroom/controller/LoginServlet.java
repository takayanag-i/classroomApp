package jp.co.collasho.classroom.controller;

import jakarta.servlet.ServletException;
import java.io.IOException;

// Jakarta Servlet 6.0 API ~
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jp.co.collasho.classroom.common.Validator;
import jp.co.collasho.classroom.constants.PathConstants;
import jp.co.collasho.classroom.constants.ScopeConstants;
import jp.co.collasho.classroom.dto.LoginStudentDto;
import jp.co.collasho.classroom.exception.LoginFailedException;
import jp.co.collasho.classroom.exception.InvalidInputException;
import jp.co.collasho.classroom.service.login.LoginDriver;

/**
 * ログイン処理のコントローラ
 */
@WebServlet(PathConstants.LOGIN_SERVLET)
public class LoginServlet extends HttpServlet {

    /**
     * doGet ログイン画面にフォワードする
     * 
     * @param req リクエスト
     * @param res レスポンス
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        req.getRequestDispatcher(PathConstants.LOGIN_VIEW).forward(req, res);
    }

    /**
     * doPost ログインする
     * 
     * @param req リクエスト
     * @param res レスポンス
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

        // パラメタの取得
        String studentId = req.getParameter(ScopeConstants.STUDENT_ID);
        String password = req.getParameter(ScopeConstants.PASSWORD);

        // バリデーションチェック
        try {
            Validator.checkStudentId(studentId);
            Validator.checkPassword(password);
        } catch (InvalidInputException e) {
            req.setAttribute(ScopeConstants.ERROR_MESSAGE, e.getMessage());
            req.getRequestDispatcher(PathConstants.LOGIN_VIEW).forward(req, res);
            return;
        }

        // ログインユーザの情報を取得
        LoginDriver loginDriver = new LoginDriver();
        try {
            LoginStudentDto loginStudent = loginDriver.getStudentToLogin(studentId, password);

            HttpSession session = req.getSession();
            session.setAttribute(ScopeConstants.LOGIN_STUDENT, loginStudent);
        } catch (LoginFailedException e) {
            req.setAttribute(ScopeConstants.ERROR_MESSAGE, e.getMessage());
            req.getRequestDispatcher(PathConstants.LOGIN_VIEW).forward(req, res);
            return;
        }

        // 表示用時間割データを取得
        req.getRequestDispatcher(PathConstants.HOME_SERVLET).forward(req, res);
    }
}
