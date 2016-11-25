package com.maltsev.labyrinth.presenter;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.maltsev.labyrinth.model.field.OutOfBoundaryOfTheField;
import com.maltsev.labyrinth.model.field.PointOnTheField;
import com.maltsev.labyrinth.model.Model;
import com.maltsev.labyrinth.view.GameScreen;

import java.util.ArrayList;

/**
 * Presenter выступает в качестве посредника между View и Model.
 * Он извлекает данные из модели и передает их во View. Также решает,
 * что нужно делать, когда вы взаимодействуете с View.
 */
public class Presenter {

    private Model model;
    private GameScreen view;


    public Presenter(GameScreen view) {

        this.view = view;

        model = Model.getInstance();

        String newField = FileReader.read("gameField.txt");    //TODO Пока так, но переделаю, чтобы была возможно создавать разные поля

        model.setGameField(newField);
    }


    public boolean isItPossibleWay(int x, int y) {

        return model.isItPassableCells(x,y);
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

        Rectangle newProtagonistPosition = new Rectangle();

        newProtagonistPosition.x = ((int)touchPos.x / view.getBlockWigth()) * view.getBlockWigth();
        newProtagonistPosition.y = ((int)touchPos.y / view.getBlockHight()) * view.getBlockHight();

        if (newProtagonistPosition.x < 0) newProtagonistPosition.x = 0;
        if (newProtagonistPosition.x > getSizeOfFieldX() * view.getBlockWigth()) newProtagonistPosition.x = getSizeOfFieldX() * view.getBlockWigth();
        if (newProtagonistPosition.y < 0) newProtagonistPosition.y = 0;
        if (newProtagonistPosition.y > getSizeOfFieldY() * view.getBlockHight()) newProtagonistPosition.y = getSizeOfFieldY() * view.getBlockHight();

        int x = (int)(newProtagonistPosition.x / view.getBlockWigth());
        int y = (int)(newProtagonistPosition.y / view.getBlockHight());

        if( !(isItPossibleWay(x, y))) {

            newProtagonistPosition.x = protagonistPositionStartX;
            newProtagonistPosition.y = protagonistPositionStartY;
        }

        model.movesOfProtagonist(x,y);

        if (model.isGameEnded()) {

            view.drawFinishingBlock(model.getFinishingPositionOnTheField().getX() * view.getBlockWigth(),
                    model.getFinishingPositionOnTheField().getY() * view.getBlockHight());

            System.out.println("  Игра окончилась!!!");
        }
    }
}