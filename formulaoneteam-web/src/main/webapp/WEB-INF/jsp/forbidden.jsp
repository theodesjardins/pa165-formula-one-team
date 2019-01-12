<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="true" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<f:message var="title" key="common.forbidden"/>

<my:basepage>
    <jsp:attribute name="head">
        <link rel="stylesheet" href="${pageContext.servletContext.contextPath}/resources/theme/css/forbidden.css">
    </jsp:attribute>

    <jsp:attribute name="body">
        <div class="container inner-container">
            <div class="imgbox">
                <img class="center-fit" src="${pageContext.servletContext.contextPath}/resources/forbidden-city.jpg" alt="">
            </div>
            <div class="centered"><c:out value="${message}"/></div>
        </div>
    </jsp:attribute>
</my:basepage>
