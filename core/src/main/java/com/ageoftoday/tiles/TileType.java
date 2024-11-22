package com.ageoftoday.tiles;


public enum TileType {
    GRASS("Prado","green"),
    MOUNTAIN("Montaña","gray"),
    WATER("Agua","blue"),
    FOREST("Bosque","darkgreen");

    final private String nombre;
    final private String color;

    TileType(String nombre, String color) {
        this.nombre = nombre;
        this.color = color;
    }

    public String getNombre() {
        return nombre;
    }

    public String getColor() {
        return color;
    }
}
