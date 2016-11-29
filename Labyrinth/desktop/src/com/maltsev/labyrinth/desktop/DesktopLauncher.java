package com.maltsev.labyrinth.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.maltsev.labyrinth.view.Labyrinth;


public class DesktopLauncher {

	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();

		config.title = "Labyrinth";
		config.width = 1920;
		config.height = 1080;
		//config.fullscreen = true;

		//config.resizable = false;

		new LwjglApplication(new Labyrinth(), config);
	}
}

