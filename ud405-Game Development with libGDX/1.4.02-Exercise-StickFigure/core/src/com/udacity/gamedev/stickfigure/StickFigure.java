package com.udacity.gamedev.stickfigure;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;

/**
 * TODO: Start here
 *
 * In this exercise you'll set up a ShapeRenderer, and use it to draw a stick figure. At minimum,
 * you'll need a circle for the head, and five lines for the torso, arms, and legs.
 *
 * Remember to set up a ShapeRenderer you'll need to declare it, initialize it, and dispose of it.
 * Then to actually use the ShapeRenderer you'll need to start a batch of the appropriate type, draw
 * your shapes, and end the batch.
 *
 * We don't have step-by-step TODOs for this one, since the aim is for you to remember the steps for
 * setting up a ShapeRenderer and be able to set one up on your own. Of course, the solution is
 * available if you run into anything confusing.
 */
public class StickFigure extends ApplicationAdapter {

        private ShapeRenderer renderer;

    @Override
    public void create() {
         renderer = new ShapeRenderer();

    }

    @Override
    public void dispose() {

    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        float screenWidth = Gdx.graphics.getWidth();
        float screenHeight = Gdx.graphics.getHeight();

        float radius = 25;
        int sharpness = 500;

        float bodylength = 150;
        Vector2 bodystart = new Vector2(screenWidth/2,screenHeight/2-radius);
        Vector2 bodyend = new Vector2(screenWidth/2, screenHeight/2 - radius - bodylength);



        // Drawing the head
        renderer.begin(ShapeRenderer.ShapeType.Filled);
        renderer.setColor(Color.BLUE);
        renderer.circle(screenWidth/2,screenHeight/2,radius,sharpness);
        renderer.end();


        // Drawing the body
        renderer.begin(ShapeRenderer.ShapeType.Line);
        renderer.setColor(Color.BLACK);
        renderer.line(bodystart,bodyend);
        renderer.end();

        // Drawing the arms
        renderer.begin(ShapeRenderer.ShapeType.Line);
        renderer.setColor(Color.BLACK);
        Vector2 armstart = new Vector2(bodystart.x,bodystart.y-50);
        Vector2 armend = new Vector2(armstart.x + 50, armstart.y + 50);
        renderer.line(armstart,armend);
        armend = new Vector2(armstart.x - 50,armend.y);
        renderer.line(armstart,armend);
        renderer.end();

        // Drawing the legs
        renderer.begin(ShapeRenderer.ShapeType.Line);
        renderer.setColor(Color.BLACK);

        Vector2 legstart = new Vector2(bodyend.x,bodyend.y);
        Vector2 legend = new Vector2(legstart.x + 50, legstart.y - 50 );
        renderer.line(legstart,legend);
        legend = new Vector2(legstart.x - 50, legstart.y - 50 );
        renderer.line(legstart,legend);
        renderer.end();


    }
}
