$(document).ready(function () {
    $(window).load(function () {
        $.ajax({
            url: 'http://localhost:8080/transport_position',
            dataType: "json",
            type: "get",
            contentType: "application/json",
            timeout: 10000,
            success: function (data, textStatus) {
                if (data != null) {
                    for (var i = 0; i < data.length; i++) {
                        $("#result").append(generateQuery(data[i]));
                    }
                }
            },
            complete: function () {}
        });
    });
});

function generateQuery(dataItem) {
    if (dataItem == null) return '';
    return 'INSERT INTO transport_position (x, y) VALUES (' + dataItem.x + ', ' + dataItem.y + ');<br />';
}
