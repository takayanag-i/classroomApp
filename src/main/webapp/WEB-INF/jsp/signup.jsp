<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html lang="ja">
<head>
    <meta charset="UTF-8">
    <title>Sign Up</title>
    <link rel="stylesheet" href="css/signup.css">
</head>
<body>
    <div class="container">
        <h2>Sign Up</h2>
        <form action="SignUpServlet" method="post">
            <div class="form-group">
                <label for="student-id">出席番号</label>
                <input type="text" id="student-id" name="student_id" required>
            </div>
            <div class="form-group">
                <label for="name">名前</label>
                <input type="text" id="name" name="name" required>
            </div>
            <div class="form-group">
                <label for="email">メールアドレス</label>
                <input type="email" id="email" name="email" required>
            </div>
            <div class="form-group">
                <label for="password">パスワード</label>
                <input type="password" id="password" name="password" required>
            </div>
            <div class="form-group">
                <button type="submit">登録</button>
            </div>
        </form>
    </div>
    <p class="error_message">${errorMessage}</p>
</body>
</html>
