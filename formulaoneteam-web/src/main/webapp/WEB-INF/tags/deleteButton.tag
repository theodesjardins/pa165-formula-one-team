<%@ tag pageEncoding="utf-8" trimDirectiveWhitespaces="true" dynamic-attributes="attr" %>
<%@ attribute name="action" required="true" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<fmt:message key="common.delete.message" var="message"/>

<sec:authorize access="hasAuthority('ADMIN')">
    <form:form action="${action}" onsubmit="return confirm('${message}')">
        <button class="btn btn-danger">
            <span class="glyphicon glyphicon-trash"></span>
            <fmt:message key="common.delete"/>
        </button>
    </form:form>
</sec:authorize>