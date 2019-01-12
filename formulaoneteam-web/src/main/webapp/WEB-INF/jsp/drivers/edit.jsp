<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="true" session="false" %>

<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="cc" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="forms" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<fmt:message key="feature.driver" var="title"/>

<my:basepage title="${title}">
    <jsp:attribute name="body">
        <div class="container inner-container">
            <form:form action="/pa165/drivers/submit" modelAttribute="driver">
                <form:hidden path="id"/>
                <div class="row">
                <spring:bind path="name">
                    <div class="form-group col-md-6 col-xs-12 ${status.error ? 'has-error' : ''}">
                        <form:label path="name"><fmt:message key="common.name"/></form:label>
                        <span class="text-danger">
                            <c:out value="${status.errorMessage}"/>
                        </span>
                        <form:input cssClass="form-control" path="name"/>
                    </div>
                </spring:bind>
                    <spring:bind path="surname">
                    <div class="form-group col-md-6 col-xs-12 ${status.error ? 'has-error' : ''}">
                        <form:label path="surname"><fmt:message key="common.surname"/></form:label>
                        <span class="text-danger">
                            <c:out value="${status.errorMessage}"/>
                        </span>
                        <form:input cssClass="form-control" path="surname"/>
                    </div>
                </spring:bind>
                </div>
                <div class="row">
                <spring:bind path="email">
                    <div class="form-group col-md-6 col-xs-12 ${status.error ? 'has-error' : ''}">
                        <form:label path="email"><fmt:message key="common.email"/></form:label>
                        <span class="text-danger">
                            <c:out value="${status.errorMessage}"/>
                        </span>
                        <form:input cssClass="form-control" path="email"/>
                    </div>
                </spring:bind>
                    <spring:bind path="nationality">
                    <div class="form-group col-md-6 col-xs-12 ${status.error ? 'has-error' : ''}">
                        <form:label path="nationality"><fmt:message key="common.nationality"/></form:label>
                        <span class="text-danger">
                            <c:out value="${status.errorMessage}"/>
                        </span>
                        <form:input cssClass="form-control" path="nationality"/>
                    </div>
                </spring:bind>
                </div>
                <div class="row">
                    <div class="form-group col-md-6 col-xs-12">
                        <form:label path="driverStatus"><fmt:message key="feature.drivers.status"/></form:label>
                        <form:select cssClass="form-control" path="driverStatus" items="${driverStatusValues}"
                                     itemLabel="displayName"/>
                    </div>
                    <spring:bind path="birthdayString">
                        <div class="form-group col-md-6 col-xs-12 ${status.error ? 'has-error' : ''}">
                            <form:label path="birthdayString"><fmt:message key="common.birthday"/>:</form:label>
                            <span class="text-danger">
                                <c:out value="${status.errorMessage}"/>
                            </span>
                            <form:input cssClass="form-control" id="datepicker" placeholder="dd/MM/yyyy"
                                        path="birthdayString" readonly="true"/>
                        </div>
                    </spring:bind>
                </div>
                <div class="row">
                <spring:bind path="password">
                    <div class="form-group col-md-6 col-xs-12 ${status.error ? 'has-error' : ''}">
                        <form:label path="password"><fmt:message key="feature.auth.form.password"/></form:label>
                        <span class="text-danger">
                            <c:out value="${status.errorMessage}"/>
                        </span>
                        <form:password cssClass="form-control" path="password"/>
                    </div>
                </spring:bind>
                    <spring:bind path="confirmPassword">
                    <div class="form-group col-md-6 col-xs-12 ${status.error ? 'has-error' : ''}">
                        <form:label path="confirmPassword"><fmt:message key="feature.auth.form.confirm_password"/></form:label>
                        <span class="text-danger">
                            <c:out value="${status.errorMessage}"/>
                        </span>
                        <form:password cssClass="form-control" path="confirmPassword"/>
                    </div>
                </spring:bind>
                </div>
                <div class="row">
                    <h4><fmt:message key="feature.drivers.characteristics"/></h4>
                    <c:forEach items="${driver.characteristics}" var="characteristicValue" varStatus="status">
                        <div class="form-group col-md-6 col-xs-12">
                            <label for="characteristics[${status.index}].value">
                                <c:out value="${characteristicValue.type.displayName}"/>
                            </label>
                            <span class="text-danger">
                                <form:errors path="characteristics[${status.index}].value"/>
                            </span>
                            <form:hidden path="characteristics[${status.index}].id"/>
                            <input type="hidden"
                                   name="characteristics[${status.index}].type"
                                   value="${characteristicValue.type.name()}"/>
                            <form:input cssClass="form-control" path="characteristics[${status.index}].value"/>
                        </div>
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
</my:basepage>
