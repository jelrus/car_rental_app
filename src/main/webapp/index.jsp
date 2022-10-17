<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="links.jspf"%>
<jsp:useBean id="user" scope="session" type="persistence.entity.user.BaseUser"/>
<jsp:useBean id="cars" scope="request" type="java.util.List"/>

<html>
<head>
    <title>Main Page</title>
</head>

<body id="main-page-body">
<c:choose>
    <c:when test="${user.roleType == null}">
        <nav class="navbar navbar-expand-lg" id="open-nav">
            <div class="collapse navbar-collapse">
                <ul class="navbar-nav me-auto">
                    <li class="nav-item">
                        <a href="/" id="link-main-open">
                            <i class="fa fa-solid fa-car fa-lg"></i>
                            Car Rental
                        </a>
                    </li>
                </ul>
            </div>
            <a href="/login" id="link-login">
                <i class="fa fa-solid fa-right-to-bracket fa-sm"></i>
                Sign Up
            </a>
            <a href="/registration" id="link-register">
                <i class="fa fa-solid fa-user-plus fa-sm"></i>
                Register
            </a>
        </nav>
    </c:when>

    <c:when test="${user.roleType != null}">
        <nav class="navbar navbar-expand-lg" id="open-nav">
            <div class="collapse navbar-collapse">
                <ul class="navbar-nav me-auto">
                    <li class="nav-item">
                        <a href="/" id="link-main-logged">
                            <i class="fa fa-solid fa-car fa-lg"></i>
                            Car Rental
                        </a>
                    </li>
                </ul>
            </div>
            <a href="/user/profile" id="link-profile">
                <img src="${user.profilePic}" id="profile-pic-nav">
                    ${user.username}
            </a>
            <a href="/logout" id="link-logout">
                <i class="fa fa-solid fa-right-from-bracket"></i>
                Logout
            </a>
        </nav>
    </c:when>
</c:choose>

<div class="card" id="product-group">
    <div class="card" id="product-filter-card">
        <div class="card-body" id="product-filter-card-body">

            <div class="card-header" id="filter-header">
                <i class="fa fa-solid fa-filter"></i>
                Filters
                <div class="dropdown-divider" id="filter-div"></div>
            </div>

            <div class="card" id="brand-group">
                <h6 id="brand-h6">Brand</h6>
                <div class="btn-group-vertical" data-toggle="buttons" id="brand-buttons-group">
                    <label class="btn btn-secondary active">
                        <input type="checkbox" checked autocomplete="off"> BMW
                    </label>
                    <label class="btn btn-secondary active">
                        <input type="checkbox" checked autocomplete="off"> Mercedes
                    </label>
                    <label class="btn btn-secondary active">
                        <input type="checkbox" checked autocomplete="off"> Toyota
                    </label>
                    <label class="btn btn-secondary active">
                        <input type="checkbox" checked autocomplete="off"> Honda
                    </label>
                </div>
            </div>

            <div class="dropdown-divider"></div>

            <div class="card" id="quality-group">
                <h6 id="quality-h6">Quality</h6>
                <div class="btn-group-vertical" data-toggle="buttons" id="quality-buttons-group">
                    <label class="btn btn-secondary active">
                        <input type="checkbox" checked autocomplete="off"> MPV
                    </label>
                    <label class="btn btn-secondary active">
                        <input type="checkbox" checked autocomplete="off"> Luxury
                    </label>
                    <label class="btn btn-secondary active">
                        <input type="checkbox" checked autocomplete="off"> Sports
                    </label>
                    <label class="btn btn-secondary active">
                        <input type="checkbox" checked autocomplete="off"> SUV
                    </label>
                </div>
            </div>

            <div class="dropdown-divider"></div>

            <h6 class="card" id="sorting-header">
                <i class="fa fa-solid fa-sort"></i>
                Sort by
            </h6>

            <div class="dropdown-divider"></div>

            <div class="card" id="sort-group">
                <div class="dropdown">
                    <button class="btn btn-default dropdown-toggle" type="button" id="sorting-dropdown-menu"
                            data-toggle="dropdown" aria-haspopup="true" aria-expanded="true">
                        Sorting selection
                        <span class="caret"></span>
                    </button>
                    <ul class="dropdown-menu" aria-labelledby="sorting-dropdown-menu" id="sorting-list">
                        <li><a class="custom-a" href="#" data-value="price-ascending">Price Ascending</a></li>
                        <li><a class="custom-a" href="#" data-value="price-descending">Price Descending</a></li>
                        <li><a class="custom-a" href="#" data-value="title-ascending">Title Ascending</a></li>
                        <li><a class="custom-a" href="#" data-value="title-descending">Title Descending</a></li>
                    </ul>
                </div>
            </div>

        </div>
    </div>
    <div class="card" id="product-page-card">
        <div class="card-body" id="product-page-card-top">
            <div class="card" id="product-card-header">Available Propositions</div>
            <div class="dropdown-divider" id="product-div"></div>
        </div>

        <div class="container" id="product-container">
            <div class="row row-cols-5" id="product-grid">
                <c:forEach var="car" items="${cars}">
                    <div class="col" id="product-col">
                        <div class="card" id="product-card">
                            <a href="/product=${car.id}" class="btn btn-light" id="custom-a-href">
                            <div class="card-body m-2" id="product-card-body">
                                <img src="${car.productPic}" id="product-pic-main">
                                <div class="card-header" id="product-header-main">
                                        ${car.title}
                                </div>
                                <div class="card" id="product-info-card">
                                    <table>
                                        <tr>
                                            <td class="car-product-cll">Brand</td>
                                            <td class="car-product-clr">${car.brand}</td>
                                        </tr>
                                        <tr>
                                            <td class="car-product-cll">Quality</td>
                                            <td class="car-product-clr">${car.quality}</td>
                                        </tr>
                                        <tr>
                                            <td class="car-product-cll">Rental price</td>
                                            <td class="car-product-clr">${car.rentalPrice} $/day</td>
                                        </tr>
                                    </table>
                                </div>
                            </div>
                            </a>
                        </div>
                    </div>
                </c:forEach>
            </div>
        </div>
    </div>
</div>
</body>
</html>