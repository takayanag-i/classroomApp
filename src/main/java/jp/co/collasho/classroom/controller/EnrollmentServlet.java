package jp.co.collasho.classroom.controller;

import jakarta.servlet.ServletException;
import java.io.IOException;
import java.util.List;
// Jakarta Servlet 5.0 API ~
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jp.co.collasho.classroom.dto.EnrollmentDto;
import jp.co.collasho.classroom.service.enrollment.DisplayDriver;


@WebServlet("/EnrollmentServlet")
public class EnrollmentServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

        // セッション情報の取得
        String studentId = req.getParameter("student_id");

        // ユーザの登録状況を表示
        DisplayDriver driver = new DisplayDriver();
        List<EnrollmentDto> enrollments = driver.drive(studentId);
        req.setAttribute("enrollments", enrollments);
        req.getRequestDispatcher("WEB-INF/jsp/enrollments.jsp");
    }
}
