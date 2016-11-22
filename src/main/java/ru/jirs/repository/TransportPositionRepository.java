package ru.jirs.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.jirs.entity.TransportPosition;

import java.util.List;

public interface TransportPositionRepository extends JpaRepository<TransportPosition, Integer> {

    @Query("select tp from TransportPosition tp WHERE tp.x >= :xFrom AND tp.x <= :xTo AND tp.y >= :yFrom AND tp.y <= :yTo")
    List<TransportPosition> findForArea(@Param("xFrom") int xFrom, @Param("xTo") int xTo, @Param("yFrom") int yFrom, @Param("yTo") int yTo);
}