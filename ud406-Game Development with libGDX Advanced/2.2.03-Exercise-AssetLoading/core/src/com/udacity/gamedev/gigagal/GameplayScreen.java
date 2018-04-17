package com.udacity.gamedev.gigagal;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.udacity.gamedev.gigagal.util.Assets;
import com.udacity.gamedev.gigagal.util.Constants;


public class GameplayScreen extends ScreenAdapter {

    public static final String TAG = GameplayScreen.class.getName();

    // TODO: Add a SpriteBatch
    SpriteBatch spriteBatch;

    // TODO: Add an ExtendViewport
    ExtendViewport extendViewport;

    @Override
    public void show() {

        // TODO: Initialize the Assets instance
        Assets.instance.init();

        // TODO: Initalize the SpriteBatch
        spriteBatch = new SpriteBatch();

        // TODO: Initialize the viewport
        extendViewport = new ExtendViewport(Constants.WORLD_SIZE,Constants.WORLD_SIZE);
    }

    @Override
    public void resize(int width, int height) {
        // TODO: Update the viewport
        extendViewport.update(width,height,true);
    }

    @Override
    public void dispose() {
        // TODO: Dispose of the Assets instance
        Assets.instance.dispose();

        // TODO: Dispose of the SpriteBatch
        spriteBatch.dispose();
    }

    @Override
    public void render(float delta) {

        // TODO: Apply the viewport
        extendViewport.apply();

        // TODO: Clear the screen to the BACKGROUND_COLOR

        Gdx.gl.glClearColor(Constants.BACKGROUND_COLOR.r,Constants.BACKGROUND_COLOR.g,Constants.BACKGROUND_COLOR.b,Constants.BACKGROUND_COLOR.a);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // TODO: Set the SpriteBatch's projection matrix
        spriteBatch.setProjectionMatrix(extendViewport.getCamera().combined);

        // TODO: Begin the SpriteBatch
        spriteBatch.begin();

        // TODO: Draw the standing right AtlasRegion
        TextureAtlas.AtlasRegion ar = Assets.instance.gigaGalAssets.atlasRegion;
        spriteBatch.draw(ar.getTexture(),
                0,
                0,
                0,
                0,
                ar.getRegionWidth(),
                ar.getRegionHeight(),
                1,
                1,
                0,
                ar.getRegionX(),
                ar.getRegionY(),
                ar.getRegionWidth(),
                ar.getRegionHeight(),
                false,
                false);

        // TODO: End the SpriteBatch
        spriteBatch.end();
    }
}
