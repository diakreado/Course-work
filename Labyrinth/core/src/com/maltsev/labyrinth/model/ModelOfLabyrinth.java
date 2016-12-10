package com.maltsev.labyrinth.model;

import com.maltsev.labyrinth.model.analyzer.WayAnalyzer;
import com.maltsev.labyrinth.model.analyzer.gameover.GameOverAnalyzer;
import com.maltsev.labyrinth.model.analyzer.gameover.GameOverListener;
import com.maltsev.labyrinth.model.field.GameField;
import com.maltsev.labyrinth.model.field.OutOfBoundaryOfTheField;
import com.maltsev.labyrinth.model.field.PointOnTheField;
import com.maltsev.labyrinth.model.protagonist.Protagonist;

import java.util.ArrayDeque;
import java.util.List;

public class ModelOfLabyrinth implements Model {

    public ModelOfLabyrinth() {}

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
        analyzerOfGameOver = new GameOverAnalyzer(this);
        analyzerOfWay = new WayAnalyzer(this);
    }

    @Override
    @org.jetbrains.annotations.Nullable
    public ArrayDeque<PointOnTheField> movesOfProtagonist(final int x, final  int y) {

        ArrayDeque<PointOnTheField> way = analyzerOfWay.getWay(getPositionOfProtagonist(), new PointOnTheField(x,y));

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

        return gameField.getSizeX();
    }

    @Override
    public int getSizeOfFieldY() {

        return gameField.getSizeY();
    }

    @Override
    public List<PointOnTheField> getPassableCells() {

        return gameField.getPassableCells();
    }

    @Override
    public PointOnTheField getPositionOfProtagonist() {

        return protagonist.getLocationOfProtagonist();
    }

    @Override
    public PointOnTheField getStartingPositionOfField() {

        return gameField.getStartingPoint();
    }

    @Override
    public PointOnTheField getFinishingPositionOfField() {

        return gameField.getFinishingPoint();
    }

    @Override
    public void setValueOfRangeOfStep(int valueOfRangeOfStep) {

        analyzerOfWay.setDefaultRange(valueOfRangeOfStep);
    }
}