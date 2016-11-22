package ru.jirs.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.jirs.pojo.AjaxResult;
import ru.jirs.pojo.PositionArea;
import ru.jirs.service.TransportPositionService;

@RestController
public class PositionAreaRestController {

    @Autowired
    private TransportPositionService transportPositionService;

    @PostMapping("/change_area")
    public AjaxResult changeTransportPositionArea(@RequestBody PositionArea positionArea){
        if (!transportPositionService.getPositionArea().equals(positionArea)) {
            transportPositionService.setPositionArea(positionArea);
            return new AjaxResult(AjaxResult.Status.OK, "Данные успешно обновлены");
        } else {
            return new AjaxResult(AjaxResult.Status.FAIL, "Ошибка! Данные не обновлены");

        }
    }
}
