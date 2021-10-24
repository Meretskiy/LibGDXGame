package com.meretskiy.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.meretskiy.game.MyStarGame;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
//		config.width = 800;
//		config.height = 800;
//		config.resizable = false;
		new LwjglApplication(new MyStarGame(), config);
	}
}
