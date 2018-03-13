<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:base>
    <jsp:attribute name="title">${param.action == 'create' ? 'Create meal' : 'Edit meal'}</jsp:attribute>

    <jsp:body>
        <jsp:useBean id="meal" scope="request" type="ru.javawebinar.topjava.model.Meal"/>
        <h2 class="page-header my-3">${param.action == 'create' ? 'Create meal' : 'Edit meal'}</h2>
        <hr/>
        <form method="post" action="meals">
            <input type="hidden" name="id" value="${meal.id}">
            <div class="form-group row">
                <label for="dateTime" class="col-sm-2 col-form-label">DateTime:</label>
                <div class="col-sm-5">
                    <input name="dateTime" type="datetime-local" class="form-control" id="dateTime" value="${meal.dateTime}" required>
                </div>
            </div>
            <div class="form-group row">
                <label for="description" class="col-sm-2 col-form-label">Description:</label>
                <div class="col-sm-5">
                    <input name="description" type="text" class="form-control" id="description" placeholder="Description" value="${meal.description}" required>
                </div>
            </div>
            <div class="form-group row">
                <label for="calories" class="col-sm-2 col-form-label">Calories:</label>
                <div class="col-sm-5">
                    <input name="calories" type="text" class="form-control" id="calories" placeholder="Calories" value="${meal.calories}" required>
                </div>
            </div>
            <div class="d-flex flex-row-reverse col-sm-7">
                <button onclick="window.history.back()" class="btn btn-secondary">Cancel</button>
                <button type="submit" class="btn btn-primary mr-2">Save</button>
            </div>
        </form>
    </jsp:body>
</t:base>
