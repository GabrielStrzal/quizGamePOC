package com.strzal.quiz.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.ImageTextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.strzal.gdxUtilLib.screenManager.ScreenManager;
import com.strzal.quiz.QuizGame;
import com.strzal.quiz.screenManager.ScreenEnum;

public class ResultScreen extends BasicMenuScreen {


    public ResultScreen(QuizGame game) {
        super(game);
    }


    @Override
    public void show() {
        super.show();
        //Stage should control input:
        Gdx.input.setInputProcessor(stage);

        //Create Table
        Table mainTable = new Table();
        //Set table to fill stage
        mainTable.setFillParent(true);
        //Set alignment of contents in the table.
        mainTable.center().bottom().left().padBottom(50).padLeft(40);
        System.out.println();
        Label resultLabel = new Label("Quiz ended!", labelStyle);
        Label resultLabelStats = new Label(
                game.levelController.getNumberOfCorrectAnswers() + " correct answers of "
                        + game.levelController.getNumberOfTotalQuestions(), labelStyle);


        //Create buttons
        ImageTextButton quitReturnToMenuButton = new ImageTextButton("Return to Menu", greenButtonStyle);
        ImageTextButton exitButton = new ImageTextButton("Exit Game", exitStyle);

        
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
        mainTable.add(resultLabel).padBottom(10);
        mainTable.row();
        mainTable.add(resultLabelStats).padBottom(100);
        mainTable.row();
        mainTable.add(quitReturnToMenuButton).padBottom(30);

        //Add table to stage
        stage.addActor(mainTable);
    }
}
