<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/static/fragments/include-links.jspf" %>
<jsp:useBean id="user" scope="session" type="view.dto.response.user.UserDtoResponse"/>
<jsp:useBean id="order" scope="request" type="view.dto.response.interaction.OrderDtoResponse"/>
<jsp:useBean id="car" scope="request" type="view.dto.response.product.CarDtoResponse"/>
<jsp:useBean id="passport" scope="request" type="view.dto.response.interaction.PassportDtoResponse"/>
<jsp:useBean id="actions" scope="request" type="java.util.List"/>
<jsp:useBean id="invoices" scope="request" type="java.util.List"/>
<jsp:useBean id="fullCost" scope="request" type="java.math.BigDecimal"/>

<!DOCTYPE html>
<html>
<head>
    <title>Personal Order Info</title>
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
        <a href="${pageContext.request.contextPath}/admin/personal/orders" id="orders-nav-link">
            <div class="card" id="dashboard-menu-nav-card-orders">Orders</div>
        </a>
        <a href="${pageContext.request.contextPath}/admin/personal/order/?id=${order.id}" id="order-nav-link">
            <div class="card" id="dashboard-menu-nav-order">Order</div>
        </a>
    </nav>

    <div class="row" id="actions-card-body">
        <div id="accordion">
            <div class="card">
                <div class="card-header" id="headingOne">
                    <h5 class="mb-0">
                        <button class="btn btn-link" data-toggle="collapse" data-target="#collapseOne"
                                aria-expanded="true" id="order-info-link"
                                aria-controls="collapseOne" onclick="$('#collapseOne').collapse('toggle')">
                            Order Info
                        </button>
                    </h5>
                </div>

                <div id="collapseOne" class="collapse" aria-labelledby="headingOne" data-parent="#accordion">
                    <div class="card-body" id="card-accordion-body-1">
                        <div class="row row-cols-9">
                            <div class="row">
                                <div class="col"></div>
                                <div class="col header">
                                    <i class="fa fa-regular fa-calendar"></i>
                                    Created On
                                </div>
                                <div class="col header">
                                    <i class="fa fa-regular fa-calendar-plus"></i>
                                    Updated On
                                </div>
                                <div class="col header">
                                    <i class="fa fa-solid fa-hourglass-start"></i>
                                    Rent Start
                                </div>
                                <div class="col header">
                                    <i class="fa-solid fa-hourglass-end"></i>
                                    Rent End
                                </div>
                                <div class="col header">
                                    <i class="fa fa-solid fa-person-circle-check"></i>
                                    With Driver
                                </div>
                                <div class="col header">
                                    <i class="fa fa-solid fa-coins"></i>
                                    Cost
                                </div>
                                <div class="col header">
                                    <i class="fa fa-solid fa-circle-exclamation"></i>
                                    Status
                                </div>
                                <div class="col"></div>
                            </div>
                            <div class="row">
                                <div class="col"></div>
                                <div class="col">${order.created}</div>
                                <div class="col">${order.updated}</div>
                                <div class="col">${order.start}</div>
                                <div class="col">${order.end}</div>
                                <div class="col">
                                    <c:if test="${order.withDriver == true}">Yes</c:if>
                                    <c:if test="${order.withDriver == false}">No</c:if>
                                </div>
                                <div class="col">${fullCost}</div>
                                <div class="col">${order.orderStatus.orderStatus}</div>
                                <div class="col"></div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

            <div class="card">
                <div class="card-header" id="headingTwo">
                    <h5 class="mb-0">
                        <button class="btn btn-link collapsed" data-toggle="collapse" data-target="#collapseTwo"
                                aria-expanded="true" aria-controls="collapseTwo" id="order-car-link"
                                onclick="$('#collapseTwo').collapse('toggle')">
                            Car Info
                        </button>
                    </h5>
                </div>
                <div id="collapseTwo" class="collapse" aria-labelledby="headingTwo" data-parent="#accordion">
                    <div class="card-body" id="card-accordion-body-2">
                        <a href="${pageContext.request.contextPath}/product/?id=${car.id}" id="product-order-href">
                            <div class="row row-cols-7" id="product-card-shorthand">
                                <div class="col"></div>
                                <div class="col header">
                                    ${car.title}
                                </div>
                                <div class="col">
                                    <img src="${car.productPic}" id="product-info-image" alt="">
                                </div>
                                <div class="col">${car.carBrand.carBrand}</div>
                                <div class="col">${car.carQuality.carQuality}</div>
                                <div class="col">${car.rentalPrice} $</div>
                                <div class="col"></div>
                            </div>
                        </a>
                    </div>
                </div>
            </div>
            <div class="card">
                <div class="card-header" id="headingThree">
                    <h5 class="mb-0">
                        <button class="btn btn-link collapsed" data-toggle="collapse" data-target="#collapseThree"
                                aria-expanded="true" aria-controls="collapseThree" id="order-passport-link"
                                onclick="$('#collapseThree').collapse('toggle')">
                            Passport Data
                        </button>
                    </h5>
                </div>
                <div id="collapseThree" class="collapse" aria-labelledby="headingThree" data-parent="#accordion">
                    <div class="card-body" id="card-accordion-body-3">
                        <div class="row row-cols-7">
                            <div class="row">
                                <div class="col"></div>
                                <div class="col"></div>
                                <div class="col">
                                    <div class="col header">
                                        <i class="fa fa-solid fa-user"></i>
                                        Customer
                                    </div>
                                </div>
                                <div class="col header">
                                    <i class="fa fa-solid fa-location-dot"></i>
                                    Address
                                </div>
                                <div class="col header">
                                    <i class="fa fa-solid fa-address-book"></i>
                                    Contact Info
                                </div>
                                <div class="col"></div>
                                <div class="col"></div>
                            </div>
                            <div class="row">
                                <div class="col"></div>
                                <div class="col"></div>
                                <div class="col">
                                    <div class="row header">${passport.firstName} ${passport.lastName}</div>
                                </div>
                                <div class="col">
                                    <div class="row header">${passport.country}, ${passport.region}</div>
                                    <div class="row header">${passport.zipCode} ${passport.city}</div>
                                    <div class="row header">${passport.street}, ${passport.building}</div>
                                </div>
                                <div class="col">
                                    <div class="row header">${passport.phoneNumber}</div>
                                    <div class="row header">${passport.email}</div>
                                </div>
                                <div class="col"></div>
                                <div class="col"></div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="card">
                <div class="card-header" id="headingFour">
                    <h5 class="mb-0">
                        <button class="btn btn-link collapsed" data-toggle="collapse" data-target="#collapseFour"
                                aria-expanded="true" aria-controls="collapseFour" id="order-actions-link"
                                onclick="$('#collapseFour').collapse('toggle')">
                            Actions
                        </button>
                    </h5>
                </div>
                <div id="collapseFour" class="collapse" aria-labelledby="headingFour" data-parent="#accordion">
                    <div class="card-body" id="card-accordion-body-4">
                        <div class="col"></div>
                        <div class="row">
                            <div class="col"></div>
                            <div class="col header">
                                <i class="fa fa-regular fa-calendar"></i>
                                Created
                            </div>
                            <div class="col header">
                                <i class="fa fa-regular fa-calendar-plus"></i>
                                Updated
                            </div>
                            <div class="col header">
                                <i class="fa fa-solid fa-key"></i>
                                Identifier
                            </div>
                            <div class="col header">
                                <i class="fa fa-solid fa-envelope"></i>
                                Message
                            </div>
                            <div class="col"></div>
                        </div>
                        <hr class="solid" id="divider-table">
                        <c:forEach var="action" items="${actions}">
                            <div class="row">
                                <div class="col"></div>
                                <div class="col">${action.created}</div>
                                <div class="col">${action.updated}</div>
                                <div class="col">${action.identifier}</div>
                                <div class="col">${action.message}</div>
                                <div class="col"></div>
                            </div>
                        </c:forEach>
                        <div class="col"></div>
                    </div>
                </div>
            </div>
            <div class="card">
                <div class="card-header" id="headingFive">
                    <h5 class="mb-0">
                        <button class="btn btn-link collapsed" data-toggle="collapse" data-target="#collapseFive"
                                aria-expanded="true" aria-controls="collapseFive" id="order-invoices-link"
                                onclick="$('#collapseFive').collapse('toggle')">
                            Invoices
                        </button>
                    </h5>
                </div>
                <div id="collapseFive" class="collapse" aria-labelledby="headingFive" data-parent="#accordion">
                    <div class="card-body" id="card-accordion-body-5">
                        <div class="row row-cols-3">
                            <div class="col"></div>
                            <div class="col">
                                <c:forEach var="invoice" items="${invoices}">
                                    <div class="row" id="invoice-row">
                                        <a href="${pageContext.request.contextPath}/download/invoice/?id=${invoice.id}"
                                            download="" id="download-pdf">
                                            <i class="fa fa-solid fa-file-pdf"></i>
                                            ${invoice.fileLink}
                                        </a>
                                    </div>
                                </c:forEach>
                            </div>
                            <div class="col"></div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
