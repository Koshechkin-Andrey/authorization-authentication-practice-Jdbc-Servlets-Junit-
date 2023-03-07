<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>Login</title>
    <style>
        <%@include file="style/LoginPageStyle.css"%>
    </style>
</head>
<body>
<%@include file="Header.jsp" %>
<form class="contentForm" action="${pageContext.request.contextPath}/login" method="post">
    <label><fmt:message key="page.login.Email"/>
        <input type="text" name="email" placeholder="<fmt:message key="page.login.placeholder.Email"/>"><br>
    </label>

    <label><fmt:message key="page.login.Password"/>
        <input type="password" name="password" placeholder="<fmt:message key="page.login.placeholder.Password"/> ">
    </label><br>

    <button class="buttonSignIn" type="submit"><fmt:message key="page.login.ButtonSign"/></button>
    <br>

    <label>
        <a href="${pageContext.request.contextPath}/registrationUser">
            <button class="buttonRegistration" type="button"><fmt:message key="page.login.ButtonRegistration"/></button>
        </a>
    </label><br>

    <div class="loginFail">
        <c:if test="${param.error != null}">
            <span><fmt:message key="page.login.ErrorMessage"/> </span>
        </c:if>
    </div>
</form>
</body>
</html>
