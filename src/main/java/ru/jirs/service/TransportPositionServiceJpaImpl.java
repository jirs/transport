package ru.jirs.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.jirs.entity.TransportPosition;
import ru.jirs.pojo.PositionArea;
import ru.jirs.repository.TransportPositionRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class TransportPositionServiceJpaImpl implements TransportPositionService {

    private static Map<Integer, TransportPosition> transportPositions;
    private static PositionArea positionArea = new PositionArea(0, 200, 0, 200);;

    @Autowired
    private TransportPositionRepository transportPositionRepository;



    @Override
    public List<TransportPosition> getAll() {
        return transportPositionRepository.findAll();
    }

    @Override
    public TransportPosition getById(int id) {
        return transportPositionRepository.findOne(id);
    }

    @Override
    public List<TransportPosition> getForArea() {
        if (transportPositions == null || transportPositions.isEmpty()) {
            return getForArea(positionArea.getxFrom(), positionArea.getxTo(), positionArea.getyFrom(), positionArea.getyTo());
        }
        return new ArrayList<>(transportPositions.values());
    }

    @Override
    public PositionArea getPositionArea() {
        return positionArea;
    }

    @Override
    public void setPositionArea(PositionArea newPositionArea) {
        getForArea(newPositionArea.getxFrom(), newPositionArea.getxTo(), newPositionArea.getyFrom(), newPositionArea.getyTo());
    }

    @Override
    public List<TransportPosition> getForArea(int xFrom, int xTo, int yFrom, int yTo) {
        PositionArea positionArea = new PositionArea(xFrom, xTo, yFrom, yTo);
        if (positionArea.equals(TransportPositionServiceJpaImpl.positionArea) && transportPositions != null) {
            return new ArrayList<>(transportPositions.values());
        } else {
            TransportPositionServiceJpaImpl.positionArea = positionArea;
            List<TransportPosition> forArea = transportPositionRepository.findForArea(xFrom, xTo, yFrom, yTo);
            transportPositions = positionsListToMap(forArea);
            return forArea;
        }
    }

    @Override
    public TransportPosition save(TransportPosition transportPosition) {
        if (transportPositions != null) {
            if (positionInArea(transportPosition)) {
                transportPositions.put(transportPosition.getId(), transportPosition);
            } else {
                if (transportPositions.containsKey(transportPosition.getId())) {
                    transportPositions.remove(transportPosition.getId());
                }
            }
        }

        return transportPositionRepository.saveAndFlush(transportPosition);
    }

    @Override
    public TransportPosition add(TransportPosition transportPosition) {
        if (positionInArea(transportPosition)) {
            transportPositions.put(transportPosition.getId(), transportPosition);
        }
        return transportPositionRepository.saveAndFlush(transportPosition);
    }

    @Override
    public boolean delete(int id) {
        if (transportPositions.containsKey(id)) {
            transportPositions.remove(id);
        }
        transportPositionRepository.delete(id);
        return true;
    }

    private Map<Integer, TransportPosition> positionsListToMap(List<TransportPosition> positions) {
        Map<Integer, TransportPosition> result = new ConcurrentHashMap<>();
        for (TransportPosition position : positions) {
            result.put(position.getId(), position);
        }
        return result;
    }

    private boolean positionInArea(TransportPosition transportPosition) {
        return positionArea != null && positionArea.getxFrom() <= transportPosition.getX() && positionArea.getxTo() >= transportPosition.getX() && positionArea.getyFrom() <= transportPosition.getY() && positionArea.getyTo() >= transportPosition.getY();
    }
}
