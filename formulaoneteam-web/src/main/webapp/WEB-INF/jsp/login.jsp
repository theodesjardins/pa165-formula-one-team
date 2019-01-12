<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="true" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>

<f:message var="title" key="feature.auth.sign_in"/>

<my:basepage title="${title}">
    <jsp:attribute name="body">

    <div class="jumbotron" id="login-form">
        <div class="container">
            <div class="col-md-5 offset-md-3">
                <form method="POST" role="form">
                    <div class="form-group">
                        <label for="username"><f:message key="common.email"/>:</label>
                        <input type="text" name="username" id="username" class="form-control" required/>
                    </div>
                    <div class="form-group">
                        <label for="password"><f:message key="feature.auth.form.password"/>:</label>
                        <input type="password" name="password" id="password" class="form-control" required/>
                    </div>
                    <c:if test="${param.error != null}">
                        <div class="alert alert-danger" role="alert">
                            <f:message key="feature.auth.form.error"/>
                        </div>
                    </c:if>
                    <div class="form-actions">
                        <button type="submit" value="Log In" class="btn btn-primary">
                            <f:message key="feature.auth.sign_in"/>
                        </button>
                    </div>
                </form>
                <br>
            </div>
        </div>
    </div>

</jsp:attribute>
</my:basepage>
