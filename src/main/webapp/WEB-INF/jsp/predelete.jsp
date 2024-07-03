<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Search Courses</title>
    <link rel="stylesheet" href="css/common.css">
    <link rel="stylesheet" href="css/header.css">
    <link rel="stylesheet" href="css/predelete.css">
    <!-- Font Awesome for icons -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">
</head>
<body>

<h2>履修抹消</h2>

<div class="container">
<p>履修を抹消しますか？</p>

<div class="button-container">
    <form action="DeleteServlet" method="post">
        <input type="hidden" name="course_id" value="${results[0].courseId}">
        <button class="btn btn-delete" type="submit">
            <i class="fas fa-trash-alt"></i> 抹消
        </button>
    </form>
    <form action="HomeServlet" method="post">
        <button class="btn btn-back" type="submit">
            <i class="fas fa-arrow-left"></i> 戻る
        </button>
    </form>
</div>
</div>

<div class="table-container">
    <table>
        <tr>
            <th>曜日</th>
            <th>時限</th>
            <th>講座コード</th>
            <th>講座名</th>
            <th>担当教員</th>
        </tr>
        <c:forEach var="course" items="${results}">
        <tr>
            <td>${course.dayOfWeek.japanese}</td>
            <td>${course.period}</td>
            <td>${course.courseId}</td>
            <td>${course.courseName}</td>
            <td>
                <c:forEach var="instructor" items="${course.instructors}">
                    ${instructor}<br>
                </c:forEach>
            </td>
        </tr>
        </c:forEach>
    </table>
</div>

</body>
</html>
