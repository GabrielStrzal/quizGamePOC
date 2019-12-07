package com.strzal.quiz.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
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
import com.strzal.quiz.constants.QuestionsPaths;
import com.strzal.quiz.controller.LevelController;
import com.strzal.quiz.entities.Question;
import com.strzal.quiz.json.QuestionJsonParser;
import com.strzal.quiz.screenManager.ScreenEnum;

import java.util.HashMap;

public class MenuScreen extends ScreenAdapter {

    private SpriteBatch batch;
    protected Stage stage;
    private Viewport viewport;
    private OrthographicCamera camera;
    private TextureAtlas atlas;
    protected Skin skin;

    QuizGame game;

    public MenuScreen(BasicGame game)
    {
        atlas = new TextureAtlas("skins/default/uiskin.atlas");
        skin = new Skin(Gdx.files.internal("skins/default/uiskin.json"), atlas);

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

        //Create buttons
        TextButton playButton = new TextButton("Play", skin);
        TextButton townButton = new TextButton("Level 2 Town", skin);
        TextButton exitButton = new TextButton("Exit", skin);

        //Add listeners to buttons
        playButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {

                game.levelController = new LevelController(QuestionsPaths.WHITE_BELT_01 + QuestionsPaths.EN_CA_JSON);

                ScreenManager.getInstance().showScreen(
                        ScreenEnum.QUIZ_SCREEN, game, game.levelController.getNextQuestion()
                );
            }
        });
//        townButton.addListener(new ClickListener(){
//            @Override
//            public void clicked(InputEvent event, float x, float y) {
//                ScreenManager.getInstance().showScreen(ScreenEnum.GAME_SCREEN, game, 2);
//            }
//        });
        exitButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Gdx.app.exit();
            }
        });

        //Add buttons to table
        mainTable.add(playButton);
        mainTable.row();
        mainTable.add(townButton);
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
