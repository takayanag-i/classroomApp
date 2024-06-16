package jp.co.collasho.classroom.controller;

// Jakarta Servlet 5.0 API ~
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/FruitServlet")
public class FruitServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException {
        res.setContentType("text/html; charset=utf-8");
        try (PrintWriter out = res.getWriter()) {
            out.println("<html><body>");
            out.println("サーブレット: Hello Servlet World Guys!<br>");
            out.println(getServletContext().getServerInfo());
            out.println("</body></html>");
        }
    }
}