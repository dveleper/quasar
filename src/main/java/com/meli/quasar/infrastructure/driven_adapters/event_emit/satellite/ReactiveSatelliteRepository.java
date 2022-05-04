package com.meli.quasar.infrastructure.driven_adapters.event_emit.satellite;

import com.meli.quasar.utils.Constants;
import org.reactivecommons.async.api.AsyncQuery;
import org.reactivecommons.async.api.DirectAsyncGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
public class ReactiveSatelliteRepository {

    @Autowired
    private DirectAsyncGateway directAsyncGateway;

    public Mono<SatelliteReply> getLocationMeteorEvent(SatelliteQuery query) {
        AsyncQuery<SatelliteQuery> asyncQuery = new AsyncQuery<>(Constants.EVENT_SATELLITE_KENOBI, query);
        return directAsyncGateway.requestReply(asyncQuery, Constants.TARGET_KENOBI, SatelliteReply.class);
    }
}
