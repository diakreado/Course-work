package com.maltsev.labyrinth.presenter;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.maltsev.labyrinth.model.field.OutOfBoundaryOfTheField;
import com.maltsev.labyrinth.model.field.PointOnTheField;
import com.maltsev.labyrinth.model.Model;
import com.maltsev.labyrinth.view.View;

import java.util.ArrayList;

/**
 * Presenter выступает в качестве посредника между View и Model.
 * Он извлекает данные из модели и передает их во View. Также решает,
 * что нужно делать, когда вы взаимодействуете с View.
 */
public class Presenter {

    private Model model;
    private View view;

    public Presenter(View view) {

        this.view = view;

        model = new Model();

        String newField = FileReader.read("gameField.txt");    //TODO Пока так, но переделаю, чтобы была возможно создавать разные поля

        model.setGameField(newField);
    }


    public boolean isItPossibleWay(int x, int y) {

        try {return model.isItPossibleWay(x,y);}
        catch(OutOfBoundaryOfTheField ex) {return false;}
    }

    public int getSizeOfFieldX() {

        return model.getSizeOfFieldX() - 1;          // т.к. отчёт с нуля
    }

    public int getSizeOfFieldY() {

        return model.getSizeOfFieldY() - 1;          // т.к. отчёт с нуля
    }

    public void drawPassableCells() {

        ArrayList<PointOnTheField> passbleCells = new ArrayList<PointOnTheField>(model.getPassableCells());

        for (int i = 0; i < passbleCells.size(); i++) {

            view.drawBlock(passbleCells.get(i).getX() * view.getBlockWigth(), passbleCells.get(i).getY() * view.getBlockHight());
        }
    }

    public PointOnTheField getPositionOfProtagonist() {

        PointOnTheField positionOfProtagonist = new PointOnTheField(
                model.getLocationOfProtagonist().getX() * view.getBlockWigth(),
                model.getLocationOfProtagonist().getY() * view.getBlockHight());

        return  positionOfProtagonist;
    }

    public void moveProtagonist(Vector3 touchPos, Rectangle protagonistPosition) {

        float protagonistPositionStartX = protagonistPosition.x;
        float protagonistPositionStartY = protagonistPosition.y;

        protagonistPosition.x = ((int)touchPos.x / view.getBlockWigth()) * view.getBlockWigth();
        protagonistPosition.y = ((int)touchPos.y / view.getBlockHight()) * view.getBlockHight();

        if (protagonistPosition.x < 0) protagonistPosition.x = 0;
        if (protagonistPosition.x > getSizeOfFieldX() * view.getBlockWigth()) protagonistPosition.x = getSizeOfFieldX() * view.getBlockWigth();
        if (protagonistPosition.y < 0) protagonistPosition.y = 0;
        if (protagonistPosition.y > getSizeOfFieldY() * view.getBlockHight()) protagonistPosition.y = getSizeOfFieldY() * view.getBlockHight();

        int x = (int)(protagonistPosition.x / view.getBlockWigth());
        int y = (int)(protagonistPosition.y / view.getBlockHight());

        if( !(isItPossibleWay(x, y))) {

            protagonistPosition.x = protagonistPositionStartX;
            protagonistPosition.y = protagonistPositionStartY;
        }

        try {
            model.movesOfProtagonist((int)protagonistPosition.x / view.getBlockWigth(), (int)protagonistPosition.y / view.getBlockHight());
        }
        catch (OutOfBoundaryOfTheField ex)
        {

        }

        if (model.isGameEnded() == true) {

            view.drawFinishingBlock(model.getFinishingPositionOnTheField().getX() * view.getBlockWigth(),
                    model.getFinishingPositionOnTheField().getY() * view.getBlockHight());

            System.out.println("  Игра окончилась!!!");
        }
    }
}
