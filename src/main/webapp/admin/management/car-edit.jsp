<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/static/fragments/include-links.jspf" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="user" scope="session" type="view.dto.response.user.UserDtoResponse"/>
<jsp:useBean id="car" scope="request" type="view.dto.response.product.CarDtoResponse"/>
<jsp:useBean id="quality" scope="request" type="java.util.List"/>
<jsp:useBean id="brand" scope="request" type="java.util.List"/>

<html>
<head>
    <title>Car Editor</title>
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

<div class="card" id="edit-car-main-card">
    <div class="card-header" id="dashboard-header">
        <i class="fa fas fa-server"></i>
        Admin Control Panel
        <div class="dropdown-divider" id="filter-div"></div>
    </div>
    <nav class="navbar navbar-expand-lg" id="dashboard-menu-nav">
        <a href="${pageContext.request.contextPath}/admin/dashboard" id="main-nav-link">
            <div class="card" id="dashboard-menu-nav-card">Control Panel</div>
        </a>
        <a href="${pageContext.request.contextPath}/admin/product/cars" id="actions-nav-link">
            <div class="card" id="dashboard-menu-nav-card-actions">Cars</div>
        </a>
        <a href="${pageContext.request.contextPath}/admin/product/car/?id=${car.id}" id="car-nav-link">
            <div class="card" id="dashboard-menu-nav-card-car">Car Details</div>
        </a>
        <a href="${pageContext.request.contextPath}/admin/product/edit/car/?id=${car.id}"
           id="action-edit-nav-link">
            <div class="card" id="dashboard-menu-nav-card-action-edit">Car Editor</div>
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
                <div class="row-menu" id="car-edit-form-holder">
                    <form id="action-form" method="post">
                        <div class="form-group" id="username-group">
                            <label for="input-identifier">
                                <i class="fa fa-solid fa-scroll"></i>
                                Title
                            </label>
                            <input type="text" class="form-control" id="input-identifier" aria-describedby="emailHelp"
                                   placeholder="Type title" name="title">
                        </div>
                        <div class="form-group" id="profile-pic-group">
                            <label for="profile-pic-message">
                                <i class="fa fa-solid fa-image"></i>
                                Product Pic URL
                            </label>
                            <input type="text" class="form-control" id="profile-pic-message"
                                   placeholder="Type product pic url" name="productPic">
                        </div>
                        <div class="form-group" id="balance-group">
                            <label for="balance-message">
                                <i class="fa fa-solid fa-coins"></i>
                                Rental Price
                            </label>
                            <input type="text" class="form-control" id="balance-message"
                                   placeholder="Type rental price" name="rentalPrice">
                        </div>
                        <div class="form-group" id="description-group">
                            <label for="description-message">
                                <i class="fa fa-solid fa-file-lines"></i>
                                Info
                            </label>
                            <input type="text" class="form-control" id="description-message"
                                   placeholder="Type product info" name="info">
                        </div>
                        <div class="form-group" id="brand-group-edit">
                            <label>
                                <i class="fa fa-solid fa-exclamation"></i>
                                Brand
                                <select class="form-control form-control" name="brand">
                                    <c:forEach items="${brand}" var="b">
                                        <option>${b}</option>
                                    </c:forEach>
                                </select>
                            </label>
                        </div>
                        <div class="form-group" id="quality-group-edit">
                            <label>
                                <i class="fa fa-solid fa-exclamation"></i>
                                Quality
                                <select class="form-control form-control" name="quality">
                                    <c:forEach items="${quality}" var="q">
                                        <option>${q}</option>
                                    </c:forEach>
                                </select>
                            </label>
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
                        <td>${car.id}</td>
                    </tr>
                    <tr>
                        <td>
                            <button type="button" class="btn btn-secondary" data-toggle="tooltip"
                                    data-placement="top" title="Created">
                                <i class="fa fa-regular fa-calendar"></i>
                            </button>
                        </td>
                        <td>${car.created}</td>
                    </tr>
                    <tr>
                        <td>
                            <button type="button" class="btn btn-secondary" data-toggle="tooltip"
                                    data-placement="top" title="Updated">
                                <i class="fa fa-regular fa-calendar-plus"></i>
                            </button>
                        </td>
                        <td>${car.updated}</td>
                    </tr>
                    <tr>
                        <td>
                            <button type="button" class="btn btn-secondary" data-toggle="tooltip"
                                    data-placement="top" title="Title">
                                <i class="fa fa-solid fa-scroll"></i>
                            </button>
                        </td>
                        <td>${car.title}</td>
                    </tr>
                    <tr>
                        <td>
                            <button type="button" class="btn btn-secondary" data-toggle="tooltip"
                                    data-placement="top" title="Message">
                                <i class="fa fa-solid fa-exclamation"></i>
                            </button>
                        </td>
                        <td>${car.carBrand.carBrand}</td>
                    </tr>
                    <tr>
                        <td>
                            <button type="button" class="btn btn-secondary" data-toggle="tooltip"
                                    data-placement="top" title="Message">
                                <i class="fa fa-solid fa-exclamation"></i>
                            </button>
                        </td>
                        <td>${car.carQuality.carQuality}</td>
                    </tr>
                    <tr>
                        <td>
                            <button type="button" class="btn btn-secondary" data-toggle="tooltip"
                                    data-placement="top" title="Message">
                                <i class="fa fa-solid fa-coins"></i>
                            </button>
                        </td>
                        <td>${car.rentalPrice}</td>
                    </tr>
                    <tr>
                        <td>
                            <button type="button" class="btn btn-secondary" data-toggle="tooltip"
                                    data-placement="top" title="Info">
                                <i class="fa fa-solid fa-file-lines"></i>
                            </button>
                        </td>
                        <td>
                            <label>
                                <textarea id="description-text-area">${car.info}</textarea>
                            </label>
                        </td>
                    </tr>
                </table>
            </div>
        </div>
        <div class="col-menu"></div>
    </div>
</div>
</body>
</html>
