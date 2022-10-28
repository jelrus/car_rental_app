<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="/static/fragments/include-links.jspf" %>
<jsp:useBean id="order" scope="request" type="view.dto.response.interaction.OrderDtoResponse"/>
<jsp:useBean id="user" scope="session" type="view.dto.response.user.UserDtoResponse"/>
<jsp:useBean id="status" scope="request" type="java.util.List"/>


<html>
<head>
    <title>Action Creator</title>
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
        <a href="${pageContext.request.contextPath}/admin/dashboard" id="main-nav-link">
            <div class="card" id="dashboard-menu-nav-card">Control Panel</div>
        </a>
        <a href="${pageContext.request.contextPath}/admin/management/orders" id="orders-nav-link">
            <div class="card" id="dashboard-menu-nav-card-orders">Orders</div>
        </a>
        <a href="${pageContext.request.contextPath}/admin/management/order/?id=${order.id}" id="order-nav-link">
            <div class="card" id="dashboard-menu-nav-order">Order</div>
        </a>
        <a href="${pageContext.request.contextPath}/admin/management/action/new/?orderId=${order.id}" id="action-new-nav-link">
            <div class="card" id="dashboard-menu-nav-action-new">Action Creator</div>
        </a>
    </nav>

    <div class="row row-cols-3" id="dashboard-menu-card-body">
        <div class="col-menu"></div>
        <div class="col-menu">
            <div class="card" id="create-new-action-menu-card">
                <div class="row-menu">
                    <i class="fa fa-solid fa-pencil"></i>
                    New Action
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
                        <div class="form-group" id="order-status-group">
                            <label>
                                <i class="fa fa-solid fa-exclamation"></i>
                                Order Status
                                <select class="form-control form-control" name="orderStatus">
                                    <c:forEach items="${status}" var="s">
                                        <option>${s}</option>
                                    </c:forEach>
                                </select>
                            </label>
                        </div>
                        <button type="submit" class="btn btn-primary" id="apply-edit-action-button">Create</button>
                    </form>
                </div>
            </div>
        </div>
        <div class="col-menu"></div>
    </div>
</div>
</body>
</html>
