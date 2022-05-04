package com.meli.quasar.infrastructure.driven_adapters.event_emit.satellite;

import com.meli.quasar.domain.model.Satellite;
import com.meli.quasar.domain.model.repository.SatelliteRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.reactivecommons.async.impl.config.annotations.EnableDirectAsyncGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

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
        return Satellite.builder()
                .distance(Float.parseFloat(satelliteReply.getDistance()))
                .message(satelliteReply.getMessage())
                .build();
    }
}
