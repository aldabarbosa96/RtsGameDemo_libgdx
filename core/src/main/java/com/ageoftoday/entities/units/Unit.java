package com.ageoftoday.entities.units;

import com.ageoftoday.entities.Entity;
import com.badlogic.gdx.graphics.Texture;

public abstract class Unit extends Entity {
    private UnitType type;
    private int currentHealth;

    public Unit(float x, float y, UnitType type, Texture texture) {
        super(x, y, type.getWidth(), type.getHeight(), texture);
        this.type = type;
        this.currentHealth = type.getHealth();
    }

    public void takeDamage(int damage) {
        currentHealth -= damage;
        if (currentHealth < 0) {
            currentHealth = 0;
        }
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

    public int getCurrentHealth() {
        return currentHealth;
    }

    public abstract void attack();

    public abstract void move();

    public abstract void work();
}
