package com.udacity.gamedev.gigagal.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.udacity.gamedev.gigagal.util.Assets;
import com.udacity.gamedev.gigagal.util.Constants;

public class GigaGal {

    public final static String TAG = GigaGal.class.getName();

    // TODO: Add a position
    Vector2 position;


    public GigaGal() {
        // TODO: Initialize GigaGal's position with height = GIGAGAL_EYE_HEIGHT
        this.position = new Vector2(20,Constants.GIGAGAL_EYEHEIGHT);

    }

    public void update(float delta) {

    }

    public void render(SpriteBatch batch) {

        // TODO: Draw GigaGal's standing-right sprite at position - GIGAGAL_EYE_POSITION
        TextureRegion region = Assets.instance.gigaGalAssets.standingRight;
        batch.draw(region.getTexture(),
                position.x - Constants.GIGAGAL_EYEPOSITION.x,
                position.y - Constants.GIGAGAL_EYEPOSITION.y,  // Gigagal's atlasregion is bigger than the sprite. EyePosition.y denotes the distance from the bottom of the atlasregion to the eyes. position.y is the eye height from the feet. Taking the difference gives us the y-offset to draw Gigagal right on the ground.
                0,
                0,
                region.getRegionWidth(),
                region.getRegionHeight(),
                1,
                1,
                0,
                region.getRegionX(),
                region.getRegionY(),
                region.getRegionWidth(),
                region.getRegionHeight(),
                false,
                false);
        Gdx.app.log(TAG,""+ (position.y-Constants.GIGAGAL_EYEPOSITION.y));


    }
}
