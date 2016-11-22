package ru.jirs.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import ru.jirs.TransportApplication;
import ru.jirs.pojo.PositionArea;
import ru.jirs.service.TransportPositionService;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(PositionAreaRestController.class)
@ContextConfiguration(classes = TransportApplication.class)
public class PositionAreaRestControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private TransportPositionService transportPositionService;

    @Before
    public void startUp() {
        Mockito.reset(transportPositionService);
    }

    @Test
    public void changeTransportPositionAreaOk() throws Exception {
        PositionArea positionArea = new PositionArea(10, 200, 20, 300);

        Mockito.when(transportPositionService.getPositionArea()).thenReturn(new PositionArea());

        mvc.perform(post("/change_area")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content("{\"xFrom\":10,\"xTo\":200,\"yFrom\":20,\"yTo\":300}"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.status", is("OK")));
        Mockito.verify(transportPositionService, Mockito.times(1)).getPositionArea();
        Mockito.verify(transportPositionService, Mockito.times(1)).setPositionArea(positionArea);
        Mockito.verifyNoMoreInteractions(transportPositionService);
    }


    @Test
    public void changeTransportPositionAreaFail() throws Exception {
        PositionArea positionArea = new PositionArea(10, 200, 20, 300);

        Mockito.when(transportPositionService.getPositionArea()).thenReturn(new PositionArea(10, 200, 20, 300));

        mvc.perform(post("/change_area")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content("{\"xFrom\":10,\"xTo\":200,\"yFrom\":20,\"yTo\":300}"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.status", is("FAIL")));
        Mockito.verify(transportPositionService, Mockito.times(1)).getPositionArea();
        Mockito.verifyNoMoreInteractions(transportPositionService);
    }



}