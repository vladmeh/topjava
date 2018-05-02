var ajaxUrl = "ajax/profile/meals/";
var datatableApi;

function updateTable() {
    var form = $("#filter");

    $.post(ajaxUrl + "filter", form.serialize(), datatableDraw);
}

function clearFilter(){
    //document.getElementById('filter').reset();
    $("#filter").trigger('reset');
    $.get(ajaxUrl, datatableDraw).done(successNoty("Clear filter"));
}

$(function () {
    datatableApi = $("#datatable").DataTable({
        "paging" : false,
        "info" : true,
        "columns" : [
            {
                "data" : "dateTime"
            },
            {
                "data" : "description"
            },
            {
                "data" : "calories"
            },
            {
                "defaultContent": "Edit",
                "orderable": false
            },
            {
                "defaultContent": "Delete",
                "orderable": false
            }
        ],
        "order": [
            [
                0,
                "desc"
            ]
        ]
    });
    makeEditable();
});
