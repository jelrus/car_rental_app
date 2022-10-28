<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="/static/fragments/include-links.jspf" %>
<jsp:useBean id="user" scope="session" type="view.dto.response.user.UserDtoResponse"/>

<html>
<head>
    <title>Manager Creator</title>
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

<div class="card" id="manager-edit-main-card">
    <div class="card-header" id="dashboard-header">
        <i class="fa fas fa-server"></i>
        Admin Control Panel
        <div class="dropdown-divider" id="filter-div"></div>
    </div>
    <nav class="navbar navbar-expand-lg" id="dashboard-menu-nav">
        <a href="${pageContext.request.contextPath}/admin/dashboard" id="main-nav-link">
            <div class="card" id="dashboard-menu-nav-card">Control Panel</div>
        </a>
        <a href="${pageContext.request.contextPath}/admin/management/managers" id="actions-nav-link">
            <div class="card" id="dashboard-menu-nav-card-actions">Managers</div>
        </a>
        <a href="${pageContext.request.contextPath}/admin/management/manager/new"
           id="action-edit-nav-link">
            <div class="card" id="dashboard-menu-nav-card-action-edit">Manager Creator</div>
        </a>
    </nav>

    <div class="row row-cols-3" id="edit-profile-menu-card-body">
        <div class="col"></div>
        <div class="col-menu">
            <div class="card" id="create-new-user-menu-card">
                <div class="row-menu">
                    <i class="fa fa-solid fa-pencil"></i>
                    Create new manager
                </div>
                <div class="row-menu" id="profile-edit-form-holder">
                    <form id="action-form" method="post">
                        <div class="form-group" id="username-group">
                            <label for="input-identifier">
                                <i class="fa fa-solid fa-laptop"></i>
                                Username
                            </label>
                            <input type="text" class="form-control" id="input-identifier" aria-describedby="emailHelp"
                                   placeholder="Type new username" name="username">
                        </div>
                        <div class="form-group" id="password-group">
                            <label for="input-password">
                                <i class="fa fa-solid fa-key"></i>
                                Password
                            </label>
                            <input type="password" class="form-control" id="input-password"
                                   placeholder="Type new password" name="password">
                        </div>
                        <div class="form-group" id="first-name-group">
                            <label for="input-first-name">
                                <i class="fa fa-solid fa-signature"></i>
                                First Name
                            </label>
                            <input type="text" class="form-control" id="input-first-name"
                                   placeholder="Type new first name" name="firstName">
                        </div>
                        <div class="form-group" id="last-name-group">
                            <label for="last-name-message">
                                <i class="fa fa-solid fa-signature"></i>
                                Last Name
                            </label>
                            <input type="text" class="form-control" id="last-name-message"
                                   placeholder="Type new lastname" name="lastName">
                        </div>
                        <div class="form-group" id="profile-pic-group">
                            <label for="profile-pic-message">
                                <i class="fa fa-solid fa-image"></i>
                                Profile Pic URL
                            </label>
                            <input type="text" class="form-control" id="profile-pic-message"
                                   placeholder="Type new profile pic url" name="profilePic">
                        </div>
                        <div class="form-group" id="balance-group">
                            <label for="balance-message">
                                <i class="fa fa-solid fa-coins"></i>
                                Balance
                            </label>
                            <input type="text" class="form-control" id="balance-message"
                                   placeholder="Type new balance" name="balance">
                        </div>
                        <div class="form-group" id="description-group">
                            <label for="description-message">
                                <i class="fa fa-solid fa-file-lines"></i>
                                Description
                            </label>
                            <input type="text" class="form-control" id="description-message"
                                   placeholder="Type new description" name="description">
                        </div>
                        <button type="submit" class="btn btn-primary" id="apply-edit-action-button">
                            Create
                        </button>
                    </form>
                </div>
            </div>
        </div>
        <div class="col-menu"></div>
    </div>
</div>
</body>
</html>
