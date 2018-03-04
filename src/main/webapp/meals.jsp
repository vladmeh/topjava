<%--
  Created by IntelliJ IDEA.
  User: vlad
  Date: 04.03.2018
  Time: 2:51
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<t:base>
    <jsp:attribute name="title">Meals</jsp:attribute>
    <jsp:body>
        <div class="row">
            <h2 class="page-header my-3">Meals</h2>
            <table class="table">
                <thead>
                    <tr>
                        <th scope="col">Дата / Время</th>
                        <th scope="col">Описание</th>
                        <th scope="col">Калории</th>
                    </tr>
                </thead>
                <tbody>
                <c:forEach var="meal" items="${mealList}">
                    <tr class="<c:out value="${meal.isExceed()?'text-danger':'text-success'}"/>">
                        <td>${meal.getDate()} ${meal.getTime()}</td>
                        <td>${meal.getDescription()}</td>
                        <td>${meal.getCalories()}</td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </jsp:body>
</t:base>
