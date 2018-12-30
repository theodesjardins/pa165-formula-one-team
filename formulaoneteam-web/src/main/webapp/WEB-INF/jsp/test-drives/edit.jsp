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

<f:message var="title" key="feature.edit.test_drive"/>

<my:pagetemplate title="${title}">
    <jsp:attribute name="head">
        <link rel="stylesheet" href="https://code.jquery.com/ui/1.12.1/themes/base/jquery-ui.min.css">
    </jsp:attribute>
    <jsp:attribute name="body">
        <div class="container">
            <form:form action="/pa165/test-drives/submit" modelAttribute="saveTestDrive">
                <form:hidden path="id"/>
                <div class="row">
                    <spring:bind path="formattedDate">
                        <div class="form-group col-md-6 col-xs-12 ${status.error ? 'has-error' : ''}">
                            <form:label path="formattedDate"><f:message key="common.date"/>:</form:label>
                            <form:input cssClass="form-control" id="datepicker" placeholder="dd/MM/yyyy"
                                        path="formattedDate" readonly="true"/>
                            <span class="text-danger">
                                <c:out value="${status.errorMessage}"/>
                            </span>
                        </div>
                    </spring:bind>
                    <spring:bind path="notes">
                        <div class="form-group col-md-6 col-xs-12 ${status.error ? 'has-error' : ''}">
                            <form:label path="notes"><f:message key="feature.test_drives.notes"/></form:label>
                            <span class="text-danger">
                                <c:out value="${status.errorMessage}"/>
                            </span>
                            <form:input cssClass="form-control" path="notes"/>
                        </div>
                    </spring:bind>
                </div>
                <div class="row">
                    <spring:bind path="carSetupId">
                        <div class="form-group col-md-6 col-xs-12">
                            <form:label path="carSetupId"><f:message key="feature.car"/></form:label>
                            <form:select cssClass="form-control"
                                         path="carSetupId"
                                         items="${cars}"
                                         itemValue="id"
                                         itemLabel="id"/>
                        </div>
                    </spring:bind>
                    <spring:bind path="driverId">
                        <div class="form-group col-md-6 col-xs-12">
                            <form:label path="driverId"><f:message key="feature.driver"/></form:label>
                            <form:select cssClass="form-control"
                                         path="driverId"
                                         items="${drivers}"
                                         itemValue="id"
                                         itemLabel="fullName"/>
                        </div>
                    </spring:bind>
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
