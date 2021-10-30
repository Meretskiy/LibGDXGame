package com.meretskiy.game.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;
import com.meretskiy.game.base.BaseScreen;
import com.meretskiy.game.math.Rect;
import com.meretskiy.game.pool.BulletPool;
import com.meretskiy.game.pool.EnemyPool;
import com.meretskiy.game.sprite.Background;
import com.meretskiy.game.sprite.EnemyShip;
import com.meretskiy.game.sprite.MainShip;
import com.meretskiy.game.sprite.Star;
import com.meretskiy.game.util.EnemyEmitter;

public class GameScreen extends BaseScreen {

    private static final int STAR_COUNT = 64;

    private TextureAtlas atlas;
    private Texture bg;

    private Background background;
    private Star[] stars;
    private BulletPool bulletPool;
    private EnemyPool enemyPool;

    private MainShip mainShip;

    private EnemyEmitter enemyEmitter;

    @Override
    public void show() {
        super.show();
        laserSound = Gdx.audio.newSound(Gdx.files.internal("sounds/laser.wav"));
        bulletSound = Gdx.audio.newSound(Gdx.files.internal("sounds/bullet.wav"));
        explosionSound = Gdx.audio.newSound(Gdx.files.internal("sounds/explosion.wav"));
        atlas = new TextureAtlas("textures/mainAtlas.tpack");
        bg = new Texture("sci-fi-space.jpeg");
        background = new Background(bg);
        stars = new Star[STAR_COUNT];
        for (int i = 0; i < stars.length; i++) {
            stars[i] = new Star(atlas);
        }
        bulletPool = new BulletPool();
        enemyPool = new EnemyPool(bulletPool, worldBounds, bulletSound, explosionSound);
        mainShip = new MainShip(atlas, bulletPool, laserSound);
        enemyEmitter = new EnemyEmitter(enemyPool, worldBounds, atlas);
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        update(delta);
        freeAllDestroyed();
        draw();
    }

    @Override
    public void resize(Rect worldBounds) {
        super.resize(worldBounds);
        background.resize(worldBounds);
        for (Star star : stars) {
            star.resize(worldBounds);
        }
        mainShip.resize(worldBounds);
    }

    @Override
    public void dispose() {
        super.dispose();
        atlas.dispose();
        bg.dispose();
        bulletPool.dispose();
        laserSound.dispose();
        enemyPool.dispose();
        bulletSound.dispose();
        explosionSound.dispose();
    }

    @Override
    public boolean keyDown(int keycode) {
        mainShip.keyDown(keycode);
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        mainShip.keyUp(keycode);
        return false;
    }

    @Override
    public boolean touchDown(Vector2 touch, int pointer, int button) {
        mainShip.touchDown(touch, pointer, button);
        return false;
    }

    @Override
    public boolean touchUp(Vector2 touch, int pointer, int button) {
        mainShip.touchUp(touch, pointer, button);
        return false;
    }

    private void update(float delta) {
        for (Star star : stars) {
            star.update(delta);
        }
        bulletPool.updateActiveObjects(delta);
        enemyPool.updateActiveObjects(delta);
        mainShip.update(delta);
        enemyEmitter.generate(delta);
        for (EnemyShip ship : enemyPool.getActiveObjects()) {
                if (!ship.isOutside(mainShip)) {
                    ship.destroy();
                    explosionSound.play(0.1f);
                }
        }
    }

    private void freeAllDestroyed() {
        bulletPool.freeAllDestroyed();
        enemyPool.freeAllDestroyed();
    }

    private void draw() {
        batch.begin();
        background.draw(batch);
        for (Star star : stars) {
            star.draw(batch);
        }
        bulletPool.drawActiveObjects(batch);
        enemyPool.drawActiveObjects(batch);
        mainShip.draw(batch);
        batch.end();
    }
}
