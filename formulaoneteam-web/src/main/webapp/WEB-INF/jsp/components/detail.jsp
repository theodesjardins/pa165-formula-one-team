<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="true" session="false" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<sec:authorize access="hasAuthority('ADMIN') || hasAuthority('ENGINEER')" var="authority"/>
<fmt:message key="feature.components.detail.title" var="title"/>

<my:basepage title="${title} ${component.name}">
    <jsp:attribute name="body">
        <div class="container inner-container">
            <my:crudButtons id="${component.id}" baseEntity="components" authority="${authority}"/>
            <div class="row">
                <div class="col-md-6 col-xs-12">
                    <h4><fmt:message key="feature.components.information"/></h4>
                    <dl class="dl-horizontal">
                        <dt><fmt:message key="feature.components.type"/>:</dt>
                        <dd><c:out value="${component.type}"/></dd>
                        <dt><fmt:message key="common.name"/>:</dt>
                        <dd><c:out value="${component.name}"/></dd>
                    </dl>
                </div>
                <div class="col-md-6 col-xs-12">
                    <h4><fmt:message key="feature.components.parameters"/></h4>
                    <table class="table" id="parameters-table">
                        <thead>
                        <tr>
                            <th><fmt:message key="common.name"/></th>
                            <th><fmt:message key="common.value"/></th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${component.parameters}" var="parameter">
                            <tr>
                                <td><c:out value="${parameter.name}"/></td>
                                <td><c:out value="${parameter.value}"/></td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </jsp:attribute>
    <jsp:attribute name="script">
            <script>
                $(document).ready(function () {
                    $("#parameters-table").DataTable({paging: false, searching: false, info: false, order: []});
                });
            </script>
    </jsp:attribute>
</my:basepage>
