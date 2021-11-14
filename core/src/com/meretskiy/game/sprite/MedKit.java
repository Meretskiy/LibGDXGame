package com.meretskiy.game.sprite;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.meretskiy.game.base.Kit;
import com.meretskiy.game.math.Rect;
import com.meretskiy.game.pool.BulletPool;
import com.meretskiy.game.pool.ExplosionPool;

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

    public void set(TextureRegion[] regions, Vector2 v, Vector2 emergingV, float height) {
        this.regions = regions;
        this.v.set(v);
        this.emergingV.set(emergingV);
        setHeightProportion(height);
    }
}
