package com.maltsev.labyrinth.presenter;



import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.maltsev.labyrinth.model.GameField.CellOfField;
import com.maltsev.labyrinth.model.GameField.GameField;
import com.maltsev.labyrinth.model.Model;

import java.util.ArrayList;

/**
 * Presenter выступает в качестве посредника между View и Model.
 * Он извлекает данные из модели и передает их во View. Также решает,
 * что нужно делать, когда вы взаимодействуете с View.
 */
public class Presenter {

    private Model model;

    ArrayList<ArrayList< CellOfField>> matrixOfCell;

    public Presenter() {

        model = new Model();

        matrixOfCell = new ArrayList<ArrayList<CellOfField>>(model.gameField.matrixOfCell);
    }

    public boolean canIWalkHere(int x, int y) {

        return model.gameField.isItPossibleWay(x,y);
    }

    public int getSize() {

        return 4;
    }


    public Rectangle pressOnTheScreen(Vector3 touchPos){

        Rectangle heroPosition = new Rectangle();

        heroPosition.x = touchPos.x - 35;
        heroPosition.y = touchPos.y - 35;

        return heroPosition;
    }

}
