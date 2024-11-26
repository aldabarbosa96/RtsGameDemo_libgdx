package com.ageoftoday.entities.buildings.type;

import com.ageoftoday.entities.EntityManager;
import com.ageoftoday.entities.buildings.Building;
import com.ageoftoday.entities.buildings.BuildingType;
import com.ageoftoday.entities.units.UnitType;
import com.ageoftoday.entities.units.rank.Citizen;
import com.ageoftoday.tiles.TextureManager;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class House extends Building {
    private TextureManager textureManager;

    public House(float x, float y, float width, float height, TextureManager textureManager) {
        super(x, y, width, height, textureManager.getTextureForBuilding(BuildingType.HOUSE), BuildingType.HOUSE.getHealth(), BuildingType.HOUSE.getHealth());
        this.textureManager = textureManager;
    }

    @Override
    public void updateBuilding() {
        if (health < maxHealth) {
            int repairRate = maxHealth / 10;
            health += repairRate * Gdx.graphics.getDeltaTime();
            if (health > maxHealth) {
                health = maxHealth;
            }
        }
    }

    @Override
    public void performAction() {
        Texture citizenTexture = textureManager.getTextureForUnit(UnitType.CITIZEN);
        Citizen citizen = new Citizen(this.getX(), this.getY(), width, height, UnitType.CITIZEN, citizenTexture);
        EntityManager.getInstance().addEntity(citizen);
        System.out.println("¡Ciudadano generado!");
    }
}
