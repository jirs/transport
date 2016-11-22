package ru.jirs.repository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;
import ru.jirs.TransportApplication;
import ru.jirs.entity.TransportPosition;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = TransportApplication.class)
public class TransportPositionRepositoryTest {

    @Autowired
    TransportPositionRepository transportPositionRepository;

    @Test
    public void findForArea() throws Exception {
        List<TransportPosition> transportPositions = transportPositionRepository.findForArea(0, 200, 0, 200);
        assertThat(transportPositions.size()).isGreaterThan(1);
    }

    @Test
    public void findsAll() {
        Page<TransportPosition> transportPositions = transportPositionRepository.findAll(new PageRequest(0, 10));
        assertThat(transportPositions.getTotalElements()).isGreaterThan(20L);
    }

}