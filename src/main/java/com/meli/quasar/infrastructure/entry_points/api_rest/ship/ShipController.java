package com.meli.quasar.infrastructure.entry_points.api_rest.ship;

import com.meli.quasar.domain.model.Position;
import com.meli.quasar.domain.model.Satellite;
import com.meli.quasar.domain.usercase.ShipSpaceUseCase;
import com.meli.quasar.infrastructure.entry_points.api_rest.exception.LocationException;
import com.meli.quasar.infrastructure.entry_points.api_rest.exception.MessageException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/")
public class ShipController {

    @Autowired
    private ShipSpaceUseCase shipSpaceUseCase;

    @PostMapping("/topsecret")
    public ResponseEntity<ShipResponse> getTopSecret(RequestEntity<ShipRequest> request) {
        try {
            List<Satellite> satellites = request.getBody().getSatellites();
            if (validateRequest(satellites)) {
                float[] location = shipSpaceUseCase.getLocation(getDistances(satellites));
                String[] message = shipSpaceUseCase.getMessage(getMessagesSatellites(satellites));
                return new ResponseEntity(ShipResponse.builder()
                        .position(Position.builder().x(location[0]).y(location[1]).build())
                        .message(String.join(" ", message))
                        .build(), HttpStatus.OK);
            } else {
                return new ResponseEntity(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("topsecret_split/{satellite_name}")
    public ResponseEntity<ShipNamePostResponse> getTopSecretSplitPost(@PathVariable String satellite_name) {
        try {
            return Optional.of(shipSpaceUseCase.getSatelliteEvent(satellite_name)).map(
                    satellite -> {
                        return new ResponseEntity<>(
                                ShipNamePostResponse.builder()
                                        .distance(satellite.getDistance())
                                        .message(Arrays.toString(satellite.getMessage()))
                                        .build(), HttpStatus.OK);
                    }).orElse(new ResponseEntity<>(ShipNamePostResponse.builder().build(), HttpStatus.NOT_FOUND));
        } catch (Exception e) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("topsecret_split/{satellite_name}")
    public ResponseEntity<ShipNameGetResponse> getTopSecretSplitGet(@PathVariable String satellite_name) {
        try {
            return Optional.of(shipSpaceUseCase.getSatelliteEvent(satellite_name)).map(
                    satellite -> {
                        return new ResponseEntity<>(
                                ShipNameGetResponse.builder()
                                        .position(satellite.getPosition())
                                        .message(Arrays.toString(satellite.getMessage()))
                                        .build(), HttpStatus.OK);
                    }).orElse(new ResponseEntity<>(ShipNameGetResponse.builder().build(), HttpStatus.NOT_FOUND));
        } catch (Exception e) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }

    private List<String[]> getMessagesSatellites(List<Satellite> satellites) {
        List<String[]> listMessages = new ArrayList<>();
        for(Satellite satellite : satellites) {
            if (satellite.getMessage() == null) continue;
            listMessages.add(satellite.getMessage());
        }
        return listMessages;
    }

    private float[] getDistances(List<Satellite> satellites) {
        float[] distances = new float[3];
        if (satellites.size() == 3) {
            distances[0] = (float) satellites.get(0).getDistance();
            distances[1] = (float) satellites.get(1).getDistance();
            distances[2] = (float) satellites.get(0).getDistance();
            return distances;
        }
        return distances;
    }

    private boolean validateRequest(List<Satellite> satellites) {
        if (!satellites.isEmpty() && satellites.size() == 3) {
            return true;
        }
        return false;
    }
}
