var ajaxUrl = "ajax/profile/meals/";
var datatableApi;

function updateTable() {
    // $.ajax({
    //     type: "POST",
    //     url: ajaxUrl + "filter",
    //     data: $("#filter").serialize(),
    //     success: updateTableByData
    // });
    $.get(ajaxUrl, updateTableByData);
}

function clearFilter() {
    $("#filter")[0].reset();
    $.get(ajaxUrl, updateTableByData);
}

$(function () {
    datatableApi = $("#datatable").DataTable({
        "paging": false,
        "info": true,
        "ajax": {
            "url": ajaxUrl,
            "dataSrc": ""
        },
        "columns": [
            {
                "data": "dateTime",
                "render": function (date, type, row) {
                    if (type === "display") {
                        return date.substring(0, 10);
                    }
                    return date;
                }
            },
            {
                "data": "description",
                "render": function (data, type, row) {
                    if (type === "display") {
                        return data;
                    }
                    return data;
                }
            },
            {
                "data": "calories",
                "render": function (data, type, row) {
                    if (type === "display") {
                        return data;
                    }
                    return data;
                }
            },
            {
                "defaultContent": "Edit",
                "orderable": false,
                "render": renderEditBtn
            },
            {
                "defaultContent": "Delete",
                "orderable": false,
                "render": renderDeleteBtn
            }
        ],
        "order": [
            [
                0,
                "desc"
            ]
        ],
        "createdRow": function (row, data, dataIndex) {
            if (!data.exceed) {
                $(row).addClass("exceeded");
            } else {
                $(row).addClass("normal");
            }
        },
        "initComplete": makeEditable
    });
    makeEditable();
});