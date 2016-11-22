$(document).ready(function (e) {
    $(window).load(function () {
        var ws = new WebSocket('ws://localhost:8080/gettransportpositions');

        ws.onmessage = function (data) {
            var transportPositions = JSON.parse(data.data);
            $("#positions_list").html(generateTransportPostionListHtml(transportPositions));
            generateTransportPositonsMap(transportPositions);
        }
    });
});

var mapParams = {"xFrom": Number.MAX_VALUE, "xTo": Number.MIN_VALUE, "yFrom": Number.MAX_VALUE, "yTo": Number.MIN_VALUE, "width": 0, "height": 0, "xStep": 1, "yStep": 1};

function generateTransportPositonsMap(list) {
    var mapDiv = $("#map");
    mapDiv.html('');
    initMapParams(list, mapDiv);
    for (var i = 0; i < list.length; i++) {
        mapDiv.append(generateTPMapItemHtml(list[i]));
    }
}

function initMapParams(list, mapDiv) {
    mapParams = {"xFrom": Number.MAX_VALUE, "xTo": Number.MIN_VALUE, "yFrom": Number.MAX_VALUE, "yTo": Number.MIN_VALUE, "width": 0, "height": 0, "xStep": 1, "yStep": 1};

    for (var i = 0; i < list.length; i++) {
        if (list[i].x < mapParams.xFrom) mapParams.xFrom = list[i].x;
        if (list[i].x > mapParams.xTo) mapParams.xTo = list[i].x;
        if (list[i].y < mapParams.yFrom) mapParams.yFrom = list[i].y;
        if (list[i].y > mapParams.yTo) mapParams.yTo = list[i].y;
    }
    mapParams.width = mapParams.xTo - mapParams.xFrom;
    mapParams.height = mapParams.yTo - mapParams.yFrom;


    mapParams.xStep = (mapParams.width > 0) ? mapDiv.width()/mapParams.width : mapDiv.width()/2;
    mapParams.yStep = (mapParams.height > 0) ? mapDiv.height()/mapParams.height : mapDiv.height()/2;
}

function generateTPMapItemHtml(item) {
    var number = Math.round((item.x - mapParams.xFrom)*mapParams.xStep);
    return '<div class="map_item" style="left: ' + Math.round((item.x - mapParams.xFrom)*mapParams.xStep) + 'px; top: ' + Math.round((item.y - mapParams.yFrom)*mapParams.yStep) + 'px;"><div class="map_item_point"></div><div class="map_item_name">' + item.id + '</div></div>';
}

function generateTransportPostionListHtml(list) {
    var html = '';
    for (var i = 0; i < list.length; i++) {
        html += generateTransportPositionListItemHtml(list[i]);
    }
    return html;
}
function generateTransportPositionListItemHtml(position) {
    return "<li>id: " + position.id + "; x: " + position.x + "; y: " + position.y + "</li>";
}
