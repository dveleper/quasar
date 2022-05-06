package com.meli.quasar.infrastructure.driven_adapters.event_emit.satellite;

import com.meli.quasar.domain.model.Position;
import com.meli.quasar.domain.model.Satellite;
import com.meli.quasar.domain.model.repository.SatelliteRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.reactivecommons.async.impl.config.annotations.EnableDirectAsyncGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Log
@RequiredArgsConstructor
@Component
@EnableDirectAsyncGateway
public class ReactiveSatelliteAdapter implements SatelliteRepository {

    @Autowired
    private ReactiveSatelliteRepository reactiveSatelliteRepository;

    @Override
    public Satellite getSatelliteNyName(String name) {
        SatelliteReply satelliteReply = reactiveSatelliteRepository.getLocationMeteorEvent(SatelliteQuery.builder().name(name).build()).block();
        String[] coordinates = satelliteReply.getCoordinates().split(",");
        return Satellite.builder()
                .distance(Double.parseDouble(satelliteReply.getDistance()))
                .message(satelliteReply.getMessage().split(","))
                .position(Position.builder()
                        .x(Float.parseFloat(coordinates[0]))
                        .y(Float.parseFloat(coordinates[1]))
                        .build())
                .build();
    }
}
