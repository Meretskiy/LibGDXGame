package com.meretskiy.game.screen;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.meretskiy.game.base.BaseScreen;
import com.meretskiy.game.math.Rect;
import com.meretskiy.game.sprite.Background;
import com.meretskiy.game.sprite.Rocket;

/**
 * Экран меню
 */
public class MenuScreen extends BaseScreen {

    private Texture bg;
    private Texture img;

    private Background background;
    private Rocket rocket;

    @Override
    public void show() {
        super.show();
        bg = new Texture("sci-fi-space.jpeg");
        background = new Background(bg);

        img = new Texture("rocket.png");
        rocket = new Rocket(img);
    }

    @Override
    public void resize(Rect worldBounds) {
        super.resize(worldBounds);
        background.resize(worldBounds);
        rocket.resize(worldBounds);
    }

    @Override
    public void render(float delta) {
        rocket.update(delta);
        super.render(delta);
        batch.begin();
        background.draw(batch);
        rocket.draw(batch);
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
        rocket.touchDown(touch, pointer, button);
        return super.touchDown(touch, pointer, button);
    }
}
