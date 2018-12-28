<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="true" session="false" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<f:message var="title" key="feature.drivers"/>

<my:pagetemplate title="${title}">
<jsp:attribute name="head">
    <link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.10.19/css/dataTables.bootstrap.min.css"/>
</jsp:attribute>
    <jsp:attribute name="body">
    <div class="container">
        <sec:authorize access="hasAuthority('ADMIN')">
            <div class="row">
                    <my:a href="/drivers/create" class="btn btn-primary pull-right">
                        <span class="glyphicon glyphicon-plus"></span>
                        Add driver
                    </my:a>
            </div>
        </sec:authorize>
        <table class="table" id="drivers-table">
            <thead>
            <tr>
                <th><f:message key="feature.drivers.name"/></th>
                <th><f:message key="feature.drivers.email"/></th>
                <th><f:message key="feature.drivers.status"/></th>
                <th>current races</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${drivers}" var="driver">
                    <tr class="clickable-row" data-href="/drivers/detail/${driver.id}">
                        <td><c:out value="${driver.fullName}"/></td>
                        <td><c:out value="${driver.email}"/></td>
                        <td><c:out value="${driver.driverStatus}"/></td>
                        <td>
                            <c:forEach items="${driver.raceParticipations}" var="participation">
                                <my:a href="/world-championship/detail/${participation.id}">${participation.race.title}</my:a><br/>
                            </c:forEach>
                        </td>
                    </tr>

                </c:forEach>
            </tbody>
        </table>
    </div>

</jsp:attribute>
    <jsp:attribute name="script">
    <script type="text/javascript" charset="utf8"
            src="https://cdn.datatables.net/1.10.19/js/jquery.dataTables.js"></script>
    <script>
        $(document).ready(function () {
            $("#drivers-table").DataTable(
                {
                    paging: false,
                    searching: false,
                    info: false,
                    order: []
                }
            );
        });
    </script>
</jsp:attribute>
</my:pagetemplate>
