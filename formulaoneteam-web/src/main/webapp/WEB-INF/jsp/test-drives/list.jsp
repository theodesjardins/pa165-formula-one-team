<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="true" session="false" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<f:message var="title" key="feature.test_drives"/>

<my:basepage title="${title}">
    <jsp:attribute name="body">
        <div class="container inner-container">
            <my:addButton baseEntity="test-drives" authority="${authority}"/>
            <table class="table" id="test-drives-table">
                <thead>
                <tr>
                    <th><f:message key="common.id"/></th>
                    <th><f:message key="feature.driver"/></th>
                    <th><f:message key="feature.car"/></th>
                    <th><f:message key="common.date"/></th>
                    <th><f:message key="feature.test_drives.notes"/></th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${testDrives}" var="testDrive">
                    <tr class="clickable-row" data-href="/test-drives/detail/${testDrive.id}">
                        <td><c:out value="${testDrive.id}"/></td>
                        <td>
                            <my:a href="/drivers/detail/${testDrive.driver.id}">
                                <c:out value="${testDrive.driver.fullName}"/>
                            </my:a>
                        </td>
                        <td>
                            <my:a href="/cars/detail/${testDrive.carSetup.id}">
                                #<c:out value="${testDrive.carSetup.id}"/>
                            </my:a>
                        </td>
                        <td><fmt:formatDate value="${testDrive.date}" pattern="dd/MM/yyyy"/></td>
                        <td><c:out value="${testDrive.notes}"/></td>
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
                $("#test-drives-table").DataTable({paging: false, searching: false, info: false, order: []});
            });
        </script>
    </jsp:attribute>
</my:basepage>
