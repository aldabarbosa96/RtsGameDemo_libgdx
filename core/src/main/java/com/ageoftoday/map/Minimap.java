package com.ageoftoday.map;

import com.ageoftoday.tiles.Textures;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class Minimap {
    private Map map;
    private OrthographicCamera camera;
    private float minimapWidth, minimapHeight;
    private float tileScale;
    private float minimapX, minimapY;

    public Minimap(Map map, OrthographicCamera camera, int screenWidth, int screenHeight) {
        this.map = map;
        this.camera = camera;
        resize(screenWidth, screenHeight);
    }

    public void resize(int screenWidth, int screenHeight) {
        int margin = 40;
        float maxSize = Math.min(screenWidth, screenHeight) / 4.2f; //ajusta al 25% del tamaño menor de la pantalla

        float aspectRatio = (float) map.getWidth() / map.getHeight();

        if (aspectRatio > 1) {
            minimapWidth = maxSize;
            minimapHeight = (int) (minimapWidth / aspectRatio);
        } else {
            minimapHeight = maxSize;
            minimapWidth = (int) (minimapHeight * aspectRatio);
        }

        minimapX = screenWidth - minimapWidth - margin -22;
        minimapY = margin;

        tileScale = Math.min( minimapWidth / map.getWidth(),  minimapHeight / map.getHeight());
    }


    public void render(SpriteBatch batch, Textures textures) {
        for (int i = 0; i < map.getWidth(); i++) {
            for (int j = 0; j < map.getHeight(); j++) {
                TextureRegion texture = textures.getTextureForTile(map.getTiles(i, j).getTileType());
                float x = minimapX + i * tileScale;
                float y = minimapY + j * tileScale;
                batch.draw(texture, x, y, tileScale, tileScale);
            }
        }
    }

    public void renderCameraView(ShapeRenderer shapeRenderer) {
        float cameraX = (camera.position.x - camera.viewportWidth / 2) / (map.getWidth() * 20f) * minimapWidth;
        float cameraY = (camera.position.y - camera.viewportHeight / 2) / (map.getHeight() * 20f) * minimapHeight;

        float cameraWidth = camera.viewportWidth / (map.getWidth() * 20f) * minimapWidth;
        float cameraHeight = camera.viewportHeight / (map.getHeight() * 20f) * minimapHeight;

        shapeRenderer.setColor(1, 1, 1, 1);
        shapeRenderer.rect(
            minimapX + cameraX,
            minimapY + cameraY,
            cameraWidth,
            cameraHeight
        );
    }


    public boolean isPointInside(int screenX, int screenY) {//verificamos si un punto está dentro del minimapa
        //invertimos la coordenada Y porque la entrada de Gdx.input.getY() está invertida
        int invertedY = Gdx.graphics.getHeight() - screenY;
        return screenX >= minimapX && screenX <= minimapX + minimapWidth &&
            invertedY >= minimapY && invertedY <= minimapY + minimapHeight;
    }


    public float[] screenToWorldCoordinates(int screenX, int screenY) { //convertimos coordenadas de pantalla a coordenadas del mundo
        int invertedY = Gdx.graphics.getHeight() - screenY;

        float minimapRelativeX = screenX - minimapX;
        float minimapRelativeY = invertedY - minimapY;
        float worldX = (minimapRelativeX / minimapWidth) * (map.getWidth() * 24f);
        float worldY = (minimapRelativeY / minimapHeight) * (map.getHeight() * 24f);

        return new float[]{worldX, worldY};
    }


    public float getMinimapX() {
        return minimapX;
    }

    public float getMinimapY() {
        return minimapY;
    }

    public float getMinimapWidth() {
        return minimapWidth;
    }

    public float getMinimapHeight() {
        return minimapHeight;
    }
}
