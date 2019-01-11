<%@ tag pageEncoding="utf-8" trimDirectiveWhitespaces="true" dynamic-attributes="attr" %>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ attribute name="action" required="true" %>
<%@ attribute name="authority" required="true" %>

<fmt:message key="common.delete.message" var="message"/>

<sec:authorize access="${authority}">
    <form:form action="${action}" onsubmit="return confirm('${message}')">
        <button class="btn btn-danger">
            <span class="glyphicon glyphicon-trash"></span>
            <fmt:message key="common.delete"/>
        </button>
    </form:form>
</sec:authorize>
