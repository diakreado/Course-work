package com.maltsev.labyrinth.presenter;

import com.maltsev.labyrinth.model.GameField.PointOnTheField;
import com.maltsev.labyrinth.model.Model;
import com.maltsev.labyrinth.view.View;

import java.util.ArrayList;

/**
 * Presenter выступает в качестве посредника между View и Model.
 * Он извлекает данные из модели и передает их во View. Также решает,
 * что нужно делать, когда вы взаимодействуете с View.
 */
public class Presenter {

    private final Model model;
    private final View view;

    public Presenter(View view) {

        model = new Model();
        this.view = view;
    }


    public boolean isItPossibleWay(int x, int y) {

        return model.isItPossibleWay(x,y);
    }

    public int getSizeX() {

        return model.getSizeOfFieldX() - 1;
    }

    public int getSizeY() {

        return model.getSizeOfFieldY() - 1;
    }

    public void drawPassableCells() {

        ArrayList<PointOnTheField> passbleCells = new ArrayList<PointOnTheField>(model.getPassableCells());

        for (int i = 0; i < passbleCells.size(); i++) {

            view.drawBlock(passbleCells.get(i).getX()*70, passbleCells.get(i).getY()*70);
        }
    }
}
