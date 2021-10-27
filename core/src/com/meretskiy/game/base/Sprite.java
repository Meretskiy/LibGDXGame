package com.meretskiy.game.base;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.meretskiy.game.math.Rect;
import com.meretskiy.game.util.Regions;

/**
 * Спрайт - это стекстура с какой - либо логикой
 */
public class Sprite extends Rect {

    protected float angle;              // уголл поворота спрайта
    protected float scale = 1f;        // величина скалирования спрайта
    protected TextureRegion[] regions;  // массив тектур
    protected int frame;                // текущий кадр
    private boolean destroyed;

    public Sprite() {

    }

    public Sprite(TextureRegion region) { // конструктор для спрайта сосотоящего из одной текстуры
        if (region == null) {
            throw new IllegalArgumentException("region is null");
        }
        regions = new TextureRegion[1];
        regions[0] = region;
    }

    public Sprite(TextureRegion region, int rows, int cols, int frames) {
        this.regions = Regions.split(region, rows, cols, frames);
    }

    /**
     * Установка ширины в зависимости от высоты
     */
    public void setHeightProportion(float height) {
        setHeight(height);
        float aspect = regions[frame].getRegionWidth() / (float) regions[frame].getRegionHeight();
        setWidth(height * aspect);
    }

    /**
     * Логика масштибирования спрайта
     */
    public void resize(Rect worldBounds) {

    }

    /**
     * Логика обновления спрайта
     *
     * @param delta
     */
    public void update(float delta) {

    }

    /**
     * Отрисовка спрайта
     *
     * @param batch
     */
    public void draw(SpriteBatch batch) {
        batch.draw(
                regions[frame],           // передача нулевого кадра
                getLeft(), getBottom(),   // передача точки отрисовки
                halfWidth, halfHeight,    // передача тачки вращения
                getWidth(), getHeight(),  // передача ширины и высоты
                scale, scale,             // скалирование по Х и Y
                angle                     // угол поворота
        );
    }

    public boolean touchDown(Vector2 touch, int pointer, int button) {
        return false;
    }

    public boolean touchUp(Vector2 touch, int pointer, int button) {
        return false;
    }

    public boolean touchDragged(Vector2 touch, int pointer) {
        return false;
    }

    public boolean keyDown(int keycode) {
        return false;
    }

    public boolean keyUp(int keycode) {
        return false;
    }

    public float getAngle() {
        return angle;
    }

    public void setAngle(float angle) {
        this.angle = angle;
    }

    public float getScale() {
        return scale;
    }

    public void setScale(float scale) {
        this.scale = scale;
    }

    public void destroy() {
        destroyed = true;
    }

    public void flushDestroy() {
        destroyed = false;
    }

    public boolean isDestroyed() {
        return destroyed;
    }
}
