<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Search Courses</title>
    <link rel="stylesheet" href="css/search.css">
    <link rel="stylesheet" href="css/predelete.css">
    <!-- Font Awesome for icons -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">
</head>
<body>

<h2>履修抹消</h2>

<p>履修を抹消しますか？</p>

<div class="button-container">
    <button class="btn btn-delete">
        <i class="fas fa-trash-alt"></i> 抹消
    </button>
    <button class="btn btn-back" onclick="history.back()">
        <i class="fas fa-arrow-left"></i> 戻る
    </button>
</div>

<c:if test="${not empty results}">
    <div class="table-container">
        <table>
            <tr>
                <th>Day of Week</th>
                <th>Period</th>
                <th>Course ID</th>
                <th>Course Name</th>
                <th>Instructors</th>
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
</c:if>

<c:if test="${empty results}">
    <p>削除エラー</p>
</c:if>

</body>
</html>
