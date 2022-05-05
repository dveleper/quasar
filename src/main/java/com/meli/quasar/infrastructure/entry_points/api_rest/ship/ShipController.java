package com.meli.quasar.infrastructure.entry_points.api_rest.ship;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.meli.quasar.domain.model.Position;
import com.meli.quasar.domain.model.Satellite;
import com.meli.quasar.domain.usercase.ShipSpaceUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("/")
public class ShipController {

    @Autowired
    private ShipSpaceUseCase shipSpaceUseCase;

    @PostMapping("/topsecret")
    public ResponseEntity<ShipResponse> getTopSecret(RequestEntity<ShipRequest> request) {
        List<Satellite> satellites = request.getBody().getSatellites();
        if (validateRequest(satellites)) {
            float[] location = shipSpaceUseCase.getLocation(getDistances(satellites));
            return new ResponseEntity(ShipResponse.builder()
                    .position(Position.builder().x(location[0]).y(location[1]).build())
                    .message(shipSpaceUseCase.getMessage(satellites.get(0).getMensaje())) // temporal porque hay que llamar al metodo que procesa los mensajes
                    .build(), HttpStatus.OK);
        } else {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }

    }

    private float[] getDistances(List<Satellite> satellites) {
        float[] distances = new float[3];
        distances[0] = (float) satellites.get(0).getDistancia();
        distances[1] = (float) satellites.get(1).getDistancia();
        distances[2] = (float) satellites.get(0).getDistancia();
        return distances;
    }

    private boolean validateRequest(List<Satellite> satellites) {
        if (!satellites.isEmpty() && satellites.size() > 2) {
            return true;
        }
        return false;
    }
}
