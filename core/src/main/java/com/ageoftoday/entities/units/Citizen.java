package com.ageoftoday.entities.units;

import com.badlogic.gdx.graphics.Texture;

public class Citizen extends Unit {
    public Citizen(float x, float y, Texture texture) {
        super(x, y, texture, UnitType.CITIZEN);
    }

    @Override
    public void attack() {
        System.out.println(this.getName() + " está atacando con la hoz...");
    }

    @Override
    public void move() {
        System.out.println(this.getName() + " se está desplazando a pie a velocidad: " + this.getMovement());
    }

    @Override
    public void work() {
        System.out.println(this.getName() + " está trabajando...");
    }
}
