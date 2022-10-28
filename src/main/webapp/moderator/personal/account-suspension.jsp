<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/static/fragments/include-links.jspf" %>
<jsp:useBean id="user" scope="session" type="view.dto.response.user.UserDtoResponse"/>

<html>
<head>
    <title>Profile Suspension</title>
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
    <a href="${pageContext.request.contextPath}/moderator/dashboard" id="link-profile">
        <img src="${user.profilePic}" id="profile-pic-nav">
        ${user.username}
    </a>
    <a href="${pageContext.request.contextPath}/logout" id="link-logout">
        <i class="fa fa-solid fa-right-from-bracket"></i>
        Logout
    </a>
</nav>

<div class="card" id="personal-info-main-card">
    <div class="card-header" id="dashboard-header">
        <i class="fa fas fa-server"></i>
        Account Suspension
        <div class="dropdown-divider" id="filter-div"></div>
    </div>
    <nav class="navbar navbar-expand-lg" id="dashboard-menu-nav">
        <a href="${pageContext.request.contextPath}/moderator/dashboard" id="main-nav-link">
            <div class="card" id="dashboard-menu-nav-card">Control Panel</div>
        </a>
        <a href="${pageContext.request.contextPath}/moderator/profile/suspension" id="actions-nav-link">
            <div class="card" id="dashboard-menu-nav-card-actions">Account Suspension</div>
        </a>
    </nav>

    <div class="row row-cols-3" id="info-menu-card-body">
        <div class="col"></div>
        <div class="col" id="suspension-col">
            <div class="card" id="suspension-card">
                <div class="row">By performing this operation you won't be able to log into your account.</div>
                <div class="row">All of your user information will be saved.</div>
                <div class="row">If you want to renew your account contact administration.</div>
                <form action="${pageContext.request.contextPath}/moderator/profile/suspension"
                      method="post" id="suspension-form">
                    <button class="btn btn-danger"
                            type="submit"
                            onclick="return confirm('Warning! You will not be able to log into account after this operation!')">
                        Suspend
                    </button>
                </form>
            </div>
        </div>
        <div class="col"></div>
    </div>
</div>
</body>
</html>