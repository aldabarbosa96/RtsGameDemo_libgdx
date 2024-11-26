package com.ageoftoday.entities.units;

import com.ageoftoday.entities.Entity;
import com.badlogic.gdx.graphics.Texture;

public abstract class Unit extends Entity {
    protected UnitType type;
    protected int currentHealth;

    public Unit(float x, float y, float width, float height, UnitType type, Texture texture) {
        super(x, y,width, height, texture);
        this.type = type;
        this.currentHealth = type.getHealth();
    }

    public void takeDamage(int damage) {
        currentHealth -= damage;
        if (currentHealth < 0) {
            currentHealth = 0;
        }
    }

    // Getters
    public String getName() { return type.getName(); }
    public int getHealth() { return currentHealth; }
    public int getAttack() { return type.getAttack(); }
    public int getDefense() { return type.getDefense(); }
    public float getMovement() { return type.getMovement(); }

    // Métodos abstractos
    public abstract void attack();
    public abstract void move();
    public abstract void work();
}
