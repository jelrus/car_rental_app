<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="/static/fragments/include-links.jspf" %>
<jsp:useBean id="user" scope="session" type="view.dto.response.user.UserDtoResponse"/>
<jsp:useBean id="pageData" scope="request" type="view.dto.response.PageData"/>
<jsp:useBean id="createUrl" scope="request" type="java.lang.String"/>
<jsp:useBean id="cardHeader" scope="request" type="java.lang.String"/>
<jsp:useBean id="headerDataList" scope="request" type="java.util.List"/>

<html>
<head>
    <title>Actions Management</title>
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
        <img src="${user.profilePic}" id="profile-pic-nav" alt="">
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
        <a href="${pageContext.request.contextPath}/admin/management/actions" id="actions-nav-link">
            <div class="card" id="dashboard-menu-nav-card-actions">Actions</div>
        </a>
    </nav>

    <div class="row" id="actions-card-body">
        <div class="row">
            <div class="col" id="main-table">
                <div class="card" id="card-main-table">
                    <div class="card-body">
                        <table class="table table-sm table-hover" id="hover-main-table">
                            <thead>
                            <tr>
                                <c:forEach var="columnHeader" items="${headerDataList}">
                                    <td>
                                        <c:if test="${columnHeader.sortable}">
                                            <c:if test="${columnHeader.active}">
                                                <span>${columnHeader.headerName}</span>
                                                <c:choose>
                                                    <c:when test="${columnHeader.order == 'desc'}">
                                                        <i class="fa fa-sort-desc float-right sort" aria-hidden="true"
                                                           data-sort="${columnHeader.sort}"
                                                           data-order="${columnHeader.order}"
                                                           onclick="runSort(this.getAttribute('data-sort'), this.getAttribute('data-order'))">
                                                        </i>
                                                    </c:when>
                                                    <c:when test="${columnHeader.order == 'asc'}">
                                                        <i class="fa fa-sort-asc float-right sort" aria-hidden="true"
                                                           data-sort="${columnHeader.sort}"
                                                           data-order="${columnHeader.order}"
                                                           onclick="runSort(this.getAttribute('data-sort'), this.getAttribute('data-order'))">
                                                        </i>
                                                    </c:when>
                                                </c:choose>
                                            </c:if>
                                            <c:if test="${!columnHeader.active}">
                                                <span>${columnHeader.headerName}</span>
                                                <i class="fa fa-sort float-right sort" aria-hidden="true"
                                                   data-sort="${columnHeader.sort}"
                                                   data-order="${columnHeader.order}"
                                                   onclick="runSort(this.getAttribute('data-sort'), this.getAttribute('data-order'))">
                                                </i>
                                            </c:if>
                                            <c:if test="${!columnHeader.sortable}">
                                                ${columnHeader.headerName}
                                            </c:if>
                                        </c:if>
                                    </td>
                                </c:forEach>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach items="${pageData.items}" var="action">
                                <tr>
                                    <td>${action.created}</td>
                                    <td>${action.updated}</td>
                                    <td>${action.identifier}</td>
                                    <td>${action.message}</td>
                                    <td>
                                        <a class="btn btn-warning"
                                           href="${pageContext.request.contextPath}/admin/management/action/?id=${action.id}">Info</a>
                                    </td>
                                    <td>
                                        <form action="${pageContext.request.contextPath}/admin/management/actions/hide/?id=${action.id}"
                                              method="post" id="hide-form">
                                            <c:if test="${action.enabled == true}">
                                                <button class="btn btn-danger"
                                                        type="submit">Hide
                                                </button>
                                            </c:if>
                                            <c:if test="${action.enabled == false}">
                                                <button class="btn btn-danger"
                                                        type="submit">Show
                                                </button>
                                            </c:if>
                                        </form>
                                    </td>
                                    <td>
                                        <form action="${pageContext.request.contextPath}/admin/management/actions/delete/?id=${action.id}"
                                              method="post" id="delete-form">
                                            <button class="btn btn-danger"
                                                    type="submit"
                                                    onclick="return confirm('Warning! This action may lead to consequences on customer side. ' +
                                                                           'Better use Disable option instead. Remove?')">
                                                Delete
                                            </button>
                                        </form>
                                    </td>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>


                        <div class="d-flex justify-content-end" id="pagination-menu">
                            <div class="d-flex justify-content-end align-items-center">
                                <div class="p-1">Showing ${pageData.currentShowFromEntries} to
                                    ${pageData.currentShowFromEntries} of ${pageData.itemsSize} entries
                                </div>
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
                                <div class="p-1">
                                    <a class="page-link btn btn-secondary" href="#" id="refresh"
                                       onclick="runPagination(${pageData.currentPage}, ${pageData.pageSize}, 0)"
                                       title="Refresh Page"><i class="fa fa-refresh"></i></a>
                                </div>
                                <div class="p-1">
                                    <a class="page-link btn btn-secondary" href="#" id="trash"
                                       onclick="runPagination(1, 10, 0)"
                                       title="Reset Page"><i class="fa fa-trash"></i></a>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <form method="POST" action="${createUrl}" id="personalSearch">
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


        <script src="../../static/js/pagination.js"></script>
    </div>
</div>
</body>
</html>
