$(document).ready(function(e) {
    $("#f_save").submit(function () {
        var id = parseInt($("#id").val(), 10);
        var transportPosition = {"x": parseInt($("#x").val(), 10), "y": parseInt($("#y").val(), 10)};

        $.ajax({
            url: 'http://localhost:8080/transport_position/'+ id,
            dataType: "json",
            type: "put",
            contentType: "application/json",
            data: JSON.stringify(transportPosition),
            timeout: 10000,
            success: function (data, textStatus) {
                if (data != null) {
                    $("#message").html(generateMessageHtml(data.statusText, data.status));
                }
            },
            complete: function () {
            }
        });
        return false;
    });

});

function generateMessageHtml(message, status) {
    return '<span style="color:' + ((status == 'OK') ? 'green' : ((status == 'FAIL') ? 'red' : '#999')) + '">' + message + '</span>';
}