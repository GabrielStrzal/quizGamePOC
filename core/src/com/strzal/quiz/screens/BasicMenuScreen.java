package com.strzal.quiz.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageTextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.strzal.gdxUtilLib.BasicGame;
import com.strzal.gdxUtilLib.utils.GdxUtils;
import com.strzal.quiz.QuizGame;
import com.strzal.quiz.config.GameConfig;
import com.strzal.quiz.constants.ImagesPaths;

public class BasicMenuScreen extends ScreenAdapter {

    private SpriteBatch batch;
    protected Stage stage;
    private Viewport viewport;
    private OrthographicCamera camera;
    private TextureAtlas atlas;
    protected Skin skin;

    protected ImageTextButton.ImageTextButtonStyle style;
    protected ImageTextButton.ImageTextButtonStyle exitStyle;

    QuizGame game;

    public BasicMenuScreen(BasicGame game)
    {
        atlas = new TextureAtlas(ImagesPaths.UI_SKIN_ATLAS);
        skin = new Skin(Gdx.files.internal(ImagesPaths.UI_SKIN_JSON), atlas);

        batch = new SpriteBatch();
        camera = new OrthographicCamera();
        viewport = new FitViewport(GameConfig.SCREEN_WIDTH, GameConfig.SCREEN_HEIGHT, camera);
        viewport.apply();

        camera.position.set(camera.viewportWidth / 2, camera.viewportHeight / 2, 0);
        camera.update();

        stage = new Stage(viewport, batch);
        this.game = (QuizGame) game;



    }


    @Override
    public void show() {
        //Create Style
        Texture buttonTexture = game.getAssetManager().get(ImagesPaths.QUESTION_BUTTON);
        Texture buttonTexturePressed = game.getAssetManager().get(ImagesPaths.QUESTION_BUTTON_PRESSED);
        style = new ImageTextButton.ImageTextButtonStyle(
                new TextureRegionDrawable(buttonTexture),
                new TextureRegionDrawable(buttonTexturePressed),
                new TextureRegionDrawable(buttonTexture),
                new BitmapFont());


        //Create Exit Style
        Texture exitButtonTexture = game.getAssetManager().get(ImagesPaths.EXIT_BUTTON);
        Texture exitButtonTexturePressed = game.getAssetManager().get(ImagesPaths.EXIT_BUTTON_PRESSED);
        exitStyle = new ImageTextButton.ImageTextButtonStyle(
                new TextureRegionDrawable(exitButtonTexture),
                new TextureRegionDrawable(exitButtonTexturePressed),
                new TextureRegionDrawable(exitButtonTexture),
                new BitmapFont());
    }

    @Override
    public void render(float delta) {
        GdxUtils.clearScreen(Color.WHITE);
        stage.act();
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height);
        camera.position.set(camera.viewportWidth / 2, camera.viewportHeight / 2, 0);
        camera.update();
        stage.getViewport().update(width, height);
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }

    @Override
    public void hide() {
    }

    @Override
    public void dispose() {
        stage.dispose();
        skin.dispose();
        atlas.dispose();
    }
}
