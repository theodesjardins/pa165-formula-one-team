<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="true" session="false" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<f:message var="title" key="feature.cars"/>

<my:basepage title="${title}">
    <jsp:attribute name="body">
        <div class="container inner-container">
            <my:addButton baseEntity="cars"/>
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
                    <th><f:message key="feature.car.races"/></th>
                    <th><f:message key="feature.test_drives"/></th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${cars}" var="car">
                        <tr class="clickable-row" data-href="/cars/detail/${car.id}">
                            <td>#<c:out value="${car.id}"/></td>
                            <td>
                                <my:a href="/components/detail/${car.engine.id}">
                                    <c:out value="${car.engine.name}"/>
                                </my:a>
                            </td>
                            <td>
                                <my:a href="/components/detail/${car.suspension.id}">
                                    <c:out value="${car.suspension.name}"/>
                                </my:a>
                            </td>
                            <td>
                                <my:a href="/components/detail/${car.brakes.id}">
                                    <c:out value="${car.brakes.name}"/>
                                </my:a>
                            </td>
                            <td>
                                <my:a href="/components/detail/${car.transmission.id}">
                                    <c:out value="${car.transmission.name}"/>
                                </my:a>
                            </td>
                            <td>
                                <my:a href="/components/detail/${car.engine.id}">
                                    <c:out value="${car.tires.name}"/>
                                </my:a>
                            </td>
                            <td>
                                <my:a href="/components/detail/${car.cover.id}">
                                    <c:out value="${car.cover.name}"/>
                                </my:a>
                            </td>
                            <td>
                                <c:forEach items="${car.raceParticipations}" var="raceParticipation">
                                    <my:a href="/world-championship/detail/${raceParticipation.id}">
                                        <c:out value="${raceParticipation.race.title}"/>
                                    </my:a>
                                    <br>
                                </c:forEach>
                            </td>
                            <td>
                                <c:forEach items="${car.testDrives}" var="testDrive">
                                    <my:a href="/test-drives/detail/${testDrive.id}">
                                        <c:out value="#${testDrive.id}"/>
                                    </my:a>
                                    <br>
                                </c:forEach>
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
                $("#car-table").DataTable({paging: false, searching: false, info: false, order: []});

                $("#car-table_wrapper").css("overflow", "auto");
            });
        </script>
    </jsp:attribute>
</my:basepage>
