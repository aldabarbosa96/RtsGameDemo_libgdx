package com.ageoftoday.entities.units;

import com.badlogic.gdx.graphics.Texture;

public class Cavalry extends Unit {
    public Cavalry(float x, float y, Texture texture) {
        super(x, y, texture, UnitType.CAVALRY);
    }

    @Override
    public void attack() {
        System.out.println(this.getName() + " está atacando montado a caballo...");
    }

    @Override
    public void move() {
        System.out.println(this.getName() + " se está desplazando a caballo a velocidad: " + this.getMovement());
    }

    @Override
    public void work() {

    }
}
