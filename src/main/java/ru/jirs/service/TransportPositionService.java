package ru.jirs.service;

import ru.jirs.entity.TransportPosition;
import ru.jirs.pojo.PositionArea;

import java.util.List;

public interface TransportPositionService {
    List<TransportPosition> getAll();
    TransportPosition getById(int id);
    TransportPosition save(TransportPosition transportPosition);
    TransportPosition add(TransportPosition transportPosition);
    boolean delete(int id);

    List<TransportPosition> getForArea(int xFrom, int xTo, int yFrom, int yTo);
    List<TransportPosition> getForArea();

    PositionArea getPositionArea();
    void setPositionArea(PositionArea newPositionArea);
}
