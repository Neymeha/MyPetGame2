package com.neymeha.mypetgame2;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import com.neymeha.mypetgame2.MyPetGame2;

// Please note that on macOS your application needs to be started with the -XstartOnFirstThread JVM argument
public class DesktopLauncher {
	public static void main (String[] arg) {
		Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
		config.setForegroundFPS(60);
		config.setTitle("My Pet Game 2");
		config.setWindowedMode(625,625);
		new Lwjgl3Application(new MyPetGame2(), config);
	}
}
