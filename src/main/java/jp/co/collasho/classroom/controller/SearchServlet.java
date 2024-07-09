package jp.co.collasho.classroom.controller;

import jakarta.servlet.ServletException;
import java.io.IOException;
import java.util.List;
// Jakarta Servlet 6.0 API ~
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jp.co.collasho.classroom.common.DayOfWeek;
import jp.co.collasho.classroom.common.Validator;
import jp.co.collasho.classroom.constants.PathConstants;
import jp.co.collasho.classroom.constants.ScopeConstants;
import jp.co.collasho.classroom.dto.CourseDto;
import jp.co.collasho.classroom.dto.SearchCriteriaDto;
import jp.co.collasho.classroom.exception.InvalidInputException;
import jp.co.collasho.classroom.service.search.SearchDriver;

/**
 * 検索機能のコントローラ
 */
@WebServlet(PathConstants.SEARCH_SERVLET)
public class SearchServlet extends HttpServlet {
    /**
     * doGet 検索画面にフォワードする
     * 
     * @param req リクエスト
     * @param res レスポンス
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

        req.getRequestDispatcher(PathConstants.SEARCH_VIEW).forward(req, res);
    }

    /**
     * doPost 講座を検索する
     * 
     * @param req リクエスト
     * @param res レスポンス
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

        // パラメタの取得
        String courseId = req.getParameter(ScopeConstants.COURSE_ID);
        String courseName = req.getParameter(ScopeConstants.COURRSE_NAME);
        String instructorName = req.getParameter(ScopeConstants.INSTRUCTOR_NAME);
        String dayOfWeek = req.getParameter(ScopeConstants.DAY_OF_WEEK);
        String period = req.getParameter(ScopeConstants.PERIOD);

        // バリデーションチェック
        try {
            Validator.checkCourseId(courseId);
            Validator.checkCourseName(courseName);
            Validator.checkInstructorName(instructorName);
            Validator.checkDayOfWeek(dayOfWeek);
            Validator.checkPeriod(period);
        } catch (InvalidInputException e) {
            req.setAttribute(ScopeConstants.ERROR_MESSAGE, e.getMessage());
            req.getRequestDispatcher(PathConstants.SEARCH_VIEW).forward(req, res);
            return;
        }

        // 検索条件オブジェクトの生成
        SearchCriteriaDto criteria = new SearchCriteriaDto();
        criteria.setCourseId(courseId);
        criteria.setCourseName(courseName);
        criteria.setInstructorName(instructorName);
        criteria.setDayOfWeek(DayOfWeek.fromNum(dayOfWeek));
        criteria.setPeriod(period);

        // 検索の実行
        SearchDriver driver = new SearchDriver();
        List<CourseDto> courseDtos = driver.getCourses(criteria);

        // 結果を格納してフォワード
        req.setAttribute(ScopeConstants.RESULTS, courseDtos);
        req.setAttribute(ScopeConstants.CRITERIA, criteria);
        req.getRequestDispatcher(PathConstants.SEARCH_VIEW).forward(req, res);
    }
}

