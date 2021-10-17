package com.meretskiy.game.base;

import com.badlogic.gdx.Screen;

/**
 * Базовый экран
 *
 * Реализует интерфейс screen с базовыми методами отвечающими за собития экрана
 *
 * show() - аналог create, отрабатывает первым, когда экран появляется перед пользователем
 */
public class BaseScreen implements Screen {

    @Override
    public void show() {
        System.out.println("show");
    }

    @Override
    public void render(float delta) {

    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
