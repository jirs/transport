package ru.jirs.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.jirs.entity.TransportPosition;
import ru.jirs.pojo.AjaxResult;
import ru.jirs.service.TransportPositionService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/transport_position")
public class TransportPositionRestController {

    @Autowired
    private TransportPositionService transportPositionService;

    @GetMapping()
    public  List<TransportPosition> getAll() {
        List<TransportPosition> all = transportPositionService.getAll();
        if (all == null) return new ArrayList<>();
        return all;
    }

    @GetMapping("/{id}")
    public AjaxResult<TransportPosition> getById(@PathVariable("id") int id) {
        TransportPosition transportPosition = transportPositionService.getById(id);
        if (transportPosition != null) {
            return new AjaxResult<>(AjaxResult.Status.OK, "Ok", transportPosition);
        } else {
            return new AjaxResult<>(AjaxResult.Status.FAIL, "Fail");
        }
    }

    @PostMapping()
    public AjaxResult<TransportPosition> saveNew(@RequestBody TransportPosition transportPosition) {
        System.out.println("transportPosition.id:" + transportPosition.getId());
        System.out.println("transportPosition.x:" + transportPosition.getX());
        System.out.println("transportPosition.y:" + transportPosition.getY());
        if (transportPositionService.save(transportPosition) != null) {
            return new AjaxResult<>(AjaxResult.Status.OK, "Ok", transportPosition);
        } else {
            return new AjaxResult<>(AjaxResult.Status.FAIL, "Не удалось добавить транспортное средство");
        }
    }

    @PutMapping("/{id}")
    public AjaxResult<TransportPosition> save(@PathVariable("id") int id, @RequestBody Map<String, Integer> position) {
        TransportPosition transportPosition = new TransportPosition(id, position.get("x"), position.get("y"));
        if (transportPositionService.save(transportPosition) != null) {
            return new AjaxResult<>(AjaxResult.Status.OK, "Ok", transportPosition);
        } else {
            return new AjaxResult<>(AjaxResult.Status.FAIL, "Не удалось изменить позицию транспортного средства (ID: " + id + ")", transportPosition);
        }
    }

    @DeleteMapping("/{id}")
    public AjaxResult<TransportPosition> delete(@PathVariable("id") int id) {
        if (transportPositionService.delete(id)) {
            return new AjaxResult<>(AjaxResult.Status.OK, "Ok");
        } else {

            return new AjaxResult<>(AjaxResult.Status.FAIL, "Не удалось удалить позицию транспортного средства (ID: " + id + ")");
        }
    }

    @RequestMapping("/{x_from}/{x_to}/{y_from}/{y_to}")
    public List<TransportPosition> getForArea(@PathVariable("x_from") int xFrom, @PathVariable("x_to") int xTo, @PathVariable("y_from") int yFrom, @PathVariable("y_to") int yTo) {
        List<TransportPosition> forArea = transportPositionService.getForArea(xFrom, xTo, yFrom, yTo);
        if (forArea == null) return new ArrayList<>();
        return forArea;
    }
}
