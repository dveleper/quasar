package com.meli.quasar.domain.model.repository;


import com.meli.quasar.domain.model.Satellite;

import java.util.List;

public interface SatelliteRepository {
    Satellite getSatelliteNyName(String name);
}
