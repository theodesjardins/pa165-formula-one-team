<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="true" session="false" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page import="cz.muni.fi.pa165.entity.RaceParticipation" %>

<f:message var="title" key="feature.world_championship"/>

<my:pagetemplate title="${title}">
    <jsp:attribute name="body">
        <div class="container">
            <my:addButton baseEntity="world-championship" authority="${authority}"/>
            <table class="table" id="world-championship-table">
                <thead>
                <tr>
                    <th><fmt:message key="common.title"/></th>
                    <th><fmt:message key="common.date"/></th>
                    <th><fmt:message key="feature.world_championship.position"/></th>
                    <th><fmt:message key="feature.car"/></th>
                    <th><fmt:message key="feature.driver"/></th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${raceParticipations}" var="raceParticipation">
                    <tr class="clickable-row" data-href="/world-championship/detail/${raceParticipation.id}">
                        <td><c:out value="${raceParticipation.race.title}"/></td>
                        <td><fmt:formatDate value="${raceParticipation.race.date}" pattern="dd/MM/YYYY"/></td>
                        <td>
                            <c:choose>
                                <c:when test="${raceParticipation.resultPosition eq RaceParticipation.
                                NO_RESULT_POSITION}">
                                    <fmt:message key="feature.race.unfinished"/>
                                </c:when>
                                <c:otherwise><c:out value="${raceParticipation.resultPosition}"/></c:otherwise>
                            </c:choose>
                        </td>
                        <td>
                            <my:a href="/cars/detail/${raceParticipation.carSetup.id}">
                                <c:out value="#${raceParticipation.carSetup.id}"/>
                            </my:a>
                        </td>
                        <td>
                            <my:a href="/drivers/detail/${raceParticipation.driver.id}">
                                <c:out value="${raceParticipation.driver.fullName}"/>
                            </my:a>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </jsp:attribute>

    <jsp:attribute name="script">
        <script>
            $(document).ready(function () {
                $.fn.dataTable.moment('DD/MM/YYYY');
                $("#world-championship-table").DataTable({paging: false, searching: false, info: false, order: []});
            });
        </script>
    </jsp:attribute>
</my:pagetemplate>
