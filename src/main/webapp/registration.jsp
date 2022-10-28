<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="static/fragments/include-links.jspf"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Sign Up Page</title>
</head>

<body id="main-page-body">
<div class="card" id="main-login-card">
    <div id="card-login-header">
        Sign Up
    </div>
    <form id="register-form" method="post" action="/registration">
        <div class="form-group" id="username-group-r">
            <label for="input-username">username</label>
            <input type="text" class="form-control" id="input-username" aria-describedby="emailHelp"
                   placeholder="Type your username" name="username">
        </div>
        <div class="form-group" id="password-group">
            <label for="input-password">password</label>
            <input type="password" class="form-control" id="input-password"
                   placeholder="Type your password" name="password">
        </div>
        <div class="form-group" id="password-confirm-group">
            <label for="input-confirm-password">confirm password</label>
            <input type="password" class="form-control" id="input-confirm-password"
                   placeholder="Confirm your password">
        </div>
        <button type="submit" class="btn btn-primary" id="login-button">SIGN UP</button>
    </form>
    <div id="sign-up-section">
        <div>Have account already?</div>
        <a href="/login" class="btn btn-primary" id="sign-up-button">LOGIN</a>
    </div>
</div>
</body>
</html>