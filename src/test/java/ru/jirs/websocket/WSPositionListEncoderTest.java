package ru.jirs.websocket;

import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import ru.jirs.entity.TransportPosition;

import javax.websocket.EncodeException;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;


public class WSPositionListEncoderTest {
    WSPositionListEncoder encoder;
    List<TransportPosition> input;

    @Before
    public void setUp() {
        encoder = new WSPositionListEncoder();
        input = new ArrayList<>();
    }

    @Test
    public void encodeNull() throws Exception {
        assertThat(encoder.encode(null), is(Matchers.nullValue()));
    }

    @Test
    public void encodeListWithOne() throws EncodeException {
        input.add(new TransportPosition(1, 5, 10));
        assertThat(encoder.encode(input), is("[{\"id\":1,\"x\":5,\"y\":10}]"));
    }

    @Test
    public void encodeListWithTwo() throws EncodeException {
        input.add(new TransportPosition(1, 15, 100));
        input.add(new TransportPosition(3, 25, 70));
        assertThat(encoder.encode(input), is("[{\"id\":1,\"x\":15,\"y\":100},{\"id\":3,\"x\":25,\"y\":70}]"));
    }

}