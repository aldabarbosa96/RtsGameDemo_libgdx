package com.ageoftoday.inputs;

import com.ageoftoday.map.Map;
import com.ageoftoday.ui.Minimap;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.OrthographicCamera;

public class InputHandler implements InputProcessor {
    private Minimap minimap;
    private OrthographicCamera camera;
    private Map map;
    private static final int TILE_SIZE = 20;

    public InputHandler(Minimap minimap, OrthographicCamera camera, Map map) {
        this.minimap = minimap;
        this.camera = camera;
        this.map = map;
    }

    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        //verificamos si el clic fue en el minimapa
        if (minimap.isPointInside(screenX, screenY)) {
            //convertimos las coordenadas de pantalla a coordenadas del mundo
            float[] worldCoords = minimap.screenToWorldCoordinates(screenX, screenY);

            camera.position.x = worldCoords[0];
            camera.position.y = worldCoords[1];

            //aseguramos que la cámara no se salga de los límites del mapa
            camera.position.x = Math.max(camera.viewportWidth / 2, Math.min(camera.position.x, map.getWidth() * TILE_SIZE - camera.viewportWidth / 2));
            camera.position.y = Math.max(camera.viewportHeight / 2, Math.min(camera.position.y, map.getHeight() * TILE_SIZE - camera.viewportHeight / 2));

            return true;
        } else {
            //todo-> hacer lógica de selección por arrastre con el ratón
        }
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchCancelled(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        if (minimap.isPointInside(screenX, screenY)) {

            float[] worldCoords = minimap.screenToWorldCoordinates(screenX, screenY);

            float smoothFactor = 0.15f; //suavidad desplazamiento (sino tiembla mucho)
            camera.position.x += (worldCoords[0] - camera.position.x) * smoothFactor;
            camera.position.y += (worldCoords[1] - camera.position.y) * smoothFactor;

            camera.position.x = Math.max(camera.viewportWidth / 2, Math.min(camera.position.x, map.getWidth() * TILE_SIZE - camera.viewportWidth / 2));
            camera.position.y = Math.max(camera.viewportHeight / 2, Math.min(camera.position.y, map.getHeight() * TILE_SIZE - camera.viewportHeight / 2));

            camera.update();
            return true;
        }
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(float amountX, float amountY) {
        return false;
    }
}
