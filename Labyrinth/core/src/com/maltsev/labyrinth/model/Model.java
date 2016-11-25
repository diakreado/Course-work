package com.maltsev.labyrinth.model;

import com.maltsev.labyrinth.model.analyzer.WayAnalyzer;
import com.maltsev.labyrinth.model.analyzer.gameover.GameOverAnalyzer;
import com.maltsev.labyrinth.model.analyzer.gameover.GameOverListener;
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
     *  Анализатор конца игры
     */
    private GameOverAnalyzer analyzerOfGameOver;

    /**
     * Анализатор пути
     */
    private WayAnalyzer analyzerOfWay;


    @Override
    public void setGameField(final String newField) {

        this.gameField = new GameField(newField);
        protagonist = new Protagonist(gameField.getStartingPoint());
        analyzerOfGameOver = new GameOverAnalyzer();
        analyzerOfWay = new WayAnalyzer();
    }

    @Override
    @org.jetbrains.annotations.Nullable
    public ArrayList<PointOnTheField> movesOfProtagonist(final int x, final  int y) {

        ArrayList<PointOnTheField> way = analyzerOfWay.getWay(getLocationOfProtagonist(), new PointOnTheField(x,y));

        if (way == null) return null;

        protagonist.movesOfProtagonist(x,y);

        analyzerOfGameOver.messageAboutChangingSystem();

        return way;
    }

    @Override
    public boolean isItPassableCells(final int x, final int y) {

        try {

            return gameField.isItPassableCell(x,y);

        }catch (OutOfBoundaryOfTheField ex) {

            return false;
        }
    }

    @Override
    public boolean isItPassableCells(final PointOnTheField point) {

        try {

            return gameField.isItPassableCell(point);

        }catch (OutOfBoundaryOfTheField ex) {

            return false;
        }
    }

    @Override
    public void addListenerOfGameOver(GameOverListener listener) {

        analyzerOfGameOver.addListener(listener);
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