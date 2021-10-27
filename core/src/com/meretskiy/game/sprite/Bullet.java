package com.meretskiy.game.sprite;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.meretskiy.game.base.Sprite;
import com.meretskiy.game.math.Rect;

public class Bullet extends Sprite {

    private final Vector2 v = new Vector2();

    private Rect worldBounds;
    private int damage;
    private Sprite owner;

    public Bullet() {
        regions = new TextureRegion[1];
    }

    public void set(
            Sprite owner,          // владелец пули
            TextureRegion region,  // текстура пули
            Vector2 pos,           // начальная позиция
            Vector2 v,             // начальная скорость
            Rect worldBounds,      // границы мира
            float height,          // размер пули
            int damage             // урон от пули
    ) {
        this.owner = owner;
        this.regions[0] = region;
        this.pos.set(pos);
        this.v.set(v);
        this.worldBounds = worldBounds;
        setHeightProportion(height);
        this.damage = damage;
    }

    @Override
    public void update(float delta) {
        pos.mulAdd(v, delta);
        if (isOutside(worldBounds)) {
            destroy();
        }
    }

    public int getDamage() {
        return damage;
    }

    public Sprite getOwner() {
        return owner;
    }
}
