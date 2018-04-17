package com.udacity.gamedev.icicles;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;

import static com.udacity.gamedev.icicles.Constants.ICICLE_COLOR;
import static com.udacity.gamedev.icicles.Constants.ICICLE_HEIGHT;
import static com.udacity.gamedev.icicles.Constants.ICICLE_WIDTH;

public class Icicle {

    public static final String TAG = Icicle.class.getName();

    // TODO: Add a Vector2 position
    Vector2 position;

    // TODO: Add a constructor that sets the position
    public Icicle(float x, float y){
        position = new Vector2(x,y);
    }
    // TODO: Add a render function that takes a ShapeRenderer
    public void render(ShapeRenderer renderer) {


        // TODO: Set the ShapeRenderer's color
        renderer.setColor(ICICLE_COLOR);

        // TODO: Set the ShapeType
        renderer.set(ShapeRenderer.ShapeType.Filled);


        // TODO: Draw the icicle using the size constants
        renderer.triangle(position.x,position.y,
                position.x + ICICLE_WIDTH, position.y,
                position.x +ICICLE_WIDTH/2,position.y - ICICLE_HEIGHT);


    }
}
