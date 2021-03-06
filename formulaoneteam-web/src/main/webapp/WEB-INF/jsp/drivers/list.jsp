<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="true" session="false" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<f:message var="title" key="feature.drivers"/>

<my:basepage title="${title}">
    <jsp:attribute name="body">
    <div class="container inner-container">
        <my:addButton baseEntity="drivers" authority="${authority}"/>
        <table class="table" id="drivers-table">
            <thead>
            <tr>
                <th><f:message key="common.name"/></th>
                <th><f:message key="common.email"/></th>
                <th><f:message key="feature.drivers.status"/></th>
                <th><f:message key="feature.drivers.current_races"/></th>
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
                                <my:a href="/world-championship/detail/${participation.id}">
                                    ${participation.race.title}
                                </my:a>
                                <br/>
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
                $.fn.dataTable.moment('DD/MM/YYYY');
                $("#drivers-table").DataTable({paging: false, searching: false, info: false, order: []});
            });
        </script>
</jsp:attribute>
</my:basepage>
