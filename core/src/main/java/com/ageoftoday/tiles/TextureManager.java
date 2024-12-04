package com.ageoftoday.tiles;

import com.ageoftoday.entities.units.UnitType;
import com.ageoftoday.entities.buildings.BuildingType;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class TextureManager {
    private Texture grassTexture, forestTexture, waterTexture, mountainTexture;
    private Texture infantryTexture, archerTexture, cavalryTexture, citizenTexture;
    private Texture houseTexture, barracksTexture, archeryTexture, barnTexture, urbanCenterTexture;

    public void initTextures() {
        //terreno
        grassTexture = new Texture("grass.png");
        forestTexture = new Texture("forest.png");
        //mountainTexture = new Texture("mountain.png");
        //waterTexture = new Texture("water.png");

        //unidades
        infantryTexture = new Texture("infantry.png");
        archerTexture = new Texture("archer.png");
        //cavalryTexture = new Texture("cavalry.png");
        //citizenTexture = new Texture("citizen.png");

        //edificios
        //houseTexture = new Texture("house.png");
        //barracksTexture = new Texture("barracks.png");
        //archeryTexture = new Texture("archery.png");
        //barnTexture = new Texture("barn.png");
        //urbanCenterTexture = new Texture("urban_center.png");
    }

    public TextureRegion getTextureForTile(TileType type) {
        switch (type) {
            case GRASS:
                return new TextureRegion(grassTexture);
            case FOREST:
                return new TextureRegion(forestTexture);
            default:
                return new TextureRegion(grassTexture);
        }
    }

    public Texture getTextureForUnit(UnitType type) {
        switch (type) {
            case INFANTRY:
                return infantryTexture;
            case ARCHER:
                return archerTexture;
            case CAVALRY:
                return cavalryTexture;
            case CITIZEN:
                return citizenTexture;
            default:
                return null;
        }
    }

    public Texture getTextureForBuilding(BuildingType type) {
        switch (type) {
            case HOUSE:
                return houseTexture;
            default:
                return null;
        }
    }

    public void dispose() {
        grassTexture.dispose();
        forestTexture.dispose();
        //mountainTexture.dispose();
        //waterTexture.dispose();

        infantryTexture.dispose();
        archerTexture.dispose();
        //cavalryTexture.dispose();
        //citizenTexture.dispose();

        //houseTexture.dispose();
        //barracksTexture.dispose();
        //archeryTexture.dispose();
        //barnTexture.dispose();
        //urbanCenterTexture.dispose();
    }
}
