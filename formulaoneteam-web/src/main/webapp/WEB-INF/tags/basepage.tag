<!DOCTYPE html>
<%@ tag pageEncoding="utf-8" dynamic-attributes="dynattrs" trimDirectiveWhitespaces="true" %>

<%@ attribute name="title" required="false" %>
<%@ attribute name="head" fragment="true" %>
<%@ attribute name="script" fragment="true" %>
<%@ attribute name="body" fragment="true" required="true" %>

<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="th" uri="http://www.springframework.org/tags/form" %>

<html lang="${pageContext.request.locale}">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <title><c:out value="${title}"/></title>

    <link rel="shortcut icon" href="${pageContext.request.contextPath}/resources/favicon.ico" type="image/x-icon">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css"
          integrity="sha384-GJzZqFGwb1QTTN6wy59ffF1BuGJpLSa9DkKMp0DgiMDm4iYMj70gZWKYbI706tWS" crossorigin="anonymous">

    <link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.10.19/css/jquery.dataTables.css">

    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/theme/css/base.css" type="text/css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/flags/flag-icon.css" type="text/css"/>

    <jsp:invoke fragment="head"/>
</head>

<body>

<nav class="navbar navbar-expand-md navbar-dark fixed-top bg-dark">
    <a class="navbar-brand" href="#">
        <img src="${pageContext.request.contextPath}/resources/f1_logo.png" width="60" height="30" alt="">
    </a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent"
            aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>

    <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav mr-auto">
            <li class="nav-item active">
                <my:a class="nav-link" href="/"><f:message key="feature.home"/></my:a>
            </li>
            <li class="nav-item">
                <my:a class="nav-link" href="/world-championship/list">
                    <f:message key="feature.world_championship"/>
                </my:a>
            </li>
            <li class="nav-item">
                <my:a class="nav-link" href="/drivers/list"><f:message key="feature.drivers"/></my:a>
            </li>
            <li class="nav-item">
                <my:a class="nav-link" href="/cars/list"><f:message key="feature.cars"/></my:a>
            </li>
            <li class="nav-item">
                <my:a class="nav-link" href="/components"><f:message key="feature.components"/></my:a>
            </li>
            <li class="nav-item">
                <my:a class="nav-link" href="/test-drives"><f:message key="feature.test_drives"/></my:a>
            </li>
        </ul>
        <ul class="navbar-nav navbar-right">
            <li class="nav-item dropdown">
                <a class="nav-link dropdown-toggle" id="dropdown09" data-toggle="dropdown" aria-haspopup="true"
                   aria-expanded="false" href="#">
                    <f:message key="common.language"/>
                </a>
                <div class="dropdown-menu" aria-labelledby="dropdown09">
                    <a class="dropdown-item" href="${requestScope['javax.servlet.forward.request_uri']}?lang=en">
                        <span class="flag-icon flag-icon-us"></span> English
                    </a>
                    <a class="dropdown-item" href="${requestScope['javax.servlet.forward.request_uri']}?lang=fr">
                        <span class="flag-icon flag-icon-fr"></span> Français
                    </a>
                    <a class="dropdown-item" href="${requestScope['javax.servlet.forward.request_uri']}?lang=cz">
                        <span class="flag-icon flag-icon-cz"></span> Čeština
                    </a>
                    <a class="dropdown-item" href="${requestScope['javax.servlet.forward.request_uri']}?lang=ru">
                        <span class="flag-icon flag-icon-ru"></span> Русский
                    </a>
                </div>
            </li>

            <sec:authorize access="!isAuthenticated()">
                <li class="nav-item">
                    <my:a class="nav-link" href="/login"><f:message key="feature.auth.sign_in"/></my:a>
                </li>
            </sec:authorize>

            <sec:authorize access="isAuthenticated()">
                <li class="nav-item">
                    <a class="nav-link"><sec:authentication property="principal"/></a>
                </li>
                <li class="nav-item">
                    <my:a class="nav-link" href="/logout"><f:message key="feature.auth.sing_out"/></my:a>
                </li>
            </sec:authorize>
        </ul>
    </div>
</nav>

<div class="container" id="main-container">

    <c:if test="${not empty errorMessage}">
        <div class="alert alert-danger" role="alert">
                ${errorMessage}
        </div>
    </c:if>

    <!-- page body -->
    <jsp:invoke fragment="body"/>

    <footer class="footer">
        <p>&copy;&nbsp;<%=java.time.Year.now().toString()%><f:message key="page.footer"/></p>
    </footer>
</div>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>

<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/js/bootstrap.min.js"
        integrity="sha384-B0UglyR+jN6CkvvICOB2joaf5I4l3gm9GU6Hc1og6Ls7i6U/mkkaduKaBhlAXv9k"
        crossorigin="anonymous"></script>

<script type="text/javascript" charset="utf8"
        src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.8.4/moment.min.js"></script>

<script type="text/javascript" charset="utf8" src="https://cdn.datatables.net/1.10.19/js/jquery.dataTables.js"></script>
<script type="text/javascript" charset="utf8"
        src="https://cdn.datatables.net/plug-ins/1.10.19/sorting/datetime-moment.js"></script>

<script>
    $(document).ready(function () {
        $(".clickable-row").click(function () {
            window.location = "/pa165" + $(this).data("href");
        });

        $('li.active').removeClass('active');
        $('a[href="' + location.pathname + '"]').closest('li').addClass('active');
    });
</script>
<jsp:invoke fragment="script"/>
</body>
</html>
