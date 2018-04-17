package com.udacity.gamedev.gigagal.entities;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.udacity.gamedev.gigagal.util.Assets;
import com.udacity.gamedev.gigagal.util.Constants;
import com.udacity.gamedev.gigagal.util.Enums.Direction;
import com.udacity.gamedev.gigagal.util.Utils;

public class Bullet {

    private final Direction direction;
    public boolean active;
    private Vector2 position;
    public boolean matrixMode;

    public Bullet(Vector2 position, Direction direction) {
        this.position = position;
        this.direction = direction;
        active = true;
        this.matrixMode = false;
    }

    public void update(float delta, boolean m) {
        this.matrixMode = m;
        switch (direction) {
            case LEFT:
                if(matrixMode){
                    position.x -= delta * Constants.BULLET_MOVE_SPEED/20;
                }else {
                    position.x -= delta * Constants.BULLET_MOVE_SPEED;
                }
                break;
            case RIGHT:
                if(matrixMode){
                    position.x += delta * Constants.BULLET_MOVE_SPEED/20;
                }else {
                    position.x += delta * Constants.BULLET_MOVE_SPEED;
                }
                break;
        }

    }

    public void render(SpriteBatch batch) {
        TextureRegion region = Assets.instance.bulletAssets.bullet;
        Utils.drawTextureRegion(batch, region, position, Constants.BULLET_CENTER);
    }
}
