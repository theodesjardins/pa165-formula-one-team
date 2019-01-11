<%@ tag pageEncoding="utf-8" trimDirectiveWhitespaces="true" dynamic-attributes="attr" %>

<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ attribute name="baseEntity" required="true" %>
<%@ attribute name="authority" required="false" %>

<c:if test="${empty authority}">
    <sec:authorize access="hasAuthority('ADMIN')" var="authority"/>
</c:if>

<fmt:message key="common.update" var="text"/>

<sec:authorize access="${authority}">
    <div class="row">
        <my:a href="/${baseEntity}/create" class="btn btn-primary pull-right">
            <span class="glyphicon glyphicon-plus"></span>
            <fmt:message key="common.add"/>
        </my:a>
    </div>
</sec:authorize>
