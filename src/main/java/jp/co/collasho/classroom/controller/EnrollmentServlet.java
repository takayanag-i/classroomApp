package jp.co.collasho.classroom.controller;

import jakarta.servlet.ServletException;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
// Jakarta Servlet 5.0 API ~
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jp.co.collasho.classroom.dto.EnrollmentDto;
import jp.co.collasho.classroom.dto.LoginStudentDto;
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
            req.getRequestDispatcher("WEB-INF/jsp/search.jsp").forward(req, res);
            return;
        }

        String studentId = loginStudent.getStudentId();

        // パラメタの取得
        String[] courseIds = req.getParameterValues("selectedCourses");

        // 現在日時の取得
        Timestamp now = new Timestamp(System.currentTimeMillis());

        // DTOリストの作成
        List<EnrollmentDto> enrollments = new ArrayList<>();

        for (String courseId : courseIds) {
            EnrollmentDto enrollment = new EnrollmentDto();
            enrollment.setStudentId(studentId);
            enrollment.setCourseId(courseId);
            enrollment.setEnrollmentDate(now);

            enrollments.add(enrollment);
        }

        // 履修登録の実行
        EnrollmentDriver driver = new EnrollmentDriver();
        driver.enroll(enrollments);
    }
}
