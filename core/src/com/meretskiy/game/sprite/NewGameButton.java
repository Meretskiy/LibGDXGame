package com.meretskiy.game.sprite;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;
import com.meretskiy.game.base.BaseButton;
import com.meretskiy.game.math.Rect;
import com.meretskiy.game.pool.BulletPool;
import com.meretskiy.game.pool.EnemyPool;

public class NewGameButton extends BaseButton {

    private static final float HEIGHT = 0.05f;
    private static final float PADDING = 0.2f;

    private final MainShip mainShip;
    private final Rect worldBounds;
    private final EnemyPool enemyPool;
    private final BulletPool bulletPool;

    public NewGameButton(TextureAtlas atlas, MainShip mainShip, Rect worldBounds,
                         EnemyPool enemyPool, BulletPool bulletPool) {
        super(atlas.findRegion("button_new_game"));
        this.mainShip = mainShip;
        this.worldBounds = worldBounds;
        this.enemyPool = enemyPool;
        this.bulletPool = bulletPool;
    }

    @Override
    public void resize(Rect worldBounds) {
        setHeightProportion(HEIGHT);
        setBottom(worldBounds.getBottom() + PADDING);
    }

    @Override
    public void action() {
        mainShip.flushDestroy();
        mainShip.setHp(MainShip.HP);
        mainShip.pos.set(new Vector2());
        mainShip.resize(worldBounds);
        for (Bullet bullet : bulletPool.getActiveObjects()) {
            bullet.remove();
        }
        for (EnemyShip enemyShip : enemyPool.getActiveObjects()) {
            enemyShip.remove();
        }
    }
}
