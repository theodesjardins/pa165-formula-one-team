<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="true" session="false" %>
<%@ page import="cz.muni.fi.pa165.entity.RaceParticipation" %>

<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="cc" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="forms" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<fmt:message key="feature.race.edit" var="title"/>

<my:pagetemplate title="${title}">

    <jsp:attribute name="body">
        <div class="container">
            <form:form id="race-participation-form"
                       action="/pa165/world-championship/submit"
                       modelAttribute="saveRaceParticipation">
                <form:hidden path="id"/>
                <div class="row">
                    <spring:bind path="carSetupId">
                        <div class="form-group col-md-6 col-xs-12 ${status.error ? 'has-error' : ''}">
                            <form:label path="carSetupId"><fmt:message key="feature.car"/></form:label>
                            <span class="text-danger">
                                <c:out value="${status.errorMessage}"/>
                            </span>
                            <form:select cssClass="form-control"
                                         path="carSetupId"
                                         items="${carSetups}"
                                         itemValue="id"
                                         itemLabel="id"/>
                        </div>
                    </spring:bind>
                    <spring:bind path="driverId">
                        <div class="form-group col-md-6 col-xs-12 ${status.error ? 'has-error' : ''}">
                            <form:label path="driverId"><fmt:message key="feature.driver"/></form:label>
                            <span class="text-danger">
                                <c:out value="${status.errorMessage}"/>
                            </span>
                            <form:select cssClass="form-control"
                                         path="driverId"
                                         items="${mainDrivers}"
                                         itemLabel="fullName"
                                         itemValue="id"/>
                        </div>
                    </spring:bind>
                    <spring:bind path="resultPosition">
                        <div class="form-group col-md-6 col-xs-12 ${status.error ? 'has-error' : ''}">
                            <form:label path="resultPosition"><fmt:message key="feature.race.result_position"/></form:label>
                            <span class="text-danger">
                                <c:out value="${status.errorMessage}"/>
                            </span>
                            <form:input cssClass="form-control"
                                        cssStyle="${saveRaceParticipation.resultPosition eq RaceParticipation.NO_RESULT_POSITION ? 'display: none;' : ''}"
                                        id="resultInput"
                                        path="resultPosition"/>
                            <br>
                            <label>
                                <input type="checkbox"
                                       id="resultCheckbox"
                                       <c:if test="${saveRaceParticipation.resultPosition eq RaceParticipation.NO_RESULT_POSITION}">checked</c:if>
                                />
                                <fmt:message key="feature.race.unfinished"/>
                            </label>
                        </div>
                    </spring:bind>
                </div>
                <div class="row">
                    <spring:bind path="raceDTO.title">
                        <div class="form-group col-md-6 col-xs-12 ${status.error ? 'has-error' : ''}">
                            <form:label path="raceDTO.title"><fmt:message key="common.title"/></form:label>
                            <span class="text-danger">
                                <c:out value="${status.errorMessage}"/>
                            </span>
                            <form:input cssClass="form-control" path="raceDTO.title"/>
                        </div>
                    </spring:bind>
                    <spring:bind path="raceDTO.location">
                        <div class="form-group col-md-6 col-xs-12 ${status.error ? 'has-error' : ''}">
                            <form:label path="raceDTO.location"><fmt:message key="feature.race.location"/></form:label>
                            <span class="text-danger">
                                <c:out value="${status.errorMessage}"/>
                            </span>
                            <form:input cssClass="form-control" path="raceDTO.location"/>
                        </div>
                    </spring:bind>
                    <spring:bind path="raceDTO.dateString">
                        <div class="form-group col-md-6 col-xs-12 ${status.error ? 'has-error' : ''}">
                            <form:label path="raceDTO.dateString"><fmt:message key="common.date"/></form:label>
                            <span class="text-danger">
                                <c:out value="${status.errorMessage}"/>
                            </span>
                            <form:input cssClass="form-control" id="datepicker" path="raceDTO.dateString"
                                        readonly="true" placeholder="dd/MM/yyyy"/>
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
                $("#resultCheckbox").click(function () {
                    let input = $("#resultInput");
                    if (this.checked) {
                        input.hide();
                        input.val("-1");
                    } else {
                        input.val("1");
                        input.show();
                    }
                });
            });
        </script>
    </jsp:attribute>

</my:pagetemplate>
