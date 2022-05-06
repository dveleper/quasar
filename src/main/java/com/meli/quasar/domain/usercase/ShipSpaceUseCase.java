package com.meli.quasar.domain.usercase;

import com.meli.quasar.domain.model.Satellite;

public interface ShipSpaceUseCase {

    float[] getLocation(float[] distances);
    String[] getMessage(String[] messages);
    Satellite getSatelliteEvent(String name);

}
