<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="links.jspf"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="user" scope="session" type="persistence.entity.user.BaseUser"/>

<html>
<head>
    <title>Login Page</title>
</head>

<body id="main-page-body">
<c:choose>
    <c:when test="${user.roleType == null}">
        <nav class="navbar navbar-expand-lg" id="open-nav">
            <div class="collapse navbar-collapse">
                <ul class="navbar-nav me-auto">
                    <li class="nav-item">
                        <a href="/" id="link-main-open">
                            <i class="fa fa-solid fa-car fa-lg"></i>
                            Car Rental
                        </a>
                    </li>
                </ul>
            </div>
            <a href="/login" id="link-login">
                <i class="fa fa-solid fa-right-to-bracket fa-sm"></i>
                Sign Up
            </a>
            <a href="/registration" id="link-register">
                <i class="fa fa-solid fa-user-plus fa-sm"></i>
                Register
            </a>
        </nav>
    </c:when>

    <c:when test="${user.roleType != null}">
        <nav class="navbar navbar-expand-lg" id="open-nav">
            <div class="collapse navbar-collapse">
                <ul class="navbar-nav me-auto">
                    <li class="nav-item">
                        <a href="/" id="link-main-logged">
                            <i class="fa fa-solid fa-car fa-lg"></i>
                            Car Rental
                        </a>
                    </li>
                </ul>
            </div>
            <a href="/user/profile" id="link-profile">
                <img src="${user.profilePic}" id="profile-pic-nav">
                    ${user.username}
            </a>
            <a href="/logout" id="link-logout">
                <i class="fa fa-solid fa-right-from-bracket"></i>
                Logout
            </a>
        </nav>
    </c:when>
</c:choose>

<div class="card" id="main-login-card">
    <div id="card-login-header">
        Login
    </div>
    <form id="login-form">
        <div class="form-group" id="username-group">
            <label for="input-username">username</label>
            <input type="email" class="form-control" id="input-username" aria-describedby="emailHelp"
                   placeholder="Type your username">
        </div>
        <div class="form-group" id="password-group">
            <label for="input-password">password</label>
            <input type="password" class="form-control" id="input-password"
                   placeholder="Type your password">
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
