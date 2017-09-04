function makeEditable() {
    $('.delete').click(function () {
        deleteRow($(this).attr("id"));
    });

    $('#detailsForm').submit(function () {
        save();
        return false;
    });

    $('#filterForm').submit(function () {
        filter();
        return false;
    });

    $('#input:checkbox').change(function() {
        // this will contain a reference to the checkbox
        if (this.checked) {
            // the checkbox is now checked
        } else {
            // the checkbox is now no longer checked
        }
    });

    $(':checkbox').change(function() {

        // do stuff here. It will fire on any checkbox change

    });

    $("#clearFilter").click(function () {
        $('#startDate').val("");
        $('#endDate').val("");
        $('#startTime').val("");
        $('#endTime').val("");
    });

    $(document).ajaxError(function (event, jqXHR, options, jsExc) {
        failNoty(event, jqXHR, options, jsExc);
    });
}

function filter() {
    var form = $('#filterForm');
    $.ajax({
        url : ajaxUrl + "filter",
        type : 'GET',
        data : form.serialize(),
        success: function () {
            updateTable();
            successNoty('Filtered');
        }
    })
}

function add() {
    $('#id').val(null);
    $('#editRow').modal();
}

function deleteRow(id) {
    $.ajax({
        url: ajaxUrl + id,
        type: 'DELETE',
        success: function () {
            updateTable();
            successNoty('Deleted');
        }
    });
}

function enableUser(id) {
    $.ajax( {
        type: 'POST',
        url: ajaxUrl + id,
        data: {checkbox: $('#id[name=checkbox]').val()},
        success: function() {
            successNoty('Check');
        }
    });
}

function updateTable() {
    $.get(ajaxUrl, function (data) {
        datatableApi.clear();
        $.each(data, function (key, item) {
            datatableApi.row.add(item);
        });
        datatableApi.draw();
    });
}

function save() {
    var form = $('#detailsForm');
    $.ajax({
        type: "POST",
        url: ajaxUrl,
        data: form.serialize(),
        success: function () {
            $('#editRow').modal('hide');
            updateTable();
            successNoty('Saved');
        }
    });
}

var failedNote;

function closeNoty() {
    if (failedNote) {
        failedNote.close();
        failedNote = undefined;
    }
}

function successNoty(text) {
    closeNoty();
    noty({
        text: text,
        type: 'success',
        layout: 'bottomRight',
        timeout: true
    });
}

function failNoty(event, jqXHR, options, jsExc) {
    closeNoty();
    failedNote = noty({
        text: 'Failed: ' + jqXHR.statusText + "<br>",
        type: 'error',
        layout: 'bottomRight'
    });
}
