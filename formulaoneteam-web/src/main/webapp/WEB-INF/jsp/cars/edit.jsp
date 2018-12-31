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

<c:set var="title">
    <fmt:message key="${car.id==0?'feature.car.create':'feature.car.edit'}" />
</c:set>

<my:pagetemplate title="${title}">
    <jsp:attribute name="body">
        <div class="container">
            <form:form action="/pa165/cars/submit" modelAttribute="car">
                <form:hidden path="id" />

                <div class="form-group">
                    <label for="engine-select">
                        <f:message key="feature.car.engine" />:</label>
                    <select class="form-control" id="engine-select" name="engineId">
                        <c:forEach items="${engines}" var="engine">
                            <option value="${engine.id}" ${car.engine.id == engine.id ? 'selected' : ''}>
                                <c:out value="${engine.name}" />
                            </option>
                        </c:forEach>
                    </select>
                </div>
                <div class="form-group">
                    <label for="suspension-select">
                        <f:message key="feature.car.suspension" />:</label>
                    <select class="form-control" id="suspension-select" name="suspensionId">
                        <c:forEach items="${suspensions}" var="suspension">
                            <option value="${suspension.id}" ${car.suspension.id == suspension.id ? 'selected' : ''}>
                                <c:out value="${suspension.name}" />
                            </option>
                        </c:forEach>
                    </select>
                </div>
                <div class="form-group">
                    <label for="brakes-select">
                        <f:message key="feature.car.brakes" />:</label>
                    <select class="form-control" id="brakes-select" name="brakesId">
                        <c:forEach items="${brakes}" var="brake">
                            <option value="${brake.id}" ${car.brakes.id == brake.id ? 'selected' : ''}>
                                <c:out value="${brake.name}" />
                            </option>
                        </c:forEach>
                    </select>
                </div>
                <div class="form-group">
                    <label for="transmission-select">
                        <f:message key="feature.car.transmission" />:</label>
                    <select class="form-control" id="transmission-select" name="transmissionId">
                        <c:forEach items="${transmissions}" var="transmission">
                            <option value="${transmission.id}" ${car.transmission.id == transmission.id ? 'selected' : ''}>
                                <c:out value="${transmission.name}" />
                            </option>
                        </c:forEach>
                    </select>
                </div>
                <div class="form-group">
                    <label for="tires-select">
                        <f:message key="feature.car.tires" />:</label>
                    <select class="form-control" id="tires-select" name="tiresId">
                        <c:forEach items="${tires}" var="tire">
                            <option value="${tire.id}" ${car.tires.id == tire.id ? 'selected' : ''}>
                                <c:out value="${tire.name}" />
                            </option>
                        </c:forEach>
                    </select>
                </div>
                <div class="form-group">
                    <label for="cover-select">
                        <f:message key="feature.car.cover" />:</label>
                    <select class="form-control" id="cover-select" name="coverId">
                        <c:forEach items="${covers}" var="cover">
                            <option value="${cover.id}" ${car.cover.id == cover.id ? 'selected' : ''}>
                                <c:out value="${cover.name}" />
                            </option>
                        </c:forEach>
                    </select>
                </div>

                <div class="row">
                    <button type="submit" class="btn btn-primary pull-right">
                        <span class="glyphicon glyphicon-floppy-disk"></span>
                        <fmt:message key="common.save" />
                    </button>
                </div>
            </form:form>
        </div>
    </jsp:attribute>
</my:pagetemplate>