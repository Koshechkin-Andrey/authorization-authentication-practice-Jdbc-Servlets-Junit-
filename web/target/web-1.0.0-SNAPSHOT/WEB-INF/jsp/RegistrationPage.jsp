<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Koshechkin
  Date: 05.02.2023
  Time: 21:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>Title</title>
    <style>
        <%@include file="style/RegistrationPageStyle.css"%>
    </style>
</head>
<body>
<%@include file="Header.jsp" %>
<form class="contentRegistrationForm" action="${pageContext.request.contextPath}/registrationUser" method="post">
    <div><fmt:message key="page.registration.Username"/> </div>
    <label>
        <input type="text" name="username" placeholder="<fmt:message key="page.registration.Username"/> ">
    </label>

    <div><fmt:message key="page.registration.Birthday"/> </div>
    <label>
        <input type="date" name="birthday">
    </label>

    <div><fmt:message key="page.registration.Email"/> </div>
    <label>
        <input type="text" name="email" placeholder="<fmt:message key="page.registration.Email"/> ">
    </label>

    <div><fmt:message key="page.registration.Password"/></div>
    <label>
        <input type="password" name="password" placeholder="<fmt:message key="page.registration.Password"/> ">
    </label>

    <div><fmt:message key="page.registration.Gender"/> </div>
    <select name="gender">
        <c:forEach var="gender" items="${requestScope.gender}">
            <option label="${gender}">${gender}</option>
        </c:forEach>
    </select><br>

    <div><fmt:message key="page.registration.Country"/> </div>
        <select name="country">
        <c:forEach var="country" items="${requestScope.country}">
            <option label="${country}">${country}</option>
        </c:forEach>
    </select><br>
    <button class="registrationButtonSubmit" type="submit">Registration</button>


    <c:if test="${not empty requestScope.error}">
        <div class="failRegistrationMessage">
            <c:forEach var="error" items="${requestScope.error}">
                <span >${error.message}</span><br>
            </c:forEach>
        </div>
    </c:if>
</form>
</body>
</html>
