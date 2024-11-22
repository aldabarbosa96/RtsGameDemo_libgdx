package com.ageoftoday.map;

import com.ageoftoday.tiles.Tile;
import com.ageoftoday.tiles.TileType;

import java.util.Random;

public class Map {
    private final int width;
    private final int height;
    private final Tile[][] tiles;

    public Map(int width, int height) {
        this.width = width;
        this.height = height;
        tiles = new Tile[width][height];
        generateRandomMap();
    }

    private void generateRandomMap() {//en un futuro habrá que dibujar los mapas
        Random random = new Random();

        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                TileType type = TileType.values()[random.nextInt(TileType.values().length)];
                tiles[i][j] = new Tile(i, j, type);
            }
        }
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public Tile getTiles(int x, int y) {
        return tiles[x][y];
    }
}
