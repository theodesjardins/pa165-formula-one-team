<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="true" session="false" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="cc" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="forms" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>

<f:message var="title" key="feature.components.edit"/>

<my:pagetemplate title="${title}">
    <jsp:attribute name="body">
        <div class="container">
            <form:form action="/pa165/components/submit" modelAttribute="component">
                <form:hidden path="id"/>
                <div class="row">
                    <spring:bind path="name">
                        <div class="form-group col-md-6 col-xs-12 ${status.error ? 'has-error' : ''}">
                            <form:label path="name"><fmt:message key="common.name"/></form:label>
                            <span class="text-danger"><c:out value="${status.errorMessage}"/></span>
                            <form:input cssClass="form-control" path="name"/>
                        </div>
                    </spring:bind>
                    <spring:bind path="type">
                        <div class="form-group col-md-6 col-xs-12 ${status.error ? 'has-error' : ''}">
                            <form:label path="type"><fmt:message key="feature.components.type"/></form:label>
                            <span class="text-danger"><c:out value="${status.errorMessage}"/></span>
                            <form:select cssClass="form-control"
                                         path="type"
                                         items="${componentTypeValues}"
                                         itemLabel="type"/>
                        </div>
                    </spring:bind>
                </div>
                <div class="row">
                    <h4><fmt:message key="feature.components.parameters"/></h4>
                    <c:forEach items="${component.parameters}" var="parameter" varStatus="status">
                        <form:hidden path="parameters[${status.index}].id"/>
                        <label><fmt:message key="common.name"/></label>
                        <form:input cssClass="form-control" path="parameters[${status.index}].name"/>
                        <label><fmt:message key="common.value"/></label>
                        <form:input cssClass="form-control" path="parameters[${status.index}].value"/>
                        <span class="text-danger">
                            <form:errors path="parameters[${status.index}].value"/>
                        </span>
                        <br>
                    </c:forEach>
                </div>
                <div class="row">
                    <button type="submit" class="btn btn-primary pull-right">
                        <span class="glyphicon glyphicon-floppy-disk"></span>
                        <fmt:message key="common.save"/>
                    </button>
                </div>
            </form:form>
        </div>
    </jsp:attribute>
    <jsp:attribute name="script">
        <script>
            $(document).ready(function () {
                $("#datepicker").datepicker({dateFormat: 'dd/mm/yy'});
            });
        </script>
    </jsp:attribute>
</my:pagetemplate>
