<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html lang="ja">
<head>
    <meta charset="UTF-8">
    <title>時間割アプリ</title>
    <link rel="stylesheet" href="css/common.css">
    <link rel="stylesheet" href="css/header.css">
    <link rel="stylesheet" href="css/enrollment.css">
</head>
<body>
    <%@ include file="header.jsp" %>
    <main>
        <h2>履修登録</h2>
            <p>履修登録を<a href="SearchServlet">授業検索・登録</a>から行ってください。</p>
            <p>以下の「履修登録状況」から，コマごとに登録・削除を行うこともできます。</p>
        <h2>履修登録状況</h2>
        <table>
            <thead>
                <tr>
                    <th class="empty"></th>
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
                        <td>${period}</td>
                        <c:forEach var="day" begin="1" end="5">
                            <c:set var="course" value="${matrix[period - 1][day - 1]}" />
                            <td rowspan="${course.rowspan}">
                                <form action="${course.formAction}" method="post">
                                    <input type="hidden" name="course_id" value="${course.courseId}">
                                    <input type="hidden" name="day_of_week" value="${course.dayOfWeek.num}">
                                    <input type="hidden" name="period" value="${course.period}">
                                    <input type="hidden" name="course_name" value="">
                                    <input type="hidden" name="instructor_name" value="">
                                </form>
                                <c:choose>
                                    <c:when test="${not empty course.courseId}">
                                        <div class="course-name">${course.courseName}</div>
                                        <c:forEach var="instructor" items="${course.instructors}">
                                            <div class="instructor-name">${instructor}</div>
                                        </c:forEach>
                                    </c:when>
                                </c:choose>
                            </td>
                        </c:forEach>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </main>
    <script>
    document.querySelectorAll('td').forEach(function(td) {
        if (td.textContent.trim() === '') {
            td.classList.add('empty');
        }
        td.addEventListener('click', function() {
            this.querySelector('form').submit();
        });
    });
    </script>
</body>
</html>
