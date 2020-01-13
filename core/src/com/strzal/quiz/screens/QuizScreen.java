package com.strzal.quiz.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.strzal.gdxUtilLib.BasicGame;
import com.strzal.gdxUtilLib.screenManager.ScreenManager;
import com.strzal.quiz.constants.ImagesPaths;
import com.strzal.quiz.entities.Question;
import com.strzal.quiz.hud.Hud;
import com.strzal.quiz.screenManager.ScreenEnum;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class QuizScreen extends BasicMenuScreen {

    Question question;
    List<Integer> questionListIndexShuffler = Arrays.asList(0, 1, 2, 3);
    Hud hud;

    public QuizScreen(BasicGame game, Question question) {
        super(game);
        this.question = question;
        Collections.shuffle(questionListIndexShuffler);
        hud = new Hud(this.game);
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

        // Image
        Image questionImage;
        if(question.getImagePath() != null){
            questionImage = new Image(new Texture(Gdx.files.internal(question.getImagePath())));
        } else {
            questionImage = new Image(new Texture(Gdx.files.internal("images/kicks/01_round.JPG")));
        }

        // Question
        Label questionTextLabel = new Label(question.getQuestionString(), skin);

        //Create Style
        Texture buttonTexture = game.getAssetManager().get(ImagesPaths.QUESTION_BUTTON);
        Texture buttonTexturePressed = game.getAssetManager().get(ImagesPaths.QUESTION_BUTTON_PRESSED);
        ImageTextButton.ImageTextButtonStyle style = new ImageTextButton.ImageTextButtonStyle(
                new TextureRegionDrawable(buttonTexture),
                new TextureRegionDrawable(buttonTexturePressed),
                new TextureRegionDrawable(buttonTexture),
                new BitmapFont());


        //Create Exit Style
        Texture exitButtonTexture = game.getAssetManager().get(ImagesPaths.EXIT_BUTTON);
        Texture exitButtonTexturePressed = game.getAssetManager().get(ImagesPaths.EXIT_BUTTON_PRESSED);
        ImageTextButton.ImageTextButtonStyle exitStyle = new ImageTextButton.ImageTextButtonStyle(
                new TextureRegionDrawable(exitButtonTexture),
                new TextureRegionDrawable(exitButtonTexturePressed),
                new TextureRegionDrawable(exitButtonTexture),
                new BitmapFont());


        //Create buttons
        ImageTextButton choice1Button = new ImageTextButton(question.getAnswers().get(questionListIndexShuffler.get(0)), style);
        ImageTextButton choice2Button = new ImageTextButton(question.getAnswers().get(questionListIndexShuffler.get(1)), style);
        ImageTextButton choice3Button = new ImageTextButton(question.getAnswers().get(questionListIndexShuffler.get(2)), style);
        ImageTextButton choice4Button = new ImageTextButton(question.getAnswers().get(questionListIndexShuffler.get(3)), style);


        ImageTextButton quitCurrentQuizListButton = new ImageTextButton("Quit Current Quiz", exitStyle);

        //Add listeners to buttons
        choice1Button.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                validate(questionListIndexShuffler.get(0));
            }
        });
        choice2Button.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                validate(questionListIndexShuffler.get(1));
            }
        });
        choice3Button.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                validate(questionListIndexShuffler.get(2));
            }
        });
        choice4Button.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                validate(questionListIndexShuffler.get(3));
            }
        });

        quitCurrentQuizListButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.audioHandler.playButtonSound();
                ScreenManager.getInstance().showScreen(ScreenEnum.MENU_SCREEN, game);
            }
        });

        //Add buttons to table
        ;


        mainTable.add(questionImage).padBottom(10);
        mainTable.row();
        mainTable.add(questionTextLabel).padBottom(10);
        mainTable.row();

        Table questionTable = new Table();
        questionTable.center();

        questionTable.add(choice1Button).padBottom(10);
        questionTable.row();
        questionTable.add(choice2Button).padBottom(10);
        questionTable.row();
        questionTable.add(choice3Button).padBottom(10);
        questionTable.row();
        questionTable.add(choice4Button).padBottom(30);
        questionTable.row();

        mainTable.add(questionTable);
        mainTable.row();
        mainTable.add(quitCurrentQuizListButton);

        //Add table to stage
        stage.addActor(mainTable);
    }

    private void validate(int choice) {
        if (choice == question.getCorrectAnswer()) {
            updateCorrectAnswer();
        } else {
            updateWrongAnswer();
        }


        if(game.levelController.getNumberOfLivesLeft() <= 0){
            //TODO game over screen
            ScreenManager.getInstance().showScreen(ScreenEnum.RESULT_SCREEN, game);
        } else if (game.levelController.hasMoreQuestionsLeft()) {
            ScreenManager.getInstance().showScreen(ScreenEnum.QUIZ_SCREEN, game, game.levelController.getNextQuestion());
        } else {
            ScreenManager.getInstance().showScreen(ScreenEnum.RESULT_SCREEN, game);
        }

    }

    private void updateCorrectAnswer(){
        game.audioHandler.playCorrectAnswerSound();
        game.levelController.increaseCorrectAnswers();
    }

    private void updateWrongAnswer(){
        game.audioHandler.playWrongAnswerSound();
        game.levelController.removeOneHeart();
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        hud.draw();
    }

    @Override
    public void resize(int width, int height) {
        super.resize(width, height);
        hud.resize(width, height);
    }
}
