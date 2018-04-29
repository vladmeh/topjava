<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fn" uri="http://topjava.javawebinar.ru/functions" %>
<html>
<jsp:include page="/WEB-INF/jsp/fragments/headTag.jsp"/>
<body>
<script type="text/javascript" src="resources/js/datatablesUtil.js" defer></script>
<script type="text/javascript" src="resources/js/mealsDatatables.js" defer></script>
<jsp:include page="/WEB-INF/jsp/fragments/bodyHeader.jsp"/>

<div class="jumbotron">
    <div class="container">
        <h3><spring:message code="meal.title"/></h3>

        <div class="row mt-3">
            <div class="col-7">
                <div class="card">
                    <div class="card-body">
                        <form id="filter" method="post" action="meals/filter">
                            <div class="form-group row">
                                <label for="startDate" class="col-3 col-form-label col-form-label-sm text-right"><spring:message
                                        code="meal.startDate"/>:</label>
                                <div class="col-3">
                                    <input class="form-control form-control-sm" type="date" name="startDate" id="startDate"
                                           value="${param.startDate}"/>
                                </div>
                                <label for="endDate" class="col-3 col-form-label col-form-label-sm text-right"><spring:message
                                        code="meal.endDate"/>:</label>
                                <div class="col-3">
                                    <input class="form-control form-control-sm" type="date" name="endDate" id="endDate" value="${param.endDate}"/>
                                </div>
                            </div>
                            <div class="form-group row">
                                <label for="startTime" class="col-3 col-form-label col-form-label-sm text-right"><spring:message
                                        code="meal.startTime"/>:</label>
                                <div class="col-3">
                                    <input name="startTime" type="time" class="form-control form-control-sm"
                                           id="startTime" value="${param.startTime}"/>
                                </div>
                                <label for="endTime" class="col-3 col-form-label col-form-label-sm text-right"><spring:message
                                        code="meal.endTime"/>:</label>
                                <div class="col-3">
                                    <input name="endTime" type="time" class="form-control form-control-sm" id="endTime"
                                           value="${param.endTime}"/>
                                </div>
                            </div>
                        </form>
                    </div>
                    <div class="card-footer">
                        <div class="d-flex flex-row-reverse col">
                            <a href="meals" class="btn btn-Light btn-sm"><spring:message code="meal.clear"/></a>
                            <button class="btn btn-success btn-sm mr-2" type="submit" form="filter"><spring:message code="meal.filter"/></button>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <div class="row mt-3">
            <div class="col">
                <a href="meals/create" class="btn btn-primary"><spring:message code="meal.add"/></a>
            </div>
        </div>

        <div class="row mt-3">
            <table class="table table-striped table-light" id="datatable">
                <thead>
                <tr>
                    <th><spring:message code="meal.dateTime"/></th>
                    <th><spring:message code="meal.description"/></th>
                    <th><spring:message code="meal.calories"/></th>
                    <th></th>
                    <th></th>
                </tr>
                </thead>
                <c:forEach items="${meals}" var="meal">
                    <jsp:useBean id="meal" scope="page" type="ru.javawebinar.topjava.to.MealWithExceed"/>
                    <tr data-mealExceed="${meal.exceed}">
                        <td>
                                <%--${meal.dateTime.toLocalDate()} ${meal.dateTime.toLocalTime()}--%>
                                <%--<%=TimeUtil.toString(meal.getDateTime())%>--%>
                                <%--${fn:replace(meal.dateTime, 'T', ' ')}--%>
                                ${fn:formatDateTime(meal.dateTime)}
                        </td>
                        <td>${meal.description}</td>
                        <td>${meal.calories}</td>
                        <td><a href="meals/update?id=${meal.id}"><spring:message code="common.update"/></a></td>
                        <td><a href="meals/delete?id=${meal.id}"><spring:message code="common.delete"/></a></td>
                    </tr>
                </c:forEach>
            </table>
        </div>
    </div>

</div>
<jsp:include page="/WEB-INF/jsp/fragments/footer.jsp"/>
</body>
</html>