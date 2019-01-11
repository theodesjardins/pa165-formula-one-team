<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="true" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<sec:authorize access="hasAuthority('ADMIN') || hasAuthority('ENGINEER')" var="authority"/>

<f:message var="title" key="feature.components"/>

<my:pagetemplate title="${title}">
    <jsp:attribute name="body">
        <div class="container">
            <my:addButton baseEntity="components" authority="${authority}"/>
        </div>

        <table class="table" id="components-table">
            <thead>
            <tr>
                <th><f:message key="common.name"/></th>
                <th><f:message key="feature.components.type"/></th>
                <th><f:message key="feature.components.parameters"/></th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${components}" var="component">
                    <tr class="clickable-row" data-href="/components/detail/${component.id}">
                        <td><c:out value="${component.name}"/></td>
                        <td><c:out value="${component.type}"/></td>
                        <td>
                            <c:forEach items="${component.parameters}" var="parameter">
                                <c:out value="${parameter.name}"/>
                                <br>
                            </c:forEach>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </jsp:attribute>

    <jsp:attribute name="script">
        <script>
            $(document).ready(function () {
                $("#components-table").DataTable({paging: false, searching: false, info: false, order: []});
            });
        </script>
    </jsp:attribute>
</my:pagetemplate>
