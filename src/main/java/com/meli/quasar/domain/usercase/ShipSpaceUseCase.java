package com.meli.quasar.domain.usercase;

import com.meli.quasar.domain.model.Satellite;

import java.util.List;

public interface ShipSpaceUseCase {

    float[] getLocation(float[] distances);
    String[] getMessage(List<String[]> listMessages);
    Satellite getSatelliteEvent(String name);

}
