package com.strzal.quiz;

import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.strzal.gdxUtilLib.BasicGame;
import com.strzal.gdxUtilLib.screenManager.ScreenManager;
import com.strzal.quiz.audio.AudioHandler;
import com.strzal.quiz.config.GameConfig;
import com.strzal.quiz.controller.LevelController;
import com.strzal.quiz.loading.LoadingPathsImpl;
import com.strzal.quiz.screenManager.ScreenEnum;
import com.strzal.quiz.screens.MenuScreen;

public class QuizGame extends BasicGame {

	public LevelController levelController;
	public AudioHandler audioHandler;

	@Override
	public void create () {
		screenWidth = GameConfig.SCREEN_WIDTH;
		screenHeight = GameConfig.SCREEN_HEIGHT;

		batch = new SpriteBatch();
		loadingPaths = new LoadingPathsImpl();
		audioHandler = new AudioHandler(this);

		assetManager.setLoader(TiledMap.class, new TmxMapLoader(new InternalFileHandleResolver()));
		ScreenManager.getInstance().initialize(this);
		ScreenManager.getInstance().showScreen(ScreenEnum.LOADING_SCREEN, this, loadingPaths, new MenuScreen(this));
	}

	@Override
	public void dispose () {
		batch.dispose();
	}
}
