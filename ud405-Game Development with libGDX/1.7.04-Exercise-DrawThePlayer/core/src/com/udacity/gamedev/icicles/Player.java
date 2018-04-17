package com.udacity.gamedev.icicles;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.viewport.Viewport;

import static com.udacity.gamedev.icicles.Constants.CIRCLE_SEGMENTS;
import static com.udacity.gamedev.icicles.Constants.HEAD_HEIGHT;
import static com.udacity.gamedev.icicles.Constants.HEAD_RADIUS;
import static com.udacity.gamedev.icicles.Constants.LIMB_LENGTH;
import static com.udacity.gamedev.icicles.Constants.LIMB_WIDTH;
import static com.udacity.gamedev.icicles.Constants.PLAYER_COLOR;

public class Player {

    public static final String TAG = Player.class.getName();

    // TODO: Add a position (add constants to Constants.java first)
    Vector2 position;

    // TODO: Add a viewport
    Viewport viewport;

    // TODO: Add constructor that accepts and sets the viewport, then calls init()
    public Player(Viewport viewport){
        this.viewport = viewport;
        init();
    }

    // TODO: Add init() function that moves the character to the bottom center of the screen
    public void init(){
        position = new Vector2(viewport.getWorldWidth()/2,HEAD_HEIGHT);
    }

    // TODO: Create a render function that accepts a ShapeRenderer and does the actual drawing
    public void render(ShapeRenderer renderer){
        renderer.setColor(PLAYER_COLOR);

        renderer.set(ShapeRenderer.ShapeType.Filled);
        renderer.circle(position.x, position.y,HEAD_RADIUS,CIRCLE_SEGMENTS);

        // Drawing the torso
        Vector2 torsoTop = new Vector2(position.x,position.y - HEAD_RADIUS);
        Vector2 torsoBottom = new Vector2(position.x,torsoTop.y - 2*HEAD_RADIUS);

        renderer.rectLine(torsoTop,torsoBottom,LIMB_WIDTH);

        // Drawing the arms
        Vector2 armStart = new Vector2(position.x, 0.75f * (torsoTop.y-torsoBottom.y) + torsoBottom.y);

        // Left Arm
        renderer.rectLine(armStart.x,
                armStart.y,
                armStart.x - LIMB_LENGTH * MathUtils.cos(45*MathUtils.degreesToRadians),
                armStart.y - LIMB_LENGTH * MathUtils.sin(45*MathUtils.degreesToRadians),
                LIMB_WIDTH);

        // Right Arm
        renderer.rectLine(armStart.x,
                armStart.y,
                armStart.x + LIMB_LENGTH * MathUtils.cos(45*MathUtils.degreesToRadians),
                armStart.y + LIMB_LENGTH * MathUtils.sin(45*MathUtils.degreesToRadians),
                LIMB_WIDTH);

        // Drawing the legs
        Vector2 legStart = new Vector2(position.x,torsoBottom.y);

        // Left Leg
        renderer.rectLine(legStart.x,
                legStart.y,
                legStart.x - LIMB_LENGTH * MathUtils.cos(45*MathUtils.degreesToRadians),
                legStart.y - LIMB_LENGTH * MathUtils.sin(45*MathUtils.degreesToRadians),
                LIMB_WIDTH);

        // Right Leg
        renderer.rectLine(legStart.x,
                legStart.y,
                legStart.x + LIMB_LENGTH * MathUtils.cos(45*MathUtils.degreesToRadians),
                legStart.y - LIMB_LENGTH * MathUtils.sin(45*MathUtils.degreesToRadians),
                LIMB_WIDTH);


    }
}
