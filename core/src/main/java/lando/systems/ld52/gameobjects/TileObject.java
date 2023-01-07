package lando.systems.ld52.gameobjects;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class TileObject implements GameObject{
    protected float margin = 10f;
    Tile tile;

    public TileObject(Tile tile) {
        this.tile = tile;
    }

    @Override
    public void update(float dt) {

    }

    @Override
    public void render(SpriteBatch batch) {

    }

    /**
     * Called when this tile object was collected with a click
     * @return
     */
    public boolean collect() {
        return false;
    }
}