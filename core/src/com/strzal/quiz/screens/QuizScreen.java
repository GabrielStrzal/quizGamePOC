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
import com.strzal.quiz.entities.Question;
import com.strzal.quiz.screenManager.ScreenEnum;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class QuizScreen extends ScreenAdapter {

    private SpriteBatch batch;
    protected Stage stage;
    private Viewport viewport;
    private OrthographicCamera camera;
    private TextureAtlas atlas;
    protected Skin skin;

    QuizGame game;
    Question question;
    List<Integer> questionListIndexes = Arrays.asList(1, 2, 3, 4);

    public QuizScreen(BasicGame game, Question question)
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
        this.question = question;

        Collections.shuffle(questionListIndexes);

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

        Label questionTextLabel = new Label(question.getQuestionText(), skin);

        //Create buttons
        TextButton choice1Button = new TextButton(question.getAnswers().get(questionListIndexes.get(0)), skin);
        choice1Button.setScale(2);
        TextButton choice2Button = new TextButton(question.getAnswers().get(questionListIndexes.get(1)), skin);
        TextButton choice3Button = new TextButton(question.getAnswers().get(questionListIndexes.get(2)), skin);
        TextButton choice4Button = new TextButton(question.getAnswers().get(questionListIndexes.get(3)), skin);

        TextButton quitCurrentQuizListButton = new TextButton("Quit Current Quiz", skin);

        //Add listeners to buttons
        choice1Button.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                validate(questionListIndexes.get(0));
            }
        });
        choice2Button.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                validate(questionListIndexes.get(1));
            }
        });
        choice3Button.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                validate(questionListIndexes.get(2));
            }
        });
        choice4Button.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                validate(questionListIndexes.get(3));
            }
        });

        quitCurrentQuizListButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                ScreenManager.getInstance().showScreen(ScreenEnum.MENU_SCREEN, game);
            }
        });

        //Add buttons to table


        mainTable.add(questionTextLabel);
        mainTable.row();

        Table questionTable = new Table();
        questionTable.center();

        questionTable.add(choice1Button);
        questionTable.add(choice2Button);
        questionTable.row();
        questionTable.add(choice3Button);
        questionTable.add(choice4Button);
        questionTable.row();

        mainTable.add(questionTable);
        mainTable.row();
        mainTable.add(quitCurrentQuizListButton);

        //Add table to stage
        stage.addActor(mainTable);
    }
    private void validate(int choice){
        System.out.println("Choice is:" + choice);

        if(choice != 0){
            if(choice == question.getCorrectAnswer()) {
                game.levelController.increaseCorrectAnswers();
                System.out.println("Success");

            }else{
                System.out.println("Not Cool");
            }
            if(game.levelController.hasMoreQuestionsLeft()) {
                ScreenManager.getInstance().showScreen(ScreenEnum.QUIZ_SCREEN, game, game.levelController.getNextQuestion());
            }else{
                ScreenManager.getInstance().showScreen(ScreenEnum.RESULT_SCREEN, game);
            }
        }
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
