package com.ageoftoday.entities.units;

import com.ageoftoday.entities.Entity;
import com.badlogic.gdx.graphics.Texture;

public abstract class Unit extends Entity {
    private String name;
    private int health,attack, defense;
    private float movement;

    public Unit(float x, float y, Texture texture, String name, int health, int attack, int defense, float movement) {
        super(x, y, texture);
        this.name = name;
        this.health = health;
        this.attack = attack;
        this.defense = defense;
        this.movement = movement;
    }


}

