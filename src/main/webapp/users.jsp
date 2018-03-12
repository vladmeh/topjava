<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Users</title>
</head>
<body>
<h3><a href="index.html">Home</a></h3>
<h2>Users</h2>
<hr/>
<table border="1" cellpadding="8" cellspacing="0">
    <thead>
    <tr>
        <th>#Id</th>
        <th>Name</th>
        <th>E-mail</th>
        <th>Password</th>
        <th></th>
    </tr>
    </thead>
    <jsp:useBean id="users" type="java.util.List" scope="request" />
    <c:forEach items="${users}" var="user">
        <jsp:useBean id="user" type="ru.javawebinar.topjava.model.User" scope="page" />
        <c:url value="meals" var="mealsUrl"><c:param name="userId" value="${user.id}"/></c:url>
        <tr>
            <td>${user.id}</td>
            <td>${user.name}</td>
            <td>${user.email}</td>
            <td>${user.password}</td>
            <td><a href="${mealsUrl}">meals</a></td>
        </tr>
    </c:forEach>
</table>
</body>
</html>