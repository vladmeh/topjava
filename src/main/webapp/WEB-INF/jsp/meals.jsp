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
                                <label for="startDate" class="col-2 col-form-label col-form-label-sm text-right"><spring:message
                                        code="meal.startDate"/>:</label>
                                <div class="col-4">
                                    <input class="form-control form-control-sm" type="date" name="startDate" id="startDate"
                                           value="${param.startDate}"/>
                                </div>
                                <label for="startTime" class="col-3 col-form-label col-form-label-sm text-right"><spring:message
                                        code="meal.startTime"/>:</label>
                                <div class="col-3">
                                    <input name="startTime" type="time" class="form-control form-control-sm"
                                           id="startTime" value="${param.startTime}"/>
                                </div>
                            </div>
                            <div class="form-group row">
                                <label for="endDate" class="col-2 col-form-label col-form-label-sm text-right"><spring:message
                                        code="meal.endDate"/>:</label>
                                <div class="col-4">
                                    <input class="form-control form-control-sm" type="date" name="endDate" id="endDate" value="${param.endDate}"/>
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
                            <button href="meals" class="btn btn-Light btn-sm" onclick="clearFilter()"><spring:message code="meal.clear"/></button>
                            <button class="btn btn-success btn-sm mr-2" onclick="updateTable()"><spring:message code="meal.filter"/></button>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <div class="row mt-3">
            <div class="col">
                <button class="btn btn-primary" onclick="add()">
                    <span class="fa fa-plus"></span>
                    <spring:message code="meal.add"/>
                </button>
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
                        <td><a><span class="fa fa-pencil"></span></a></td>
                        <td><a onclick="deleteRow(${meal.id})"><span class="fa fa-remove"></span></a></td>
                    </tr>
                </c:forEach>
            </table>
        </div>
    </div>

</div>

<div class="modal fade" tabindex="-1" id="editRow">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title"><spring:message code="meal.add"/></h4>
            </div>
            <div class="modal-body">
                <form id="detailsForm">
                    <input type="hidden" id="id" name="id" value="${meal.id}"/>
                    <div class="form-group">
                        <label for="dateTime" class="col-form-label"><spring:message code="meal.dateTime"/>:</label>
                        <input class="form-control" type="datetime-local" value="${meal.dateTime}" name="dateTime" id="dateTime" required />
                    </div>
                    <div class="form-group">
                        <label for="description" class="col-form-label"><spring:message code="meal.description"/>:</label>
                        <input class="form-control" type="text" value="${meal.description}" size=40 name="description" id="description" required placeholder="<spring:message code="meal.description"/>"/>
                    </div>
                    <div class="form-group">
                        <label for="calories" class="col-form-label"><spring:message code="meal.calories"/>:</label>
                        <input class="form-control" type="number" value="${meal.calories}" name="calories" id="calories" required placeholder="1000"/>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-dismiss="modal">
                    <span class="fa fa-close" aria-hidden="true"></span>
                    <spring:message code="common.cancel"/>
                </button>
                <button type="button" class="btn btn-primary" onclick="save()">
                    <span class="fa fa-check" aria-hidden="true"></span>
                    <spring:message code="common.save"/>
                </button>
            </div>
        </div>
    </div>
</div>

<jsp:include page="/WEB-INF/jsp/fragments/footer.jsp"/>
</body>
</html>