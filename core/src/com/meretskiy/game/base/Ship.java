package com.meretskiy.game.base;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.meretskiy.game.math.Rect;
import com.meretskiy.game.pool.BulletPool;
import com.meretskiy.game.pool.ExplosionPool;
import com.meretskiy.game.sprite.Bullet;
import com.meretskiy.game.sprite.Explosion;

public class Ship extends Sprite {

    private static final float DAMAGE_ANIMATE_INTERVAL = 0.1f;

    protected ExplosionPool explosionPool;
    protected BulletPool bulletPool;
    protected Sound bulletSound;
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

    private float damageAnimateTimer = DAMAGE_ANIMATE_INTERVAL;

    public Ship() {
    }

    public Ship(TextureRegion region, int rows, int cols, int frames) {
        super(region, rows, cols, frames);
    }

    @Override
    public void update(float delta) {
        if (getTop() > worldBounds.getTop()) {
            pos.mulAdd(emergingV, delta);
            reloadTimer = reloadInterval * 0.9f;
        } else {
            pos.mulAdd(v, delta);
        }
        reloadTimer += delta;
        if (reloadTimer >= reloadInterval && getTop() < worldBounds.getTop()) {
            reloadTimer = 0f;
             bulletPos.set(pos);
            shoot();
        }
        damageAnimateTimer += delta;
        if (damageAnimateTimer >= DAMAGE_ANIMATE_INTERVAL) {
            frame = 0;
        }
    }

    public void damage(int hp) {
        this.hp -= hp;
        if (this.hp <= 0) {
            this.hp = 0;
            destroy();
        }
        damageAnimateTimer = 0f;
        frame = 1;
    }

    public int getDamage() {
        return damage;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    @Override
    public void destroy() {
        super.destroy();
        boom();
    }

    private void shoot() {
        Bullet bullet = bulletPool.obtain();
        bullet.set(this, bulletRegion, bulletPos, bulletV, worldBounds, bulletHeight, damage);
        bulletSound.play(0.1f);
    }

    private void boom() {
        Explosion explosion = explosionPool.obtain();
        explosion.set(this.pos, getHeight());
    }

}
