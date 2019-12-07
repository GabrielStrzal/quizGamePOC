package com.strzal.quiz.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.strzal.gdx.BasicGame;
import com.strzal.gdx.screenManager.ScreenManager;
import com.strzal.quiz.entities.Question;
import com.strzal.quiz.screenManager.ScreenEnum;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class QuizScreen extends BasicMenuScreen {

    Question question;
    List<Integer> questionListIndexes = Arrays.asList(1, 2, 3, 4);

    public QuizScreen(BasicGame game, Question question) {
        super(game);
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
        choice1Button.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                validate(questionListIndexes.get(0));
            }
        });
        choice2Button.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                validate(questionListIndexes.get(1));
            }
        });
        choice3Button.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                validate(questionListIndexes.get(2));
            }
        });
        choice4Button.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                validate(questionListIndexes.get(3));
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
            game.audioHandler.playCorrectAnswerSound();
            game.levelController.increaseCorrectAnswers();

        } else {
            game.audioHandler.playWrongAnswerSound();
        }
        if (game.levelController.hasMoreQuestionsLeft()) {
            ScreenManager.getInstance().showScreen(ScreenEnum.QUIZ_SCREEN, game, game.levelController.getNextQuestion());
        } else {
            ScreenManager.getInstance().showScreen(ScreenEnum.RESULT_SCREEN, game);
        }

    }
}
