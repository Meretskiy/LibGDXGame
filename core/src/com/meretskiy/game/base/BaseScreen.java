package com.meretskiy.game.base;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

/**
 * Базовый класс для экранов
 *
 *
 * Реализует интерфейс screen с базовыми методами отвечающими за собития экрана
 *
 * show() - аналог create, отрабатывает первым, когда экран появляется перед пользователем,
 * в нем обычно идет вся инициализация и настройка
 *
 * render(float delta) - срабатывает 60 раз в секцнду с дельтой времени между срабатываниями
 *
 * resize(int width, int height) - отрабатывает сразу после show и каждый раз после изменения размеров экрана
 *
 * pause() - отрабатывает когда сворачиваем экран
 *
 * resume() - отрабатывает когда разворачиваем экран
 *
 * hide() - отрабатывает при закрытии экрана
 *
 * dispose() - не отрабатывает сам, нужно дергать самостоятельно
 *
 *
 * Реализует интерефейс InputProcessor с пользовательскими методами
 *
 * keyDown(int keycode) - нажатие клавиши (число - код клавиши)
 *
 * keyUp(int keycode) - отпустили клавишу
 *
 * keyTyped(char character) - факт нажатия клавиши (символ)
 *
 * touchDown(int screenX, int screenY, int pointer, int button) - клик мышкой по экрану / тап
 *  (screenX,Y - координаты, pointer - номер пальца, button -  нумерация кнопок мыши)
 *
 * touchUp(int screenX, int screenY, int pointer, int button) - убрали палец с кнопки мыши / отпустили тап
 *
 * touchDragged(int screenX, int screenY, int pointer) - нажали на экран и провели пальцем и отпустили
 *  в другом месте (перетаскивание по экрану)
 *
 * mouseMoved(int screenX, int screenY) - движение мышью
 *
 * scrolled(float amountX, float amountY) - скроллинг по осям X и Y
 *
 *
 * Gdx.input.setInputProcessor(this); - устанавливаем InputProcessor для экрана
 */
public class BaseScreen implements Screen, InputProcessor {

    protected SpriteBatch batch;

    @Override
    public void show() {
        System.out.println("show");
        batch = new SpriteBatch();
        Gdx.input.setInputProcessor(this);
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(Color.BLACK);
    }

    @Override
    public void resize(int width, int height) {
        System.out.println("resize width = " + width + " height = " + height);
    }

    @Override
    public void pause() {
        System.out.println("pause");
    }

    @Override
    public void resume() {
        System.out.println("resume");
    }

    @Override
    public void hide() {
        System.out.println("hide");
        dispose();
    }

    @Override
    public void dispose() {
        System.out.println("dispose");
        batch.dispose();
    }

    @Override
    public boolean keyDown(int keycode) {
        System.out.println("keyDown keycode = " + keycode);
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        System.out.println("keyUp keycode = " + keycode);
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        System.out.println("keyTyped character = " + character);
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        System.out.println("touchDown " + screenX + " " + screenY + " " + pointer + " " + button);
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        System.out.println("touchUp " + screenX + " " + screenY + " " + pointer + " " + button);
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        System.out.println("touchDragged " + screenX + " " + screenY + " " + pointer);
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(float amountX, float amountY) {
        System.out.println("scrolled " + amountX + " " + amountY);
        return false;
    }
}
