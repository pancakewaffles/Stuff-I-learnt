package com.udacity.gamedev.gigagal.overlays;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.udacity.gamedev.gigagal.util.Assets;
import com.udacity.gamedev.gigagal.util.Constants;
import com.udacity.gamedev.gigagal.util.Utils;


public class GameOverOverlay {

    public final Viewport viewport;
    final BitmapFont font;

    public GameOverOverlay() {
        this.viewport = new ExtendViewport(Constants.WORLD_SIZE, Constants.WORLD_SIZE);

        font = new BitmapFont(Gdx.files.internal(Constants.FONT_FILE));
        font.getData().setScale(1);
    }

    public void init() {

    }

    public void render(SpriteBatch batch) {

        viewport.apply();
        batch.setProjectionMatrix(viewport.getCamera().combined);
        batch.begin();

        // TODO: Draw a game over message
        // Feel free to get more creative with this screen. Perhaps you could cover the screen in enemy robots?
        font.draw(batch,
                Constants.GAME_OVER_MESSAGE,
                0,
                viewport.getWorldHeight()/2,
                viewport.getWorldWidth(),
                Align.center,
                false);

        for(int i = 0; i<Constants.GAMEOVER_ENEMY_COUNT;i++){
            Utils.drawTextureRegion(batch,
                    Assets.instance.enemyAssets.enemy,
                    new Vector2(MathUtils.random(0,viewport.getWorldWidth()) , MathUtils.random(0,viewport.getWorldHeight())),
                    Constants.ENEMY_CENTER);
        }


        batch.end();

    }
}
