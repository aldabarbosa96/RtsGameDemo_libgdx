package com.ageoftoday.entities.units.rank;

import com.ageoftoday.entities.units.Unit;
import com.ageoftoday.entities.units.UnitType;
import com.badlogic.gdx.graphics.Texture;

public class Archer extends Unit {
    public Archer(float x, float y,  UnitType type, Texture texture) {
        super(x, y, type, texture);
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
