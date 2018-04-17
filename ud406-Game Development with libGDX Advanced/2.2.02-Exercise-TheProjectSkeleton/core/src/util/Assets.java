package util;

import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.assets.AssetErrorListener;
import com.badlogic.gdx.utils.Disposable;

/**
 * Created by Chavez Tan on 12/8/2016.
 */

public class Assets implements Disposable, AssetErrorListener {

    public final static String TAG = Assets.class.getName();

    public Assets(){

    }


    @Override
    public void error(AssetDescriptor asset, Throwable throwable) {

    }

    @Override
    public void dispose() {

    }
}
