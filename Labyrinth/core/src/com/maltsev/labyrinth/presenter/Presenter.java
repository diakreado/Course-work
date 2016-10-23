package com.maltsev.labyrinth.presenter;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.maltsev.labyrinth.model.GameField.PointOnTheField;
import com.maltsev.labyrinth.model.Model;
import com.maltsev.labyrinth.view.View;

import java.util.ArrayList;

/**
 * Presenter выступает в качестве посредника между View и Model.
 * Он извлекает данные из модели и передает их во View. Также решает,
 * что нужно делать, когда вы взаимодействуете с View.
 */
public class Presenter {                                        //TODO Обдумать, что к чему отностися, и разделить Presenter и View

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

        return model.getSizeOfFieldX() - 1;          // т.к. отсёт с нуля
    }

    public int getSizeY() {

        return model.getSizeOfFieldY() - 1;          // т.к. отсёт с нуля
    }

    public void drawPassableCells(Texture block) {

        ArrayList<PointOnTheField> passbleCells = new ArrayList<PointOnTheField>(model.getPassableCells());

        for (int i = 0; i < passbleCells.size(); i++) {

            view.drawBlock(passbleCells.get(i).getX() * block.getWidth(), passbleCells.get(i).getY() * block.getHeight());
        }
    }

    public void moveProtagonist(Vector3 touchPos, Rectangle heroPosition, Texture block) {

        float heroPositionStartX = heroPosition.x;
        float heroPositionStartY = heroPosition.y;

        heroPosition.x = ((int)touchPos.x / block.getWidth())*block.getWidth();
        heroPosition.y = ((int)touchPos.y / block.getHeight())*block.getHeight();

        if (heroPosition.x < 0) heroPosition.x = 0;
        if (heroPosition.x > getSizeX() * block.getWidth()) heroPosition.x = getSizeX() * block.getWidth();
        if (heroPosition.y < 0) heroPosition.y = 0;
        if (heroPosition.y > getSizeY() * block.getHeight()) heroPosition.y = getSizeY() * block.getHeight();

        int x = (int)(heroPosition.x / block.getWidth());
        int y = (int)(heroPosition.y / block.getWidth());

        if( !(isItPossibleWay(x, y))) {

            heroPosition.x = heroPositionStartX;
            heroPosition.y = heroPositionStartY;
        }

        model.moveProtagonist((int)heroPosition.x / block.getWidth(), (int)heroPosition.y / block.getHeight());

        if (model.isGameEnded() == true) {

            System.out.println("  Игра окончилась!!!");
        }
    }
}
