<%@ tag pageEncoding="utf-8" trimDirectiveWhitespaces="true" dynamic-attributes="attr" %>

<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ attribute name="id" required="true" %>
<%@ attribute name="baseEntity" required="true" %>
<%@ attribute name="authority" required="false" %>

<c:if test="${empty authority}">
    <sec:authorize access="hasAuthority('ADMIN')" var="authority"/>
</c:if>

<fmt:message key="common.delete.message" var="message"/>

<div class="row">
    <div class="pull-right">
        <my:updateButton updatePage="/${baseEntity}/edit/${id}" authority="${authority}"/>
        <my:deleteButton action="/pa165/${baseEntity}/delete/${id}" authority="${authority}"/>
    </div>
</div>
