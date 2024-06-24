package jp.co.collasho.classroom.controller;

import jakarta.servlet.ServletException;
import java.io.IOException;
// Jakarta Servlet 5.0 API ~
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jp.co.collasho.classroom.dto.StudentDto;
import jp.co.collasho.classroom.exception.SignUpError;
import jp.co.collasho.classroom.exception.ValidationError;
import jp.co.collasho.classroom.service.signup.SignUpDriver;
import jp.co.collasho.classroom.util.Validator;

@WebServlet("/SignUpServlet")
public class SignUpServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        req.setAttribute("errorMessage", "");
        req.getRequestDispatcher("WEB-INF/jsp/signup.jsp").forward(req, res);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

        // パラメタの取得
        String studentId = req.getParameter("student_id");
        String name = req.getParameter("name");
        String email = req.getParameter("email");
        String password = req.getParameter("password");

        // バリデーションチェック
        try {
            Validator.checkStudentId(studentId);
            Validator.checkName(name);
            Validator.checkEmail(email);
            Validator.checkPassword(password);
        } catch (ValidationError e) {
            req.setAttribute("errorMessage", e.getMessage());
            req.getRequestDispatcher("WEB-INF/jsp/signup.jsp").forward(req, res);
        }

        // DTOの生成
        StudentDto student = new StudentDto();
        student.setStudentId(studentId);
        student.setName(name);
        student.setEmail(email);
        student.setPassword(password);

        // ユーザ登録の実行
        SignUpDriver driver = new SignUpDriver();

        try {
            driver.drive(student);
            req.setAttribute("studentId", studentId);
            // 成功したらログイン画面にフォワード
            req.getRequestDispatcher("WEB-INF/jsp/login.jsp").forward(req, res);
        } catch (SignUpError e) {
            req.setAttribute("errorMessage", e.getMessage());
            // 失敗したらユーザ登録画面にリフォワード
            req.getRequestDispatcher("WEB-INF/jsp/signup.jsp").forward(req, res);
        }
    }
}
