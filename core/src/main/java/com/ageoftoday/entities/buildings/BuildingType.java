package com.ageoftoday.entities.buildings;

public enum BuildingType {
    HOUSE("Casa", 180, 0, 0, 0),
    BARRACKS("Cuartel", 250, 15f, 10f, 10f),
    ARCHERY("Arquería", 220, 10f, 8f, 5f),
    BARN("Establo", 280, 20f, 15f, 10f),
    URBAN_CENTER("Centro Urbano", 500, 35f, 25f, 20f);

    private final String name;
    private final int health;
    private final float fireRes, projectileRes, meleeRes;

    BuildingType(String name, int health, float fireRes, float projectileRes, float meleeRes) {
        this.name = name;
        this.health = health;
        this.fireRes = fireRes;
        this.projectileRes = projectileRes;
        this.meleeRes = meleeRes;
    }

    public String getName() {
        return name;
    }

    public int getHealth() {
        return health;
    }

    public float getFireRes() {
        return fireRes;
    }

    public float getProjectileRes() {
        return projectileRes;
    }

    public float getMeleeRes() {
        return meleeRes;
    }
}
