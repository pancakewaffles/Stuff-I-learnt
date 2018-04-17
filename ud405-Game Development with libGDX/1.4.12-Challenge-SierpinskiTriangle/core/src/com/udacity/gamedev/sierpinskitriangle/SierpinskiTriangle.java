package com.udacity.gamedev.sierpinskitriangle;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.viewport.FitViewport;

/**
 * TODO: Start here
 *
 * Your challenge, should you choose to accept it, is to draw a Serpinski Triangle. I offer no hints
 * beyond the fact that ShapeRenderer has a very convenient triangle() function, and that using a
 * FitViewport can simplify matters considerably. Good luck!
 */


public class SierpinskiTriangle extends ApplicationAdapter {

    static final float SIZE = 10;
    private static final int RECURSIONS = 7;


    ShapeRenderer renderer;
    FitViewport viewport;

    @Override
    public void create() {
        renderer = new ShapeRenderer();
        viewport = new FitViewport(SIZE,SIZE);
        }

    @Override
    public void dispose() {
        renderer.dispose();
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width,height,true);
    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        viewport.apply();
        renderer.setProjectionMatrix(viewport.getCamera().combined);

        renderer.begin(ShapeRenderer.ShapeType.Filled);
        initializeSierpinskiTriangle(renderer,SIZE,RECURSIONS);

        renderer.end();

    }

    public void initializeSierpinskiTriangle(ShapeRenderer renderer,float size, int recursions){
        renderer.setColor(Color.BLACK);
        // I am setting up the first triangle
        Vector2 corner1 = new Vector2(0,0);
        Vector2 corner2 = new Vector2(size,0);
        Vector2 corner3 = new Vector2(size/2,(float)(size*Math.sin(Math.PI/3)));

        renderer.triangle(corner1.x,corner1.y,corner2.x,corner2.y,corner3.x,corner3.y);
        renderer.setColor(Color.WHITE);
        drawSierpinskiTriangle(renderer,corner1,corner2,corner3,recursions); // corner1 corner 2 and corner3 are now the new starting parameters
        // corner1 corner2 and corner3 make up the vertices of the first equilateral triangle
        /*
        Here's how I defined my triangle
                   corner3



        corner1                 corner2
         */

    }

    public void drawSierpinskiTriangle(ShapeRenderer renderer, Vector2 c1, Vector2 c2, Vector2 c3, int recursions){
        if(recursions == 0){
            return;
        }
        Vector2 midpt1 = new Vector2((c3.x+c1.x)/2,(c3.y+c1.y)/2); // The point halfway between corner1 and corner3
        Vector2 midpt2 = new Vector2((c2.x+c1.x)/2,(c2.y+c1.y)/2); // The point halfway between corner1 and corner2
        Vector2 midpt3 = new Vector2((c2.x+c3.x)/2,(c2.y+c3.y)/2); // The point halfway between corner2 and corner3

        renderer.triangle(midpt1.x,midpt1.y,midpt2.x,midpt2.y,midpt3.x,midpt3.y);

        // Now we have 4 triangles: 3 black and 1 white in the middle. We will call drawSierpinskiTriangle on the 3 black ones
        drawSierpinskiTriangle(renderer,midpt1,midpt3,c3,recursions-1); // The top black triangle
        drawSierpinskiTriangle(renderer,c1,midpt2,midpt1,recursions-1); // The bottom left triangle
        drawSierpinskiTriangle(renderer,midpt2,c2,midpt3,recursions-1); // The bottom right triangle

    }




}
