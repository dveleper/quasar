package com.meli.quasar.infrastructure.driven_adapters.event_emit.triangulation;

import com.meli.quasar.infrastructure.driven_adapters.event_emit.satellite.SatelliteQuery;
import com.meli.quasar.infrastructure.driven_adapters.event_emit.satellite.SatelliteReply;
import com.meli.quasar.utils.Constants;
import org.reactivecommons.async.api.AsyncQuery;
import org.reactivecommons.async.api.DirectAsyncGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import static com.meli.quasar.utils.Constants.EVENT_TRIANGULATION;
import static com.meli.quasar.utils.Constants.TARGET_METEORO;

@Component
public class ReactiveTriangulationRepository {

    @Autowired
    private DirectAsyncGateway directAsyncGateway;

    public Mono<TriangulationReply> getLocationEvent(TriangulationQuery query) {
        AsyncQuery<TriangulationQuery> asyncQuery = new AsyncQuery<>(EVENT_TRIANGULATION, query);
        return directAsyncGateway.requestReply(asyncQuery, TARGET_METEORO, TriangulationReply.class);
    }
}
