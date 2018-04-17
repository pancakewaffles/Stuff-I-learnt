package com.udacity.gamedev.gigagal;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.udacity.gamedev.gigagal.entities.GigaGal;
import com.udacity.gamedev.gigagal.entities.Platform;
import com.udacity.gamedev.gigagal.util.Assets;
import com.udacity.gamedev.gigagal.util.Constants;

public class Level {

    public static final String TAG = Level.class.getName();

    private Viewport viewport;

    private GigaGal gigaGal;
    private Array<Platform> platforms;


    public Level(Viewport viewport) {
        this.viewport = viewport;
        initializeDebugLevel();
    }

    public void update(float delta) {
        // Update GigaGal
        gigaGal.update(delta, platforms);


    }

    public void render(SpriteBatch batch) {

        for (Platform platform : platforms) {
            platform.render(batch);
        }

        // TODO: Throw an enemy on the screen

        batch.draw(Assets.instance.enemyAssets.enemy.getTexture(),
                Constants.ENEMY_CENTER.x,
                Constants.ENEMY_CENTER.y,
                0,
                0,
                Assets.instance.enemyAssets.enemy.getRegionWidth(),
                Assets.instance.enemyAssets.enemy.getRegionHeight(),
                1,
                1,
                0,
                Assets.instance.enemyAssets.enemy.getRegionX(),
                Assets.instance.enemyAssets.enemy.getRegionY(),
                Assets.instance.enemyAssets.enemy.getRegionWidth(),
                Assets.instance.enemyAssets.enemy.getRegionHeight(),
                false,
                false
                );

        gigaGal.render(batch);


    }

    private void initializeDebugLevel() {

        gigaGal = new GigaGal(new Vector2(15, 40), this);

        platforms = new Array<Platform>();

        platforms.add(new Platform(15, 100, 30, 20));
        platforms.add(new Platform(75, 90, 100, 65));
        platforms.add(new Platform(35, 55, 50, 20));
        platforms.add(new Platform(10, 20, 20, 9));

    }

    public Array<Platform> getPlatforms() {
        return platforms;
    }


    public Viewport getViewport() {
        return viewport;
    }

    public void setViewport(Viewport viewport) {
        this.viewport = viewport;
    }

    public GigaGal getGigaGal() {
        return gigaGal;
    }

    public void setGigaGal(GigaGal gigaGal) {
        this.gigaGal = gigaGal;
    }


}
