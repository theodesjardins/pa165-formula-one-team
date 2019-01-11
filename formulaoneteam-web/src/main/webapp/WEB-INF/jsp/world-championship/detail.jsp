<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="true" session="false" %>
<%@ page import="cz.muni.fi.pa165.entity.RaceParticipation" %>

<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<my:pagetemplate title="${raceParticipation.race.title}">
    <jsp:attribute name="body">
        <div class="container">
            <my:crudButtons id="${raceParticipation.id}" baseEntity="world-championship"/>
            <div class="row">
                <div class="col-md-6 col-xs-12">
                    <h4><fmt:message key="feature.drivers.detail.info"/></h4>
                    <dl class="dl-horizontal">
                        <dt><fmt:message key="common.first_name"/>:</dt>
                        <dd><c:out value="${raceParticipation.driver.name}"/></dd>
                        <dt><fmt:message key="common.surname"/>:</dt>
                        <dd><c:out value="${raceParticipation.driver.surname}"/></dd>
                        <dt><fmt:message key="common.email"/>:</dt>
                        <dd><c:out value="${raceParticipation.driver.email}"/></dd>
                        <dt><fmt:message key="common.nationality"/>:</dt>
                        <dd><c:out value="${raceParticipation.driver.nationality}"/></dd>
                        <dt><fmt:message key="common.birthday"/>:</dt>
                        <dd><fmt:formatDate value="${raceParticipation.driver.birthday}" pattern="dd/MM/YYYY"/></dd>
                        <dt><fmt:message key="feature.drivers.status"/>:</dt>
                        <dd><c:out value="${raceParticipation.driver.driverStatus}"/></dd>
                    </dl>
                </div>
            </div>
            <div class="row">
                <div class="col-md-6 col-xs-12">
                    <h4><fmt:message key="feature.car.race"/></h4>
                    <table class="table" id="race-table">
                        <thead>
                        <tr>
                            <th><fmt:message key="common.title"/></th>
                            <th><fmt:message key="feature.race.result"/></th>
                            <th><fmt:message key="feature.race.result"/></th>
                        </tr>
                        </thead>
                        <tbody>
                        <td><c:out value="${raceParticipation.race.title}"/></td>
                        <td>
                            <c:choose>
                                <c:when test="${raceParticipation.resultPosition eq RaceParticipation. NO_RESULT_POSITION}">
                                    <fmt:message key="feature.race.unfinished"/>
                                </c:when>
                                <c:otherwise><c:out value="${raceParticipation.resultPosition}"/></c:otherwise>
                            </c:choose>
                        </td>
                        <td><fmt:formatDate value="${raceParticipation.race.date}" pattern="dd/MM/YYYY"/></td>
                        </tbody>
                    </table>
                </div>
            </div>
            <div class="row">
                <h4><f:message key="feature.car"/></h4>
                <c:set var="carSetup" value="${raceParticipation.carSetup}"/>
                <table class="table" id="car-table">
                    <thead>
                    <tr>
                        <th><f:message key="common.id"/></th>
                        <th><f:message key="feature.car.engine"/></th>
                        <th><f:message key="feature.car.suspension"/></th>
                        <th><f:message key="feature.car.brakes"/></th>
                        <th><f:message key="feature.car.transmission"/></th>
                        <th><f:message key="feature.car.tires"/></th>
                        <th><f:message key="feature.car.cover"/></th>
                    </tr>
                    </thead>
                    <tbody>
                    <tr class="clickable-row" data-href="/cars/detail/${carSetup.id}">
                        <td><c:out value="${carSetup.id}"/></td>
                        <td>
                            <my:a href="/components/detail/${carSetup.engine.id}">
                                <c:out value="${carSetup.engine.name}"/>
                            </my:a>
                        </td>
                        <td>
                            <my:a href="/components/detail/${carSetup.suspension.id}">
                                <c:out value="${carSetup.suspension.name}"/>
                            </my:a>
                        </td>
                        <td>
                            <my:a href="/components/detail/${carSetup.brakes.id}">
                                <c:out value="${carSetup.brakes.name}"/>
                            </my:a>
                        </td>
                        <td>
                            <my:a href="/components/detail/${carSetup.transmission.id}">
                                <c:out value="${carSetup.transmission.name}"/>
                            </my:a>
                        </td>
                        <td>
                            <my:a href="/components/detail/${carSetup.tires.id}">
                                <c:out value="${carSetup.tires.name}"/>
                            </my:a>
                        </td>
                        <td>
                            <my:a href="/components/detail/${carSetup.cover.id}">
                                <c:out value="${carSetup.cover.name}"/>
                            </my:a>
                        </td>
                    </tr>
                    </tbody>
                </table>
            </div>
        </div>
    </jsp:attribute>
</my:pagetemplate>
