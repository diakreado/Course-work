package com.maltsev.labyrinth;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.badlogic.gdx.backends.android.AndroidFragmentApplication;
import com.maltsev.labyrinth.view.Labyrinth;

public class GameFragment extends AndroidFragmentApplication {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();

        config.useAccelerometer = false;
        config.useCompass = false;
        config.useImmersiveMode = true;

        // return the GLSurfaceView on which libgdx is drawing game stuff
        return initializeForView(new Labyrinth(), config);
    }
}