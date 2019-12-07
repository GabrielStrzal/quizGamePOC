package com.strzal.quiz.constants;

import com.strzal.gdx.loading.LoadingPaths;
import java.util.ArrayList;
import java.util.List;

public class LoadingPathsImpl implements LoadingPaths {
    @Override
    public List<String> getTexturePaths() {
        List<String> list = new ArrayList<>();

        //Menu
        list.add(ImagesPaths.MENU_BACKGROUND);

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
        return null;
    }

}
