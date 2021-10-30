package com.meretskiy.game.base;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Matrix3;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.ScreenUtils;
import com.meretskiy.game.math.MatrixUtils;
import com.meretskiy.game.math.Rect;

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

    private Rect screenBounds; // границы игрового мира в пикселях
    protected Rect worldBounds;  // мировая система координат
    private Rect glBounds;     // координатная сетка OpenGL

    private Matrix4 worldToGl; // матрица проекции из мировой системы в OpenGl
    private Matrix3 screenToWorld; // матрица проекции из пикселей в мировую систему для событий

    private final Vector2 touch = new Vector2();

    protected SpriteBatch batch;
    protected Music music;
    protected Sound laserSound;
    protected Sound bulletSound;
    protected Sound explosionSound;

    @Override
    public void show() {
        System.out.println("show");
        music = Gdx.audio.newMusic(Gdx.files.internal("sounds/music.mp3"));
        music.setVolume(0.7f);
        music.setLooping(true);
        music.play();
        screenBounds = new Rect();
        worldBounds = new Rect();
        glBounds = new Rect(0, 0, 1, 1);
        worldToGl = new Matrix4();
        screenToWorld = new Matrix3();
        batch = new SpriteBatch();
        batch.getProjectionMatrix().idt();
        Gdx.input.setInputProcessor(this);
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(Color.BLACK);
    }

    @Override
    public void resize(int width, int height) {
        System.out.println("resize width = " + width + " height = " + height);
        screenBounds.setSize(width, height);
        screenBounds.setLeft(0);
        screenBounds.setBottom(0);
        float aspect = width / (float) height;
        worldBounds.setHeight(1f);
        worldBounds.setWidth(1f * aspect);
        MatrixUtils.calcTransitionMatrix(screenToWorld, screenBounds, worldBounds);
        MatrixUtils.calcTransitionMatrix(worldToGl, worldBounds, glBounds); //конфигурируем матрицу проекции
        batch.setProjectionMatrix(worldToGl); // устанавливаем матрицу проекции
        resize(worldBounds);
    }

    public void resize(Rect worldBounds) {
        System.out.println("resize worldBounds.width = " + worldBounds.getWidth()
                + " worldBounds.height = " + worldBounds.getHeight());
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
        music.dispose();
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
        touch.set(screenX, screenBounds.getHeight() - screenY).mul(screenToWorld); // получение вектора события в мировой системе координат
        touchDown(touch, pointer, button);
        return false;
    }

    public boolean touchDown(Vector2 touch, int pointer, int button) {
        System.out.println("touchDown touch.x = " + touch.x + " touch.y = " + touch.y);
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        System.out.println("touchUp " + screenX + " " + screenY + " " + pointer + " " + button);
        touch.set(screenX, screenBounds.getHeight() - screenY).mul(screenToWorld); // получение вектора события в мировой системе координат
        touchUp(touch, pointer, button);
        return false;
    }

    public boolean touchUp(Vector2 touch, int pointer, int button) {
        System.out.println("touchUp touch.x = " + touch.x + " touch.y = " + touch.y);
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        System.out.println("touchDragged " + screenX + " " + screenY + " " + pointer);
        touch.set(screenX, screenBounds.getHeight() - screenY).mul(screenToWorld); // получение вектора события в мировой системе координат
        touchDragged(touch, pointer);
        return false;
    }

    public boolean touchDragged(Vector2 touch, int pointer) {
        System.out.println("touchDragged touch.x = " + touch.x + " touch.y = " + touch.y);
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
