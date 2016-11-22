package ru.jirs.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.jirs.service.TransportPositionService;

@Controller
public class TransportPositionController {

    @Autowired
    private TransportPositionService transportPositionService;

    @RequestMapping("/")
    public String mainPage() {
        return "index";
    }

    @RequestMapping("/save")
    public String savePage() {
        return "save";
    }

    @RequestMapping("/generate_sql")
    public String generateSql() {
        return "generate_sql";
    }

    @RequestMapping("/view_db")
    public String viewDb() {
        return "view_db";
    }

    @RequestMapping("/position_area")
    public String positionArea(ModelMap model) {
        model.addAttribute("positionArea", transportPositionService.getPositionArea());
        return "position_area";
    }
}