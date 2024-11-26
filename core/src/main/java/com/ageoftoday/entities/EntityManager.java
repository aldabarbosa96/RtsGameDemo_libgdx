package com.ageoftoday.entities;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.ArrayList;
import java.util.List;

public class EntityManager {
    private static EntityManager instance;
    private List<Entity> entities;

    private EntityManager() {
        entities = new ArrayList<>();
    }

    public static EntityManager getInstance() {
        if (instance == null) {
            instance = new EntityManager();
        }
        return instance;
    }

    public void addEntity(Entity entity) {
        entities.add(entity);
    }

    public void removeEntity(Entity entity) {
        entities.remove(entity);
    }

    public void updateEntities(float deltaTime) {
        for (Entity entity : new ArrayList<>(entities)) {
            entity.update(deltaTime);
        }
    }

    public void renderEntities(SpriteBatch batch) {
        batch.begin();
        for (Entity entity : entities) {
            entity.render(batch);
        }
        batch.end();
    }

    public List<Entity> getEntities() {
        return entities;
    }
}
