<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Login</title>
    <link rel="stylesheet" href="css/common.css">
    <link rel="stylesheet" href="css/login.css">
</head>
<body>
    <div class="login-container">
        <h1>Login</h1>
        <form action="LoginServlet" method="post">
            <label for="student_id">出席番号:</label>
            <input type="text" id="student_id" name="student_id" value="${studentId}" required>

            <label for="password">パスワード:</label>
            <input type="password" id="password" name="password" required>

            <input type="submit" value="Login">
        </form>
        <a class="register-link" href="SignUpServlet">New student? Register here</a>
    </div>
    <p class="error_message">${errorMessage}</p>
</body>
</html>
