package com.maltsev.labyrinth.model;

import com.maltsev.labyrinth.model.analyzer.WayAnalyzer;
import com.maltsev.labyrinth.model.field.GameField;
import com.maltsev.labyrinth.model.field.OutOfBoundaryOfTheField;
import com.maltsev.labyrinth.model.field.PointOnTheField;
import com.maltsev.labyrinth.model.protagonist.Protagonist;

import java.util.ArrayList;


/**
 *  Model реализует шаблон Singleton
 */
public class Model implements ModelAPI {

    private static Model ourInstance = new Model();

    /**
     * @return единственный экземпляр класса
     */
    public static Model getInstance() {

        return ourInstance;
    }

    private Model() {}

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


    @Override
    public void setGameField(String newField) {

        isGameEnded = false;
        this.gameField = new GameField(newField);
        protagonist = new Protagonist(gameField.getStartingPoint());
    }

    @Override
    public ArrayList<PointOnTheField> movesOfProtagonist(int x, int y) {

        ArrayList<PointOnTheField> way = WayAnalyzer.getWay(getLocationOfProtagonist(), new PointOnTheField(x,y));

        if (way.isEmpty()) return way;

        protagonist.movesOfProtagonist(x,y);

        isGameEnded = protagonist.getLocationOfProtagonist().equals(gameField.getFinishingPoint());

        return way;
    }

    @Override
    public boolean isItPassableCells(int x, int y) {

        try {

            return gameField.isItPassableCell(x,y);

        }catch (OutOfBoundaryOfTheField ex) {

            return false;
        }
    }

    @Override
    public boolean isItPassableCells(PointOnTheField point) {

        try {

            return gameField.isItPassableCell(point);

        }catch (OutOfBoundaryOfTheField ex) {

            return false;
        }
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