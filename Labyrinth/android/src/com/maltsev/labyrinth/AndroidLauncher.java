package com.maltsev.labyrinth;

import android.os.Bundle;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.CardView;
import android.view.View;
import android.view.WindowManager;

import com.badlogic.gdx.backends.android.AndroidFragmentApplication;


public class AndroidLauncher extends FragmentActivity implements AndroidFragmentApplication.Callbacks {


	@Override
	protected void onCreate (Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_main);
	}

	@Override
	protected void onResume() {
		super.onResume();

		hideSystemAndActionBar();
	}

	private void hideSystemAndActionBar() {

		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

		View decorView = getWindow().getDecorView();
		int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;
		decorView.setSystemUiVisibility(uiOptions);
	}


	private void setupGameFragment() {
		GameFragment fragment = new GameFragment();
		FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
		transaction.replace(R.id.game_fragment_container, fragment);
		transaction.commit();
	}



	public void foo(View v) {

		//setupGameFragment();
	}

    @Override
    public void exit() {

    }
}
