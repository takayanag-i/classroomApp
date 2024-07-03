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
    <link rel="stylesheet" href="css/signup.css">
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
    <main class="container">
        <div class="sub-container">
            <h2>新規登録</h2>
            <p>注意事項をよく読んでください。</p>
            <ul>
                <li>出席番号は4桁の半角数字です。ただし，1401, 1402, 1403は使えません。</li>
                <li>パスワードは8文字以上で，半角英数字または半角記号から構成してください。</li>
            </ul>
            <p class="error-message">${errorMessage}</p>
        </div>
        <div class="signup-container">
            <form action="SignUpServlet" method="post">
                    <input type="text" id="student-id" name="student_id" placeholder="出席番号" required>
                    <input type="text" id="name" name="name" placeholder="名前" required>
                    <input type="email" id="email" name="email" placeholder="メールアドレス" required>
                    <input type="password" id="password" name="password" placeholder="パスワード" required>
                    <input type="submit" value="登録">
            </form>
        </div>
    </main>
</body>
</html>
