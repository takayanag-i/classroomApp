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
import jp.co.collasho.classroom.dto.CourseDto;
import jp.co.collasho.classroom.dto.LoginStudentDto;
import jp.co.collasho.classroom.service.enrollment.DisplayDriver;


@WebServlet("/HomeServlet")
public class HomeServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

        // セッション情報の取得
        HttpSession session = req.getSession();
        LoginStudentDto loginStudent = (LoginStudentDto) session.getAttribute("loginStudent");

        if (loginStudent == null) {
            req.getRequestDispatcher("WEB-INF/jsp/login.jsp").forward(req, res);
            return;
        }

        String studentId = loginStudent.getStudentId();

        // 表示用時間割データを取得
        DisplayDriver displayDriver = new DisplayDriver();
        List<CourseDto> enrollments = displayDriver.getCourses(studentId);
        req.setAttribute("enrollments", enrollments);
        req.getRequestDispatcher("WEB-INF/jsp/enrollment.jsp").forward(req, res);
    }
}
