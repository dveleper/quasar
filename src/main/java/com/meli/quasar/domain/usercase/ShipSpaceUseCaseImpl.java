package com.meli.quasar.domain.usercase;

import com.meli.quasar.domain.model.Position;
import com.meli.quasar.domain.model.Satellite;
import com.meli.quasar.domain.model.repository.SatelliteRepository;
import com.meli.quasar.infrastructure.driven_adapters.event_emit.triangulation.ReactiveTriangulationAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.meli.quasar.utils.Constants.TARGET_KENOBI;
import static com.meli.quasar.utils.Constants.TARGET_SATO;
import static com.meli.quasar.utils.Constants.TARGET_SKYWALKER;

@Service
public class ShipSpaceUseCaseImpl implements ShipSpaceUseCase {

    @Autowired
    private SatelliteRepository satelliteRepository;

    @Autowired
    private ReactiveTriangulationAdapter triangulationAdapter;

    @Override
    public float[] getLocation(float[] distances) {

        List<Position> positions = getPositionList();
        List<Double> distancesDouble = getDistancesDouble(distances);

        Satellite ship = triangulationAdapter.getShip(positions, distancesDouble);

        return getLocationShip(ship);
    }

    private float[] getLocationShip(Satellite ship) {
        float[] location = new float[2];
        location[0] = ship.getPosition().getX();
        location[1] = ship.getPosition().getY();
        return location;
    }

    private List<Double> getDistancesDouble(float[] distances) {
        List<Double> distancesDouble = new ArrayList<>();
        distancesDouble.add((double) distances[0]);
        distancesDouble.add((double) distances[1]);
        distancesDouble.add((double) distances[2]);
        return distancesDouble;
    }

    private List<Position> getPositionList() {
        List<Position> positions = new ArrayList<>();
        Arrays.stream(getSatellitesPosition()).forEach(satellite -> {
            positions.add(new Position(satellite.getPosition().getX(), satellite.getPosition().getY()));
        });
        return positions;
    }

    /**
     * obtener las posiciones de los satelites mediante eventos
     * @return lista de objeto completo Satellite
     */
    private Satellite[] getSatellitesPosition() {
        Satellite[] satellites = new Satellite[3];
        satellites[0] = getSatelliteEvent(TARGET_KENOBI);
        satellites[1] = getSatelliteEvent(TARGET_SKYWALKER);
        satellites[2] = getSatelliteEvent(TARGET_SATO);
        return satellites;
    }

    @Override
    public Satellite getSatelliteEvent(String name) {
        return satelliteRepository.getSatelliteNyName(name);
    }

    @Override
    public String[] getMessage(String[] messages) {
        // TODO: procesar la lista de mensajes para retornar un unico mensaje completo
        String [] message = new String[2];
        message[0] = "Mensaje de prueba ";
        message[1] = "sin procesamiento";
        return message;
    }

}
