<%@ tag pageEncoding="utf-8" trimDirectiveWhitespaces="true" dynamic-attributes="attr" %>

<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ attribute name="updatePage" required="true" %>
<%@ attribute name="authority" required="true"%>

<fmt:message key="common.update" var="text"/>

<sec:authorize access="${authority}">
    <my:a href="${updatePage}" class="btn btn-primary pull-right">
        <span class="glyphicon glyphicon-pencil"></span>
        <f:message key="common.update"/>
    </my:a>
</sec:authorize>
