<%--
  Created by IntelliJ IDEA.
  User: vlad
  Date: 04.03.2018
  Time: 2:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="fn" uri="http://topjava.javawebinar.ru/functions"%>

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
                <jsp:useBean id="mealList" scope="request" type="java.util.List"/>
                <c:forEach var="meal" items="${mealList}">
                    <jsp:useBean id="meal" scope="page" type="ru.javawebinar.topjava.model.MealWithExceed"/>
                    <tr class="<c:out value="${meal.exceed?'text-danger':'text-success'}"/>">
                        <%--<td>${meal.date} ${meal.time}</td>--%>
                        <td>${fn:formatLocalDateTime(meal.dateTime, 'dd.MM.yyyy hh:mm')}</td>
                        <td>${meal.description}</td>
                        <td>${meal.calories}</td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </jsp:body>
</t:base>
