package com.meli.quasar.infrastructure.driven_adapters.event_emit.triangulation;

import com.meli.quasar.domain.model.Position;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class TriangulationQuery {
    List<Position> positions;
    List<Double> distances;
}
