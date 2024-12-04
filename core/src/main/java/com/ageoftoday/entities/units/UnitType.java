package com.ageoftoday.entities.units;

public enum UnitType {
    CITIZEN("Citizen", 70, 5, 0, 1.5f, 30, 30),
    INFANTRY("Infantry", 100, 10, 3, 2f, 45, 55),
    CAVALRY("Cavalry", 150, 15, 6, 4f, 50, 60),
    ARCHER("Archer", 80, 12, 1, 2.5f, 40, 50);

    private final String name;
    private final int health, attack, defense;
    private final float movement;
    private final float width, height;

    UnitType(String name, int health, int attack, int defense, float movement, float width, float height) {
        this.name = name;
        this.health = health;
        this.attack = attack;
        this.defense = defense;
        this.movement = movement;
        this.width = width;
        this.height = height;
    }

    public String getName() {
        return name;
    }

    public int getHealth() {
        return health;
    }

    public int getAttack() {
        return attack;
    }

    public int getDefense() {
        return defense;
    }

    public float getMovement() {
        return movement;
    }

    public float getWidth() {
        return width;
    }

    public float getHeight() {
        return height;
    }
}

