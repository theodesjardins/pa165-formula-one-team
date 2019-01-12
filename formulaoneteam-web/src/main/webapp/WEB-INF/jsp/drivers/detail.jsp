<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="true" session="false" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page import="cz.muni.fi.pa165.entity.RaceParticipation" %>

<fmt:message key="feature.drivers.detail.title" var="title"/>

<my:basepage title="${title} ${driver.name}">
    <jsp:attribute name="body">
        <div class="container inner-container">
            <c:if test="${editingEnabled}">
                <my:updateButton updatePage="/drivers/edit/${driver.id}" authority="true"/>
            </c:if>
            <div class="row">
                <div class="col-md-6 col-xs-12">
                    <h4><fmt:message key="feature.drivers.detail.info"/></h4>
                    <dl class="dl-horizontal">
                        <dt><fmt:message key="common.first_name"/>:</dt>
                        <dd><c:out value="${driver.name}"/></dd>
                        <dt><fmt:message key="common.surname"/>:</dt>
                        <dd><c:out value="${driver.surname}"/></dd>
                        <dt><fmt:message key="common.email"/>:</dt>
                        <dd><c:out value="${driver.email}"/></dd>
                        <dt><fmt:message key="common.nationality"/>:</dt>
                        <dd><c:out value="${driver.nationality}"/></dd>
                        <dt><fmt:message key="common.birthday"/>:</dt>
                        <dd><fmt:formatDate value="${driver.birthday}" pattern="dd/MM/YYYY"/></dd>
                        <dt><fmt:message key="feature.drivers.status"/>:</dt>
                        <dd><c:out value="${driver.driverStatus}"/></dd>
                    </dl>
                </div>
            </div>
            <div class="row">
                <div class="col-md-6 col-xs-12">
                    <h4><fmt:message key="feature.drivers.characteristic_values"/></h4>
                    <table class="table" id="characteristics-value-table">
                        <thead>
                        <tr>
                            <th><fmt:message key="common.name"/></th>
                            <th><fmt:message key="common.value"/></th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${driver.characteristics}" var="characteristic">
                                <tr>
                                    <td><c:out value="${characteristic.type}"/></td>
                                    <td><c:out value="${characteristic.value}"/></td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>

            <div class="row" style=" padding-top: 20px;">
                <c:if test="${driver.raceParticipations.size() gt 0}">
                <div class="col-md-6 col-xs-12">
                    <h4><fmt:message key="feature.race.race_participations"/></h4>
                    <table class="table" id="race-participations-table">
                        <thead>
                        <tr>
                            <th><fmt:message key="common.date"/></th>
                            <th><fmt:message key="common.title"/></th>
                            <th><fmt:message key="feature.race.result"/></th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${driver.raceParticipations}" var="participation">
                                <tr class="clickable-row" data-href="/test-drives/detail/${participation.id}">
                                    <td><fmt:formatDate value="${participation.race.date}" pattern="dd/MM/yyyy"/></td>
                                    <td><c:out value="${participation.race.title}"/></td>
                                    <td>
                                        <c:choose>
                                            <c:when test="${participation.resultPosition eq RaceParticipation.NO_RESULT_POSITION}">
                                                <fmt:message key="feature.race.unfinished"/>
                                            </c:when>
                                            <c:otherwise><c:out value="${participation.resultPosition}"/></c:otherwise>
                                        </c:choose>
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
                </c:if>
            </div>
            <div class="row" style=" padding-top: 20px;">
                <c:if test="${driver.testDrives.size() gt 0}">
                <div class="col-md-6 col-xs-12">
                    <h4><fmt:message key="feature.test_drives"/></h4>
                    <table class="table" id="test-drive-table">
                        <thead>
                        <tr>
                            <th><fmt:message key="common.date"/></th>
                            <th><fmt:message key="feature.test_drives.notes"/></th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${driver.testDrives}" var="drive">
                            <tr class="clickable-row" data-href="/test-drives/detail/${drive.id}">
                                <td><fmt:formatDate value="${drive.date}" pattern="dd/MM/yyyy"/></td>
                                <td><c:out value="${drive.notes}"/></td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
                </c:if>
            </div>
        </div>
    </jsp:attribute>
    <jsp:attribute name="script">
        <script>
            $(document).ready(function () {
                $("#race-participations-table").DataTable(
                    {paging: false, searching: false, info: false, order: []}
                );
                $("#test-drive-table").DataTable(
                    {paging: false, searching: false, info: false, order: []}
                );
                $("#characteristics-value-table").DataTable(
                    {paging: false, searching: false, info: false, order: []}
                );
            });
        </script>
    </jsp:attribute>
</my:basepage>
