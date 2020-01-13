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
import com.strzal.quiz.constants.QuestionsPaths;
import com.strzal.quiz.controller.LevelController;
import com.strzal.quiz.screenManager.ScreenEnum;

public class MenuScreen extends BasicMenuScreen {


    public MenuScreen(BasicGame game) {
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
        ImageTextButton playButton = new ImageTextButton("Play", style);
        ImageTextButton exitButton = new ImageTextButton("Exit", exitStyle);


        Image logo = new Image((Texture) game.getAssetManager().get(ImagesPaths.SPIN_LOGO));

        //Add listeners to buttons
        playButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {

                game.audioHandler.playButtonSound();
                game.levelController = new LevelController(QuestionsPaths.WHITE_BELT_01 + QuestionsPaths.EN_CA_JSON);

                ScreenManager.getInstance().showScreen(
                        ScreenEnum.QUIZ_SCREEN, game, game.levelController.getNextQuestion()
                );
            }
        });

        exitButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Gdx.app.exit();
            }
        });

        //Add buttons to table
        mainTable.add(logo).padBottom(100);
        mainTable.row();
        mainTable.add(playButton).padBottom(10);
        mainTable.row();
        mainTable.add(exitButton);

        //Add table to stage
        stage.addActor(mainTable);


        /*

        https://github.com/libgdx/libgdx/wiki/Table#padding


        table.row().colspan(3).expandX().fillX();
        table.add(topLabel).fillX();
        table.row().colspan(3).expandX().fillX();
        table.add(slider).fillX();
        table.row().colspan(3).expandX().fillX();
        table.add(anotherLabel).fillX();
        table.row().expandX().fillX();

        table.add(checkBoxA).expandX().fillX();
        table.add(checkBoxB).expandX().fillX();
        table.add(checkBoxC).expandX().fillX();
        table.row().expandX().fillX();;

        table.add(buttonTable).colspan(3);

        buttonTable.pad(16);
        buttonTable.row().fillX().expandX();
        buttonTable.add(buttonA).width(cw/3.0f);
        buttonTable.add(buttonB).width(cw/3.0f);

        tableContainer.setActor(table);
        stage.addActor(tableContainer);
         */
    }

}
