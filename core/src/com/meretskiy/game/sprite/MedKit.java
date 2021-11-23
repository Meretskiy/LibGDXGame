package com.meretskiy.game.sprite;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.meretskiy.game.base.Kit;
import com.meretskiy.game.math.Rect;

public class MedKit extends Kit {

    public MedKit(Rect worldBounds) {
        this.worldBounds = worldBounds;
        this.v = new Vector2();
        this.emergingV = new Vector2();
    }

    @Override
    public void update(float delta) {
        super.update(delta);
        if (getBottom() < worldBounds.getBottom()) {
            destroy();
        }
    }

    public void set(TextureRegion[] regions, Vector2 v, Vector2 emergingV, float height, int power) {
        this.regions = regions;
        this.v.set(v);
        this.emergingV.set(emergingV);
        this.power = power;
        setHeightProportion(height);
    }
}
