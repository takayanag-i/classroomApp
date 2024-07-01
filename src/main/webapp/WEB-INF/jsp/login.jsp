<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html lang="ja">
<head>
    <meta charset="UTF-8">
    <title>時間割アプリ</title>
    <link rel="stylesheet" href="css/common.css">
    <link rel="stylesheet" href="css/header.css">
    <link rel="stylesheet" href="css/login.css">
</head>
<body>
    <header>
    <div id="header-wrapper">
        <h1>This is the header</h1>
        <nav>
            <div></div>
            <div></div>
        </nav>
    </div>
    </header>
    <main>
        <div class="login-container">
            <h2>ログイン</h2>
            <form action="LoginServlet" method="post">
                <input type="text" id="student_id" name="student_id" value="${studentId}" placeholder="出席番号" required>
                <input type="password" id="password" name="password" placeholder="パスワード" required>
                <input type="submit" value="GO">
            </form>
            <a class="register-link" href="SignUpServlet">新規登録はこちら</a>
        </div>
    </main>
    <p class="error_message">${errorMessage}</p>
</body>
</html>
