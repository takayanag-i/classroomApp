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
import jp.co.collasho.classroom.common.Validator;
import jp.co.collasho.classroom.dto.CourseDto;
import jp.co.collasho.classroom.dto.LoginStudentDto;
import jp.co.collasho.classroom.dto.SearchCriteriaDto;
import jp.co.collasho.classroom.exception.InvalidInputException;
import jp.co.collasho.classroom.service.search.SearchDriver;

/**
 * 削除前確認処理のコントローラ
 */
@WebServlet("/PreDeleteServlet")
public class PreDeleteServlet extends HttpServlet {

    /**
     * doPost 履修削除の前の確認をとる
     * 
     * @param req リクエスト
     * @param res レスポンス
     */
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
        SearchCriteriaDto criteriaDto = new SearchCriteriaDto();
        criteriaDto.setCourseId(courseId);
        criteriaDto.setCourseName(""); // Dtoのフィールド初期値を適切に設定
        criteriaDto.setDayOfWeek(DayOfWeek.UNSET);
        criteriaDto.setPeriod("");
        criteriaDto.setInstructorName("");

        // 検索の実行
        SearchDriver driver = new SearchDriver();
        List<CourseDto> courseDtos = driver.getCourses(criteriaDto);

        // 結果を格納してフォワード
        req.setAttribute("results", courseDtos);
        req.getRequestDispatcher("WEB-INF/jsp/predelete.jsp").forward(req, res);
    }
}
