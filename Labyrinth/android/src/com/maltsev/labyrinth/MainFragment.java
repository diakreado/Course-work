package com.maltsev.labyrinth;


import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.widget.Button;
import android.widget.RelativeLayout;

public class MainFragment extends Fragment implements  View.OnClickListener, View.OnTouchListener {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private RelativeLayout mRelativeLayout;

    CardView card_main_1_1;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        mRelativeLayout = (RelativeLayout) inflater.inflate(R.layout.fragment_main, container, false);

        mRecyclerView = (RecyclerView) mRelativeLayout.findViewById(R.id.my_recycler_view);
        mRecyclerView.setHasFixedSize(true);

        mLayoutManager = new LinearLayoutManager(this.getContext(), LinearLayoutManager.HORIZONTAL, false);
        mRecyclerView.setLayoutManager(mLayoutManager);

        String polka[] = {"Петушёк","Полка","Летающий корабль","Окно","Лабиринт"};
        mAdapter = new RecyclerAdapter(polka, this);
        mRecyclerView.setAdapter(mAdapter);

//         Inflate the layout for this fragment
        return mRelativeLayout;
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {

            case R.id.btn_card_main1_action1:
                //System.out.println(1);
                break;

            case R.id.btn_card_main1_action2:
                //System.out.println(12);
                break;

            case R.id.card_main_1_1:
                //System.out.println(123);
                break;
        }
    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {

        switch (motionEvent.getAction()) {
            case MotionEvent.ACTION_DOWN:
                ObjectAnimator upAnim = ObjectAnimator.ofFloat(view, "translationZ", 16);
                upAnim.setDuration(150);
                upAnim.setInterpolator(new DecelerateInterpolator());
                upAnim.start();
                break;

            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:
                ObjectAnimator downAnim = ObjectAnimator.ofFloat(view, "translationZ", 0);
                downAnim.setDuration(150);
                downAnim.setInterpolator(new AccelerateInterpolator());
                downAnim.start();
                break;
        }
        return false;
    }

}
