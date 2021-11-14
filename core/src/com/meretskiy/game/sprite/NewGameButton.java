package com.meretskiy.game.sprite;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.meretskiy.game.base.BaseButton;
import com.meretskiy.game.math.Rect;
import com.meretskiy.game.screen.GameScreen;

public class NewGameButton extends BaseButton {

    private final GameScreen gameScreen;

    private static final float HEIGHT = 0.05f;
    private static final float PADDING = 0.2f;

    public NewGameButton(TextureAtlas atlas, GameScreen gameScreen) {
        super(atlas.findRegion("button_new_game"));
        this.gameScreen = gameScreen;
    }

    @Override
    public void resize(Rect worldBounds) {
        setHeightProportion(HEIGHT);
        setBottom(worldBounds.getBottom() + PADDING);
    }

    @Override
    public void action() {
        gameScreen.startNewGame();
    }
}
