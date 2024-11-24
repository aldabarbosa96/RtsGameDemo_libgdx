package com.ageoftoday.entities.units;

import com.badlogic.gdx.graphics.Texture;

public class Infantry extends Unit {
    public Infantry(float x, float y, Texture texture) {
        super(x, y, texture, UnitType.INFANTRY);
    }

    @Override
    public void attack() {
        System.out.println(this.getName() + " está atacando con la lanza...");
    }

    @Override
    public void move() {
        System.out.println(this.getName() + " se está desplazando a pie a velocidad: " + this.getMovement());

    }

    @Override
    public void work() {
    }
}
