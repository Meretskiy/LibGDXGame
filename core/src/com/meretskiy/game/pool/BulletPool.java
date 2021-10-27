package com.meretskiy.game.pool;

import com.meretskiy.game.base.SpritesPool;
import com.meretskiy.game.sprite.Bullet;

public class BulletPool extends SpritesPool<Bullet> {

    @Override
    protected Bullet newObject() {
        return new Bullet();
    }
}
