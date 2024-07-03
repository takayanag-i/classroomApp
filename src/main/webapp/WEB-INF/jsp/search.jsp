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
    <link rel="stylesheet" href="css/search.css">
    <!-- Font Awesome for icons -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">
</head>
<body>
<%@ include file="header.jsp" %>
<main>
    <p class="error-message">${errorMessage}</p>
    <div class="container">
        <div class="sub-container">
            <h2>講座検索</h2>
            <div class="form-container">
                <form action="SearchServlet" method="post">
                    <div class="form-row">
                        <div class="col">
                            <select id="day_of_week" name="day_of_week">
                                <option value="">曜日</option>
                                <option value="1">月</option>
                                <option value="2">火</option>
                                <option value="3">水</option>
                                <option value="4">木</option>
                                <option value="5">金</option>
                            </select>
                        </div>
                        <div class="col">
                            <input type="text" id="period" name="period" placeholder="時限">
                        </div>
                        <div class="col">
                            <input type="text" id="course_id" name="course_id" placeholder="講座コード">
                        </div>
                        <div class="col">
                            <input type="text" id="course_name" name="course_name" placeholder="講座名">
                        </div>
                        <div class="col">
                            <input type="text" id="instructor_name" name="instructor_name" placeholder="担当教員名">
                        </div>
                        <div class="col-full">
                            <button type="submit" class="btn">
                                <i class="fas fa-search"></i>検索
                            </button>
                        </div>
                    </div>
                </form>
            </div>
        </div>

        <div class="results-container">
            <c:if test="${not empty results}">
                <form action="EnrollmentServlet" method="post">
                    <div class="results-sub-container">
                        <p>表の部分はスクロールできます。1つ選択して登録してください。</p>
                        <button type="submit" class="btn"><i class="fas fa-check"></i>登録</button>
                    </div>
                    <div class="table-container scrollable-container">
                        <table>
                            <tr>
                                <th>曜日</th>
                                <th>時限</th>
                                <th>講座コード</th>
                                <th>講座名</th>
                                <th>担当教員</th>
                                <th></th>
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
                                <td><input type="radio" name="selectedCourse" value="${course.courseId}"></td>
                            </tr>
                            </c:forEach>
                        </table>
                    </div>
                </form>
            </c:if>

            <c:if test="${empty results}">
                <p>No results found.</p>
            </c:if>
        </div>
    </div>
</main>
</body>
</html>
