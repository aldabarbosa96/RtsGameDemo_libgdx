package com.ageoftoday.entities.buildings;

import com.ageoftoday.entities.Entity;
import com.badlogic.gdx.graphics.Texture;

public abstract class Building extends Entity {
    protected int health;
    protected int maxHealth;

    public Building(float x, float y,float width, float height, Texture texture, int health, int maxHealth) {
        super(x, y,width, height, texture);
        this.health = health;
        this.maxHealth = maxHealth;
    }

    public void takeDamage(int damage) {
        this.health -= damage;
        if (this.health < 0) {
            this.health = 0;
        }
    }

    public void repair(float deltaTime) {
        int repairRate = maxHealth / 10;
        if (health < maxHealth) {
            health += repairRate * deltaTime;
            if (health > maxHealth) {
                health = maxHealth;
            }
        }
    }

    public boolean isDestroyed() {
        return this.health == 0;
    }

    public abstract void updateBuilding();
    public abstract void performAction();
}
