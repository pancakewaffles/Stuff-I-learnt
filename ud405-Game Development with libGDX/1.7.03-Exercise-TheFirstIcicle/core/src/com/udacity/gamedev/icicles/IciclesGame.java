package com.udacity.gamedev.icicles;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;


public class IciclesGame extends Game {

    //TODO: call setScreen() with a new IciclesScreen()
    @Override
    public void create() {
        Gdx.app.log("ICICLESGAME","create()");
        setScreen(new IciclesScreen());

    }
}
