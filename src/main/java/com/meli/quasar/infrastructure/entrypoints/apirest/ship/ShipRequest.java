package com.meli.quasar.infrastructure.entrypoints.apirest.ship;

import com.meli.quasar.domain.model.Satellite;
import lombok.Data;
import java.util.List;

@Data
public class ShipRequest {
    private List<Satellite> satellites;
}
