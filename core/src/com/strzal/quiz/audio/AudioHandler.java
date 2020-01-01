package com.strzal.quiz.audio;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.strzal.gdxUtilLib.BasicGame;
import com.strzal.quiz.constants.SoundPaths;

public class AudioHandler {
    private BasicGame game;
    private Music backgroundMusic;

    private AssetManager assetManager;
    long gameSoundID;
    float volume = 1;// 0.3f;

    public AudioHandler(BasicGame game){
        game = game;
        assetManager = game.getAssetManager();
    }

    public void playCorrectAnswerSound(){
        Sound sound = assetManager.get(SoundPaths.CORRECT_AUDIO);
//        if(game.getGameStatsHandler().isAudioOn())
        sound.play(volume);
    }

    public void playWrongAnswerSound(){
        Sound sound = assetManager.get(SoundPaths.WRONG_AUDIO);
//        if(game.getGameStatsHandler().isAudioOn())
        sound.play(volume);
    }

    public void playButtonSound(){
        Sound sound = assetManager.get(SoundPaths.MENU_BUTTON_AUDIO);
//        if(game.getGameStatsHandler().isAudioOn())
        sound.play(volume);
    }


}
