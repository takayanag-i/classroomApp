<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Enrollment</title>
    <link rel="stylesheet" href="css/common.css">
    <link rel="stylesheet" href="css/enrollment.css">
</head>
<body>
    <h2>Enrollment Schedule</h2>
    <table>
        <thead>
            <tr>
                <th></th>
                <th>月</th>
                <th>火</th>
                <th>水</th>
                <th>木</th>
                <th>金</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="period" begin="1" end="5">
                <tr>
                    <th>${period}</th>
                    <c:forEach var="day" items="${['Mon', 'Tue', 'Wed', 'Thu', 'Fri']}">
                        <td>
                            <c:forEach var="course" items="${enrollments}">
                                <c:if test="${course.dayOfWeek.abbreviation == day && course.period == period}">
                                    <div class="course-name">${course.courseName}</div>
                                    <c:forEach var="instructor" items="${course.instructors}">
                                        <div class="instructor-name">${instructor}</div>
                                    </c:forEach>
                                </c:if>
                            </c:forEach>
                        </td>
                    </c:forEach>
                </tr>
            </c:forEach>
        </tbody>
    </table>
    <a href="SearchServlet">履修登録</a>
</body>
</html>
