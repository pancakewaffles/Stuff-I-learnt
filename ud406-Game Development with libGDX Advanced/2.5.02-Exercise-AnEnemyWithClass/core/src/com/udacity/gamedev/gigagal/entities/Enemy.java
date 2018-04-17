package com.udacity.gamedev.gigagal.entities;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.udacity.gamedev.gigagal.util.Assets;
import com.udacity.gamedev.gigagal.util.Utils;


public class Enemy {

    // TODO: Add a Platform
    Platform platform;

    // TODO: Add a Vector2 position
    Vector2 position;

    public Enemy(Platform platform) {

        // TODO: Initialize the platform member variable
        this.platform = platform;

        // TODO: Position the enemy at the top left of the platform
        position = new Vector2(platform.left,platform.top);

    }

    public void update(float delta) {

    }

    public void render(SpriteBatch batch) {

        // TODO: Complete render()
        Utils.drawTextureRegion(batch, Assets.instance.enemyAssets.enemy,position.x,position.y);


    }
}
