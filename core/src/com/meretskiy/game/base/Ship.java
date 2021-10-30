package com.meretskiy.game.base;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.meretskiy.game.math.Rect;
import com.meretskiy.game.pool.BulletPool;
import com.meretskiy.game.sprite.Bullet;

public class Ship extends Sprite {

    protected BulletPool bulletPool;
    protected Sound bulletSound;
    protected Sound explosionSound;
    protected TextureRegion bulletRegion;
    protected Vector2 bulletV;
    protected Vector2 bulletPos;
    protected float bulletHeight;
    protected int damage;
    protected int hp;

    protected Vector2 v;  // постоянная скорость
    protected Vector2 v0; // константное значение вектора направления
    protected Vector2 emergingV;  // скорость появления

    protected float reloadTimer;
    protected float reloadInterval;
    protected Rect worldBounds;

    public Ship() {
    }

    public Ship(TextureRegion region, int rows, int cols, int frames) {
        super(region, rows, cols, frames);
    }

    @Override
    public void update(float delta) {
        if (getTop() > worldBounds.getTop()) {
            pos.mulAdd(emergingV, delta);
            reloadTimer = 5f;
        } else {
            pos.mulAdd(v, delta);
        }
        reloadTimer += delta;
        if (reloadTimer >= reloadInterval && getTop() < worldBounds.getTop()) {
            reloadTimer = 0f;
            shoot();
        }
        bulletPos.set(pos);
    }

    private void shoot() {
        Bullet bullet = bulletPool.obtain();
        bullet.set(this, bulletRegion, bulletPos, bulletV, worldBounds, bulletHeight, damage);
        bulletSound.play(0.1f);
    }


}
