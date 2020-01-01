package com.strzal.quiz.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.strzal.gdxUtilLib.BasicGame;
import com.strzal.gdxUtilLib.screenManager.ScreenManager;
import com.strzal.quiz.screenManager.ScreenEnum;

public class ResultScreen extends BasicMenuScreen {


    public ResultScreen(BasicGame game) {
        super(game);
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
                        + game.levelController.getNumberOfTotalQuestions(), skin);
        //Create buttons

        TextButton quitReturnToMenuButton = new TextButton("Return to Menu", skin);
        TextButton exitButton = new TextButton("Exit Game", skin);

        //Add listeners to buttons
        quitReturnToMenuButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.audioHandler.playButtonSound();
                ScreenManager.getInstance().showScreen(ScreenEnum.MENU_SCREEN, game);
            }
        });
        exitButton.addListener(new ClickListener() {
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
}
