package com.strzal.quiz.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.ImageTextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.strzal.gdxUtilLib.BasicGame;
import com.strzal.gdxUtilLib.screenManager.ScreenManager;
import com.strzal.quiz.constants.ImagesPaths;
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
        ImageTextButton quitReturnToMenuButton = new ImageTextButton("Return to Menu", style);
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
