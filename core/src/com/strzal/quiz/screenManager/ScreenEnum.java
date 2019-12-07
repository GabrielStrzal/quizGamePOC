package com.strzal.quiz.screenManager;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.ScreenAdapter;
import com.strzal.gdx.loading.LoadingPaths;
import com.strzal.gdx.screenManager.ScreenEnumInterface;
import com.strzal.gdx.screens.LoadingScreen;
import com.strzal.quiz.QuizGame;
import com.strzal.quiz.entities.Question;
import com.strzal.quiz.screens.MenuScreen;
import com.strzal.quiz.screens.QuizScreen;
import com.strzal.quiz.screens.ResultScreen;

/**
 * Based on http://www.pixnbgames.com/blog/libgdx/how-to-manage-screens-in-libgdx/
 */

public enum ScreenEnum implements ScreenEnumInterface {

    LOADING_SCREEN {
        public Screen getScreen(Object... params) {
            return new LoadingScreen((QuizGame) params[0], (LoadingPaths)params[1], (ScreenAdapter)params[2]);
        }
    },
    MENU_SCREEN {
        public Screen getScreen(Object... params) {
            return new MenuScreen((QuizGame)params[0]);
        }
    },
    QUIZ_SCREEN {
        public Screen getScreen(Object... params) {
            return new QuizScreen((QuizGame)params[0], (Question)params[1]);
        }
    },
    RESULT_SCREEN {
        public Screen getScreen(Object... params) {
            return new ResultScreen((QuizGame)params[0] );
        }
    }

}
