package com.ageoftoday.camera;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;

public class CameraController {
    private static final int CAMERA_SPEED = 750;
    private static final int EDGE_THRESHOLD = 50; //umbral desplazamiento al tocar los bordes
    private final OrthographicCamera camera;
    private final int mapWidth;
    private final int mapHeight;
    private final int tileSize;
    private boolean isEnabled = true; //bandera para activar/desactivar el controlador

    public CameraController(OrthographicCamera camera, int mapWidth, int mapHeight, int tileSize) {
        this.camera = camera;
        this.mapWidth = mapWidth;
        this.mapHeight = mapHeight;
        this.tileSize = tileSize;
    }

    public void update(float deltaTime) {
        if (!isEnabled) return;

        int mouseX = Gdx.input.getX();
        int mouseY = Gdx.input.getY();

        float moveSpeed = CAMERA_SPEED * deltaTime;

        if (mouseX < EDGE_THRESHOLD) {//actualizamos cámara horizontalmente
            camera.position.x -= moveSpeed;
        } else if (mouseX > Gdx.graphics.getWidth() - EDGE_THRESHOLD) {
            camera.position.x += moveSpeed;
        }

        if (mouseY < EDGE_THRESHOLD) {//verticalmente
            camera.position.y += moveSpeed;
        } else if (mouseY > Gdx.graphics.getHeight() - EDGE_THRESHOLD) {
            camera.position.y -= moveSpeed;
        }

        //limitar cámara a los bordes del mapa
        camera.position.x = Math.max(camera.viewportWidth / 2, Math.min(camera.position.x, mapWidth * tileSize - camera.viewportWidth / 2));
        camera.position.y = Math.max(camera.viewportHeight / 2, Math.min(camera.position.y, mapHeight * tileSize - camera.viewportHeight / 2));
    }
}
