package com.udacity.gamedev.icicles;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.viewport.ExtendViewport;

import static com.udacity.gamedev.icicles.Constants.BACKGROUND_COLOR;
import static com.udacity.gamedev.icicles.Constants.WORLD_SIZE;


public class IciclesScreen implements Screen {

    public static final String TAG = IciclesScreen.class.getName();


    // TODO: Add an ExtendViewport
    ExtendViewport viewport;

    // TODO: Add a ShapeRenderer
    ShapeRenderer renderer;

    // TODO: Add an Icicle
    Icicle icicle;

    @Override
    public void show() {
        Gdx.app.log(TAG,"show()");
        // TODO: Initialize the viewport using the world size constant
        viewport = new ExtendViewport(WORLD_SIZE,WORLD_SIZE);

        // TODO: Initialize the ShapeRenderer
        renderer = new ShapeRenderer();

        // TODO: Set autoShapeType(true) on the ShapeRenderer
        renderer.setAutoShapeType(true);

        // TODO: Create a new Icicle in the middle of the world
        icicle = new Icicle(WORLD_SIZE/2,WORLD_SIZE/2);


    }

    @Override
    public void resize(int width, int height) {
        // TODO: Ensure that the viewport updates correctly
        viewport.update(width,height,true);
    }

    @Override
    public void dispose() {
        renderer.dispose();

    }


    @Override
    public void render(float delta) {
        Gdx.app.log(TAG,"remnder()");
        // TODO: Apply the viewport
        viewport.apply(true);

        // TODO: Clear the screen to the background color
        Gdx.gl.glClearColor(BACKGROUND_COLOR.r,BACKGROUND_COLOR.g,BACKGROUND_COLOR.b,BACKGROUND_COLOR.a);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // TODO: Set the ShapeRenderer's projection matrix
        renderer.setProjectionMatrix(viewport.getCamera().combined);

        // TODO: Draw the Icicle
        renderer.begin(ShapeRenderer.ShapeType.Filled);
        icicle.render(renderer);
        renderer.end();




    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    // TODO: Dispose of the ShapeRenderer
    @Override
    public void hide() {
        renderer.dispose();
    }
}
