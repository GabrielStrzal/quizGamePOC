package com.strzal.quiz.screens;

import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.strzal.gdx.utils.GdxUtils;
import com.strzal.quiz.QuizGame;
import com.strzal.quiz.config.GameConfig;
import com.strzal.quiz.constants.ImagesPaths;

/**
 * based on "LibGDX Game Development By Example"
 */

public class LoadingScreen extends ScreenAdapter{

    private static final float PROGRESS_BAR_WIDTH = 400;
    private static final float PROGRESS_BAR_HEIGHT = 30;
    private ShapeRenderer shapeRenderer;
    private Viewport viewport;
    private Camera camera;
    private float progress = 0;
    private QuizGame game;
    protected final AssetManager assetManager;

    public LoadingScreen(QuizGame game) {
        this.game = game;
        this.assetManager = game.getAssetManager();
    }
    @Override
    public void resize(int width, int height) {
        viewport.update(width, height);
    }
    @Override
    public void show() {
        camera = new OrthographicCamera();
        camera.position.set(GameConfig.SCREEN_WIDTH /2, GameConfig.SCREEN_HEIGHT, 0);
        camera.update();
        viewport = new FitViewport(GameConfig.SCREEN_WIDTH, GameConfig.SCREEN_HEIGHT, camera);
        shapeRenderer = new ShapeRenderer();

        //Menu
        assetManager.load(ImagesPaths.MENU_BACKGROUND, Texture.class);


    }
    @Override
    public void render(float delta) {
        update();
        GdxUtils.clearScreen();
        draw();
    }
    @Override
    public void dispose() {
        shapeRenderer.dispose();
    }
    private void update() {
        if (assetManager.update()) {
            game.setScreen(new MenuTestScreen(game));
        } else {
            progress = assetManager.getProgress();
        }
    }
    private void draw() {
        shapeRenderer.setProjectionMatrix(camera.projection);
        shapeRenderer.setTransformMatrix(camera.view);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(Color.WHITE);
        shapeRenderer.rect(
                (GameConfig.SCREEN_WIDTH - PROGRESS_BAR_WIDTH) / 2,
                (GameConfig.SCREEN_HEIGHT - PROGRESS_BAR_HEIGHT / 2),
                progress * PROGRESS_BAR_WIDTH, PROGRESS_BAR_HEIGHT);
        shapeRenderer.end();

    }
}
