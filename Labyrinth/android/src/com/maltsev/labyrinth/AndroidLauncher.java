package com.maltsev.labyrinth;

import android.os.Bundle;

import android.support.v4.app.FragmentActivity;
import com.badlogic.gdx.backends.android.AndroidFragmentApplication;

public class AndroidLauncher extends FragmentActivity implements AndroidFragmentApplication.Callbacks{
	@Override
	protected void onCreate (Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
//		AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
//
//		config.useAccelerometer = false;
//		config.useCompass = false;
//		config.useImmersiveMode = true;

		//View gameView = initializeForView(new Labyrinth(), config);

		setContentView(R.layout.layout);

        // Create libgdx fragment
        GameFragment libgdxFragment = new GameFragment();

        // Put it inside the framelayout (which is defined in the layout.xml file).
        getSupportFragmentManager().beginTransaction().
                add(R.id.content_framelayout, libgdxFragment).
                commit();

		//initialize(new Labyrinth(), config);
	}

    @Override
    public void exit() {

    }
}
