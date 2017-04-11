package com.maltsev.labyrinth;

import android.os.Bundle;

import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewDebug;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.badlogic.gdx.scenes.scene2d.ui.List;
import com.bumptech.glide.Glide;
import com.badlogic.gdx.backends.android.AndroidFragmentApplication;

import java.util.ArrayList;


public class AndroidLauncher extends FragmentActivity implements AndroidFragmentApplication.Callbacks{

    private CardView card_main_1_1;
	private ImageView img_main_card_1;

	private RecyclerView mRecyclerView;
	private RecyclerView.Adapter mAdapter;
	private RecyclerView.LayoutManager mLayoutManager;

	@Override
	protected void onCreate (Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.fragment_main_1);
		hideSystemAndActionBar();
		img_main_card_1 = (ImageView) findViewById(R.id.img_main_card_1);
		card_main_1_1 = (CardView) findViewById(R.id.card_main_1_1);
//		Glide.with(getApplicationContext()).load(R.drawable.material_design_2).fitCenter().into(img_main_card_1);

		mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
		mRecyclerView.setHasFixedSize(true);

		mLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
//		mLayoutManager.
		mRecyclerView.setLayoutManager(mLayoutManager);

		// specify an adapter (see also next example)
		String polka[] = {"123","321","123121","123","123","321","123121","123","123","321","123121","123","123","321","123121","123","123","321","123121","123"};
		mAdapter = new MyAdapter(polka);
		mRecyclerView.setAdapter(mAdapter);
	}

	private void createCards() {



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
