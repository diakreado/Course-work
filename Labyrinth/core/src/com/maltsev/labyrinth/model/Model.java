package com.maltsev.labyrinth.model;

import com.maltsev.labyrinth.model.GameField.GameField;
import com.maltsev.labyrinth.model.GameField.PointOnTheField;

import java.util.ArrayList;


public class Model implements ModelAPI {

    private Protagonist protagonist;  // Объект обозначающий игрок
    private GameField gameField;     // Объект обозначающий игровое поле


    PointOnTheField startPoint;     // Точка из которой начинается игра
    PointOnTheField finalPoint;    //  Точка в которой игра заканчивается


    @Override
    public boolean isItPossibleWay(int x, int y) {

        return gameField.isItPossibleWay(x,y);
    }

    public Model() {

        gameField = new GameField();
        protagonist = new Protagonist(new PointOnTheField(0,0));
    }


    @Override
    public int getSizeOfFieldX() {

        return gameField.getSizeOfFieldX();
    }

    @Override
    public int getSizeOfFieldY() {

        return gameField.getSizeOfFieldY();
    }

    @Override
    public ArrayList<PointOnTheField> getPassableCells() {

        return gameField.getPassableCells();
    }

    @Override
    public void setGameField(int[][] gameField, PointOnTheField startPoint, PointOnTheField finalPoint) {

        throw new UnsupportedOperationException();
    }
}
