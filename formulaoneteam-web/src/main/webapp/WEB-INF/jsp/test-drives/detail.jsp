<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="true" session="false" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<f:message var="title" key="feature.test_drives.detail"/>

<my:basepage title="${title}">
    <jsp:attribute name="body">
        <div class="container inner-container">
            <my:crudButtons id="${testDrive.id}" baseEntity="test-drives"/>
            <div class="row">
                <div class="col-md-6 col-xs-12">
                    <h4><f:message key="feature.test_drives.detail.title"/></h4>
                    <dl class="dl-horizontal">
                        <dt><f:message key="common.id"/>:</dt>
                        <dd><c:out value="${testDrive.id}"/></dd>
                        <dt><f:message key="common.date"/>:</dt>
                        <dd><fmt:formatDate value="${testDrive.date}" pattern="dd/MM/yyyy"/></dd>
                        <dt><f:message key="feature.test_drives.notes"/>:</dt>
                        <dd><c:out value="${testDrive.notes}"/></dd>
                    </dl>
                </div>
            </div>
            <div class="row">
                <div class="col">
                    <h4><f:message key="feature.car"/></h4>
                    <c:set var="carSetup" value="${testDrive.carSetup}"/>
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
            <div class="row" style=" padding-top: 20px;">
                <div class="col">
                    <h4><f:message key="feature.driver"/></h4>
                    <c:set var="driver" value="${testDrive.driver}"/>
                    <table class="table" id="drivers-table">
                        <thead>
                        <tr>
                            <th><f:message key="common.name"/></th>
                            <th><f:message key="common.email"/></th>
                            <th><f:message key="feature.drivers.status"/></th>
                        </tr>
                        </thead>
                        <tbody>
                        <tr class="clickable-row" data-href="/drivers/detail/${driver.id}">
                            <td><c:out value="${driver.fullName}"/></td>
                            <td><c:out value="${driver.email}"/></td>
                            <td><c:out value="${driver.driverStatus}"/></td>
                        </tr>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </jsp:attribute>
    <jsp:attribute name="script">
        <script>
            $(document).ready(function () {
                $.fn.dataTable.moment('DD/MM/YYYY');

                $("#car-table").DataTable({paging: false, searching: false, info: false, order: []});
                $("#drivers-table").DataTable({paging: false, searching: false, info: false, order: []});
            });
        </script>
    </jsp:attribute>
</my:basepage>
