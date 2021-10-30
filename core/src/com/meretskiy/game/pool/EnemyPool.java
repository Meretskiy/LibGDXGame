package com.meretskiy.game.pool;

import com.badlogic.gdx.audio.Sound;
import com.meretskiy.game.base.SpritesPool;
import com.meretskiy.game.math.Rect;
import com.meretskiy.game.sprite.EnemyShip;

public class EnemyPool extends SpritesPool<EnemyShip> {

    private final BulletPool bulletPool;
    private final Rect worldBounds;
    private final Sound bulletSound;
    private final Sound explosionSound;

    public EnemyPool(BulletPool bulletPool, Rect worldBounds, Sound bulletSound, Sound explosionSound) {
        this.bulletPool = bulletPool;
        this.worldBounds = worldBounds;
        this.bulletSound = bulletSound;
        this.explosionSound = explosionSound;
    }

    @Override
    protected EnemyShip newObject() {
        return new EnemyShip(bulletPool, worldBounds, bulletSound, explosionSound);
    }
}
