package com.strzal.quiz.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.strzal.gdx.BasicGame;
import com.strzal.gdx.screenManager.ScreenManager;
import com.strzal.gdx.utils.GdxUtils;
import com.strzal.quiz.QuizGame;
import com.strzal.quiz.config.GameConfig;
import com.strzal.quiz.constants.ImagesPaths;
import com.strzal.quiz.screenManager.ScreenEnum;

public class ResultScreen extends ScreenAdapter {

    private SpriteBatch batch;
    protected Stage stage;
    private Viewport viewport;
    private OrthographicCamera camera;
    private TextureAtlas atlas;
    protected Skin skin;

    QuizGame game;

    public ResultScreen(BasicGame game)
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
        //Stage should control input:
        Gdx.input.setInputProcessor(stage);

        //Create Table
        Table mainTable = new Table();
        //Set table to fill stage
        mainTable.setFillParent(true);
        //Set alignment of contents in the table.
        mainTable.center();
        System.out.println();
        Label resultLabel = new Label("Quiz ended!", skin);
        Label resultLabelStats = new Label(
                game.levelController.getNumberOfCorrectAnswers() + " correct answers of "
                        + game.levelController.getNumberOfTotalQuestions() , skin);
        //Create buttons

        TextButton quitReturnToMenuButton = new TextButton("Return to Menu", skin);
        TextButton exitButton = new TextButton("Exit Game", skin);

        //Add listeners to buttons
        quitReturnToMenuButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.audioHandler.playButtonSound();
                ScreenManager.getInstance().showScreen(ScreenEnum.MENU_SCREEN, game);
            }
        });
        exitButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Gdx.app.exit();
            }
        });

        //Add buttons to table
        mainTable.add(resultLabel);
        mainTable.row();
        mainTable.add(resultLabelStats);
        mainTable.row();
        mainTable.add(quitReturnToMenuButton);
        mainTable.row();
        mainTable.add(exitButton);

        //Add table to stage
        stage.addActor(mainTable);
    }

    @Override
    public void render(float delta) {
        GdxUtils.clearScreen();
        stage.act();
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height);
        camera.position.set(camera.viewportWidth / 2, camera.viewportHeight / 2, 0);
        camera.update();
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
