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
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ru.jirs.TransportApplication;
import ru.jirs.entity.TransportPosition;
import ru.jirs.service.TransportPositionService;

import java.util.ArrayList;
import java.util.Arrays;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Matchers.anyInt;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;



@RunWith(SpringRunner.class)
@WebMvcTest(TransportPositionRestController.class)
@ContextConfiguration(classes = TransportApplication.class)
public class TransportPositionRestControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private TransportPositionService transportPositionService;

    @Before
    public void startUp() {
        Mockito.reset(transportPositionService);
    }

    @Test
    public void testGetByIdNotFound() throws Exception {
        Mockito.when(transportPositionService.getById(6)).thenReturn(null);
        mvc.perform(MockMvcRequestBuilders.get("/transport_position/6"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.status", is("FAIL")));
        Mockito.verify(transportPositionService, Mockito.times(1)).getById(6);
        Mockito.verifyNoMoreInteractions(transportPositionService);
    }

    @Test
    public void testGetByIdOk() throws Exception {
        Mockito.when(transportPositionService.getById(6)).thenReturn(new TransportPosition(6, 15, 35));
        mvc.perform(MockMvcRequestBuilders.get("/transport_position/6"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.status", is("OK")))
                .andExpect(jsonPath("$.result.id", is(6)))
                .andExpect(jsonPath("$.result.x", is(15)))
                .andExpect(jsonPath("$.result.y", is(35)));
        Mockito.verify(transportPositionService, Mockito.times(1)).getById(6);
        Mockito.verifyNoMoreInteractions(transportPositionService);
    }

    @Test
    public void testGetAllNotFound() throws Exception {
        Mockito.when(transportPositionService.getAll()).thenReturn(null);
        mvc.perform(MockMvcRequestBuilders.get("/transport_position"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$", hasSize(0)));
        Mockito.verify(transportPositionService, Mockito.times(1)).getAll();
        Mockito.verifyNoMoreInteractions(transportPositionService);
    }

    @Test
    public void testGetAllOk() throws Exception {
        Mockito.when(transportPositionService.getAll()).thenReturn(new ArrayList<TransportPosition>(Arrays.asList(new TransportPosition[]{new TransportPosition(6, 15, 35), new TransportPosition(9, 35, 75)})));
        mvc.perform(MockMvcRequestBuilders.get("/transport_position"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].id", is(6)))
                .andExpect(jsonPath("$[0].x", is(15)))
                .andExpect(jsonPath("$[0].y", is(35)))
                .andExpect(jsonPath("$[1].id", is(9)))
                .andExpect(jsonPath("$[1].x", is(35)))
                .andExpect(jsonPath("$[1].y", is(75)));
        Mockito.verify(transportPositionService, Mockito.times(1)).getAll();
        Mockito.verifyNoMoreInteractions(transportPositionService);
    }


    @Test
    public void testGetForAreaNotFound() throws Exception {
        Mockito.when(transportPositionService.getForArea(anyInt(), anyInt(), anyInt(), anyInt())).thenReturn(null);
        mvc.perform(MockMvcRequestBuilders.get("/transport_position/0/1000/0/1000"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$", hasSize(0)));
        Mockito.verify(transportPositionService, Mockito.times(1)).getForArea(anyInt(), anyInt(), anyInt(), anyInt());
        Mockito.verifyNoMoreInteractions(transportPositionService);
    }

    @Test
    public void testGetForAreaOk() throws Exception {
        Mockito.when(transportPositionService.getForArea(anyInt(), anyInt(), anyInt(), anyInt())).thenReturn(new ArrayList<TransportPosition>(Arrays.asList(new TransportPosition[]{new TransportPosition(6, 15, 35), new TransportPosition(9, 35, 75)})));
        mvc.perform(MockMvcRequestBuilders.get("/transport_position/0/1000/0/1000"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].id", is(6)))
                .andExpect(jsonPath("$[0].x", is(15)))
                .andExpect(jsonPath("$[0].y", is(35)))
                .andExpect(jsonPath("$[1].id", is(9)))
                .andExpect(jsonPath("$[1].x", is(35)))
                .andExpect(jsonPath("$[1].y", is(75)));
        Mockito.verify(transportPositionService, Mockito.times(1)).getForArea(anyInt(), anyInt(), anyInt(), anyInt());
        Mockito.verifyNoMoreInteractions(transportPositionService);
    }


    @Test
    public void testSaveNewPostFail() throws Exception {
        TransportPosition transportPosition = new TransportPosition(5, 15, 35);

        Mockito.when(transportPositionService.save(new TransportPosition(5, 15, 35))).thenReturn(null);
        mvc.perform(post("/transport_position")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content("{\"id\":5,\"x\":15,\"y\":35}"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.status", is("FAIL")));
        Mockito.verify(transportPositionService, Mockito.times(1)).save(new TransportPosition(5, 15, 35));
        Mockito.verifyNoMoreInteractions(transportPositionService);
    }

    @Test
    public void testSaveNewPostOk() throws Exception {
        TransportPosition transportPosition = new TransportPosition(5, 15, 35);

        Mockito.when(transportPositionService.save(new TransportPosition(5, 15, 35))).thenReturn(new TransportPosition(5, 15, 35));
        mvc.perform(post("/transport_position")
                    .contentType(MediaType.APPLICATION_JSON_UTF8)
                    .content("{\"id\":5,\"x\":15,\"y\":35}"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.status", is("OK")))
                .andExpect(jsonPath("$.result.id", is(5)))
                .andExpect(jsonPath("$.result.x", is(15)))
                .andExpect(jsonPath("$.result.y", is(35)));
        Mockito.verify(transportPositionService, Mockito.times(1)).save(new TransportPosition(5, 15, 35));
        Mockito.verifyNoMoreInteractions(transportPositionService);
    }


    @Test
    public void testSavePutFail() throws Exception {
        TransportPosition transportPosition = new TransportPosition(5, 15, 35);

        Mockito.when(transportPositionService.save(new TransportPosition(5, 15, 35))).thenReturn(null);
        mvc.perform(put("/transport_position/5")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content("{\"id\":5,\"x\":15,\"y\":35}"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.status", is("FAIL")));
        Mockito.verify(transportPositionService, Mockito.times(1)).save(new TransportPosition(5, 15, 35));
        Mockito.verifyNoMoreInteractions(transportPositionService);
    }

    @Test
    public void testSavePutOk() throws Exception {
        TransportPosition transportPosition = new TransportPosition(5, 15, 35);

        Mockito.when(transportPositionService.save(new TransportPosition(5, 15, 35))).thenReturn(new TransportPosition(5, 15, 35));
        mvc.perform(put("/transport_position/5")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content("{\"id\":5,\"x\":15,\"y\":35}"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.status", is("OK")))
                .andExpect(jsonPath("$.result.id", is(5)))
                .andExpect(jsonPath("$.result.x", is(15)))
                .andExpect(jsonPath("$.result.y", is(35)));
        Mockito.verify(transportPositionService, Mockito.times(1)).save(new TransportPosition(5, 15, 35));
        Mockito.verifyNoMoreInteractions(transportPositionService);
    }


    @Test
    public void testDeleteFail() throws Exception {

        Mockito.when(transportPositionService.delete(5)).thenReturn(false);
        mvc.perform(delete("/transport_position/5"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.status", is("FAIL")));
        Mockito.verify(transportPositionService, Mockito.times(1)).delete(5);
        Mockito.verifyNoMoreInteractions(transportPositionService);
    }

    @Test
    public void testDeleteOk() throws Exception {

        Mockito.when(transportPositionService.delete(5)).thenReturn(true);
        mvc.perform(delete("/transport_position/5"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.status", is("OK")));
        Mockito.verify(transportPositionService, Mockito.times(1)).delete(5);
        Mockito.verifyNoMoreInteractions(transportPositionService);
    }

}