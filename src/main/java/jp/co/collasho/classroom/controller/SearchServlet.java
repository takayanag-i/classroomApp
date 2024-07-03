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
import jp.co.collasho.classroom.common.DayOfWeek;
import jp.co.collasho.classroom.dto.CourseDto;
import jp.co.collasho.classroom.dto.LoginStudentDto;
import jp.co.collasho.classroom.dto.SearchCriteriaDto;
import jp.co.collasho.classroom.service.search.SearchDriver;

/**
 * 検索機能のコントローラ
 */
@WebServlet("/SearchServlet")
public class SearchServlet extends HttpServlet {
    /**
     * doGet
     * 
     * @param req リクエスト
     * @param res レスポンス
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        HttpSession session = req.getSession();
        LoginStudentDto loginStudent = (LoginStudentDto) session.getAttribute("loginStudent");
        String forwardPath;
        if (loginStudent == null) {
            forwardPath = "WEB-INF/jsp/login.jsp";
        } else {
            forwardPath = "WEB-INF/jsp/search.jsp";
        }

        req.getRequestDispatcher(forwardPath).forward(req, res);
    }

    /**
     * doPost
     * 
     * @param req リクエスト
     * @param res レスポンス
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

        // パラメタの取得
        String courseId = req.getParameter("course_id");
        String courseName = req.getParameter("course_name");
        String instructorName = req.getParameter("instructor_name");
        String dayOfWeek = req.getParameter("day_of_week");
        String period = req.getParameter("period");

        // TODO バリデーション 特にnullじゃないか

        // 検索条件オブジェクトの生成
        SearchCriteriaDto criteria = new SearchCriteriaDto();
        criteria.setCourseId(courseId);
        criteria.setCourseName(courseName);
        criteria.setInstructorName(instructorName);
        criteria.setDayOfWeek(DayOfWeek.fromNum(dayOfWeek));
        criteria.setPeriod(period);

        // 検索の実行
        SearchDriver driver = new SearchDriver();
        List<CourseDto> results = driver.getCourses(criteria);

        // 結果を格納してフォワード
        req.setAttribute("results", results);
        req.setAttribute("criteria", criteria);
        req.getRequestDispatcher("WEB-INF/jsp/search.jsp").forward(req, res);
    }
}

