<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="true" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>

<f:message var="title" key="feature.home"/>

<my:pagetemplate title="${title}">
    <jsp:attribute name="head">
        <style>
            .imgbox {
                display: grid;
                height: 100%;
            }

            .center {
                display: block;
                margin-left: auto;
                margin-right: auto;
                width: 80%;
            }
        </style>
    </jsp:attribute>

    <jsp:attribute name="body">
        <div class="jumbotron">
            <h1><f:message key="feature.home.header"/></h1>
            <p class="lead"><f:message key="feature.home.text"/></p>
            <div class="imgbox">
                <img class="center" src="${pageContext.servletContext.contextPath}/formula-one-team.jpg" alt="">
            </div>
        </div>
    </jsp:attribute>
</my:pagetemplate>