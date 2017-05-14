package com.maltsev.labyrinth;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;

import com.alibaba.fastjson.JSON;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.badlogic.gdx.backends.android.AndroidFragmentApplication;
import com.badlogic.gdx.files.FileHandle;
import com.maltsev.labyrinth.view.Labyrinth;
import com.maltsev.labyrinth.view.utils.InfoAboutSettings;
import com.maltsev.labyrinth.view.utils.ViewSwitcher;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class GameFragment extends AndroidFragmentApplication {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();

        config.useAccelerometer = false;
        config.useCompass = false;
        config.useImmersiveMode = true;

        int numberOfField = 0;
        numberOfField = ((GetterOfNumberField)getActivity()).getNumberOfField();
        String readString = "";
        try {
            File file = new File(getContext().getExternalFilesDir(""), "gameFields");
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
        } catch (Exception e) {
            e.printStackTrace();
        }
        InfoAboutSettings infoAboutSettings = new InfoAboutSettings(getFieldOnTheNumber(numberOfField,readString), false);
        View view = initializeForView(new Labyrinth((ViewSwitcher)getActivity() ,infoAboutSettings), config);
        setupSurfaceView();
        return view;
    }

    String getFieldOnTheNumber(int numberOfTheField,String fileData) {

        String[] arrayOfField = fileData.split("#\n");

        if (numberOfTheField >= arrayOfField.length || numberOfTheField < 0) {

            numberOfTheField = 0;
        }

        return arrayOfField[numberOfTheField];
    }

    private void setupSurfaceView() {
        if (graphics.getView() instanceof SurfaceView) {
            SurfaceView surfaceView = (SurfaceView) graphics.getView();
            surfaceView.setZOrderOnTop(false);
        }
    }
}