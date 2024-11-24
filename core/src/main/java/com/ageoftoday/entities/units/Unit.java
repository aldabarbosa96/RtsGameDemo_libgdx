package com.ageoftoday.entities.units;

import com.ageoftoday.entities.Entity;
import com.badlogic.gdx.graphics.Texture;

public abstract class Unit extends Entity {
    private UnitType type;

    public Unit(float x, float y, Texture texture, UnitType type) {
        super(x, y, texture);
        this.type = type;
    }

    public String getName() {
        return type.getName();
    }

    public int getHealth() {
        return type.getHealth();
    }

    public int getAttack() {
        return type.getAttack();
    }

    public int getDefense() {
        return type.getDefense();
    }

    public float getMovement() {
        return type.getMovement();
    }


    public abstract void attack();
    public abstract void move();
    public abstract void work();

}

