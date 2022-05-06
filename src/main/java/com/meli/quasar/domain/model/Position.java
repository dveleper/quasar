package com.meli.quasar.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder(toBuilder = true)
public class Position {
    private float x;
    private float y;

    @Override
    public String toString() {
        return x + ", " + y;
    }
}