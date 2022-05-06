package com.meli.quasar.infrastructure.entrypoints.apirest.ship;

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
