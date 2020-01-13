package com.strzal.quiz.loading;

import com.strzal.gdxUtilLib.loading.LoadingPaths;
import com.strzal.quiz.constants.ImagesPaths;
import com.strzal.quiz.constants.SoundPaths;

import java.util.ArrayList;
import java.util.List;

public class LoadingPathsImpl implements LoadingPaths {
    @Override
    public List<String> getTexturePaths() {
        List<String> list = new ArrayList<>();

        //Menu
        list.add(ImagesPaths.SPIN_LOGO);
        list.add(ImagesPaths.QUESTION_BUTTON);
        list.add(ImagesPaths.QUESTION_BUTTON_PRESSED);

        list.add(ImagesPaths.EXIT_BUTTON);
        list.add(ImagesPaths.EXIT_BUTTON_PRESSED);

        list.add(ImagesPaths.HEART);

        return list;
    }

    @Override
    public List<String> getBitmapPaths() {
        return null;
    }

    @Override
    public List<String> getTileMapPaths() {
        return null;
    }

    @Override
    public List<String> getMusicPaths() {
        return null;
    }

    @Override
    public List<String> getSoundPaths() {
        List<String> list = new ArrayList<>();

        //Menu
        list.add(SoundPaths.CORRECT_AUDIO);
        list.add(SoundPaths.WRONG_AUDIO);
        list.add(SoundPaths.MENU_BUTTON_AUDIO);

        return list;
    }

}
