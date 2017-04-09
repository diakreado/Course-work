package com.maltsev.labyrinth;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.SurfaceView;
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

        View view = initializeForView(new Labyrinth(), config);

        setupSurfaceView();

        return view;
    }

    private void setupSurfaceView() {
        if (graphics.getView() instanceof SurfaceView) {
            SurfaceView surfaceView = (SurfaceView) graphics.getView();
            surfaceView.setZOrderOnTop(false);
        }
    }
}