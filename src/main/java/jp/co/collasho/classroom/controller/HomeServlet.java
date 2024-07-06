package jp.co.collasho.classroom.controller;

import jakarta.servlet.ServletException;
import java.io.IOException;
import java.util.List;
// Jakarta Servlet 6.0 API ~
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jp.co.collasho.classroom.constants.PathConstants;
import jp.co.collasho.classroom.constants.ScopeConstants;
import jp.co.collasho.classroom.dto.CourseDto;
import jp.co.collasho.classroom.dto.LoginStudentDto;
import jp.co.collasho.classroom.service.enrollment.DisplayDriver;

/**
 * ホームへ戻る処理のコントローラ
 */
@WebServlet(PathConstants.HOME_SERVLET)
public class HomeServlet extends HttpServlet {

    /**
     * doGet ホームへ戻る
     * 
     * @param req リクエスト
     * @param res レスポンス
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

        // セッション情報の取得
        HttpSession session = req.getSession();
        LoginStudentDto loginStudent =
                (LoginStudentDto) session.getAttribute(ScopeConstants.LOGIN_STUDENT);
        String studentId = loginStudent.getStudentId();

        // 表示用時間割データを取得
        DisplayDriver driver = new DisplayDriver();
        List<CourseDto> dtos = driver.getCourses(studentId);
        req.setAttribute(ScopeConstants.ENROLLMETNS, dtos);
        req.getRequestDispatcher(PathConstants.HOME_VIEW).forward(req, res);
    }

    /**
     * doPost ホームへ戻る
     * 
     * @param req リクエスト
     * @param res レスポンス
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        this.doGet(req, res);
    }
}
