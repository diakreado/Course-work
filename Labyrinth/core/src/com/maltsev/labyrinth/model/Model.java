package com.maltsev.labyrinth.model;

import com.maltsev.labyrinth.model.GameField.GameField;
import com.maltsev.labyrinth.model.GameField.OutOfBoundaryOfTheField;
import com.maltsev.labyrinth.model.GameField.PointOnTheField;

import java.util.ArrayList;


public class Model implements ModelAPI {                                            //TODO реализовать сохранение и запись в файл

    //TODO обдумать генерацию лабиринтов

    private Protagonist protagonist;  // Объект обозначающий игрок
    private GameField gameField;     // Объект обозначающий игровое поле


    PointOnTheField startPoint = new PointOnTheField(0,0);     // Точка из которой начинается игра         //TODO решить куда засунуть эти поля
    PointOnTheField finalPoint = new PointOnTheField(0,5);    //  Точка в которой игра заканчивается

    private boolean isGameEnded;

    @Override
    public boolean isItPossibleWay(int x, int y) {
        try {

            return gameField.isItPossibleWay(x,y);
        }
        catch (OutOfBoundaryOfTheField ex) {

            return false;
        }
    }

    public boolean isGameEnded() {

        return isGameEnded;
    }

    public Model() {

        isGameEnded = false;
        protagonist = new Protagonist(new PointOnTheField(0,0));



        int newField[][]= {{0,1,1,0,0,0},
                {0,0,1,0,1,1},
                {1,0,1,0,0,0},
                {0,0,1,1,1,0},
                {0,1,1,1,0,0},
                {0,1,0,0,0,1},
                {0,1,0,1,1,1},
                {0,1,0,0,0,0},
                {0,1,1,1,1,0},
                {0,0,0,0,0,0}};

        gameField = new GameField(newField);
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


    }

    public void moveProtagonist(int x, int y) {

        protagonist.locationOfProtagonist = new PointOnTheField(x,y);

        if (protagonist.locationOfProtagonist.equals(finalPoint)) isGameEnded = true;
        else isGameEnded = false;
    }
}