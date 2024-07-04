package jp.co.collasho.classroom.controller;

import jakarta.servlet.ServletException;
import java.io.IOException;
// Jakarta Servlet 6.0 API ~
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jp.co.collasho.classroom.common.Validator;
import jp.co.collasho.classroom.dto.StudentDto;
import jp.co.collasho.classroom.exception.SignUpFailedException;
import jp.co.collasho.classroom.exception.InvalidInputException;
import jp.co.collasho.classroom.service.signup.SignUpDriver;

/**
 * ユーザ登録処理のコントローラ
 */
@WebServlet("/SignUpServlet")
public class SignUpServlet extends HttpServlet {

    /**
     * doGet 新規登録画面にフォワードする
     * 
     * @param req リクエスト
     * @param res レスポンス
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        req.setAttribute("errorMessage", "");
        req.getRequestDispatcher("WEB-INF/jsp/signup.jsp").forward(req, res);
    }

    /**
     * doPost 新しい学生を登録する
     * 
     * @param req リクエスト
     * @param res レスポンス
     */
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
        } catch (InvalidInputException e) {
            req.setAttribute("errorMessage", e.getMessage());
            req.getRequestDispatcher("WEB-INF/jsp/signup.jsp").forward(req, res);
        }

        // DTOの生成
        StudentDto studentDto = new StudentDto();
        studentDto.setStudentId(studentId);
        studentDto.setName(name);
        studentDto.setEmail(email);
        studentDto.setPassword(password);

        // ユーザ登録の実行
        SignUpDriver driver = new SignUpDriver();

        try {
            driver.drive(studentDto);
            req.setAttribute("studentId", studentId);
            // 成功したらログイン画面にフォワード
            req.getRequestDispatcher("WEB-INF/jsp/login.jsp").forward(req, res);
        } catch (SignUpFailedException e) {
            req.setAttribute("errorMessage", e.getMessage());
            // 失敗したらユーザ登録画面にリフォワード
            req.getRequestDispatcher("WEB-INF/jsp/signup.jsp").forward(req, res);
        }
    }
}
