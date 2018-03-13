<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:base>
    <jsp:attribute name="title">Java Enterprise (Topjava)</jsp:attribute>

    <jsp:body>
        <div class="jumbotron mt-5">
            <h1 class="display-4">Java Enterprise проект</h1>
            <p class="lead">с регистрацией/авторизацией и интерфейсом на основе ролей (USER, ADMIN). Администратор может создавать/редактировать/удалять пользователей, а пользователи - управлять своим профилем и данными (день, еда, калории) через UI (по AJAX) и по REST интерфейсу с базовой авторизацией. Возможна фильтрация данных по датам и времени, при этом цвет записи таблицы еды зависит от того, превышает ли сумма калорий за день норму (редактируемый параметр в профиле пользователя). Весь REST интерфейс покрывается JUnit тестами, используя Spring MVC Test и Spring Security Test.</p>
            <hr class="my-4">
            <div class="col-md-3 d-flex flex-column flex-md-row lead">
                <a class="btn btn-outline-primary btn-lg btn-block" href="meals" role="button">Go started</a>
            </div>
        </div>
    </jsp:body>
</t:base>
