package com.meli.quasar.domain.model.repository;

import com.meli.quasar.domain.model.Position;
import com.meli.quasar.domain.model.Satellite;

import java.util.List;

public interface TriangulationRepository {
    Satellite getShip(List<Position> positions, List<Double> distances);
}
