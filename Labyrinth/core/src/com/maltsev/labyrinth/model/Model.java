package com.maltsev.labyrinth.model;

import com.badlogic.gdx.math.Vector2;
import com.maltsev.labyrinth.model.GameField.GameField;

import java.util.ArrayList;

/**
 * Model рассматривается в качестве поставщика данных,
 * которые будут отображаться во View.
 */
public class Model {

    Protagonist protagonist;
    public GameField gameField;

    public boolean isItPossibleWay(int x, int y) {

        return gameField.isItPossibleWay(x,y);
    }

    public Model() {

        gameField = new GameField();
        protagonist = new Protagonist();
    }

    public int getSizeOfFieldX() {

        return gameField.getSizeOfFieldX();
    }

    public int getSizeOfFieldY() {

        return gameField.getSizeOfFieldY();
    }

    public ArrayList<Vector2> getPassableCells() {

        return gameField.getPassableCells();
    }
}
