<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="true" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<f:message var="title" key="common.not_found"/>

<my:pagetemplate>
    <jsp:attribute name="head">
        <style>
            * {
                margin: 0;
                padding: 0;
            }

            .imgbox {
                display: grid;
                height: 100%;
            }

            .center-fit {
                max-width: 100%;
                max-height: 80vh;
                margin: auto;
            }
        </style>
    </jsp:attribute>

    <jsp:attribute name="body">
        <div class="alert alert-danger" role="alert">
                <c:out value="${message}"/>
        </div>
        <div class="imgbox">
            <img class="center-fit" src="${pageContext.servletContext.contextPath}/not_found_404.jpg" alt="">
        </div>
    </jsp:attribute>
</my:pagetemplate>
