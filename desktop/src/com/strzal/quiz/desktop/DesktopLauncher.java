package com.strzal.quiz.desktop;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import com.strzal.quiz.QuizGame;
import com.strzal.quiz.config.GameConfig;

public class DesktopLauncher {
	public static void main (String[] arg) {
		Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
		config.setWindowedMode( GameConfig.SCREEN_DESKTOP_DISPLAY_WIDTH, GameConfig.SCREEN_DESKTOP_DISPLAY_HEIGHT);
		new Lwjgl3Application(new QuizGame(), config);
	}
}
