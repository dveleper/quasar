package com.meli.quasar.infrastructure.entrypoints.apirest.ship;

import com.meli.quasar.domain.model.Position;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class ShipResponse {
    private Position position;
    private String message;
}
