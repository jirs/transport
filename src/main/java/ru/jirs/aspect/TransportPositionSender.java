package ru.jirs.aspect;

import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import ru.jirs.config.WebSocketConfig;
import ru.jirs.entity.TransportPosition;
import ru.jirs.pojo.AjaxResult;
import ru.jirs.service.TransportPositionService;
import ru.jirs.websocket.WSPositionListEncoder;

import javax.websocket.EncodeException;
import java.io.IOException;
import java.util.List;


@Component
@Aspect
public class TransportPositionSender {

    @Autowired
    private TransportPositionService transportPositionService;


    @Pointcut("execution(public * ru.jirs.controller.TransportPositionRestController.save* (..))")
    public void saveTransportPositionPointcut(){}

    @Pointcut("execution(public * ru.jirs.controller.PositionAreaRestController.changeTransportPositionArea(..))")
    public void changeTransportPositionArea(){}


    @AfterReturning(pointcut = "saveTransportPositionPointcut()", returning = "retVal")
    public void doAfterSaveTransportPosition(AjaxResult retVal){
        if (retVal.getStatus().equals(AjaxResult.Status.OK.name())){
            sentTransportPositions();
        }
    }

    @AfterReturning(pointcut = "changeTransportPositionArea()", returning = "retVal")
    public void doAfterChangeTransportPositionArea(AjaxResult retVal){
        if (retVal.getStatus().equals(AjaxResult.Status.OK.name())){
            sentTransportPositions();
        }
    }


    private void sentTransportPositions() {
        List<TransportPosition> positions = transportPositionService.getForArea();
        System.out.println(positions);
        for (WebSocketSession webSocketSession : WebSocketConfig.getSessions()) {
            try {
                webSocketSession.sendMessage(new TextMessage(new WSPositionListEncoder().encode(positions)));
            } catch (IOException e) {
                e.printStackTrace();
            } catch (EncodeException e) {
                e.printStackTrace();
            }
        }
    }


}
