$(document).ready(function () {
    $(window).load(function () {
        $.ajax({
            url: 'http://localhost:8080/transport_position',
            dataType: "json",
            type: "get",
            contentType: "application/json",
            timeout: 10000,
            success: function (data, textStatus) {
                $("#result").html(generateListHtml(data));
            },
            complete: function () {}
        });
    });
});

function generateListHtml(list) {
    var html = '';
    for (var i = 0; i < list.length; i++) {
        html += generateListItemHtml(list[i]);
    }
    return html;
}

function generateListItemHtml(dataItem) {
    if (dataItem == null) return '';
    return '<li>id: ' + dataItem.id + ', x: ' + dataItem.x + ', y: ' + dataItem.y + '</li>';
}
