package com.ageoftoday.entities.units.rank;

import com.ageoftoday.entities.units.Unit;
import com.ageoftoday.entities.units.UnitType;
import com.badlogic.gdx.graphics.Texture;

public class Cavalry extends Unit {
    public Cavalry(float x, float y, Texture texture, UnitType type) {
        super(x, y, type,texture);
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