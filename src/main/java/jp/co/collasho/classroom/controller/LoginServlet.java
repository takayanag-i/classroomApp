package jp.co.collasho.classroom.controller;

import jakarta.servlet.ServletException;
import java.io.IOException;
import java.util.List;
// Jakarta Servlet 5.0 API ~
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jp.co.collasho.classroom.dto.CourseDto;
import jp.co.collasho.classroom.dto.LoginStudentDto;
import jp.co.collasho.classroom.exception.LoginFailedException;
import jp.co.collasho.classroom.exception.InvalidInputException;
import jp.co.collasho.classroom.service.enrollment.DisplayDriver;
import jp.co.collasho.classroom.service.login.LoginDriver;
import jp.co.collasho.classroom.util.Validator;

/**
 * ログイン処理のコントローラ
 */
@WebServlet("/LoginServlet")
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
        req.getRequestDispatcher("WEB-INF/jsp/login.jsp").forward(req, res);
    }

    /**
     * doPost 入力されたIDとパスワードを使用してログインを行う。
     * 
     * @param req リクエスト
     * @param res レスポンス
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

        // パラメタの取得
        String studentId = req.getParameter("student_id");
        String password = req.getParameter("password");

        // バリデーションチェック
        try {
            Validator.checkStudentId(studentId);
            Validator.checkPassword(password);
        } catch (InvalidInputException e) {
            req.setAttribute("errorMessage", e.getMessage());
            req.getRequestDispatcher("WEB-INF/jsp/login.jsp").forward(req, res);
            return;
        }

        // ログインユーザの情報を取得
        LoginDriver loginDriver = new LoginDriver();
        try {
            LoginStudentDto loginStudent = loginDriver.drive(studentId, password);

            HttpSession session = req.getSession();
            session.setAttribute("loginStudent", loginStudent);
        } catch (LoginFailedException e) {
            req.setAttribute("errorMessage", e.getMessage());
            req.getRequestDispatcher("WEB-INF/jsp/login.jsp").forward(req, res);
            return;
        }

        // 表示用時間割データを取得
        DisplayDriver displayDriver = new DisplayDriver();
        List<CourseDto> enrollments = displayDriver.drive(studentId);
        req.setAttribute("enrollments", enrollments);
        req.getRequestDispatcher("WEB-INF/jsp/enrollment.jsp").forward(req, res);
    }
}
