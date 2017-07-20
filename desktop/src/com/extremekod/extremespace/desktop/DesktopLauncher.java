package com.extremekod.extremespace.desktop;

import com.badlogic.gdx.Files;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.extremekod.extremespace.extremeSpace;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();

		config.width  = extremeSpace.width;
		config.height = extremeSpace.height;
		config.title  = extremeSpace.TITLE;

        config.addIcon("icon.png", Files.FileType.Internal);

		new LwjglApplication(new extremeSpace(), config);
	}
}
