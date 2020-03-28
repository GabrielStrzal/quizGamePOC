package com.strzal.quiz.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.strzal.gdxUtilLib.screenManager.ScreenManager;
import com.strzal.quiz.QuizGame;
import com.strzal.quiz.entities.Question;
import com.strzal.quiz.hud.Hud;
import com.strzal.quiz.hud.HudMode;
import com.strzal.quiz.screenManager.ScreenEnum;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class QuizScreen extends BasicMenuScreen {

    Question question;
    List<Integer> questionListIndexShuffler = Arrays.asList(0, 1, 2, 3);
    Hud hud;

    private ImageTextButton choice1Button;
    private ImageTextButton choice2Button;
    private ImageTextButton choice3Button;
    private ImageTextButton choice4Button;

    private Table questionTable;

    private int initialValue = 10;
    private int playerChoice = initialValue;

    public QuizScreen(QuizGame game, Question question) {
        super(game);
        this.question = question;
        Collections.shuffle(questionListIndexShuffler);
        hud = new Hud(this.game, HudMode.QUIZ_MODE);
    }


    @Override
    public void show() {
        super.show();
        //Stage should control input:

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
        mainTable.center().top().left().padTop(250).padLeft(40);

        // Image
        Image questionImage;
        if(question.getImagePath() != null){
            questionImage = new Image(new Texture(Gdx.files.internal(question.getImagePath())));
        } else {
            questionImage = new Image(new Texture(Gdx.files.internal("images/kicks/01_round.JPG")));
        }

        // Question
        Label questionTextLabel = new Label(question.getQuestionString(), labelStyle);
        questionTextLabel.setWrap(true);
        questionTextLabel.setAlignment(Align.center);
        questionTextLabel.setFillParent(true);

        Container container = new Container(questionTextLabel);
        container.width(870);

        VerticalGroup verticalGroup = new VerticalGroup();
        verticalGroup.addActor(container);





        //Add buttons to table
        mainTable.add(questionImage).padBottom(10);
        mainTable.row();
        mainTable.add(verticalGroup).padBottom(10);
        mainTable.row();




        //Add table to stage
        stage.addActor(mainTable);

        questionTable = new Table();
        questionTable.center().bottom().left().padBottom(50).padLeft(40);



        if(!"TrueFalse".equals(question.getQuestionType())){
            createAnswersButtons();
        } else {
            createTrueFalseButtons();
        }

        addCheckButton();

        stage.addActor(questionTable);

    }

    private void addCheckButton(){
        ImageTextButton validateButton = new ImageTextButton("CHECK", greenButtonStyle);
        validateButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if(playerChoice != initialValue){
                    validate(playerChoice);
                }

            }
        });

        questionTable.add(validateButton).padBottom(30);
        questionTable.row();
    }

    private void createTrueFalseButtons() {
        //Create buttons
        final ImageTextButton choice1Button = new ImageTextButton("True", blueButtonStyle);
        final ImageTextButton choice2Button = new ImageTextButton("False", blueButtonStyle);

        //Add listeners to buttons
        choice1Button.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                playerChoice = 0;
                choice1Button.setChecked(true);
                choice2Button.setChecked(false);
            }
        });
        choice2Button.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                playerChoice = 1;
                choice1Button.setChecked(false);
                choice2Button.setChecked(true);
            }
        });


        questionTable.add(choice1Button).padBottom(10);
        questionTable.row();
        questionTable.add(choice2Button).padBottom(30);
        questionTable.row();
    }


    private void createAnswersButtons(){
        //Create buttons
        final ImageTextButton choice1Button = new ImageTextButton(question.getAnswers().get(questionListIndexShuffler.get(0)).getText(), blueButtonStyle);
        final ImageTextButton choice2Button = new ImageTextButton(question.getAnswers().get(questionListIndexShuffler.get(1)).getText(), blueButtonStyle);
        final ImageTextButton choice3Button = new ImageTextButton(question.getAnswers().get(questionListIndexShuffler.get(2)).getText(), blueButtonStyle);
        final ImageTextButton choice4Button = new ImageTextButton(question.getAnswers().get(questionListIndexShuffler.get(3)).getText(), blueButtonStyle);



        //Add listeners to buttons
        choice1Button.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                playerChoice = questionListIndexShuffler.get(0);
                choice1Button.setChecked(true);
                choice2Button.setChecked(false);
                choice3Button.setChecked(false);
                choice4Button.setChecked(false);
            }
        });
        choice2Button.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                playerChoice = questionListIndexShuffler.get(1);
                choice1Button.setChecked(false);
                choice2Button.setChecked(true);
                choice3Button.setChecked(false);
                choice4Button.setChecked(false);
            }
        });
        choice3Button.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                playerChoice = questionListIndexShuffler.get(2);
                choice1Button.setChecked(false);
                choice2Button.setChecked(false);
                choice3Button.setChecked(true);
                choice4Button.setChecked(false);
            }
        });
        choice4Button.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                playerChoice = questionListIndexShuffler.get(3);
                choice1Button.setChecked(false);
                choice2Button.setChecked(false);
                choice3Button.setChecked(false);
                choice4Button.setChecked(true);
            }
        });

        questionTable.add(choice1Button).padBottom(10);
        questionTable.row();
        questionTable.add(choice2Button).padBottom(10);
        questionTable.row();
        questionTable.add(choice3Button).padBottom(10);
        questionTable.row();
        questionTable.add(choice4Button).padBottom(10);
        questionTable.row();

    }

    private void unCheckAll(){

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
