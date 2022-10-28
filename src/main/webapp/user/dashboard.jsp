<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/static/fragments/include-links.jspf" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="user" scope="session" type="view.dto.response.user.UserDtoResponse"/>

<html>
<head>
    <title>Dashboard</title>
</head>

<body id="dashboard-page-body">


<nav class="navbar navbar-expand-lg" id="open-nav">
    <div class="collapse navbar-collapse">
        <ul class="navbar-nav me-auto">
            <li class="nav-item">
                <a href="${pageContext.request.contextPath}/" id="link-main-logged">
                    <i class="fa fa-solid fa-car fa-lg"></i>
                    Car Rental
                </a>
            </li>
        </ul>
    </div>
    <a href="${pageContext.request.contextPath}/user/dashboard" id="link-profile">
        <img src="${user.profilePic}" id="profile-pic-nav">
        ${user.username}
    </a>
    <a href="${pageContext.request.contextPath}/logout" id="link-logout">
        <i class="fa fa-solid fa-right-from-bracket"></i>
        Logout
    </a>
</nav>


<div class="card" id="dashboard-main-card">
    <div class="card-header" id="dashboard-header">
        <i class="fa fas fa-server"></i>
        User Control Panel
        <div class="dropdown-divider" id="filter-div"></div>
    </div>
    <nav class="navbar navbar-expand-lg" id="dashboard-menu-nav">
        <a href="${pageContext.request.contextPath}/user/dashboard" id="main-nav-link">
            <div class="card" id="dashboard-menu-nav-card">Control Panel</div>
        </a>
    </nav>
    <div class="row row-cols-3" id="dashboard-menu-card-body">
        <div class="col-menu"></div>
        <div class="col-menu">
            <div class="card" id="personal-menu-card-user">
                <div class="row-menu">
                    <i class="fa fa-solid fa-user"></i>
                    Personal menu
                </div>
                <div class="row-menu">
                    <a href="${pageContext.request.contextPath}/user/personal/profile" class="custom-am">
                        <i class="fa fa-solid fa-address-card"></i>
                         Profile
                    </a>
                </div>
                <div class="row-menu">
                    <a href="${pageContext.request.contextPath}/user/personal/profile/edit/" class="custom-am">
                        <i class="fa fa-solid fa-pen-to-square"></i>
                         Edit Profile
                    </a>
                </div>
                <div class="row-menu">
                    <a href="${pageContext.request.contextPath}/user/personal/orders" class="custom-am">
                        <i class="fa fa-solid fa-money-check"></i>
                         My Orders
                    </a>
                </div>
                <div class="row-menu">
                    <a href="${pageContext.request.contextPath}/user/profile/suspension" class="custom-am">
                        <i class="fa fa-solid fa-user-lock"></i>
                         Suspend account
                    </a>
                </div>
            </div>
        </div>
        <div class="col-menu"></div>
    </div>
</div>
</body>
</html>