<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="links.jspf" %>
<jsp:useBean id="user" scope="session" type="view.dto.response.user.UserDtoResponse"/>
<jsp:useBean id="pageData" scope="request" type="view.dto.response.PageData"/>
<jsp:useBean id="createUrl" scope="request" type="java.lang.String"/>
<jsp:useBean id="headerDataList" scope="request" type="java.util.List"/>

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
                        <input type="checkbox" checked autocomplete="off" name="bmw" value="1"> BMW
                    </label>
                    <label class="btn btn-secondary active">
                        <input type="checkbox" checked autocomplete="off" name="mercedes" value="1"> Mercedes
                    </label>
                    <label class="btn btn-secondary active">
                        <input type="checkbox" checked autocomplete="off" name="toyota" value="1"> Toyota
                    </label>
                    <label class="btn btn-secondary active">
                        <input type="checkbox" checked autocomplete="off" name="honda" value="1"> Honda
                    </label>
                </div>
            </div>

            <div class="dropdown-divider"></div>

            <div class="card" id="quality-group">
                <h6 id="quality-h6">Quality</h6>
                <div class="btn-group-vertical" data-toggle="buttons" id="quality-buttons-group">
                    <label class="btn btn-secondary">
                        <input type="checkbox" checked autocomplete="off" name="mpv" value="1"> MPV
                    </label>
                    <label class="btn btn-secondary">
                        <input type="checkbox" checked autocomplete="off" name="luxury" value="1"> Luxury
                    </label>
                    <label class="btn btn-secondary">
                        <input type="checkbox" checked autocomplete="off" name="sports" value="1"> Sports
                    </label>
                    <label class="btn btn-secondary">
                        <input type="checkbox" checked autocomplete="off" name="suv" value="1"> SUV
                    </label>
                </div>
            </div>

            <div class="dropdown-divider"></div>

            <h6 class="card" id="sorting-header">
                <i class="fa fa-solid fa-sort"></i>
                Sort by
            </h6>

            <div class="card" id="sort-headers-card">
            <c:forEach var="columnHeader" items="${headerDataList}">
                <div class="row" id="row-sort">
                    <c:if test="${columnHeader.sortable}">
                    <c:if test="${columnHeader.active}">
                    <div class="col sort">${columnHeader.headerName}</div>
                        <c:choose>
                            <c:when test="${columnHeader.order == 'desc'}">
                                <div class="col sort">
                                <i class="fa fa-sort-desc float-right sort" aria-hidden="true"
                                   data-sort="${columnHeader.sort}"
                                   data-order="${columnHeader.order}"
                                   onclick="runSort(this.getAttribute('data-sort'), this.getAttribute('data-order'))">
                                </i>
                                </div>
                            </c:when>
                            <c:when test="${columnHeader.order == 'asc'}">
                                <div class="col sort">
                                <i class="fa fa-sort-asc float-right sort" aria-hidden="true"
                                   data-sort="${columnHeader.sort}"
                                   data-order="${columnHeader.order}"
                                   onclick="runSort(this.getAttribute('data-sort'), this.getAttribute('data-order'))">
                                </i>
                                </div>
                            </c:when>
                        </c:choose>
                        </c:if>
                        <c:if test="${!columnHeader.active}">
                            <div class="col sort">${columnHeader.headerName}</div>
                            <div class="col sort">
                            <i class="fa fa-sort float-right sort" aria-hidden="true"
                               data-sort="${columnHeader.sort}"
                               data-order="${columnHeader.order}"
                               onclick="runSort(this.getAttribute('data-sort'), this.getAttribute('data-order'))">
                            </i>
                            </div>
                        </c:if>
                        <c:if test="${!columnHeader.sortable}">
                            ${columnHeader.headerName}
                        </c:if>
                        </c:if>
                </div>
            </c:forEach>
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
                <c:forEach var="car" items="${pageData.items}">
                    <div class="col" id="product-col">
                        <div class="card" id="product-card">
                            <a href="${pageContext.request.contextPath}/product/?id=${car.id}" class="btn btn-light"
                               id="custom-a-href">
                                <div class="card-body m-2" id="product-card-body">
                                    <img src="${car.productPic}" id="product-pic-main" alt="">
                                    <div class="card-header" id="product-header-main">
                                            ${car.title}
                                    </div>
                                    <div class="card" id="product-info-card">
                                        <table>
                                            <tr>
                                                <td class="car-product-cll">Brand</td>
                                                <td class="car-product-clr">${car.carBrand}</td>
                                            </tr>
                                            <tr>
                                                <td class="car-product-cll">Quality</td>
                                                <td class="car-product-clr">${car.carQuality}</td>
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

        <div class="d-flex justify-content-end" id="pagination-menu">
            <div class="d-flex justify-content-end align-items-center">
                <div class="p-1">
                    <div class="dropdown dropup">
                        <button class="btn btn-primary dropdown-toggle" type="button"
                                id="dropdownMenuLink"
                                data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                            <span>${pageData.pageSize}</span>
                        </button>
                        <div class="dropdown-menu" aria-labelledby="dropdownMenuLink">
                            <c:forEach var="size" items="${pageData.pageSizeItems}">
                                <a class="dropdown-item" href="#"
                                   onclick="runPagination(1, ${size}, 0)">${size}</a>
                            </c:forEach>
                        </div>
                    </div>
                </div>
                <div class="p-1" style="margin-top: 1em !important;">
                    <ul class="pagination align-middle">
                        <li class="page-item ${pageData.showFirst ? '' : 'disabled'}">
                            <a class="page-link" href="#" id="fast-backward"
                               onclick="runPagination(1, ${pageData.pageSize}, 0)"
                               title="First Page"><i class="fa fa-fast-backward"></i></a>
                        </li>
                        <li class="page-item ${pageData.showPrevious ? '' : 'disabled'}">
                            <a class="page-link" href="#" id="backward"
                               onclick="runPagination(${pageData.currentPage}, ${pageData.pageSize}, -1)"
                               title="Previous Page"><i class="fa fa-backward"></i></a>
                        </li>
                        <li class="page-item disabled">
                            <a class="page-link" id="current-page"
                               href="">${pageData.currentPage}</a>
                        </li>
                        <li class="page-item ${pageData.showNext ? '' : 'disabled'}">
                            <a class="page-link" href="#" id="forward"
                               onclick="runPagination(${pageData.currentPage}, ${pageData.pageSize}, 1)"
                               title="Next Page"><i class="fa fa-forward"></i></a>
                        </li>
                        <li class="page-item ${pageData.showLast ? '' : 'disabled'}">
                            <a class="page-link" href="#" id="fast-forward"
                               onclick="runPagination(${pageData.totalPageSize}, ${pageData.pageSize}, 0)"
                               title="Last Page"><i class="fa fa-fast-forward"></i></a>
                        </li>
                    </ul>
                </div>
            </div>
        </div>
    </div>

    <form method="POST" action="${createUrl}" id="personalSearch" style="display: none">
        <input type="submit"
               id="personalSearchSubmit"
               style="visibility: hidden"
        />
    </form>

    <span data-sort="${pageData.sort}"
          data-order="${pageData.order}"
          data-page="${pageData.currentPage}"
          data-size="${pageData.pageSize}"
          id="pageData">
    </span>

    <script src="static/js/pagination.js"></script>
</div>
</body>
</html>