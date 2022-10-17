<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="/fragments/admin-links.jspf" %>
<jsp:useBean id="headerDataList" scope="session" type="java.util.List"/>
<jsp:useBean id="cardHeader" scope="session" type="java.lang.String"/>
<jsp:useBean id="pageData" scope="session" type="view.dto.response.PageData"/>

<html>
<head>
    <title>Title</title>
</head>
<body>
<div class="row">
    <div class="col-12" id="main-table">
        <div class="card mb-2 mt-2" id="card-main-table">
            <div class="card-header">
                All "${cardHeader}"
            </div>
            <div class="card-body">
                <table class="table table-sm table-hover" id="hover-main-table">
                    <thead>
                    <tr>
                        <c:forEach var="columnHeader" items="${headerDataList}">
                            <c:if test="${columnHeader.sortable}">
                                <c:if test="${columnHeader.active == true}">
                                    <span>${columnHeader.headerName}</span>
                                    <c:if test="${columnHeader.order.equals('desc')}">
                                        <i class="fa fa-sort-desc float-right sort" aria-hidden="true"
                                           onclick="runSort(this.getAttribute('data-sort'), this.getAttribute('data-order'))">
                                                ${columnHeader.sort}
                                                ${columnHeader.order()}
                                        </i>
                                    </c:if>
                                    <c:if test="${columnHeader.order.equals('asc')}">
                                        <i class="fa fa-sort-desc float-right sort" aria-hidden="true"
                                           onclick="runSort(this.getAttribute('data-sort'), this.getAttribute('data-order'))">
                                        </i>
                                    </c:if>
                                </c:if>
                                <c:if test="${columnHeader.active == false}">
                                    <span>${columnHeader.headerName}</span>
                                    <i class="fa fa-sort float-right sort" aria-hidden="true"
                                       data-sort="${columnHeader.getSort()}"
                                       data-order="${columnHeader.getOrder()}"
                                       onclick="runSort(this.getAttribute('data-sort'), this.getAttribute('data-order'))">
                                    </i>
                                </c:if>
                            </c:if>
                        </c:forEach>
                    </tr>
                    </thead>
                    <tbody>

                    <tr>
                        <td></td>
                    </tr>

                    </tbody>
                </table>
            </div>
        </div>
    </div>
</div>
</body>
</html>
