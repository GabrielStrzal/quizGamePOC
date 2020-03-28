package com.strzal.quiz.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageTextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.strzal.gdxUtilLib.screenManager.ScreenManager;
import com.strzal.quiz.QuizGame;
import com.strzal.quiz.constants.ImagesPaths;
import com.strzal.quiz.constants.QuestionsPaths;
import com.strzal.quiz.controller.LevelController;
import com.strzal.quiz.hud.Hud;
import com.strzal.quiz.hud.HudMode;
import com.strzal.quiz.screenManager.ScreenEnum;

public class QuizSelectionScreen extends BasicMenuScreen {

    private Hud hud;

    public QuizSelectionScreen(QuizGame game) {
        super(game);
        hud = new Hud(this.game, HudMode.MENU_MODE);
    }


    @Override
    public void show() {
        super.show();
        //Not sure
        InputMultiplexer inputMultiplexer = new InputMultiplexer();
        inputMultiplexer.addProcessor(stage);
        inputMultiplexer.addProcessor(hud.getStage());
        Gdx.input.setInputProcessor(inputMultiplexer);

        //Create Table
        Table mainTable = new Table();
        //Set table to fill stage
        mainTable.setFillParent(true);
        //Set alignment of contents in the table.
        mainTable.center().bottom().left().padBottom(50).padLeft(40);


        //Create buttons
        ImageTextButton barreJaune = new ImageTextButton("Barre jaune", greenButtonStyle);
        ImageTextButton numbersInKorean = new ImageTextButton("Compter de 1 à 10 en coréen", greenButtonStyle);
        ImageTextButton exitButton = new ImageTextButton("Exit", exitStyle);


        Image logo = new Image((Texture) game.getAssetManager().get(ImagesPaths.SPIN_LOGO));

        //Add listeners to buttons
        barreJaune.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {

                game.audioHandler.playButtonSound();
                game.levelController = new LevelController(QuestionsPaths.WHITE_BELT_01 + QuestionsPaths.EN_CA_JSON);
                ScreenManager.getInstance().showScreen(
                        ScreenEnum.QUIZ_SCREEN, game, game.levelController.getNextQuestion()
                );
            }
        });

        numbersInKorean.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {

                game.audioHandler.playButtonSound();
                game.levelController = new LevelController(QuestionsPaths.NUMBERS_IN_KOREAN_01 + QuestionsPaths.FR_CA_JSON);
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
        mainTable.row();
        mainTable.add(barreJaune).padBottom(10);
        mainTable.row();
        mainTable.add(numbersInKorean).padBottom(30);
        mainTable.row();
        //mainTable.add(exitButton);

        //Add table to stage
        //Create Table
        Table logoTable = new Table();
        //Set table to fill stage
        logoTable.setFillParent(true);
        //Set alignment of contents in the table.
        logoTable.center().top().padTop(300);
        logoTable.add(logo);

        stage.addActor(logoTable);
        stage.addActor(mainTable);

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
