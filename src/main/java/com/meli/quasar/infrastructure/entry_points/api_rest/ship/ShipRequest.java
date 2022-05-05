package com.meli.quasar.infrastructure.entry_points.api_rest.ship;

import com.meli.quasar.domain.model.Satellite;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
public class ShipRequest {
    private List<Satellite> satellites;
}
