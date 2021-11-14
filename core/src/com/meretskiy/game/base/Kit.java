package com.meretskiy.game.base;

import com.badlogic.gdx.math.Vector2;
import com.meretskiy.game.math.Rect;

public class Kit extends Sprite {

    protected Vector2 v;
    protected Vector2 emergingV;
    protected Rect worldBounds;

    public Kit() {
    }

    @Override
    public void update(float delta) {
        if (getTop() > worldBounds.getTop()) {
            pos.mulAdd(emergingV, delta);
        } else {
            pos.mulAdd(v, delta);
        }
    }
}
