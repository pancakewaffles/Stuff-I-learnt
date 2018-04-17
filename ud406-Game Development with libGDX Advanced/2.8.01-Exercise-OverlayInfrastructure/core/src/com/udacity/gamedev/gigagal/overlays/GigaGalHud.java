package com.udacity.gamedev.gigagal.overlays;


import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.udacity.gamedev.gigagal.util.Constants;

public class GigaGalHud {

    public Viewport viewport;
    BitmapFont font;

    public GigaGalHud() {

        // TODO: Initialize the viewport with an ExtendViewport, using Constants.HUD_VIEWPORT_SIZE
        viewport = new ExtendViewport(Constants.HUD_VIEWPORT_SIZE, Constants.HUD_VIEWPORT_SIZE);
        // TODO: Initialize a new BitmapFont
        font = new BitmapFont();

    }

    public void render(SpriteBatch batch) {

        // TODO: Apply the viewport
        viewport.apply();
        // TODO: Set the projection matrix
        batch.setProjectionMatrix(viewport.getCamera().combined);
        // TODO: Begin a batch
        batch.begin();
        // TODO: Draw some test text in the middle of the screen
        font.draw(batch,"lul test text",Constants.HUD_VIEWPORT_SIZE/2,Constants.HUD_VIEWPORT_SIZE/2);
        // TODO: End the batch
        batch.end();
    }
}
