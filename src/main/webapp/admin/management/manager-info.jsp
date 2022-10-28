<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/static/fragments/include-links.jspf" %>
<jsp:useBean id="bUser" scope="request" type="view.dto.response.user.UserDtoResponse"/>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Manager Profile</title>
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
    <a href="${pageContext.request.contextPath}/admin/personal/profile" id="link-profile">
        <img src="${bUser.profilePic}" id="profile-pic-nav">
        ${bUser.username}
    </a>
    <a href="${pageContext.request.contextPath}/logout" id="link-logout">
        <i class="fa fa-solid fa-right-from-bracket"></i>
        Logout
    </a>
</nav>

<div class="card" id="personal-info-main-card">
    <div class="card-header" id="dashboard-header">
        <i class="fa fas fa-server"></i>
        Admin Control Panel
        <div class="dropdown-divider" id="filter-div"></div>
    </div>
    <nav class="navbar navbar-expand-lg" id="dashboard-menu-nav">
        <a href="${pageContext.request.contextPath}/admin/dashboard" id="main-nav-link">
            <div class="card" id="dashboard-menu-nav-card">Control Panel</div>
        </a>
        <a href="${pageContext.request.contextPath}/admin/management/managers" id="managers-nav-link">
            <div class="card" id="dashboard-menu-nav-card-managers">Managers</div>
        </a>
        <a href="${pageContext.request.contextPath}/admin/management/manager/?id=${bUser.id}" id="manager-nav-link">
            <div class="card" id="dashboard-menu-nav-card-manager">Manager Profile</div>
        </a>
    </nav>

    <div class="row row-cols-4" id="info-menu-card-body">
        <div class="col-menu" id="left-divider"></div>
        <div class="col-menu">
            <div class="card" id="personal-info-menu-card">
                <div class="row" id="pic-name-header">
                    <div class="col" id="pic-col">
                        <img src="${bUser.profilePic}" id="profile-personal-pic">
                    </div>
                    <div class="col" id="name-col">
                        <div class="row" id="username">${bUser.username}</div>
                        <div class="row" id="full-name">${bUser.firstName} ${bUser.lastName}</div>
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
                            <td>${bUser.created}</td>
                        </tr>
                        <tr>
                            <td>
                                <button type="button" class="btn btn-secondary" data-toggle="tooltip"
                                        data-placement="top" title="Updated">
                                    <i class="fa fa-regular fa-calendar-plus"></i>
                                </button>
                            </td>
                            <td>${bUser.updated}</td>
                        </tr>
                        <tr>
                            <td>
                                <button type="button" class="btn btn-secondary" data-toggle="tooltip"
                                        data-placement="top" title="Balance">
                                    <i class="fa fa-solid fa-coins"></i>
                                </button>
                            </td>
                            <td>${bUser.balance}</td>
                        </tr>
                        <tr>
                            <td>
                                <button type="button" class="btn btn-secondary" data-toggle="tooltip"
                                        data-placement="top" title="Role">
                                    <i class="fa fa-solid fa-lock"></i>
                                </button>
                            </td>
                            <td>${bUser.roleType.roleType}</td>
                        </tr>
                    </table>
                </div>
            </div>
            <div class="row" id="edit-profile-button">
                <a href="${pageContext.request.contextPath}/admin/management/edit/manager/?id=${bUser.id}"
                   class="btn btn-primary" id="edit-profile-href">
                    Edit Profile
                </a>
            </div>
        </div>
        <div class="col-menu" id="divider-left-2">
            <div class="card" id="description-card">
                <div class="row" id="description-header">Profile description</div>
                <div class="row" id="description-body">${bUser.description}</div>
            </div>
        </div>
        <div class="col-menu"></div>
    </div>
</div>
</body>
</html>
