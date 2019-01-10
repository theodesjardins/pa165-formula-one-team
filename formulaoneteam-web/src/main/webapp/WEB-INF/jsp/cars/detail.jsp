<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="true" session="false" %>
<%@ page import="cz.muni.fi.pa165.entity.RaceParticipation" %>

<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<f:message var="title" key="feature.car.detail"/>

<my:pagetemplate title="${title}${car.id}">
    <jsp:attribute name="body">
        <div class="container">
            <my:crudButtons id="${car.id}" baseEntity="cars"/>
            <div class="row">
                <div class="col-md-6 col-xs-12">
                    <h4><f:message key="feature.components"/></h4>
                    <dl class="dl-horizontal">
                        <dt><f:message key="feature.car.engine"/>:</dt>
                        <dd>
                            <my:a href="/components/detail/${car.engine.id}">
                                <c:out value="${car.engine.name}"/>
                            </my:a>
                        </dd>
                        <dt><f:message key="feature.car.suspension"/>:</dt>
                        <dd>
                            <my:a href="/components/detail/${car.suspension.id}">
                                <c:out value="${car.suspension.name}"/>
                            </my:a>
                        </dd>
                        <dt><f:message key="feature.car.brakes"/>:</dt>
                        <dd>
                            <my:a href="/components/detail/${car.brakes.id}">
                                <c:out value="${car.brakes.name}"/>
                            </my:a>
                        </dd>
                        <dt><f:message key="feature.car.transmission"/>:</dt>
                        <dd>
                            <my:a href="/components/detail/${car.transmission.id}">
                                <c:out value="${car.transmission.name}"/>
                            </my:a>
                        </dd>
                        <dt><f:message key="feature.car.tires"/>:</dt>
                        <dd>
                            <my:a href="/components/detail/${car.tires.id}">
                                <c:out value="${car.tires.name}"/>
                            </my:a>
                        </dd>
                        <dt><f:message key="feature.car.cover"/>:</dt>
                        <dd>
                            <my:a href="/components/detail/${car.cover.id}">
                                <c:out value="${car.cover.name}"/>
                            </my:a>
                        </dd>
                    </dl>
                </div>
            </div>
            <div class="row">
                <div class="col-md-6 col-xs-12">
                    <h4><f:message key="feature.car.races"/></h4>
                    <table class="table" id="races-table">
                        <thead>
                        <tr>
                            <th><f:message key="feature.race.title"/></th>
                            <th><f:message key="feature.driver"/></th>
                            <th><f:message key="feature.race.position"/></th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${car.raceParticipations}" var="raceParticipation">
                            <tr class="clickable-row" data-href="/world-championship/detail/${raceParticipation.id}">
                                <td><c:out value="${raceParticipation.race.title}"/></td>
                                <td>
                                    <my:a href="/drivers/detail/${raceParticipation.driver.id}">
                                        <c:out value="${raceParticipation.driver.fullName}"/>
                                    </my:a>
                                </td>
                                <td>
                                    <c:choose>
                                        <c:when test="${raceParticipation.resultPosition eq
                                        RaceParticipation.NO_RESULT_POSITION}">
                                            <fmt:message key="feature.race.unfinished"/>
                                        </c:when>
                                        <c:otherwise><c:out value="${raceParticipation.resultPosition}"/></c:otherwise>
                                    </c:choose>
                                </td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
            <div class="row">
                <h4><f:message key="feature.test_drives"/></h4>
                <table class="table">
                    <thead>
                    <tr>
                        <th><f:message key="common.id"/></th>
                        <th><f:message key="feature.driver"/></th>
                        <th><f:message key="common.date"/></th>
                        <th><f:message key="feature.test_drives.notes"/></th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${car.testDrives}" var="testDrive">
                        <tr class="clickable-row" data-href="/test-drives/detail/${testDrive.id}">
                            <td><c:out value="#${testDrive.id}"/></td>
                            <td>
                                <my:a href="/drivers/detail/${testDrive.driver.id}">
                                    <c:out value="${testDrive.driver.fullName}"/>
                                </my:a>
                            </td>
                            <td><fmt:formatDate value="${testDrive.date}" pattern="dd/MM/yyyy"/></td>
                            <td><c:out value="${testDrive.notes}"/></td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
    </jsp:attribute>

    <jsp:attribute name="script">
        <script>
            $(document).ready(function () {
                $("#races-table").DataTable({paging: false, searching: false, info: false, order: []});
            });
        </script>
    </jsp:attribute>

</my:pagetemplate>