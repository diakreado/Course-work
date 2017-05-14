package com.maltsev.labyrinth;


import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class MainFragment extends Fragment implements  View.OnClickListener, View.OnTouchListener {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private RelativeLayout mRelativeLayout;

    private int counter = 0;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mRelativeLayout = (RelativeLayout) inflater.inflate(R.layout.fragment_main, container, false);

        mRecyclerView = (RecyclerView) mRelativeLayout.findViewById(R.id.my_recycler_view);
        mRecyclerView.setHasFixedSize(true);

        mLayoutManager = new LinearLayoutManager(this.getContext(), LinearLayoutManager.HORIZONTAL, false);
        mRecyclerView.setLayoutManager(mLayoutManager);

        JSONArray newLevelInfo = new JSONArray();

        String readString = "";
        try {
            File file = new File(getContext().getExternalFilesDir(""), "labyrinth");
            FileInputStream fIn = new FileInputStream(file);
            InputStreamReader isr = new InputStreamReader(fIn);
            BufferedReader bufferedReader = new BufferedReader(isr);
            String polka = bufferedReader.readLine();
            while (polka!=null) {
                readString+=polka + '\n';
                polka = bufferedReader.readLine();
            }
            isr.close();
            bufferedReader.close();

            newLevelInfo = JSON.parseArray(readString);
        } catch (Exception e) {
            e.printStackTrace();
        }

//        ArrayList<LevelInfo> levelInfo = new ArrayList<>();
//        levelInfo.add(new LevelInfo("Уровень 1","карткое описание 1","полное описание 1"));
//        levelInfo.add(new LevelInfo("Уровень 2","карткое описание 2","полное описание 2"));
//        levelInfo.add(new LevelInfo("Уровень 3","карткое описание 3","полное описание 3"));
//        levelInfo.add(new LevelInfo("Уровень 4","карткое описание 4","полное описание 4"));
//        levelInfo.add(new LevelInfo("Уровень 5","карткое описание 5","полное описание 5"));
//        levelInfo.add(new LevelInfo("Уровень 6","карткое описание 6","полное описание 6"));
//        levelInfo.add(new LevelInfo("Уровень 7","карткое описание 7","полное описание 7"));
//        levelInfo.add(new LevelInfo("Уровень 8","карткое описание 8","полное описание 8"));
//        levelInfo.add(new LevelInfo("Уровень 9","карткое описание 9","полное описание 9"));
//        levelInfo.add(new LevelInfo("Уровень 10","карткое описание 10","полное описание 10"));
//        levelInfo.add(new LevelInfo("Уровень 11","карткое описание 11","полное описание 11"));
//        levelInfo.add(new LevelInfo("Уровень 12","карткое описание 12","полное описание 12"));
//        levelInfo.add(new LevelInfo("Уровень 13","карткое описание 13","полное описание 13"));
//
//        String jsonString = JSON.toJSONString(levelInfo);
//
//        JSONArray newLevelInfo = new JSONArray();
//
//        try {
//            File file = new File(getContext().getExternalFilesDir(""), "labyrinth");
//
//            FileOutputStream fOut = new FileOutputStream(file);
//            OutputStreamWriter osw = new OutputStreamWriter(fOut);
//            osw.write(jsonString);
//            osw.flush();
//            osw.close();
//
//            FileInputStream fIn = new FileInputStream(file);
//            InputStreamReader isr = new InputStreamReader(fIn);
//            char[] inputBuffer = new char[jsonString.length()];
//            isr.read(inputBuffer);
//            isr.close();
//            String readString = new String(inputBuffer);
//
//            newLevelInfo = JSON.parseArray(readString);
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

        mAdapter = new RecyclerAdapter(newLevelInfo, this);
        mRecyclerView.setAdapter(mAdapter);

        return mRelativeLayout;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_card_main_1_action1:
                int num = (int)view.getTag();
                ((StartGame)getActivity()).startGame(num);
                break;
            case R.id.btn_card_main_1_action2:
                ((Button)view).setText(Integer.toString(counter));
                counter++;
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
