package com.meli.quasar.infrastructure.driven_adapters.event_emit.satellite;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class SatelliteQuery {
    private String name;
}
