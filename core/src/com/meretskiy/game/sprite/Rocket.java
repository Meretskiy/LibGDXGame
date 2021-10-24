package com.meretskiy.game.sprite;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.meretskiy.game.base.Sprite;
import com.meretskiy.game.math.Rect;

public class Rocket extends Sprite {

    public static final float V_LEN = 0.02f;
    public static final float SIZE = 0.1f;
    private Vector2 v = new Vector2();
    private Vector2 touch = new Vector2();

    public Rocket(Texture texture) {
        super(new TextureRegion(texture));
    }

    @Override
    public void resize(Rect worldBounds) {
        setHeightProportion(SIZE);
    }

    @Override
    public void update(float delta) {
        if (touch.dst(pos) > V_LEN) {
            pos.add(v);
        } else {
            pos.set(touch);
        }
    }

    @Override
    public boolean touchDown(Vector2 touch, int pointer, int button) {
        this.touch = touch;
        v.set(touch.cpy().sub(pos).setLength(V_LEN));
        return super.touchDown(touch, pointer, button);
    }
}
