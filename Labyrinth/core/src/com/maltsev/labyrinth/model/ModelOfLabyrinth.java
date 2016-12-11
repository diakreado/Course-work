package com.maltsev.labyrinth.model;

import com.maltsev.labyrinth.model.analyzer.WayAnalyzer;
import com.maltsev.labyrinth.model.analyzer.event.gameover.GameOverAnalyzer;
import com.maltsev.labyrinth.model.analyzer.event.gameover.GameOverListener;
import com.maltsev.labyrinth.model.analyzer.event.keysanddoors.doors.OpenDoorAnalyzer;
import com.maltsev.labyrinth.model.analyzer.event.keysanddoors.keys.FoundKeyAnalyzer;
import com.maltsev.labyrinth.model.field.FieldIsEmptyException;
import com.maltsev.labyrinth.model.field.GameField;
import com.maltsev.labyrinth.model.field.OutOfBoundaryOfTheFieldException;
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
     *  Наблюдатель за концом игры
     */
    private GameOverAnalyzer analyzerOfGameOver;

    /**
     * Наблюдатель за открытием дверей
     */
    private OpenDoorAnalyzer analyzerOfOpenDoor;

    /**
     * Наблюдатель за нахождением ключей
     */
    private FoundKeyAnalyzer analyzerOfFoundKey;

    /**
     * Анализатор пути
     */
    private WayAnalyzer analyzerOfWay;

    private List<PointOnTheField> doors;


    @Override
    public void setGameField(final String newField) throws FieldIsEmptyException {

        this.gameField = new GameField(newField);
        protagonist = new Protagonist(gameField.getStartingPoint());
        analyzerOfGameOver = new GameOverAnalyzer(this);
        analyzerOfFoundKey = new FoundKeyAnalyzer(this);
        analyzerOfOpenDoor = new OpenDoorAnalyzer(this);
        analyzerOfWay = new WayAnalyzer(this);
        doors = gameField.getDoors();

        analyzerOfFoundKey.addListener(protagonist);
    }

    private void checkDoors(PointOnTheField point) {

        if (doors.contains(point) && protagonist.getNumberOfKeys() > 0) {

            protagonist.useKey();
            gameField.openDoor(point.getX(), point.getY());
            analyzerOfOpenDoor.doorIsOpen();
        }
    }

    @Override
    @org.jetbrains.annotations.Nullable
    public ArrayDeque<PointOnTheField> movesOfProtagonist(final int x, final  int y) {

        checkDoors(new PointOnTheField(x,y));

        ArrayDeque<PointOnTheField> way = analyzerOfWay.getWay(getPositionOfProtagonist(), new PointOnTheField(x,y));

        if (way == null) return null;

        protagonist.movesOfProtagonist(x,y);

        analyzerOfGameOver.messageAboutChangingSystem();
        analyzerOfFoundKey.messageAboutChangingSystem();

        return way;
    }

    @Override
    public boolean isItPassableCells(final int x, final int y) {

        try {

            return gameField.isItPassableCell(x,y);

        }catch (OutOfBoundaryOfTheFieldException ex) {

            return false;
        }
    }

    @Override
    public boolean isItPassableCells(final PointOnTheField point) {

        try {

            return gameField.isItPassableCell(point);

        }catch (OutOfBoundaryOfTheFieldException ex) {

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
    public PointOnTheField getStartPosition() {

        return gameField.getStartingPoint();
    }

    @Override
    public PointOnTheField getFinishPosition() {

        return gameField.getFinishingPoint();
    }

    @Override
    public void setValueOfRangeOfStep(int valueOfRangeOfStep) {

        analyzerOfWay.setDefaultRange(valueOfRangeOfStep);
    }

    @Override
    public List<PointOnTheField> getKeys() {

        return gameField.getKeys();
    }

    @Override
    public List<PointOnTheField> getDoors() {

        return gameField.getDoors();
    }
}