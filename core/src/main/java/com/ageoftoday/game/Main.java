package com.ageoftoday.game;

import com.ageoftoday.camera.CameraController;
import com.ageoftoday.entities.EntityManager;
import com.ageoftoday.entities.units.UnitFactory;
import com.ageoftoday.entities.units.UnitType;
import com.ageoftoday.inputs.InputHandler;
import com.ageoftoday.tiles.TextureManager;
import com.ageoftoday.map.Map;
import com.ageoftoday.ui.Minimap;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class Main extends ApplicationAdapter {
    private static final int TILE_SIZE = 20;
    private static final int VIRTUAL_WIDTH = 1280;
    private static final int VIRTUAL_HEIGHT = 720;
    private Map map;
    private Minimap minimap;
    private OrthographicCamera camera, uiCamera;
    private CameraController cameraController;
    private SpriteBatch batch;
    private TextureManager textureManager;
    private ShapeRenderer shapeRenderer;

    private Viewport viewport, uiViewport;
    private EntityManager entityManager;
    private UnitFactory unitFactory;
    private InputHandler inputHandler;


    @Override
    public void create() {
        map = new Map(135, 135);

        camera = new OrthographicCamera();
        viewport = new FitViewport(VIRTUAL_WIDTH, VIRTUAL_HEIGHT, camera);

        cameraController = new CameraController(camera, map.getWidth(), map.getHeight(), TILE_SIZE);
        batch = new SpriteBatch();
        textureManager = new TextureManager();
        textureManager.initTextures();

        entityManager = EntityManager.getInstance();
        unitFactory = new UnitFactory(textureManager, entityManager);

        unitFactory.createGroups(UnitType.INFANTRY,500,175);
        unitFactory.createGroups(UnitType.ARCHER,750,175);

        uiCamera = new OrthographicCamera();
        uiViewport = new ScreenViewport(uiCamera);

        minimap = new Minimap(map, camera, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        shapeRenderer = new ShapeRenderer();
        inputHandler = new InputHandler(minimap,camera,map);

        // Configuramos el input processor
        Gdx.input.setInputProcessor(inputHandler);
    }

    @Override
    public void render() {
        cameraController.update(Gdx.graphics.getDeltaTime());
        camera.update();

        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        viewport.apply();

        // Renderizamos el mapa
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

                    TextureRegion textureRegion = textureManager.getTextureForTile(map.getTiles(i, j).getTileType());
                    batch.draw(textureRegion, i * TILE_SIZE, j * TILE_SIZE, TILE_SIZE, TILE_SIZE);
                }
            }
        }
        batch.end();

        // Actualizamos y renderizamos las entidades
        float deltaTime = Gdx.graphics.getDeltaTime();
        entityManager.updateEntities(deltaTime);
        entityManager.renderEntities(batch);

        // Renderizamos la UI
        uiCamera.update();
        uiViewport.apply();

        batch.setProjectionMatrix(uiCamera.combined);
        batch.begin();
        minimap.render(batch, textureManager);
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
        textureManager.dispose();
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height);
        uiViewport.update(width, height, true);

        minimap.resize(width, height);
    }

}
