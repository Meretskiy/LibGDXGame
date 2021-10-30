package com.meretskiy.game.sprite;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.meretskiy.game.base.Ship;
import com.meretskiy.game.math.Rect;
import com.meretskiy.game.pool.BulletPool;

public class EnemyShip extends Ship {

    public EnemyShip(BulletPool bulletPool, Rect worldBounds, Sound bulletSound, Sound explosionSound) {
        this.bulletPool = bulletPool;
        this.worldBounds = worldBounds;
        this.bulletSound = bulletSound;
        this.explosionSound = explosionSound;
        this.bulletV = new Vector2();
        this.bulletPos = new Vector2();
        this.v = new Vector2();
        this.v0 = new Vector2();
        this.emergingV = new Vector2();
    }

    @Override
    public void update(float delta) {
        super.update(delta);
//        bulletPos.set(this.pos.x, getBottom()); // стрельба из носа коробля
        if (getBottom() < worldBounds.getBottom()) {
            destroy();
            explosionSound.play(0.1f);
        }
    }

    public void set(
            TextureRegion[] regions,
            Vector2 v,
            Vector2 emergingV,
            TextureRegion bulletRegion,
            float bulletHeight,
            Vector2 bulletV,
            int damage,
            int hp,
            float reloadInterval,
            float height
    ) {
        this.regions = regions;
        this.v.set(v);
        this.emergingV.set(emergingV);
        this.bulletRegion = bulletRegion;
        this.bulletHeight = bulletHeight;
        this.bulletV.set(bulletV);
        this.damage = damage;
        this.hp = hp;
        this.reloadInterval = reloadInterval;
        setHeightProportion(height);
    }
}
