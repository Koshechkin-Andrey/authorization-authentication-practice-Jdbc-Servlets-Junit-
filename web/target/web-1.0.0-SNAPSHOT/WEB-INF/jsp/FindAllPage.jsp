<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Find All</title>
    <style>
        <%@include file="style/FindAllPageStyle.css"%>
    </style>
</head>
<body>
<div>
    <%@include file="Header.jsp" %>
</div>


<table>
    <tr class="headerTable">
        <th>Username</th>
        <th>Email</th>
        <th>Gender</th>
        <th>Country</th>
    </tr>
    <c:forEach var="user" items="${requestScope.users}">
        <tr>
            <th>${user.username}</th>
            <th>${user.email}</th>
            <th>${user.gender}</th>
            <th>${user.country}</th>
        </tr>
    </c:forEach>
</table>

</body>
</html>
