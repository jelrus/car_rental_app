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
    <a href="${pageContext.request.contextPath}/admin/dashboard" id="link-profile">
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
        Admin Control Panel
        <div class="dropdown-divider" id="filter-div"></div>
    </div>
    <nav class="navbar navbar-expand-lg" id="dashboard-menu-nav">
        <a href="${pageContext.request.contextPath}/admin/dashboard.jsp" id="main-nav-link">
            <div class="card" id="dashboard-menu-nav-card">Control Panel</div>
        </a>
    </nav>
    <div class="row row-cols-4" id="dashboard-menu-card-body">
        <div class="col-menu"></div>
        <div class="col-menu">
            <div class="card" id="personal-menu-card">
                <div class="row-menu">
                    <i class="fa fa-solid fa-user"></i>
                    Personal menu
                </div>
                <div class="row-menu">
                    <a href="${pageContext.request.contextPath}/admin/personal/profile" class="custom-am">
                        <i class="fa fa-solid fa-address-card"></i>
                         Profile
                    </a>
                </div>
                <div class="row-menu">
                    <a href="${pageContext.request.contextPath}/admin/personal/profile/edit/" class="custom-am">
                        <i class="fa fa-solid fa-pen-to-square"></i>
                         Edit Profile
                    </a>
                </div>
                <div class="row-menu">
                    <a href="${pageContext.request.contextPath}/admin/personal/orders" class="custom-am">
                        <i class="fa fa-solid fa-money-check"></i>
                         My Orders
                    </a>
                </div>
                <div class="row-menu">
                    <a href="${pageContext.request.contextPath}/admin/profile/suspension" class="custom-am">
                        <i class="fa fa-solid fa-user-lock"></i>
                         Suspend account
                    </a>
                </div>
            </div>
        </div>
        <div class="col-menu" id="dashboard-admin-menu">
            <div class="card" id="admin-menu-card">
                <div class="row-menu">
                    <i class="fa fa-solid fa-screwdriver-wrench"></i>
                    Admin menu
                </div>
                <div class="row-menu">
                    <a href="${pageContext.request.contextPath}/admin/management/users" class="custom-am">
                        <i class="fa fa-solid fa-users"></i>
                        Users
                    </a>
                </div>
                <div class="row-menu">
                    <a href="${pageContext.request.contextPath}/admin/management/managers" class="custom-am">
                        <i class="fa-solid fa-users-gear"></i>
                         Managers
                    </a>
                </div>
                <div class="row-menu">
                    <a href="${pageContext.request.contextPath}/admin/management/orders" class="custom-am">
                        <i class="fa fa-solid fa-wallet"></i>
                         Orders
                    </a>
                </div>
                <div class="row-menu">
                    <a href="${pageContext.request.contextPath}/admin/management/actions" class="custom-am">
                        <i class="fa fa-solid fa-list"></i>
                         Actions
                    </a>
                </div>
                <div class="row-menu">
                    <a href="${pageContext.request.contextPath}/admin/product/cars" class="custom-am">
                        <i class="fa fa-solid fa-car"></i>
                         Cars
                    </a>
                </div>
            </div>
        </div>
        <div class="col-menu"></div>
    </div>
</div>
</body>
</html>
