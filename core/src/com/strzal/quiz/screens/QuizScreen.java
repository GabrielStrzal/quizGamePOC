package com.strzal.quiz.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.strzal.gdxUtilLib.BasicGame;
import com.strzal.gdxUtilLib.screenManager.ScreenManager;
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

        Label questionTextLabel = new Label(question.getQuestionString(), skin);

        //Create buttons
        TextButton choice1Button = new TextButton(question.getAnswers().get(questionListIndexShuffler.get(0)), skin);
        choice1Button.setScale(2);
        TextButton choice2Button = new TextButton(question.getAnswers().get(questionListIndexShuffler.get(1)), skin);
        TextButton choice3Button = new TextButton(question.getAnswers().get(questionListIndexShuffler.get(2)), skin);
        TextButton choice4Button = new TextButton(question.getAnswers().get(questionListIndexShuffler.get(3)), skin);

        TextButton quitCurrentQuizListButton = new TextButton("Quit Current Quiz", skin);

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
