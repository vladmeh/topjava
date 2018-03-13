<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<form method="post" action="meals" class="my-0">
    <div class="form-group row">
        <label for="startDate" class="col-sm-3 col-form-label col-form-label-sm">Date from:</label>
        <div class="col-sm-4">
            <input name="startDate" type="date" class="form-control form-control-sm" id="startDate" value="${param.startDate}" />
        </div>
        <label for="endDate" class="col-sm-1 col-form-label col-form-label-sm">to:</label>
        <div class="col-sm-4">
            <input name="endDate" type="date" class="form-control form-control-sm" id="endDate" value="${param.startDate}" />
        </div>
    </div>
    <div class="form-group row">
        <label for="startTime" class="col-sm-3 col-form-label col-form-label-sm">Date from:</label>
        <div class="col-sm-4">
            <input name="startTime" type="time" class="form-control form-control-sm" id="startTime" value="${param.startTime}" />
        </div>
        <label for="endTime" class="col-sm-1 col-form-label col-form-label-sm">to:</label>
        <div class="col-sm-4">
            <input name="endTime" type="time" class="form-control form-control-sm" id="endTime" value="${param.startTime}" />
        </div>
    </div>
    <div class="d-flex flex-row-reverse col">
        <button type="reset" class="btn btn-secondary btn-sm">Clear</button>
        <button type="submit" name="filter" class="btn btn-primary btn-sm mr-2">Filter</button>
    </div>
</form>
