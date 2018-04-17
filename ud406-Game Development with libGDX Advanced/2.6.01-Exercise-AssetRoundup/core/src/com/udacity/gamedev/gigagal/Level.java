package com.udacity.gamedev.gigagal;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.DelayedRemovalArray;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.udacity.gamedev.gigagal.entities.Enemy;
import com.udacity.gamedev.gigagal.entities.GigaGal;
import com.udacity.gamedev.gigagal.entities.Platform;
import com.udacity.gamedev.gigagal.util.Assets;
import com.udacity.gamedev.gigagal.util.Constants;
import com.udacity.gamedev.gigagal.util.Utils;

public class Level {

    public static final String TAG = Level.class.getName();

    private Viewport viewport;

    private GigaGal gigaGal;
    private Array<Platform> platforms;
    private DelayedRemovalArray<Enemy> enemies;

    public Level(Viewport viewport) {
        this.viewport = viewport;
        initializeDebugLevel();
    }

    public void update(float delta) {
        // Update GigaGal
        gigaGal.update(delta, platforms);

        // Update Enemies
        for (int i = 0; i < enemies.size; i++) {
            Enemy enemy = enemies.get(i);
            enemy.update(delta);
        }
    }

    public void render(SpriteBatch batch) {

        for (Platform platform : platforms) {
            platform.render(batch);
        }


        for (Enemy enemy : enemies) {
            enemy.render(batch);
        }

        gigaGal.render(batch);

        // TODO: Test draw the bullet
        Utils.drawTextureRegion(batch,Assets.instance.bulletAssets.region, new Vector2(0,0),Constants.BULLET_CENTER);

        // TODO: Test draw the powerup
        Utils.drawTextureRegion(batch,Assets.instance.powerupAssets.region,new Vector2(20,0),Constants.POWERUP_CENTER);


        // TODO: Test draw the first frame of the explosion
        Utils.drawTextureRegion(batch,Assets.instance.explosionAssets.explosions.getKeyFrame(0),new Vector2(40,0),Constants.EXPLOSION_CENTER);

        // TODO: Test draw the second frame of the explosion
        Utils.drawTextureRegion(batch,Assets.instance.explosionAssets.explosions.getKeyFrame(0.50f),new Vector2(60,0),Constants.EXPLOSION_CENTER);

        // TODO: Test draw the third frame of the explosion
        Utils.drawTextureRegion(batch,Assets.instance.explosionAssets.explosions.getKeyFrame(1.0f),new Vector2(80,0),Constants.EXPLOSION_CENTER);



    }

    private void initializeDebugLevel() {

        gigaGal = new GigaGal(new Vector2(15, 40), this);

        platforms = new Array<Platform>();

        enemies = new DelayedRemovalArray<Enemy>();

        platforms.add(new Platform(15, 100, 30, 20));

        Platform enemyPlatform = new Platform(75, 90, 100, 65);
        enemies.add(new Enemy(enemyPlatform));

        platforms.add(enemyPlatform);
        platforms.add(new Platform(35, 55, 50, 20));
        platforms.add(new Platform(10, 20, 20, 9));

    }

    public Array<Platform> getPlatforms() {
        return platforms;
    }

    public DelayedRemovalArray<Enemy> getEnemies() {
        return enemies;
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
