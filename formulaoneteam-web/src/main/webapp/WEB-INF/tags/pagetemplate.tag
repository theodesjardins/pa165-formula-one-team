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

    <link rel="shortcut icon" href="${pageContext.request.contextPath}/favicon.ico" type="image/x-icon">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
          integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css"
          integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp" crossorigin="anonymous">
    <link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.10.19/css/dataTables.bootstrap.min.css"/>

    <style>
        .clickable-row:hover {
            background-color: lightgray;
            cursor: pointer;
        }

        .clickable-row td {
            vertical-align: inherit !important;
        }

        /* Sticky footer styles */
        html {
            position: relative;
            min-height: 100%;
        }

        body {
            /* Margin bottom by footer height */
            margin-bottom: 60px;
        }

        .footer {
            position: absolute;
            bottom: 0;
        }

        #locale {
            color: #000000;
        }
    </style>

    <jsp:invoke fragment="head"/>
</head>
<body>
<nav class="navbar navbar-inverse navbar-static-top">
    <div class="container">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar"
                    aria-expanded="false" aria-controls="navbar">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
        </div>
        <div id="navbar" class="collapse navbar-collapse">
            <ul class="nav navbar-nav">
                <li><my:a href="/"><f:message key="feature.home"/></my:a></li>
                <li><my:a href="/world-championship/list"><f:message key="feature.world_championship"/></my:a></li>
                <li><my:a href="/drivers/list"><f:message key="feature.drivers"/></my:a></li>
                <li><my:a href="/cars/list"><f:message key="feature.cars"/></my:a></li>
                <li><my:a href="/components"><f:message key="feature.components"/></my:a></li>
                <li><my:a href="/test-drives"><f:message key="feature.test_drives"/></my:a></li>
            </ul>
            <ul class="nav navbar-nav navbar-right">
                <li><a>
                    <select id="locale">
                        <option value="en">English</option>
                        <option value="fr">Français</option>
                        <option value="cz">Čeština</option>
                        <option value="ru">Русский</option>
                    </select>
                </a></li>

                <sec:authorize access="!isAuthenticated()">
                    <li><my:a href="/login"><f:message key="feature.auth.sign_in"/></my:a></li>
                </sec:authorize>

                <sec:authorize access="isAuthenticated()">
                    <li><a><sec:authentication property="principal"/></a></li>
                    <li><my:a href="/logout"><f:message key="feature.auth.sing_out"/></my:a></li>
                </sec:authorize>
            </ul>
        </div>
    </div>
</nav>

<div class="container">

    <!-- page title -->
    <c:if test="${not empty title}">
        <div class="page-header">
            <h1><c:out value="${title}"/></h1>
        </div>
    </c:if>

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
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"
        integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa"
        crossorigin="anonymous"></script>
<script type="text/javascript" charset="utf8" src="https://cdn.datatables.net/1.10.19/js/jquery.dataTables.js"></script>
<script type="text/javascript" charset="utf8"
        src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.8.4/moment.min.js"></script>
<script type="text/javascript" charset="utf8"
        src="https://cdn.datatables.net/plug-ins/1.10.19/sorting/datetime-moment.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>

<script>
    $(document).ready(function () {
        $(".clickable-row").click(function () {
            window.location = "/pa165" + $(this).data("href");
        });

        $("#locale").val("${lang}");

        $("#locale").on('change', function () {
            window.location.replace('${requestScope['javax.servlet.forward.request_uri']}?lang=' + this.value);
        });
    });
</script>
<jsp:invoke fragment="script"/>
</body>
</html>
