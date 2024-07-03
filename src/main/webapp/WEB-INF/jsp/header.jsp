<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<header>
    <div id="header-wrapper">
        <h1><a href="HomeServlet">時間割アプリ</a></h1>
        <nav>
            <div><a href="HomeServlet">${loginStudent.name}さん</a></div>
            <div><a href="LogoutServlet">ログアウト</a></div>
        </nav>
    </div>
</header>