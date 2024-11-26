package com.ageoftoday.entities.units;

public enum UnitType {
    CITIZEN("Ciudadano", 70, 5, 0, 1.5f),
    INFANTRY("Infantería", 100, 10, 3, 2f),
    CAVALRY("Caballería", 150, 15, 6, 4f),
    ARCHER("Arquero", 80, 12, 1, 2.5f);

    private final String name;
    private final int health, attack, defense;
    private final float movement;

    UnitType(String name, int health, int attack, int defense, float movement) {
        this.name = name;
        this.health = health;
        this.attack = attack;
        this.defense = defense;
        this.movement = movement;
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
}
