package com.ageoftoday.game;

import com.ageoftoday.camera.CameraController;
import com.ageoftoday.entities.units.Unit;
import com.ageoftoday.map.Map;
import com.ageoftoday.map.Minimap;
import com.ageoftoday.tiles.Textures;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class Main extends ApplicationAdapter implements InputProcessor {
    private static final int TILE_SIZE = 20;
    private static final int VIRTUAL_WIDTH = 1280;
    private static final int VIRTUAL_HEIGHT = 720;
    private Map map;
    private Minimap minimap;
    private OrthographicCamera camera, uiCamera;
    private CameraController cameraController;
    private SpriteBatch batch;
    private Textures textures;
    private ShapeRenderer shapeRenderer;

    private Viewport viewport, uiViewport;

    @Override
    public void create() {
        map = new Map(135, 135);

        camera = new OrthographicCamera();
        viewport = new FitViewport(VIRTUAL_WIDTH, VIRTUAL_HEIGHT, camera);

        cameraController = new CameraController(camera, map.getWidth(), map.getHeight(), TILE_SIZE);
        batch = new SpriteBatch();
        textures = new Textures();
        textures.initTextures();

        uiCamera = new OrthographicCamera();
        uiViewport = new ScreenViewport(uiCamera);

        minimap = new Minimap(map, camera, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        shapeRenderer = new ShapeRenderer();

        //configuramos el input processor
        Gdx.input.setInputProcessor(this);
    }

    @Override
    public void render() {
        cameraController.update(Gdx.graphics.getDeltaTime());
        camera.update();

        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        viewport.apply();

        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        for (int i = 0; i < map.getWidth(); i++) {
            for (int j = 0; j < map.getHeight(); j++) {
                float mundoX = i * TILE_SIZE;
                float mundoY = j * TILE_SIZE;

                if (mundoX + TILE_SIZE > camera.position.x - camera.viewportWidth / 2 &&
                    mundoX < camera.position.x + camera.viewportWidth / 2 &&
                    mundoY + TILE_SIZE > camera.position.y - camera.viewportHeight / 2 &&
                    mundoY < camera.position.y + camera.viewportHeight / 2) {
                    TextureRegion textureRegion = textures.getTextureForTile(map.getTiles(i, j).getTileType());
                    batch.draw(textureRegion, i * TILE_SIZE, j * TILE_SIZE, TILE_SIZE, TILE_SIZE);
                }
            }
        }
        batch.end();

        uiCamera.update();
        uiViewport.apply();

        batch.setProjectionMatrix(uiCamera.combined);
        batch.begin();
        minimap.render(batch, textures);
        batch.end();

        shapeRenderer.setProjectionMatrix(uiCamera.combined);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        minimap.renderCameraView(shapeRenderer);
        shapeRenderer.setColor(1, 0, 0, 1);
        shapeRenderer.rect(minimap.getMinimapX(), minimap.getMinimapY(), minimap.getMinimapWidth(), minimap.getMinimapHeight());
        shapeRenderer.end();
    }

    @Override
    public void dispose() {
        batch.dispose();
        shapeRenderer.dispose();
        textures.getGrassTexture().dispose();
        textures.getForestTexture().dispose();
        textures.getMountainTexture().dispose();
        textures.getWaterTexture().dispose();
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height);
        uiViewport.update(width, height, true);

        minimap.resize(width, height);
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
        }
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        if (minimap.isPointInside(screenX, screenY)) {

            float[] worldCoords = minimap.screenToWorldCoordinates(screenX, screenY);

            float smoothFactor = 0.075f; //suavidad desplazamiento
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
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchCancelled(int screenX, int screenY, int pointer, int button) {
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
