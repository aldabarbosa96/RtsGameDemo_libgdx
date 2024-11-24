package com.ageoftoday.entities.units;

import com.badlogic.gdx.graphics.Texture;

public class Archer extends Unit {
    public Archer(float x, float y, Texture texture) {
        super(x, y, texture, UnitType.ARCHER);
    }

    @Override
    public void attack() {
        System.out.println(this.getName() + " está atacando con el arco...");
    }

    @Override
    public void move() {
        System.out.println(this.getName() + " se está desplazando a pie a velocidad: " + this.getMovement());

    }

    @Override
    public void work() {

    }
}
