package com.meretskiy.game.screen;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.meretskiy.game.base.BaseScreen;

/**
 * Экран меню
 */
public class MenuScreen extends BaseScreen {

    private Texture img;
    private Texture backGround;
    private Vector2 pos;

    @Override
    public void show() {
        super.show();
        img = new Texture("rocket.png");
        backGround = new Texture("sci-fi-space.jpeg");
        pos = new Vector2();
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        batch.begin();
        batch.draw(backGround, worldBounds.getLeft(), worldBounds.getBottom());
        batch.draw(img, pos.x, pos.y, 0.1f, 0.1f);
        batch.end();
    }

    @Override
    public void dispose() {
        super.dispose();
        img.dispose();
        backGround.dispose();
    }

    @Override
    public boolean touchDown(Vector2 touch, int pointer, int button) {
        pos.set(touch);
        return super.touchDown(touch, pointer, button);
    }

    @Override
    public boolean touchUp(Vector2 touch, int pointer, int button) {
        return super.touchUp(touch, pointer, button);
    }

    @Override
    public boolean touchDragged(Vector2 touch, int pointer) {
        return super.touchDragged(touch, pointer);
    }
}
