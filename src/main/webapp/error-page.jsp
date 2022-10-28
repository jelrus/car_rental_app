<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/static/fragments/include-links.jspf" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Suspicious Page</title>
</head>

<body id="main-page-body">

<div class="card" id="error-card">
    <div id="card-login-header">
        Error
    </div>
    <div class="card" id="error-body">You are doing something wrong, go back and do something else!</div>
    <div class="btn btn-dark" id="go-back-button" onclick="back()">Go back</div>
</div>
<script src="static/js/main.js"></script>
</body>
</html>