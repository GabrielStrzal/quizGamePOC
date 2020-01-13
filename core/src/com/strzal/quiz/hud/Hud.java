package com.strzal.quiz.hud;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.strzal.quiz.QuizGame;
import com.strzal.quiz.config.GameConfig;
import com.strzal.quiz.constants.ImagesPaths;

public class Hud {

    private AssetManager assetManager;
    private Stage stage;
    private QuizGame game;

    private Image heartImage;

    private static int HEART_PADDING_X = 60;
    private static int HEART_PADDING_Y = 100;

    public Hud(QuizGame game){
        this.game = game;
        stage = new Stage(new FitViewport(GameConfig.SCREEN_WIDTH, GameConfig.SCREEN_HEIGHT));
        assetManager = game.getAssetManager();

        //Not sure
        InputMultiplexer inputMultiplexer = new InputMultiplexer();
        inputMultiplexer.addProcessor(stage);

        Gdx.input.setInputProcessor(inputMultiplexer);

        addHearts();
    }

    private void addHearts() {
        for(int i = 1; i <= game.levelController.getNumberOfLivesLeft(); i++) {
            addHeart(i * HEART_PADDING_X);
        }
    }

    private void addHeart(long xPosition) {
        heartImage = new Image((Texture) assetManager.get(ImagesPaths.HEART));
        //heartImage.setSize(HEART_SIZE,HEART_SIZE);
        heartImage.setPosition(xPosition, GameConfig.SCREEN_HEIGHT - HEART_PADDING_Y);
        stage.addActor(heartImage);
    }


    public void resize(int width, int height){
        stage.getViewport().update(width, height);
    }

    public void draw(){
        stage.act(Gdx.graphics.getDeltaTime());
        stage.draw();
    }

}
