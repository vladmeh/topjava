var ajaxUrl = "ajax/admin/users/";
var datatableApi;

function updateTable() {
    $.get(ajaxUrl, function (data) {
        datatableApi.clear().rows.add(data).draw();
    });
}

function enabled(checkbox, id){
    //console.log(checkbox.parentNode.parentNode);
    $.post(ajaxUrl + id, {'enable' : checkbox.checked},
        function () {
            var tr = checkbox.parentNode.parentNode;
            $(tr).toggleClass('table-dark');
            successNoty((checkbox.checked ? "Enabled" : "Disabled") + " - id: " + id);
        })
}

// $(document).ready(function () {
$(function () {
    datatableApi = $("#datatable").DataTable({
        "paging": false,
        "info": true,
        "columns": [
            {
                "data": "name"
            },
            {
                "data": "email"
            },
            {
                "data": "roles"
            },
            {
                "data": "enabled"
            },
            {
                "data": "registered"
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