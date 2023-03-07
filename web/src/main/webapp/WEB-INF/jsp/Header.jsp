<%--
  Created by IntelliJ IDEA.
  User: Koshechkin
  Date: 10.02.2023
  Time: 19:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>Title</title>
    <style>
        <%@include file="style/HeaderStyle.css"%>
    </style>
</head>
<body>

<div class="headerContent">

    <c:if test="${sessionScope.user == null}">
        <a href="${pageContext.request.contextPath}/login">
            <button class="loginButton" type="button">Login</button>
        </a>
    </c:if>

    <form class="formLocale" action="${pageContext.request.contextPath}/locale" method="post">
        <button class="localeButton" type="submit" name="locale" value="ru_RU">RU</button>
        <button class="localeButton" type="submit" name="locale" value="en_US">EN</button>

        <fmt:setLocale
                value="${sessionScope.locale != null ? sessionScope.locale : (param.locale != null ? param.locale : 'en_US')}"/>
        <fmt:setBundle basename="translation"/>
    </form>

    <c:if test="${not empty sessionScope.user}">
        <form class="formExit" action="${pageContext.request.contextPath}/logout" method="post">
            <button class="logoutButton" type="submit">Exit</button>
        </form>
    </c:if>

</div>

</body>
</html>
