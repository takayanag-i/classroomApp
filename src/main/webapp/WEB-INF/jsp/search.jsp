<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Search Courses</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
            display: flex;
            flex-direction: column;
            align-items: center;
        }
        h2 {
            color: #223a70;
        }
        .form-container {
            max-width: 600px;
            margin: 20px auto;
            padding: 20px;
            box-shadow: 2px 2px 10px rgba(0, 0, 0, 0.1);
            background-color: #f9f9f9;
            border-radius: 8px;
        }
        .form-container input[type="text"], .form-container select {
            width: 100%;
            padding: 8px;
            margin: 10px 0;
            box-sizing: border-box;
            border: 1px solid #ccc;
            border-radius: 4px;
        }
        .form-row {
            display: flex;
            justify-content: space-between;
        }
        .form-row .col {
            flex: 0 0 48%;
        }
        .form-row .col-full {
            flex: 0 0 100%;
            display: flex;
            justify-content: flex-end;
        }
        button {
            padding: 10px 15px;
            background-color: #223a70;
            color: white;
            border: none;
            cursor: pointer;
            border-radius: 4px;
        }
        button:hover {
            background-color: #1e3270;
        }
        button i {
            margin-right: 5px;
        }
        .table-container {
            max-width: 1100px;
            width: 100%;
            margin-top: 20px;
            overflow-x: auto;
        }
        table {
            width: 100%;
            border-collapse: collapse;
        }
        table, th, td {
            border: 1px solid black;
        }
        th, td {
            padding: 8px;
            text-align: left;
        }
        th {
            background-color: #f2f2f2;
        }
        .fixed-button-container {
            position: fixed;
            right: 20px;
            bottom: 20px;
        }
        .fixed-button-container button {
            padding: 20px 30px;
            font-size: 18px;
        }
    </style>
    <!-- Font Awesome for icons -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">
</head>
<body>

<h2>Search Courses</h2>

<div class="form-container">
    <form action="SearchServlet" method="post">
        <div class="form-row">
            <div class="col">
                <select id="day_of_week" name="day_of_week">
                    <option value="">Select Day</option>
                    <option value="Mon">月</option>
                    <option value="Tue">火</option>
                    <option value="Wed">水</option>
                    <option value="Thu">木</option>
                    <option value="Fri">金</option>
                </select>
            </div>
            <div class="col">
                <input type="text" id="period" name="period" placeholder="Period">
            </div>
        </div>
        <div class="form-row">
            <div class="col">
                <input type="text" id="course_id" name="course_id" placeholder="Course ID">
            </div>
            <div class="col">
                <input type="text" id="course_name" name="course_name" placeholder="Course Name">
            </div>
        </div>
        <div class="form-row">
            <div class="col">
                <input type="text" id="instructor_name" name="instructor_name" placeholder="Instructor Name">
            </div>
            <div class="col">
                <button type="submit"><i class="fas fa-search"></i>検索</button>
            </div>
        </div>
    </form>
</div>

<c:if test="${not empty results}">
    <h2>Search Results</h2>
    <form action="EnrollmentServlet" method="post">
        <div class="table-container">
            <table>
                <tr>
                    <th>Day of Week</th>
                    <th>Period</th>
                    <th>Course ID</th>
                    <th>Course Name</th>
                    <th>Instructors</th>
                    <th>Select</th>
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
                    <td><input type="checkbox" name="selectedCourses" value="${course.courseId}"></td>
                </tr>
                </c:forEach>
            </table>
        </div>
        <div class="fixed-button-container">
            <button type="submit"><i class="fas fa-check"></i>登録</button>
        </div>
    </form>
</c:if>

<c:if test="${empty results}">
    <p>No results found.</p>
</c:if>


</body>
</html>
