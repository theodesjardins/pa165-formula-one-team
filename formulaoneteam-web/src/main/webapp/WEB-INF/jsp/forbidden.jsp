<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="true" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<f:message var="title" key="common.forbidden"/>

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

            .centered {
                position: absolute;
                top: 45%;
                left: 50%;
                transform: translate(-50%, -50%);
            }
        </style>
    </jsp:attribute>

    <jsp:attribute name="body">
        <div class="imgbox">
            <img class="center-fit" src="${pageContext.servletContext.contextPath}/forbidden-city.jpg" alt="">
        </div>
        <div class="centered"><c:out value="${message}"/></div>
    </jsp:attribute>
</my:pagetemplate>
