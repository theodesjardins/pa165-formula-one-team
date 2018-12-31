<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="true" session="false" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<my:pagetemplate title="Component detail ${component.name}">
    <jsp:attribute name="body">
        <div class="container">
            <sec:authorize access="hasAuthority('ADMIN') || hasAuthority('ENGINEER')">
                <div class="row">
                    <div class="pull-right">
                        <my:a href="/components/edit/${component.id}" class="btn btn-primary">
                            <span class="glyphicon glyphicon-pencil"></span>
                            <fmt:message key="common.update"/>
                        </my:a>
                        <my:deleteButton action="/pa165/components/delete/${component.id}"/>
                    </div>
                </div>
            </sec:authorize>
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
</my:pagetemplate>