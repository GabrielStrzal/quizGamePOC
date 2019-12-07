package com.strzal.quiz.loading;

import com.strzal.gdx.loading.LoadingPaths;
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
