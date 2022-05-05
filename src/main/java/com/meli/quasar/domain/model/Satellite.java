package com.meli.quasar.domain.model;

import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;

@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class Satellite {
    private String nombre;
    private double distancia;
    private String[] mensaje;
    private Position position;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getDistancia() {
        return distancia;
    }

    public void setDistancia(double distancia) {
        this.distancia = distancia;
    }

    public String[] getMensaje() {
        return mensaje;
    }

    public void setMensaje(String[] mensaje) {
        this.mensaje = mensaje;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }
}

