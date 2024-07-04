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
import jp.co.collasho.classroom.common.DayOfWeek;
import jp.co.collasho.classroom.common.Validator;
import jp.co.collasho.classroom.dto.CourseDto;
import jp.co.collasho.classroom.dto.LoginStudentDto;
import jp.co.collasho.classroom.dto.SearchCriteriaDto;
import jp.co.collasho.classroom.exception.InvalidInputException;
import jp.co.collasho.classroom.service.search.SearchDriver;


@WebServlet("/PreDeleteServlet")
public class PreDeleteServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

        // セッション情報の取得
        HttpSession session = req.getSession();
        LoginStudentDto loginStudent = (LoginStudentDto) session.getAttribute("loginStudent");

        if (loginStudent == null) {
            req.getRequestDispatcher("WEB-INF/jsp/login.jsp").forward(req, res);
            return;
        }

        // パラメタの取得
        String courseId = req.getParameter("course_id");

        // バリデーションチェック
        try {
            Validator.checkCourseId(courseId);
        } catch (InvalidInputException e) {
            req.setAttribute("errorMessage", e.getMessage());
            req.getRequestDispatcher("WEB-INF/jsp/search.jsp").forward(req, res);
            return;
        }

        // 検索条件オブジェクトの生成
        SearchCriteriaDto criteria = new SearchCriteriaDto();
        criteria.setCourseId(courseId);
        criteria.setCourseName(""); // Dtoのフィールド初期値を適切に設定
        criteria.setDayOfWeek(DayOfWeek.UNSET);
        criteria.setPeriod("");
        criteria.setInstructorName("");

        // 検索の実行
        SearchDriver driver = new SearchDriver();
        List<CourseDto> results = driver.getCourses(criteria);

        // 結果を格納してフォワード
        req.setAttribute("results", results);
        req.getRequestDispatcher("WEB-INF/jsp/predelete.jsp").forward(req, res);
    }
}
