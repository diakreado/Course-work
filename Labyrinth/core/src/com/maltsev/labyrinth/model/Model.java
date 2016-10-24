package com.maltsev.labyrinth.model;

import com.maltsev.labyrinth.model.field.GameField;
import com.maltsev.labyrinth.model.field.OutOfBoundaryOfTheField;
import com.maltsev.labyrinth.model.field.PointOnTheField;
import com.maltsev.labyrinth.model.protagonist.ObjectAlreadyExists;
import com.maltsev.labyrinth.model.protagonist.Protagonist;

import java.util.ArrayList;


public class Model implements ModelAPI {

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

        try {

            setProtagonist();
        }
        catch (ObjectAlreadyExists ex) {

            System.out.println(ex.getMessage());
        }

        protagonist = Protagonist.getInstance();

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

    public void setProtagonist() throws ObjectAlreadyExists {

        Protagonist.setInstance(startPoint);
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

    public void moveProtagonist(int x, int y){

        protagonist.moveProtagonist(x,y);

        if (protagonist.getLocationOfProtagonist().equals(finalPoint)) isGameEnded = true;
        else isGameEnded = false;
    }
}