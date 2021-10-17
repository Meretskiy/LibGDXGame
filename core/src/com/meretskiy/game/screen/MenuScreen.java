package com.meretskiy.game.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.meretskiy.game.base.BaseScreen;

/**
 * Экран меню
 */
public class MenuScreen extends BaseScreen {

    private Texture img;
    private Texture backGround;
    private Vector2 touch;
    private Vector2 pos;
    private Vector2 v;
    private Vector2 temp;

    @Override
    public void show() {
        super.show();
        img = new Texture("rocket.png");
        backGround = new Texture("sci-fi-space.jpeg");
        touch = new Vector2();
        pos = new Vector2();
        v = new Vector2();
        temp = new Vector2();
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        temp.set(touch);
        pos = temp.sub(pos).len() > 1 ? pos.add(v) : pos.set(touch);
        batch.begin();
        batch.draw(backGround, 0, 0);
        batch.draw(img, pos.x, pos.y, 150, 200);
        batch.end();
    }

    @Override
    public void dispose() {
        super.dispose();
        img.dispose();
        backGround.dispose();
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        touch.set(screenX, Gdx.graphics.getHeight() - screenY);
        v.set(touch.cpy().sub(pos)).nor();
        return super.touchDown(screenX, screenY, pointer, button);
    }
}
