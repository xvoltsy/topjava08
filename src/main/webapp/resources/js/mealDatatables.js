var ajaxUrl = 'ajax/profile/meals/';
var datatableApi;

$(function () {
    datatableApi = $('#meals_datatable').DataTable({
        "paging": false,
        "info": true,
        "columns": [
            {
                "data": "date_time"
            },
            {
                "data": "description"
            },
            {
                "data": "calories"
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
