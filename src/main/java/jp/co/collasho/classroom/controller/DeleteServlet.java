package jp.co.collasho.classroom.controller;

import jakarta.servlet.ServletException;
import java.io.IOException;
// Jakarta Servlet 6.0 API ~
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jp.co.collasho.classroom.constants.ScopeConstants;
import jp.co.collasho.classroom.dto.LoginStudentDto;
import jp.co.collasho.classroom.service.delete.DeleteDriver;

/**
 * 削除処理のコントローラ
 */
@WebServlet("/DeleteServlet")
public class DeleteServlet extends HttpServlet {

    /**
     * doPost 履修登録を削除する
     * 
     * @param req リクエスト
     * @param res レスポンス
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

        // セッション情報の取得
        HttpSession session = req.getSession();
        LoginStudentDto loginStudent =
                (LoginStudentDto) session.getAttribute(ScopeConstants.LOGIN_STUDENT);

        if (loginStudent == null) {
            req.getRequestDispatcher("WEB-INF/jsp/login.jsp").forward(req, res);
            return;
        }

        String studentId = loginStudent.getStudentId();

        // パラメタの取得
        String courseId = req.getParameter("course_id");

        // 履修抹消の実行
        DeleteDriver driver = new DeleteDriver();
        driver.deleteEnrollment(studentId, courseId);
        req.getRequestDispatcher("HomeServlet").forward(req, res);
    }
}
