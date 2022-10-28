<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/static/fragments/include-links.jspf" %>
<jsp:useBean id="user" scope="session" type="view.dto.response.user.UserDtoResponse"/>

<html>
<head>
    <title>My Profile</title>
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
        Manager Control Panel
        <div class="dropdown-divider" id="filter-div"></div>
    </div>
    <nav class="navbar navbar-expand-lg" id="dashboard-menu-nav">
        <a href="${pageContext.request.contextPath}/moderator/dashboard" id="main-nav-link">
            <div class="card" id="dashboard-menu-nav-card">Control Panel</div>
        </a>
        <a href="${pageContext.request.contextPath}/moderator/personal/profile" id="actions-nav-link">
            <div class="card" id="dashboard-menu-nav-card-actions">My Profile</div>
        </a>
    </nav>

    <div class="row row-cols-4" id="info-menu-card-body">
        <div class="col-menu" id="left-divider"></div>
        <div class="col-menu">
            <div class="card" id="personal-info-menu-card">
                <div class="row" id="pic-name-header">
                    <div class="col" id="pic-col">
                        <img src="${user.profilePic}" id="profile-personal-pic">
                    </div>
                    <div class="col" id="name-col">
                        <div class="row" id="username">${user.username}</div>
                        <div class="row" id="full-name">${user.firstName} ${user.lastName}</div>
                    </div>
                </div>
                <div class="col-menu">
                    <table class="custom-table-profile-info">
                        <tr>
                            <td class="td-id">
                                <button type="button" class="btn btn-secondary" data-toggle="tooltip"
                                        data-placement="top" title="Created">
                                    <i class="fa fa-regular fa-calendar"></i>
                                </button>
                            </td>
                            <td>${user.created}</td>
                        </tr>
                        <tr>
                            <td>
                                <button type="button" class="btn btn-secondary" data-toggle="tooltip"
                                        data-placement="top" title="Updated">
                                    <i class="fa fa-regular fa-calendar-plus"></i>
                                </button>
                            </td>
                            <td>${user.updated}</td>
                        </tr>
                        <tr>
                            <td>
                                <button type="button" class="btn btn-secondary" data-toggle="tooltip"
                                        data-placement="top" title="Balance">
                                    <i class="fa fa-solid fa-coins"></i>
                                </button>
                            </td>
                            <td>${user.balance}</td>
                        </tr>
                        <tr>
                            <td>
                                <button type="button" class="btn btn-secondary" data-toggle="tooltip"
                                        data-placement="top" title="Role">
                                    <i class="fa fa-solid fa-lock"></i>
                                </button>
                            </td>
                            <td>${user.roleType.roleType}</td>
                        </tr>
                    </table>
                </div>
            </div>
            <div class="row" id="edit-profile-button">
                <a href="${pageContext.request.contextPath}/moderator/personal/profile/edit/"
                   class="btn btn-primary" id="edit-profile-href">
                    Edit Profile
                </a>
            </div>
        </div>
        <div class="col-menu" id="divider-left-2">
            <div class="card" id="description-card">
                <div class="row" id="description-header">Profile description</div>
                <div class="row" id="description-body">${user.description}</div>
            </div>
        </div>
        <div class="col-menu"></div>
    </div>
</div>
</body>
</html>