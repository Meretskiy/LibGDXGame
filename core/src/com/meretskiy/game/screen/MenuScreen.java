package com.meretskiy.game.screen;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.meretskiy.game.base.BaseScreen;
import com.meretskiy.game.math.Rect;
import com.meretskiy.game.sprite.Background;

/**
 * Экран меню
 */
public class MenuScreen extends BaseScreen {

    private Texture bg;
    private Texture img;
    private Vector2 pos;

    private Background background;

    @Override
    public void show() {
        super.show();
        bg = new Texture("sci-fi-space.jpeg");
        background = new Background(bg);

        img = new Texture("rocket.png");
        pos = new Vector2();
    }

    @Override
    public void resize(Rect worldBounds) {
        super.resize(worldBounds);
        background.resize(worldBounds);
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        batch.begin();
        background.draw(batch);
        batch.draw(img, pos.x, pos.y, 0.1f, 0.1f);
        batch.end();
    }

    @Override
    public void dispose() {
        super.dispose();
        bg.dispose();
        img.dispose();
    }

    @Override
    public boolean touchDown(Vector2 touch, int pointer, int button) {
        pos.set(touch);
        return super.touchDown(touch, pointer, button);
    }
}
