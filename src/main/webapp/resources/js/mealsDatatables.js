var ajaxUrl = "ajax/profile/meals/";
var datatableApi;

function updateTable() {
    var form = $("#filter");

    $.post(ajaxUrl + "filter", form.serialize(), function (data) {
        datatableApi.clear().rows.add(data).draw();
    })
}

function clearFilter(){
    //document.getElementById('filter').reset();
    $("#filter").trigger('reset');
    $.get(ajaxUrl, function (data) {
        datatableApi.clear().rows.add(data).draw();
    });
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
                "asc"
            ]
        ]
    });
    makeEditable();
});
