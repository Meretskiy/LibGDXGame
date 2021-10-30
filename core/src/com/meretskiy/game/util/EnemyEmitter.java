package com.meretskiy.game.util;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.meretskiy.game.math.Rect;
import com.meretskiy.game.math.Rnd;
import com.meretskiy.game.pool.EnemyPool;
import com.meretskiy.game.sprite.EnemyShip;

/**
 * Генерация вражеских кораблей
 */
public class EnemyEmitter {

    private static final float GENERATE_INTERVAL = 4f;

    private static final float ENEMY_SMALL_HEIGHT = 0.1f;
    private static final float ENEMY_SMALL_BULLET_HEIGHT = 0.01f;
    private static final int ENEMY_SMALL_BULLET_DAMAGE = 1;
    private static final float ENEMY_SMALL_RELOAD_INTERVAL = 3f;
    private static final int ENEMY_SMALL_HP = 1;

    private final EnemyPool enemyPool;
    private final Rect worldBounds;
    private final TextureRegion bulletRegion;

    private final TextureRegion[] enemySmallRegions;

    private final Vector2 enemySmallV = new Vector2(0f, -0.2f);
    private final Vector2 enemySmallBulletV = new Vector2(0f, -0.3f);

    private float generateTimer;

    public EnemyEmitter(EnemyPool enemyPool, Rect worldBounds, TextureAtlas atlas) {
        this.enemyPool = enemyPool;
        this.worldBounds = worldBounds;
        this.bulletRegion = atlas.findRegion("bulletEnemy");
        enemySmallRegions = Regions.split(atlas.findRegion("enemy0"), 1, 2, 2);
    }

    public void generate(float delta) {
        generateTimer += delta;
        if (generateTimer >= GENERATE_INTERVAL) {
            generateTimer = 0f;
            EnemyShip enemyShip = enemyPool.obtain();
            enemyShip.set(
                    enemySmallRegions,
                    enemySmallV,
                    bulletRegion,
                    ENEMY_SMALL_BULLET_HEIGHT,
                    enemySmallBulletV,
                    ENEMY_SMALL_BULLET_DAMAGE,
                    ENEMY_SMALL_HP,
                    ENEMY_SMALL_RELOAD_INTERVAL,
                    ENEMY_SMALL_HEIGHT
            );
            enemyShip.pos.x = Rnd.nextFloat(
                    worldBounds.getLeft() + enemyShip.getHalfWidth(),
                    worldBounds.getRight() - enemyShip.getHalfWidth()
            );
            enemyShip.setBottom(worldBounds.getTop());
        }
    }
}
