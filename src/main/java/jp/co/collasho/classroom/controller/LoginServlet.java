package jp.co.collasho.classroom.controller;

import jakarta.servlet.ServletException;
import java.io.IOException;
// Jakarta Servlet 5.0 API ~
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jp.co.collasho.classroom.dto.LoginStudentDto;
import jp.co.collasho.classroom.exception.LoginError;
import jp.co.collasho.classroom.exception.ValidationError;
import jp.co.collasho.classroom.service.login.LoginDriver;
import jp.co.collasho.classroom.util.Validator;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

        req.getRequestDispatcher("WEB-INF/jsp/login.jsp").forward(req, res);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

        // パラメタの取得
        String studentId = req.getParameter("student_id");
        String password = req.getParameter("password");

        // バリデーションチェック
        try {
            Validator.checkUserId(studentId);
            Validator.checkPassword(password);
        } catch (ValidationError e) {
            // TODO
        }

        // ログインユーザの情報を取得
        LoginDriver driver = new LoginDriver();
        try {
            LoginStudentDto loginStudent = driver.drive(studentId, password);

            HttpSession session = req.getSession();
            session.setAttribute("loginStudent", loginStudent);
        } catch (LoginError e) {
            // TODO
        }
    }
}
