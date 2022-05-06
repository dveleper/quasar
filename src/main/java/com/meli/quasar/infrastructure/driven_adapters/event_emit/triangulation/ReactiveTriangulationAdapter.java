package com.meli.quasar.infrastructure.driven_adapters.event_emit.triangulation;

import com.meli.quasar.domain.model.Position;
import com.meli.quasar.domain.model.Satellite;
import com.meli.quasar.domain.model.repository.TriangulationRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.reactivecommons.async.impl.config.annotations.EnableDirectAsyncGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.List;

@Log
@RequiredArgsConstructor
@Component
@EnableDirectAsyncGateway
public class ReactiveTriangulationAdapter implements TriangulationRepository {

    @Autowired
    private ReactiveTriangulationRepository triangulationRepository;

    /**
     * metodo para obtener las coordenadas de la nave a traves del evento del micro de triangulacion (meteoro)
     * @param positions
     * @param distances
     * @return coordenadas de la nave
     */
    @Override
    public Satellite getShip(List<Position> positions, List<Double> distances) {
        TriangulationQuery query = TriangulationQuery.builder()
                .positions(positions)
                .distances(distances)
                .build();
        TriangulationReply triangulationReply = triangulationRepository.getLocationEvent(query).block();
        return Satellite.builder()
                .position(Position.builder()
                        .x((float)triangulationReply.getPosition()[0])
                        .y((float)triangulationReply.getPosition()[1])
                        .build())
                .build();
    }
}
