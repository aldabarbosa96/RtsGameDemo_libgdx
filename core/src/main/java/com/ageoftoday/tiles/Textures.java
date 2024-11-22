package com.ageoftoday.tiles;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Textures {
    Texture grassTexture, forestTexture, waterTexture, mountainTexture;

    public void initTextures() {
        grassTexture = new Texture("grass.png");
        forestTexture = new Texture("forest.png");
        mountainTexture = new Texture("mountain.png");
        waterTexture = new Texture("water.png");
    }

    public TextureRegion getTextureForTile(TileType type) {
        switch (type) {
            case GRASS:
                return new TextureRegion(grassTexture);
            case FOREST:
                return new TextureRegion(forestTexture);
            case WATER:
                return new TextureRegion(waterTexture);
            case MOUNTAIN:
                return new TextureRegion(mountainTexture);
            default:
                return new TextureRegion(grassTexture);
        }
    }

    public Texture getGrassTexture() {
        return grassTexture;
    }

    public Texture getForestTexture() {
        return forestTexture;
    }

    public Texture getWaterTexture() {
        return waterTexture;
    }

    public Texture getMountainTexture() {
        return mountainTexture;
    }
}
