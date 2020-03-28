package com.strzal.quiz.config;


public class GameConfig {

    public static final float SCREEN_WIDTH = 960; //480; //pixels
    public static final float SCREEN_HEIGHT = 1600; //800; //pixels

    //Only used for HTML Display size
    public static final int SCREEN_HTML_DISPLAY_WIDTH = (int)SCREEN_WIDTH/2 ; //pixels
    public static final int SCREEN_HTML_DISPLAY_HEIGHT = (int)SCREEN_HEIGHT/2 ; //pixels

    //Only used for Desktop Display size
    public static final int SCREEN_DESKTOP_DISPLAY_WIDTH = (int)SCREEN_WIDTH/2 ; //pixels
    public static final int SCREEN_DESKTOP_DISPLAY_HEIGHT = (int)SCREEN_HEIGHT/2 ; //pixels

    public static final String GAME_VERSION = "V.0.7";

    public static final boolean debug = true;

    private GameConfig(){}
}
