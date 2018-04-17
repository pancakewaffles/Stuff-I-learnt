package com.udacity.gamedev.gigagal.entities;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.udacity.gamedev.gigagal.util.Assets;
import com.udacity.gamedev.gigagal.util.Constants;
import com.udacity.gamedev.gigagal.util.Utils;


public class Powerup {

    // TODO: Add a Vector2 to hold the powerup's position
    Vector2 position;

    public Powerup(Vector2 position) {
        // TODO: Set position
        this.position = position;
    }

    public void render(SpriteBatch batch) {
        // TODO: Complete render function
        Utils.drawTextureRegion(
                batch,
                Assets.instance.powerupAssets.powerup,
                position,
                Constants.POWERUP_CENTER);


    }

}
