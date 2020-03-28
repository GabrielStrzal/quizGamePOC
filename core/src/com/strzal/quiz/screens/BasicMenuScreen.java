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
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.*;
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

    protected ImageTextButton.ImageTextButtonStyle greenButtonStyle;
    protected ImageTextButton.ImageTextButtonStyle blueButtonStyle;
    protected ImageTextButton.ImageTextButtonStyle exitStyle;
    protected Label.LabelStyle labelStyle;

    QuizGame game;

    public BasicMenuScreen(QuizGame game)
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

        BitmapFont font = new BitmapFont(Gdx.files.internal("fonts/sonic_comic.fnt"));

        //Create Green Button Style
        Texture buttonTexture = game.getAssetManager().get(ImagesPaths.QUESTION_GREEN_BUTTON);
        Texture buttonTexturePressed = game.getAssetManager().get(ImagesPaths.QUESTION_GREEN_BUTTON_PRESSED);
        greenButtonStyle = new ImageTextButton.ImageTextButtonStyle(
                new TextureRegionDrawable(buttonTexture),
                new TextureRegionDrawable(buttonTexturePressed),
                new TextureRegionDrawable(buttonTexture),
                font);

        //Create Blue Button Style
        Texture blueButtonTexture = game.getAssetManager().get(ImagesPaths.QUESTION_BLUE_BUTTON);
        Texture blueButtonTexturePressed = game.getAssetManager().get(ImagesPaths.QUESTION_BLUE_BUTTON_PRESSED);
        Texture blueButtonTextureChecked = game.getAssetManager().get(ImagesPaths.QUESTION_BLUE_BUTTON_CHECKED);
        blueButtonStyle = new ImageTextButton.ImageTextButtonStyle(
                new TextureRegionDrawable(blueButtonTexture),
                new TextureRegionDrawable(blueButtonTexturePressed),
                new TextureRegionDrawable(blueButtonTextureChecked),
                font);

        //Create Exit Style
        Texture exitButtonTexture = game.getAssetManager().get(ImagesPaths.EXIT_BUTTON);
        Texture exitButtonTexturePressed = game.getAssetManager().get(ImagesPaths.EXIT_BUTTON_PRESSED);
        exitStyle = new ImageTextButton.ImageTextButtonStyle(
                new TextureRegionDrawable(exitButtonTexture),
                new TextureRegionDrawable(exitButtonTexturePressed),
                new TextureRegionDrawable(exitButtonTexture),
                font);

        //text Label style
        labelStyle = new Label.LabelStyle(font, Color.GRAY);
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
