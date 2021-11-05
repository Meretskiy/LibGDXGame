package com.meretskiy.game.sprite;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.meretskiy.game.base.Sprite;
import com.meretskiy.game.math.Rect;

public class GameOverMessage extends Sprite {

    private static final float HEIGHT = 0.06f;
    private static final float PADDING = 0.6f;

    public GameOverMessage(TextureAtlas atlas) {
        super(atlas.findRegion("message_game_over"));
    }

    @Override
    public void resize(Rect worldBounds) {
        setHeightProportion(HEIGHT);
        setBottom(worldBounds.getBottom() + PADDING);
    }
}
