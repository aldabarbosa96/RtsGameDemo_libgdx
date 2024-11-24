package com.ageoftoday.entities.buildings;

import com.ageoftoday.entities.Entity;
import com.badlogic.gdx.graphics.Texture;

public abstract class Building extends Entity {
    protected int health;
    protected int maxHealth;

    public Building(float x, float y, Texture texture, int health, int maxHealth) {
        super(x, y, texture);
        this.health = health;
        this.maxHealth = maxHealth;
    }

    public void takeDamage(int damage) {
        this.health -= damage;
        if (this.health < 0) {
            this.health = 0;
        }
    }

    public void repair() throws InterruptedException {
        while (maxHealth >= health) {
            int coeficienteReparacion = maxHealth / 10;
            Thread.sleep((long) (Math.random() * 250));
            health += coeficienteReparacion;
        }
    }

    public boolean isDestroyed() {
        return this.health == 0;
    }

    public abstract void updateBuilding();

    public abstract void performAction();
}
