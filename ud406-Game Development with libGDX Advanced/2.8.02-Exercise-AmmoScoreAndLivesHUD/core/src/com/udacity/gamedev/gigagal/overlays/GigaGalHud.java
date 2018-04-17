package com.udacity.gamedev.gigagal.overlays;


import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.udacity.gamedev.gigagal.util.Assets;
import com.udacity.gamedev.gigagal.util.Constants;
import com.udacity.gamedev.gigagal.util.Utils;

public class GigaGalHud {

    public final Viewport viewport;
    final BitmapFont font;

    public GigaGalHud() {
        this.viewport = new ExtendViewport(Constants.HUD_VIEWPORT_SIZE, Constants.HUD_VIEWPORT_SIZE);
        font = new BitmapFont();
        font.getData().setScale(1);
    }

    public void render(SpriteBatch batch, int lives, int ammo, int score) {
        viewport.apply();
        batch.setProjectionMatrix(viewport.getCamera().combined);
        batch.begin();

        // TODO: Draw GigaGal's score and ammo count in the top left of the viewport
        font.draw(batch,
                Constants.HUD_SCORE_LABEL + score,
                Constants.HUD_MARGIN,
                Constants.HUD_VIEWPORT_SIZE-Constants.HUD_MARGIN);

        font.draw(batch,
                Constants.HUD_AMMO_LABEL + ammo,
                Constants.HUD_MARGIN,
                Constants.HUD_VIEWPORT_SIZE-2*Constants.HUD_MARGIN );

        // TODO: Draw a tiny GigaGal in the top right for each life left
        for(int i=1;i<=lives;i++){
            Utils.drawTextureRegion(batch,
                    Assets.instance.gigaGalAssets.standingRight,
                    new Vector2(viewport.getWorldWidth() - i*(Constants.HUD_MARGIN/2 + Assets.instance.gigaGalAssets.standingRight.getRegionWidth()) ,  viewport.getWorldHeight() - Constants.HUD_MARGIN - Assets.instance.gigaGalAssets.standingRight.getRegionHeight())
                    );
        }

        batch.end();
    }
}
