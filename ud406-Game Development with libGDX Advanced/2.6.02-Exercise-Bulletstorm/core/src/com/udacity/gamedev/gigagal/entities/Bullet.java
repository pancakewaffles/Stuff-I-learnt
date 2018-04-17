package com.udacity.gamedev.gigagal.entities;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.udacity.gamedev.gigagal.util.Assets;
import com.udacity.gamedev.gigagal.util.Constants;
import com.udacity.gamedev.gigagal.util.Enums;
import com.udacity.gamedev.gigagal.util.Enums.Direction;
import com.udacity.gamedev.gigagal.util.Utils;

public class Bullet {

    // TODO: Add a Direction
    Direction direction;

    // TODO: Add a position
    Vector2 position;

    public Enums.BulletState bulletState;


    public Bullet(Vector2 position, Direction direction, Enums.BulletState bulletState) {

        // TODO: Set position and direction
        this.position = position;
        this.direction = direction;
        this.bulletState = bulletState;



    }

    public void update(float delta,Enums.BulletState b) {
        this.bulletState = b;
        // TODO: Move the bullet the correct amount in the correction direction
        if(direction == Direction.RIGHT){
            if(bulletState != Enums.BulletState.MATRIX){
                position.x += delta * Constants.BULLET_MOVE_SPEED;
            }else{
                position.x += delta * Constants.BULLET_MOVE_SPEED/20;
            }

        }else{
            if(bulletState != Enums.BulletState.MATRIX) {
                position.x -= delta * Constants.BULLET_MOVE_SPEED;
            }else{
                position.x -= delta * Constants.BULLET_MOVE_SPEED/20;
            }
        }

    }

    public void render(SpriteBatch batch) {
        // TODO: Complete render function
        Utils.drawTextureRegion(batch, Assets.instance.bulletAssets.bullet,position,Constants.BULLET_CENTER);


    }
}
