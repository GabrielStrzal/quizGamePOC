package com.strzal.quiz;

import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.strzal.gdx.BasicGame;
import com.strzal.gdx.screenManager.ScreenManager;
import com.strzal.quiz.config.GameConfig;
import com.strzal.quiz.constants.LoadingPathsImpl;
import com.strzal.quiz.screenManager.ScreenEnum;
import com.strzal.quiz.screens.MenuTestScreen;

public class QuizGame extends BasicGame {

	@Override
	public void create () {
		screenWidth = GameConfig.SCREEN_WIDTH;
		screenHeight = GameConfig.SCREEN_HEIGHT;

		batch = new SpriteBatch();
		loadingPaths = new LoadingPathsImpl();

		assetManager.setLoader(TiledMap.class, new TmxMapLoader(new InternalFileHandleResolver()));
		ScreenManager.getInstance().initialize(this);
		ScreenManager.getInstance().showScreen(ScreenEnum.LOADING_SCREEN, this, loadingPaths, new MenuTestScreen(this));
	}

	@Override
	public void dispose () {
		batch.dispose();
	}
}
