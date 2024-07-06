package jp.co.collasho.classroom.controller;

import jakarta.servlet.ServletException;
import java.io.IOException;
// Jakarta Servlet 6.0 API ~
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jp.co.collasho.classroom.common.Validator;
import jp.co.collasho.classroom.constants.PathConstants;
import jp.co.collasho.classroom.constants.ScopeConstants;
import jp.co.collasho.classroom.dto.StudentDto;
import jp.co.collasho.classroom.exception.SignUpFailedException;
import jp.co.collasho.classroom.exception.InvalidInputException;
import jp.co.collasho.classroom.service.signup.SignUpDriver;

/**
 * ユーザ登録処理のコントローラ
 */
@WebServlet(PathConstants.SIGNUP_SERVLET)
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
        req.setAttribute(ScopeConstants.ERROR_MESSAGE, "");
        req.getRequestDispatcher(PathConstants.SIGNUP_VIEW).forward(req, res);
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
        String studentId = req.getParameter(ScopeConstants.STUDENT_ID);
        String name = req.getParameter(ScopeConstants.NAME);
        String email = req.getParameter(ScopeConstants.EMAIL);
        String password = req.getParameter(ScopeConstants.PASSWORD);

        // バリデーションチェック
        try {
            Validator.checkStudentId(studentId);
            Validator.checkName(name);
            Validator.checkEmail(email);
            Validator.checkPassword(password);
        } catch (InvalidInputException e) {
            req.setAttribute(ScopeConstants.ERROR_MESSAGE, e.getMessage());
            req.getRequestDispatcher(PathConstants.SIGNUP_VIEW).forward(req, res);
        }

        // DTOの生成
        StudentDto studentDto = new StudentDto();
        studentDto.setStudentId(studentId);
        studentDto.setName(name);
        studentDto.setEmail(email);
        studentDto.setPassword(password);

        // ユーザ登録の実行
        SignUpDriver driver = new SignUpDriver();

        String forwardPath;
        try {
            driver.signUp(studentDto);
            req.setAttribute(ScopeConstants.SIGNED_UP_ID, studentId);
            // 成功したらログイン画面にフォワード
            forwardPath = PathConstants.LOGIN_VIEW;
        } catch (SignUpFailedException e) {
            req.setAttribute("errorMessage", e.getMessage());
            // 失敗したらユーザ登録画面にリフォワード
            forwardPath = PathConstants.SIGNUP_VIEW;
        }

        req.getRequestDispatcher(forwardPath).forward(req, res);
    }
}
