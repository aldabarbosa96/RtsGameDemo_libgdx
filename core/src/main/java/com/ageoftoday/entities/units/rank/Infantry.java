package com.ageoftoday.entities.units.rank;

import com.ageoftoday.entities.units.Unit;
import com.ageoftoday.entities.units.UnitType;
import com.badlogic.gdx.graphics.Texture;

public class Infantry extends Unit {
    public Infantry(float x, float y, UnitType type, Texture texture) {
        super(x, y,type, texture);
    }

    @Override
    public void attack() {
        System.out.println(this.getName() + " está atacando con su espada...");
    }

    @Override
    public void move() {
        System.out.println(this.getName() + " se está moviendo a velocidad: " + this.getMovement());
    }

    @Override
    public void work() {
        //infantería no trabaja
    }
}
