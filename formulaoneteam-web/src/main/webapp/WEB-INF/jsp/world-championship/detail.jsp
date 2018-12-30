<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="true" session="false" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>

<my:pagetemplate title="World Championship detail ${raceParticipation.race.title}">
    <jsp:attribute name="body">
        <div class="container">
            <div class="row">
                <my:a href="/world-championship/edit/${raceParticipation.id}" class="btn btn-primary pull-right">
                    <span class="glyphicon glyphicon-pencil"></span>
                    <fmt:message key="common.update"/>
                </my:a>
            </div>
            <div class="row">
                <div class="col-md-6 col-xs-12">
                    <h4>Driver information</h4>
                    <dl class="dl-horizontal">
                        <dt>First name:</dt>
                        <dd><c:out value="${raceParticipation.driver.name}"/></dd>
                        <dt>Surname:</dt>
                        <dd><c:out value="${raceParticipation.driver.surname}"/></dd>
                        <dt>Email:</dt>
                        <dd><c:out value="${raceParticipation.driver.email}"/></dd>
                        <dt>Nationality:</dt>
                        <dd><c:out value="${raceParticipation.driver.nationality}"/></dd>
                        <dt>Birthday:</dt>
                        <dd><fmt:formatDate value="${raceParticipation.driver.birthday}" pattern="dd/MM/YYYY"/></dd>
                        <dt>Status:</dt>
                        <dd><c:out value="${raceParticipation.driver.driverStatus}"/></dd>
                    </dl>
                </div>
            </div>
            <div class="row">
                <div class="col-md-6 col-xs-12">
                    <h4>Race</h4>
                    <table class="table" id="race-table">
                        <thead>
                        <tr>
                            <th>Date</th>
                            <th>Title</th>
                            <th>Result</th>
                        </tr>
                        </thead>
                        <tbody>
                        <td><c:out value="${raceParticipation.race.title}"/></td>
                        <td><c:out value="${raceParticipation.resultPosition}"/></td>
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
                        <th><f:message key="feature.car.table.engine"/></th>
                        <th><f:message key="feature.car.table.suspension"/></th>
                        <th><f:message key="feature.car.table.brakes"/></th>
                        <th><f:message key="feature.car.table.transmission"/></th>
                        <th><f:message key="feature.car.table.tires"/></th>
                        <th><f:message key="feature.car.table.cover"/></th>
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
