package com.udacity.gamedev.gigagal.entities;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class Platform {

    // TODO: Add members for the platform top, bottom, left edge, right edge, width, and height
    float top,bottom,width,height,left,right;


    public Platform(float left, float top, float width, float height) {
        // TODO: Populate the member variables
        this.top = top;
        this.left = left;
        this.right = left + width;
        this.bottom = top - height;
        this.width = width;
        this.height = height;
    }

    public void render(ShapeRenderer renderer) {

        // TODO: Draw a box representing the platform
        renderer.set(ShapeRenderer.ShapeType.Filled);
        renderer.setColor(Color.BLACK);
        renderer.rect(left,bottom,width,height);

    }

}
