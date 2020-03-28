package com.strzal.quiz.hud;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ActorGestureListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.strzal.gdxUtilLib.screenManager.ScreenManager;
import com.strzal.quiz.QuizGame;
import com.strzal.quiz.config.GameConfig;
import com.strzal.quiz.constants.ImagesPaths;
import com.strzal.quiz.screenManager.ScreenEnum;

public class Hud {

    private AssetManager assetManager;
    private Stage stage;
    private QuizGame game;
    private HudMode mode;

    private ShapeRenderer shapeRenderer;

    private Image heartImage;
    private Texture backButtonTexture;
    private Texture backButtonPressedTexture;

    private static int SCREEN_PADDING = 50;
    private static int HEART_PADDING_X = 90;
    private static int HEART_PADDING_Y = 200;

    private static int PROGRESS_BAR_X_POSITION = 160;
    private static int PROGRESS_BAR_Y_POSITION = 80;

    public Hud(QuizGame game, HudMode mode){
        this.game = game;
        this.mode = mode;
        stage = new Stage(new FitViewport(GameConfig.SCREEN_WIDTH, GameConfig.SCREEN_HEIGHT));
        assetManager = game.getAssetManager();
        shapeRenderer = game.getShapeRenderer();
        shapeRenderer.setProjectionMatrix(stage.getViewport().getCamera().combined);


        if(mode == HudMode.QUIZ_MODE){
            addHearts();
        }
        addExitButton();
    }

    private void addExitButton() {
        backButtonTexture = assetManager.get(ImagesPaths.EXIT_CROSS);
        backButtonPressedTexture = assetManager.get(ImagesPaths.EXIT_CROSS_PRESSED);
        ImageButton backBtn = new ImageButton(
                new TextureRegionDrawable(new TextureRegion(backButtonTexture)),
                new TextureRegionDrawable(new TextureRegion(backButtonPressedTexture)));
        backBtn.setPosition(SCREEN_PADDING, GameConfig.SCREEN_HEIGHT - SCREEN_PADDING - 50);
        //backBtn.setSize(BUTTON_SIZE, BUTTON_SIZE);

        backBtn.addListener(new ActorGestureListener() {
            @Override
            public void tap(InputEvent event, float x, float y, int count,
                            int button) {
                super.tap(event, x, y, count, button);
                game.audioHandler.playButtonSound();
                ScreenManager.getInstance().showScreen(ScreenEnum.MENU_SCREEN, game);

            }
        });
        stage.addActor(backBtn);

    }

    private void addHearts() {
        for(int i = 1; i <= game.levelController.getNumberOfLivesLeft(); i++) {
            addHeart((long) ((GameConfig.SCREEN_WIDTH - SCREEN_PADDING) - (i * HEART_PADDING_X)));
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
        if(mode == HudMode.QUIZ_MODE){
            drawProgressBar();
        }
    }

    public Stage getStage() {
        return stage;
    }

    private void drawProgressBar() {
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);

        //Base bar form
        shapeRenderer.setColor(Color.GRAY);
        shapeRenderer.rect(
                PROGRESS_BAR_X_POSITION,
                GameConfig.SCREEN_HEIGHT - PROGRESS_BAR_Y_POSITION,
                740, 15);

        //Progress bar
        shapeRenderer.setColor(Color.LIME);
        shapeRenderer.rect(
                PROGRESS_BAR_X_POSITION,
                GameConfig.SCREEN_HEIGHT - PROGRESS_BAR_Y_POSITION,
                ( 740 * game.levelController.getNumberOfQuestionsAnswered())
                        /game.levelController.getNumberOfTotalQuestions(),
                15);
        shapeRenderer.end();
    }
}
