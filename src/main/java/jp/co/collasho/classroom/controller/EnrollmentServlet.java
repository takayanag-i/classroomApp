package jp.co.collasho.classroom.controller;

import jakarta.servlet.ServletException;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;
// Jakarta Servlet 6.0 API ~
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jp.co.collasho.classroom.dto.CourseDto;
import jp.co.collasho.classroom.dto.EnrollmentDto;
import jp.co.collasho.classroom.dto.LoginStudentDto;
import jp.co.collasho.classroom.exception.InValidEnrollmentException;
import jp.co.collasho.classroom.service.enrollment.DisplayDriver;
import jp.co.collasho.classroom.service.enrollment.EnrollmentDriver;


@WebServlet("/EnrollmentServlet")
public class EnrollmentServlet extends HttpServlet {

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

        String studentId = loginStudent.getStudentId();

        // パラメタの取得
        String courseId = req.getParameter("selectedCourse");

        // 現在日時の取得
        Timestamp now = new Timestamp(System.currentTimeMillis());

        // DTOの作成

        EnrollmentDto enrollment = new EnrollmentDto();
        enrollment.setStudentId(studentId);
        enrollment.setCourseId(courseId);
        enrollment.setEnrollmentDate(now);

        // 履修登録の実行
        EnrollmentDriver driver = new EnrollmentDriver();
        String forwardPath;
        try {
            driver.enroll(enrollment);
            forwardPath = "WEB-INF/jsp/enrollment.jsp";
            // 表示用時間割データを取得
            DisplayDriver displayDriver = new DisplayDriver();
            List<CourseDto> enrollments = displayDriver.getCourses(studentId);
            req.setAttribute("enrollments", enrollments);
        } catch (InValidEnrollmentException e) {
            forwardPath = "WEB-INF/jsp/search.jsp";
            req.setAttribute("errorMessage", e.getMessage());
        }

        // フォワード
        req.getRequestDispatcher(forwardPath).forward(req, res);
    }
}
