package ru.jirs.websocket;

import ru.jirs.entity.TransportPosition;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.websocket.EncodeException;
import javax.websocket.Encoder;
import javax.websocket.EndpointConfig;
import java.util.List;

public class WSPositionListEncoder implements Encoder.Text<List<TransportPosition>> {
    @Override
    public String encode(List<TransportPosition> positions) throws EncodeException {
        if (positions == null) return null;

        JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();
        for (TransportPosition position : positions) {
            arrayBuilder.add(Json.createObjectBuilder()
                    .add("id", position.getId())
                    .add("x", position.getX())
                    .add("y", position.getY())
                    .build());
        }
        return arrayBuilder.build().toString();
    }

    @Override
    public void init(EndpointConfig endpointConfig) {
    }

    @Override
    public void destroy() {
    }
}
