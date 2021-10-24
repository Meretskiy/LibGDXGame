package com.meretskiy.game.sprite;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.meretskiy.game.base.Sprite;
import com.meretskiy.game.math.Rect;

public class Ship extends Sprite {

    public static final float V_LEN = 0.0001f;
    public static final float SIZE = 0.1f;
    private final Vector2 v;
    private Rect worldBounds;

    public Ship(TextureAtlas atlas) {
        super(new TextureRegion(
                atlas.findRegion("main_ship").getTexture(),
                916, 95, 195, 287));
        v = new Vector2();
        pos.set(0, -0.4f);
    }

    @Override
    public void resize(Rect worldBounds) {
        this.worldBounds = worldBounds;
        setHeightProportion(SIZE);
    }

    @Override
    public void update(float delta) {
        pos.mulAdd(v, delta);
        checkBounds();
    }

    private void checkBounds() {
        if (getRight() < worldBounds.getLeft()) {
            pos.set(worldBounds.getLeft(), pos.y);
            v.set(0, 0);
        }
        if (getLeft() > worldBounds.getRight()) {
            pos.set(worldBounds.getRight(), pos.y);
            v.set(0, 0);
        }
    }

    @Override
    public boolean touchDown(Vector2 touch, int pointer, int button) {
        if (touch.x < 0) {
            v.set(worldBounds.getLeft(), 0);
        } else {
            v.set(worldBounds.getRight(), 0);
        }
        return super.touchDown(touch, pointer, button);
    }

    @Override
    public boolean keyDown(int keycode) {
        if (keycode == 29) {
            v.set(worldBounds.getLeft(), 0);
        }
        if (keycode == 32) {
            v.set(worldBounds.getRight(), 0);
        }
        return super.keyDown(keycode);
    }

}
