$(document).ready(function(e) {
    $("#f_save").submit(function () {
        var b_save = $("#b_save");
        b_save.addClass("preloader");
        var positionArea = {"xFrom": parseInt($("#x_from").val(), 10),
                                "xTo": parseInt($("#x_to").val(), 10),
                                "yFrom": parseInt($("#y_from").val(), 10),
                                "yTo": parseInt($("#y_to").val(), 10)};

        $.ajax({
            url: 'http://localhost:8080/change_area',
            dataType: "json",
            type: "post",
            contentType: "application/json",
            data: JSON.stringify(positionArea),
            timeout: 10000,
            success: function (data, textStatus) {
                if (data != null) {
                    $("#message").html(generateMessageHtml(data.statusText, data.status));
                }
            },
            complete: function () {
                b_save.removeClass("preloader");
            }
        });
        return false;
    });

});

function generateMessageHtml(message, status) {
    return '<span style="color:' + ((status == 'OK') ? 'green' : ((status == 'FAIL') ? 'red' : '#999')) + '">' + message + '</span>';
}