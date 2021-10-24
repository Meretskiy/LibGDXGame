package com.meretskiy.game.sprite;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.meretskiy.game.base.Sprite;
import com.meretskiy.game.math.Rect;

public class Background extends Sprite {

    public Background(Texture texture) {
        super(new TextureRegion(texture));
    }

    @Override
    public void resize(Rect worldBounds) {
        setHeightProportion(worldBounds.getHeight());// задаем высоту фона
        pos.set(worldBounds.pos); // задаем позицию
    }
}
