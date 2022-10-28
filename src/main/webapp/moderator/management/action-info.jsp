<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/static/fragments/include-links.jspf" %>
<jsp:useBean id="user" scope="session" type="view.dto.response.user.UserDtoResponse"/>
<jsp:useBean id="action" scope="request" type="view.dto.response.interaction.ActionDtoResponse"/>
<jsp:useBean id="manager" scope="request" type="view.dto.response.user.UserDtoResponse"/>
<jsp:useBean id="order" scope="request" type="view.dto.response.interaction.OrderDtoResponse"/>
<jsp:useBean id="customer" scope="request" type="view.dto.response.user.UserDtoResponse"/>

<html>
<head>
    <title>Action</title>
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
    </nav>

    <div class="row row-cols-6" id="info-menu-card-body">
        <div class="col-menu"></div>
        <div class="col-menu">
            <div class="card" id="personal-menu-card-info">
                <div class="row-menu">
                    <a href="${pageContext.request.contextPath}/moderator/management/action/?id=${action.id}" class="custom-am-header">
                        <i class="fa fa-solid fa-envelope-open-text"></i>
                        Action Details
                    </a>
                </div>
                <table class="custom-table">
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
        <div class="col-menu">
            <div class="card" id="manager-menu-card-info">
                <div class="row-menu">
                    <a class="custom-am-header">
                        <i class="fa fa-solid fa-user-gear"></i>
                        Manager Info
                    </a>
                </div>
                <table class="custom-table">
                    <tr>
                        <td class="td-id">
                            <button type="button" class="btn btn-secondary" data-toggle="tooltip"
                                    data-placement="top" title="ID">
                                <i class="fa fa-solid fa-id-card"></i>
                            </button>
                        </td>
                        <td>${manager.id}</td>
                    </tr>
                    <tr>
                        <td>
                            <button type="button" class="btn btn-secondary" data-toggle="tooltip"
                                    data-placement="top" title="Username">
                                <i class="fa fa-solid fa-laptop"></i>
                            </button>
                        </td>
                        <td>${manager.username}</td>
                    </tr>
                    <tr>
                        <td>
                            <button type="button" class="btn btn-secondary" data-toggle="tooltip"
                                    data-placement="top" title="Role">
                                <i class="fa fa-solid fa-lock"></i>
                            </button>
                        </td>
                        <td>${manager.roleType.roleType}</td>
                    </tr>
                </table>
            </div>
        </div>
        <div class="col-menu">
            <div class="card" id="order-menu-card-info">
                <div class="row-menu">
                    <a class="custom-am-header">
                        <i class="fa fa-solid fa-wallet"></i>
                        Order Info
                    </a>
                </div>
                <table class="custom-table">
                    <tr>
                        <td class="td-id">
                            <button type="button" class="btn btn-secondary" data-toggle="tooltip"
                                    data-placement="top" title="ID">
                                <i class="fa fa-solid fa-id-card"></i>
                            </button>
                        </td>
                        <td>${order.id}</td>
                    </tr>
                    <tr>
                        <td>
                            <button type="button" class="btn btn-secondary" data-toggle="tooltip"
                                    data-placement="top" title="Created">
                                <i class="fa fa-regular fa-calendar"></i>
                            </button>
                        </td>
                        <td>${order.created}</td>
                    </tr>
                    <tr>
                        <td>
                            <button type="button" class="btn btn-secondary" data-toggle="tooltip"
                                    data-placement="top" title="Updated">
                                <i class="fa fa-regular fa-calendar-plus"></i>
                            </button>
                        </td>
                        <td>${order.updated}</td>
                    </tr>
                    <tr>
                        <td>
                            <button type="button" class="btn btn-secondary" data-toggle="tooltip"
                                    data-placement="top" title="Order Status">
                                <i class="fa fa-solid fa-triangle-exclamation"></i>
                            </button>
                        </td>
                        <td>${order.orderStatus.orderStatus}</td>
                    </tr>
                </table>
            </div>
        </div>
        <div class="col-menu">
            <div class="card" id="user-menu-card">
                <div class="row-menu">
                    <a class="custom-am-header">
                        <i class="fa fa-solid fa-user   "></i>
                        Customer Info
                    </a>
                </div>
                <table class="custom-table">
                    <tr>
                        <td class="td-id">
                            <button type="button" class="btn btn-secondary" data-toggle="tooltip"
                                    data-placement="top" title="ID">
                                <i class="fa fa-solid fa-id-card"></i>
                            </button>
                        </td>
                        <td>${customer.id}</td>
                    </tr>
                    <tr>
                        <td>
                            <button type="button" class="btn btn-secondary" data-toggle="tooltip"
                                    data-placement="top" title="Username">
                                <i class="fa fa-solid fa-laptop"></i>
                            </button>
                        </td>
                        <td>${customer.username}</td>
                    </tr>
                    <tr>
                        <td>
                            <button type="button" class="btn btn-secondary" data-toggle="tooltip"
                                    data-placement="top" title="Role">
                                <i class="fa fa-solid fa-lock"></i>
                            </button>
                        </td>
                        <td>${customer.roleType.roleType}</td>
                    </tr>
                </table>
            </div>
        </div>
        <div class="col-menu"></div>
    </div>
    <div id="div-edit">
        <div class="col-menu" id="edit-header">
            <a href="${pageContext.request.contextPath}/moderator/management/edit/action/?id=${action.id}" class="custom-am">
                <i class="fa fa-solid fa-pen-to-square"></i>
                Edit
            </a>
        </div>
    </div>
</div>
</body>
</html>
