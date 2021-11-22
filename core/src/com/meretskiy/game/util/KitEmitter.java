package com.meretskiy.game.util;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.meretskiy.game.math.Rect;
import com.meretskiy.game.math.Rnd;
import com.meretskiy.game.pool.KitPool;
import com.meretskiy.game.sprite.MedKit;

public class KitEmitter {

    private static final float MED_KIT_HEIGHT = 0.07f;
    private static final int MED_KIT_POWER = 50;

    private final KitPool kitPool;
    private final Rect worldBounds;
    private final TextureRegion[] regions;

    private final Vector2 medKitV = new Vector2(0f, -0.2f);
    private final Vector2 emergingV = new Vector2(0f, -0.4f);

    private int currentLevel;

    public KitEmitter(KitPool kitPool, Rect worldBounds, Texture texture) {
        this.kitPool = kitPool;
        this.worldBounds = worldBounds;
        this.regions = new TextureRegion[1];
        regions[0] = new TextureRegion(texture);
    }

    public void generate(int level) {
        if (level > currentLevel) {
            currentLevel = level;
            if(kitPool.getActiveObjects().isEmpty()) {
                MedKit medKit = kitPool.obtain();
                medKit.set(regions, medKitV, emergingV, MED_KIT_HEIGHT);
                medKit.pos.x = Rnd.nextFloat(
                        worldBounds.getLeft() + medKit.getHalfWidth(),
                        worldBounds.getRight() - medKit.getHalfWidth()
                );
                medKit.setBottom(worldBounds.getTop());
            }
        }
    }
}
