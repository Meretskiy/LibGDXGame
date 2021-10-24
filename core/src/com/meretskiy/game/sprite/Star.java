package com.meretskiy.game.sprite;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;
import com.meretskiy.game.base.Sprite;
import com.meretskiy.game.math.Rect;
import com.meretskiy.game.math.Rnd;

public class Star extends Sprite {

    private final Vector2 v;
    private Rect worldBounds;

    public Star(TextureAtlas atlas) {
        super(atlas.findRegion("star")); // вытаскиваем из атласа нужный регион
        v = new Vector2(Rnd.nextFloat(-0.005f, 0.005f), Rnd.nextFloat(-0.2f, -0.05f)); // установка рандомной скорости
    }

    @Override
    public void resize(Rect worldBounds) {
        this.worldBounds = worldBounds;
        setHeightProportion(Rnd.nextFloat(0.005f, 0.015f)); // установка рандомного размера
        float x = Rnd.nextFloat(worldBounds.getLeft(), worldBounds.getRight());
        float y = Rnd.nextFloat(worldBounds.getBottom(), worldBounds.getTop());
        pos.set(x, y); // установка рандомной позиции
    }

    @Override
    public void update(float delta) {
        pos.mulAdd(v, delta); // задаем движение - складываем вектора и умножаем на скалр delta(отрезок между срабатыванием метода render)
        checkBounds();
    }

    private void checkBounds() { // перемещаем объект в противоположный край
        if (getRight() < worldBounds.getLeft()) {
            setLeft(worldBounds.getRight());
        }
        if (getLeft() > worldBounds.getRight()) {
            setRight(worldBounds.getLeft());
        }
        if (getTop() < worldBounds.getBottom()) {
            setBottom(worldBounds.getTop());
        }
    }
}
