package com.udacity.gamedev.gigagal;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.utils.Array;
import com.udacity.gamedev.gigagal.entities.GigaGal;
import com.udacity.gamedev.gigagal.entities.Platform;

public class Level {

    GigaGal gigaGal;

    // TODO: Add an Array of Platforms
    Array<Platform> platformArray;

    public Level() {
        gigaGal = new GigaGal();

        // TODO: Initialize the platform array
        platformArray = new Array<Platform>();

        // TODO: Add a test platform
        platformArray.add(new Platform(20,100,50,50));
    }

    public void update(float delta) {
        gigaGal.update(delta);
    }

    public void render(SpriteBatch batch, ShapeRenderer renderer) {

        renderer.begin(ShapeType.Filled);
        // TODO: Render all platforms in the platform array
        for(Platform p : platformArray){
            p.render(renderer);
        }
        renderer.end();

        batch.begin();
        gigaGal.render(batch);
        batch.end();
    }

}
