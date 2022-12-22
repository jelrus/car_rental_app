<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/static/fragments/include-links.jspf" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="user" scope="session" type="view.dto.response.user.UserDtoResponse"/>
<jsp:useBean id="car" scope="request" type="view.dto.response.product.CarDtoResponse"/>

<html>
<head>
    <title>Order Page</title>
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
        Order
        <div class="dropdown-divider" id="filter-div"></div>
    </div>
    <div class="">
        <form method="post">
            <div class="row row-cols-5" id="card-create-order">
                <div class="col"></div>
                <div class="col">
                    <div class="row-details">Order Details</div>
                    <div class="col" id="car-details">
                        <div class="row">
                            <img src="${car.productPic}" id="order-create-pic" alt="">
                        </div>
                        <div class="row-car-details">${car.carBrand.carBrand}</div>
                        <div class="row-car-details">${car.carQuality.carQuality}</div>
                        <div class="row-car-details">${car.rentalPrice} $/day</div>
                    </div>
                    <div class="col" id="start-date-col">
                        <label for="startDate">Start</label>
                        <input id="startDate" class="form-control" type="date" name="start"/>
                        <span id="startDateSelected"></span>
                    </div>
                    <div class="col" id="end-date-col">
                        <label for="endDate">End</label>
                        <input id="endDate" class="form-control" type="date" name="end"/>
                        <span id="endDateSelected"></span>
                    </div>
                    <div class="form-check form-switch">
                        <input class="form-check-input" type="checkbox" id="flexSwitchCheckDefault" name="withDriver">
                        <label class="form-check-label" for="flexSwitchCheckDefault">With Driver</label>
                    </div>
                </div>
                <div class="col">
                    <div class="row-details">Contact Info</div>
                    <div class="row">
                        <div class="form-group" id="first-name-group-order">
                            <label for="input-first-name">
                                First Name
                            </label>
                            <input type="text" class="form-control" id="input-first-name" aria-describedby="emailHelp"
                                   placeholder="Type first name" name="firstName">
                        </div>
                    </div>
                    <div class="row">
                        <div class="form-group" id="last-name-group-order">
                            <label for="input-last-name">
                                Last Name
                            </label>
                            <input type="text" class="form-control" id="input-last-name" aria-describedby="emailHelp"
                                   placeholder="Type last name" name="lastName">
                        </div>
                    </div>
                    <div class="row">
                        <div class="col" id="birthdate-col">
                            <label for="birthdate">Birth Date</label>
                            <input id="birthdate" class="form-control" type="date" name="birthDate"/>
                            <span id="birthDateSelected"></span>
                        </div>
                    </div>
                    <div class="row">
                        <div class="form-group" id="phone-number-group">
                            <label for="input-phone-number">
                                Phone Number
                            </label>
                            <input type="text" class="form-control" id="input-phone-number" aria-describedby="emailHelp"
                                   placeholder="Type phone number" name="phoneNumber">
                        </div>
                    </div>
                    <div class="row">
                        <div class="form-group" id="email-group">
                            <label for="input-email">
                                Email
                            </label>
                            <input type="text" class="form-control" id="input-email" aria-describedby="emailHelp"
                                   placeholder="Type email" name="email">
                        </div>
                    </div>
                </div>
                <div class="col">
                    <div class="row-details">Address</div>
                    <div class="row">
                        <div class="form-group" id="country-group">
                            <label for="input-country">
                                Country
                            </label>
                            <input type="text" class="form-control" id="input-country" aria-describedby="emailHelp"
                                   placeholder="Type country" name="country">
                        </div>
                    </div>
                    <div class="row">
                        <div class="form-group" id="zip-code-group">
                            <label for="input-zip-code">
                                Zip Code
                            </label>
                            <input type="text" class="form-control" id="input-zip-code" aria-describedby="emailHelp"
                                   placeholder="Type zip code" name="zipCode">
                        </div>
                    </div>
                    <div class="row">
                        <div class="form-group" id="region-group">
                            <label for="input-region">
                                Region
                            </label>
                            <input type="text" class="form-control" id="input-region" aria-describedby="emailHelp"
                                   placeholder="Type region" name="region">
                        </div>
                    </div>
                    <div class="row">
                        <div class="form-group" id="city-group">
                            <label for="input-city">
                                City
                            </label>
                            <input type="text" class="form-control" id="input-city" aria-describedby="emailHelp"
                                   placeholder="Type city" name="city">
                        </div>
                    </div>
                    <div class="row">
                        <div class="form-group" id="street-group">
                            <label for="input-street">
                                Street
                            </label>
                            <input type="text" class="form-control" id="input-street" aria-describedby="emailHelp"
                                   placeholder="Type street" name="street">
                        </div>
                    </div>
                    <div class="row">
                        <div class="form-group" id="building-group">
                            <label for="input-building">
                                Building
                            </label>
                            <input type="text" class="form-control" id="input-building" aria-describedby="emailHelp"
                                   placeholder="Type building" name="building">
                        </div>
                    </div>
                </div>
                <div class="col"></div>
            </div>
            <div class="col"></div>
            <button type="submit" class="btn btn-primary" id="apply-edit-action-button">ORDER</button>
        </form>
    </div>
</div>
</body>
</html>
