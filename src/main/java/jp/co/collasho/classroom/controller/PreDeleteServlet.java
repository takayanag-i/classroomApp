package jp.co.collasho.classroom.controller;

import jakarta.servlet.ServletException;
import java.io.IOException;
import java.util.List;
// Jakarta Servlet 6.0 API ~
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jp.co.collasho.classroom.common.Validator;
import jp.co.collasho.classroom.constants.PathConstants;
import jp.co.collasho.classroom.constants.ScopeConstants;
import jp.co.collasho.classroom.dto.CourseDto;
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

        // パラメタの取得
        String courseId = req.getParameter(ScopeConstants.COURSE_ID);

        // バリデーションチェック
        try {
            Validator.checkCourseIdForSearch(courseId);
        } catch (InvalidInputException e) {
            req.setAttribute(ScopeConstants.ERROR_MESSAGE, e.getMessage());
            req.getRequestDispatcher(PathConstants.SEARCH_VIEW).forward(req, res);
            return;
        }

        // 検索条件オブジェクトの生成
        SearchCriteriaDto criteria = new SearchCriteriaDto();
        criteria.setCourseId(courseId);

        // 検索の実行
        SearchDriver driver = new SearchDriver();
        List<CourseDto> courseDtos = driver.getCourses(criteria);

        // 結果を格納してフォワード
        req.setAttribute(ScopeConstants.RESULTS, courseDtos);
        req.getRequestDispatcher(PathConstants.PREDELETE_VIEW).forward(req, res);
    }
}
