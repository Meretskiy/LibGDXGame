package com.meretskiy.game.pool;

import com.badlogic.gdx.graphics.Texture;
import com.meretskiy.game.base.SpritesPool;
import com.meretskiy.game.math.Rect;
import com.meretskiy.game.sprite.MedKit;

public class KitPool extends SpritesPool<MedKit> {

    private final Rect worldBounds;
    private final Texture texture;

    public KitPool(Rect worldBounds, Texture texture) {
        this.worldBounds = worldBounds;
        this.texture = texture;
    }

    @Override
    protected MedKit newObject() {
        return new MedKit(worldBounds);
    }
}
