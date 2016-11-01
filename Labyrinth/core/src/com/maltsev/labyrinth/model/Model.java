package com.maltsev.labyrinth.model;

import com.maltsev.labyrinth.model.field.GameField;
import com.maltsev.labyrinth.model.field.OutOfBoundaryOfTheField;
import com.maltsev.labyrinth.model.field.PointOnTheField;
import com.maltsev.labyrinth.model.protagonist.Protagonist;

import java.util.ArrayList;


public class Model implements ModelAPI {

    /**
     * Главный герой
     */
    private Protagonist protagonist;

    /**
     * Игровое поле
     */
    private GameField gameField;

    /**
     *  Закончена ли игра
     */
    private boolean isGameEnded;


    /**
     * Временно пустой конструктор
     */
    public Model() {}

    @Override
    public void setGameField(String newField) {

        isGameEnded = false;
        this.gameField = new GameField(newField);
        protagonist = new Protagonist(gameField.getStartingPoint());
    }

    @Override
    public void movesOfProtagonist(int x, int y) throws OutOfBoundaryOfTheField  {

        if (x < 0 || x >= getSizeOfFieldX())
            throw new OutOfBoundaryOfTheField("Illegal request of moving protagonist", "x", x, getSizeOfFieldX() - 1);
        if (y < 0 || y >= getSizeOfFieldY())
            throw new OutOfBoundaryOfTheField("Illegal request of moving protagonist", "y", y, getSizeOfFieldY() - 1);


        protagonist.movesOfProtagonist(x,y);

        if (protagonist.getLocationOfProtagonist().equals(gameField.getFinishingPoint())) isGameEnded = true;
        else isGameEnded = false;
    }

    @Override
    public boolean isItPossibleWay(int x, int y) throws OutOfBoundaryOfTheField {

        return gameField.isItPossibleWay(x,y);
    }

    @Override
    public boolean isGameEnded() {

        return isGameEnded;
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
    public PointOnTheField getLocationOfProtagonist() {

        return protagonist.getLocationOfProtagonist();
    }

    @Override
    public PointOnTheField getStartingPositionOnTheField() {

        return gameField.getStartingPoint();
    }

    @Override
    public PointOnTheField getFinishingPositionOnTheField() {

        return gameField.getFinishingPoint();
    }
}