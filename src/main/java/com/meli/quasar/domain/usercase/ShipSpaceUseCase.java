package com.meli.quasar.domain.usercase;

import com.meli.quasar.domain.model.Satellite;
import com.meli.quasar.infrastructure.entry_points.api_rest.exception.LocationException;
import com.meli.quasar.infrastructure.entry_points.api_rest.exception.MessageException;

import java.util.List;

public interface ShipSpaceUseCase {

    float[] getLocation(float[] distances) throws LocationException;
    String[] getMessage(List<String[]> listMessages) throws MessageException;
    Satellite getSatelliteEvent(String name);

}
