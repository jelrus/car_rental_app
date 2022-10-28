<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="static/fragments/include-links.jspf"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Login Page</title>
</head>

<body id="main-page-body">

<div class="card" id="main-login-card">
    <div id="card-login-header">
        Login
    </div>
    <form id="login-form" method="post">
        <div class="form-group" id="username-group">
            <label for="input-username">username</label>
            <input type="text" class="form-control" id="input-username" aria-describedby="emailHelp"
                   placeholder="Type your username" name="username">
        </div>
        <div class="form-group" id="password-group">
            <label for="input-password">password</label>
            <input type="password" class="form-control" id="input-password"
                   placeholder="Type your password" name="password">
        </div>
        <button type="submit" class="btn btn-primary" id="login-button">LOGIN</button>
    </form>
    <div id="sign-up-section">
        <div>Don't have account yet?</div>
        <a href="/registration" class="btn btn-primary" id="sign-up-button">SIGN UP</a>
    </div>
</div>
</body>
</html>
