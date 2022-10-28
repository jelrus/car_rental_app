<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/static/fragments/include-links.jspf" %>
<jsp:useBean id="user" scope="session" type="view.dto.response.user.UserDtoResponse"/>
<jsp:useBean id="action" scope="request" type="view.dto.response.interaction.ActionDtoResponse"/>

<html>
<head>
    <title>Action Editor</title>
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

<div class="card" id="dashboard-main-card">
    <div class="card-header" id="dashboard-header">
        <i class="fa fas fa-server"></i>
        Manager Control Panel
        <div class="dropdown-divider" id="filter-div"></div>
    </div>
    <nav class="navbar navbar-expand-lg" id="dashboard-menu-nav">
        <a href="${pageContext.request.contextPath}/moderator/dashboard" id="main-nav-link">
            <div class="card" id="dashboard-menu-nav-card">Control Panel</div>
        </a>
        <a href="${pageContext.request.contextPath}/moderator/management/actions" id="actions-nav-link">
            <div class="card" id="dashboard-menu-nav-card-actions">Actions</div>
        </a>
        <a href="${pageContext.request.contextPath}/moderator/management/action/?id=${action.id}" id="action-info-nav-link">
            <div class="card" id="dashboard-menu-nav-card-action-info">Action Details</div>
        </a>
        <a href="${pageContext.request.contextPath}/moderator/management/edit/action/?id=${action.id}"
           id="action-edit-nav-link">
            <div class="card" id="dashboard-menu-nav-card-action-edit">Action Editor</div>
        </a>
    </nav>

    <div class="row row-cols-4" id="dashboard-menu-card-body">
        <div class="col-menu"></div>
        <div class="col-menu">
            <div class="card" id="edit-old-action-menu-card">
                <div class="row-menu">
                    <i class="fa fa-solid fa-pencil"></i>
                    New Values
                </div>
                <div class="row-menu" id="action-edit-form-holder">
                    <form id="action-form" method="post">
                        <div class="form-group" id="identifier-group">
                            <label for="input-identifier">
                                <i class="fa fa-solid fa-key"></i>
                                Identifier
                            </label>
                            <input type="text" class="form-control" id="input-identifier" aria-describedby="emailHelp"
                                   placeholder="Type new identifier" name="identifier">
                        </div>
                        <div class="form-group" id="message-group">
                            <label for="input-message">
                                <i class="fa fa-solid fa-envelope"></i>
                                Message
                            </label>
                            <input type="text" class="form-control" id="input-message"
                                   placeholder="Type new message" name="message">
                        </div>
                        <button type="submit" class="btn btn-primary" id="apply-edit-action-button">Apply Changes</button>
                    </form>
                </div>
            </div>
        </div>
        <div class="col-menu">
            <div class="card" id="edit-action-menu-card">
                <div class="row-menu">
                    <i class="fa fa-solid fa-screwdriver-wrench"></i>
                    Current values
                </div>
                <table class="custom-table" id="action-old-values-table">
                    <tr>
                        <td class="td-id">
                            <button type="button" class="btn btn-secondary" data-toggle="tooltip"
                                    data-placement="top" title="ID">
                                <i class="fa fa-solid fa-id-card"></i>
                            </button>
                        </td>
                        <td>${action.id}</td>
                    </tr>
                    <tr>
                        <td>
                            <button type="button" class="btn btn-secondary" data-toggle="tooltip"
                                    data-placement="top" title="Created">
                                <i class="fa fa-regular fa-calendar"></i>
                            </button>
                        </td>
                        <td>${action.created}</td>
                    </tr>
                    <tr>
                        <td>
                            <button type="button" class="btn btn-secondary" data-toggle="tooltip"
                                    data-placement="top" title="Updated">
                                <i class="fa fa-regular fa-calendar-plus"></i>
                            </button>
                        </td>
                        <td>${action.updated}</td>
                    </tr>
                    <tr>
                        <td>
                            <button type="button" class="btn btn-secondary" data-toggle="tooltip"
                                    data-placement="top" title="Identifier">
                                <i class="fa fa-solid fa-key"></i>
                            </button>
                        </td>
                        <td>${action.identifier}</td>
                    </tr>
                    <tr>
                        <td>
                            <button type="button" class="btn btn-secondary" data-toggle="tooltip"
                                    data-placement="top" title="Message">
                                <i class="fa fa-solid fa-envelope"></i>
                            </button>
                        </td>
                        <td>${action.message}</td>
                    </tr>
                </table>
            </div>
        </div>
        <div class="col-menu"></div>
    </div>
</div>
</body>
</html>