<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/static/fragments/include-links.jspf" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="user" scope="session" type="view.dto.response.user.UserDtoResponse"/>
<jsp:useBean id="car" scope="request" type="view.dto.response.product.CarDtoResponse"/>

<html>
<head>
    <title>Car Details</title>
</head>

<body id="dashboard-page-body">
<c:choose>
    <c:when test="${user.roleType == null}">
        <nav class="navbar navbar-expand-lg" id="open-nav">
            <div class="collapse navbar-collapse">
                <ul class="navbar-nav me-auto">
                    <li class="nav-item">
                        <a href="${pageContext.request.contextPath}/" id="link-main-open">
                            <i class="fa fa-solid fa-car fa-lg"></i>
                            Car Rental
                        </a>
                    </li>
                </ul>
            </div>
            <a href="${pageContext.request.contextPath}/login" id="link-login">
                <i class="fa fa-solid fa-right-to-bracket fa-sm"></i>
                Login
            </a>
            <a href="${pageContext.request.contextPath}/registration" id="link-register">
                <i class="fa fa-solid fa-user-plus fa-sm"></i>
                Register
            </a>
        </nav>
    </c:when>

    <c:when test="${user.roleType.roleType == 'Admin'}">
        <nav class="navbar navbar-expand-lg" id="open-nav">
            <div class="collapse navbar-collapse">
                <ul class="navbar-nav me-auto">
                    <li class="nav-item">
                        <a href="${pageContext.request.contextPath}/" id="link-main-logged-admin">
                            <i class="fa fa-solid fa-car fa-lg"></i>
                            Car Rental
                        </a>
                    </li>
                </ul>
            </div>
            <a href="${pageContext.request.contextPath}/admin/dashboard" id="link-profile-admin">
                <img src="${user.profilePic}" id="profile-pic-nav" alt="">
                    ${user.username}
            </a>
            <a href="${pageContext.request.contextPath}/logout" id="link-logout-admin">
                <i class="fa fa-solid fa-right-from-bracket"></i>
                Logout
            </a>
        </nav>
    </c:when>

    <c:when test="${user.roleType.roleType == 'Manager'}">
        <nav class="navbar navbar-expand-lg" id="open-nav">
            <div class="collapse navbar-collapse">
                <ul class="navbar-nav me-auto">
                    <li class="nav-item">
                        <a href="${pageContext.request.contextPath}/" id="link-main-logged-manager">
                            <i class="fa fa-solid fa-car fa-lg"></i>
                            Car Rental
                        </a>
                    </li>
                </ul>
            </div>
            <a href="${pageContext.request.contextPath}/moderator/dashboard" id="link-profile-manager">
                <img src="${user.profilePic}" id="profile-pic-nav" alt="">
                    ${user.username}
            </a>
            <a href="${pageContext.request.contextPath}/logout" id="link-logout-manager">
                <i class="fa fa-solid fa-right-from-bracket"></i>
                Logout
            </a>
        </nav>
    </c:when>

    <c:when test="${user.roleType.roleType == 'User'}">
        <nav class="navbar navbar-expand-lg" id="open-nav">
            <div class="collapse navbar-collapse">
                <ul class="navbar-nav me-auto">
                    <li class="nav-item">
                        <a href="${pageContext.request.contextPath}/" id="link-main-logged-user">
                            <i class="fa fa-solid fa-car fa-lg"></i>
                            Car Rental
                        </a>
                    </li>
                </ul>
            </div>
            <a href="${pageContext.request.contextPath}/user/dashboard" id="link-profile-user">
                <img src="${user.profilePic}" id="profile-pic-nav" alt="">
                    ${user.username}
            </a>
            <a href="${pageContext.request.contextPath}/logout" id="link-logout-user">
                <i class="fa fa-solid fa-right-from-bracket"></i>
                Logout
            </a>
        </nav>
    </c:when>
</c:choose>

<div class="card" id="dashboard-main-card">
    <div class="card-header" id="dashboard-header">
        <i class="fa fa-solid fa-car fa-lg"></i>
        Car Info
        <div class="dropdown-divider" id="filter-div"></div>
    </div>
    <div class="row-menu" id="car-title-header">${car.title}</div>
    <div class="row row-cols-4" id="info-car-card-body">
        <div class="col-menu" id="left-divider"></div>
        <div class="col-menu" id="pic-car-col">
            <img src="${car.productPic}" id="profile-car-pic" alt="">
            <div id="div-car-order">
                <div class="col-menu" id="order-create-header">
                    <a href="${pageContext.request.contextPath}/order/create/?carId=${car.id}" class="custom-am" id="order-link">
                        <i class="fa-solid fa-cart-shopping"></i>
                        ORDER NOW
                    </a>
                </div>
            </div>
        </div>
        <div class="col-menu" id="divider-left-2">
            <div class="card" id="description-card">
                <div class="row" id="car-description-header">Car Details</div>
                <table class="custom-table-profile-info">
                    <tr>
                        <td class="td-id">
                            Brand:
                        </td>
                        <td>${car.carBrand.carBrand}</td>
                    </tr>
                    <tr>
                        <td>
                            Quality:
                        </td>
                        <td>${car.carQuality.carQuality}</td>
                    </tr>
                    <tr>
                        <td>
                            Rental price:
                        </td>
                        <td>${car.rentalPrice} $/day</td>
                    </tr>
                </table>
                <div class="row" id="car-description-body">${car.info}</div>
            </div>
        </div>
        <div class="col-menu"></div>
    </div>
</div>
</body>
</html>
