package com.ageoftoday.entities.units;

import com.ageoftoday.entities.EntityManager;
import com.ageoftoday.entities.units.rank.Archer;
import com.ageoftoday.entities.units.rank.Cavalry;
import com.ageoftoday.entities.units.rank.Citizen;
import com.ageoftoday.entities.units.rank.Infantry;
import com.ageoftoday.tiles.TextureManager;
import com.badlogic.gdx.graphics.Texture;

import java.util.ArrayList;
import java.util.List;

public class UnitFactory { //clase que gestionar la creación y destrucción de unidades
    private TextureManager textureManager;
    private EntityManager entityManager;

    public UnitFactory(TextureManager textureManager, EntityManager entityManager) {
        this.textureManager = textureManager;
        this.entityManager = entityManager;
    }

    public Unit createUnit(UnitType type, int cordX, int cordY) {
        Unit unit = null;
        Texture texture = textureManager.getTextureForUnit(type);

        switch (type) {
            case INFANTRY:
                unit = new Infantry(cordX, cordY, type, texture);
                break;
            case CITIZEN:
                unit = new Citizen(cordX, cordY, type, texture);
                break;
            case ARCHER:
                unit = new Archer(cordX, cordY, type, texture);
            case CAVALRY:
                unit = new Cavalry(cordX, cordY, type, texture);
                break;
            default:
                throw new IllegalArgumentException("UnitType unkown -> " + type);
        }

        if (unit != null) entityManager.addEntity(unit);

        return unit;
    }

    public void deleteUnit(Unit unit) {
        entityManager.removeEntity(unit);
    }

    public List<Unit> createGroups(UnitType type, int cordX, int cordY) { //prueba para generar grupos de unidades por tipos
        List<Unit> units = new ArrayList<>();

        int filas = 8;
        int columnas = 4;

        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                Unit unit = createUnit(type, cordX + i * 30, cordY + j * 40);
                units.add(unit);
            }
        }
        return units;
    }

}

