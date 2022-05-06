package com.meli.quasar.infrastructure.entry_points.api_rest.ship;

import com.meli.quasar.domain.model.Position;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class ShipNamePostResponse {
    private double distance;
    private String message;
}
