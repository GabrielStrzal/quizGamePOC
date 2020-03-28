package com.strzal.quiz.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageTextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.strzal.gdxUtilLib.screenManager.ScreenManager;
import com.strzal.quiz.QuizGame;
import com.strzal.quiz.config.GameConfig;
import com.strzal.quiz.constants.ImagesPaths;
import com.strzal.quiz.screenManager.ScreenEnum;

public class MenuScreen extends BasicMenuScreen {

    private static final int GAME_VERSION_X_POSITION = 40;
    private static final int GAME_VERSION_Y_POSITION = (int) (GameConfig.SCREEN_HEIGHT - 70);



    public MenuScreen(QuizGame game) {
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


        //Create buttons
        ImageTextButton playButton = new ImageTextButton("Select Quiz", greenButtonStyle);
        ImageTextButton exitButton = new ImageTextButton("Exit", exitStyle);


        Image logo = new Image((Texture) game.getAssetManager().get(ImagesPaths.SPIN_LOGO));

        //Add listeners to buttons
        playButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {

                game.audioHandler.playButtonSound();
                ScreenManager.getInstance().showScreen( ScreenEnum.QUIZ_SELECTION_SCREEN, game );
            }
        });

        exitButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Gdx.app.exit();
            }
        });

        //Add buttons to table
//        mainTable.add(logo).padBottom(100);
        mainTable.row();
        mainTable.add(playButton).padBottom(30);
        //mainTable.row();
        //mainTable.add(exitButton).padBottom(30);

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
        addVersionText();

    }

    private void addVersionText() {
        Label textLabel = new Label(GameConfig.GAME_VERSION, labelStyle);
        textLabel.setPosition(GAME_VERSION_X_POSITION, GAME_VERSION_Y_POSITION);
        stage.addActor(textLabel);
    }

}
