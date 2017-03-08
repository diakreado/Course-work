package com.maltsev.labyrinth.model;

import com.maltsev.labyrinth.model.analyzer.WayAnalyzer;
import com.maltsev.labyrinth.model.analyzer.event.gameover.GameOverAnalyzer;
import com.maltsev.labyrinth.model.analyzer.event.gameover.GameOverListener;
import com.maltsev.labyrinth.model.analyzer.event.keysanddoors.doors.OpenDoorAnalyzer;
import com.maltsev.labyrinth.model.analyzer.event.keysanddoors.doors.OpenDoorListener;
import com.maltsev.labyrinth.model.analyzer.event.keysanddoors.keys.FoundKeyAnalyzer;
import com.maltsev.labyrinth.model.analyzer.event.keysanddoors.keys.FoundKeyListener;
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
     * Здесь хранится информацию о том, где он находится и его
     * определённые параметры (например, сколько у него ключей)
     */
    private Protagonist protagonist;

    /**
     * Игровое поле.
     */
    private GameField gameField;

    /**
     *  Наблюдатель за концом игры
     *  Оповещает его слушателей о том, что игра закночилась
     */
    private GameOverAnalyzer analyzerOfGameOver;

    /**
     * Наблюдатель за открытием дверей
     * Оповещает слушателей о том, что была открыта дверь
     */
    private OpenDoorAnalyzer analyzerOfOpenDoor;

    /**
     * Наблюдатель за нахождением ключей
     * Оповещает слушателей о том, что найден ключ
     */
    private FoundKeyAnalyzer analyzerOfFoundKey;

    /**
     * Анализатор пути
     * Строит путь определённой длинны между двумя точкам
     * Длинна ограниченна, потому что не хотелось бы, чтобы игрок в одно касание перешёл от начал к концу
     */
    private WayAnalyzer analyzerOfWay;

    /**
     * Массив дверей
     * Запоминается, т.к. многократно используется при проверке возможности хода
     */
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
        analyzerOfFoundKey.addListener(gameField);
    }

    /**
     * Вызывается перед тем, как сделать ход, т.к. возможно дверь откроется
     * @param point - точка, в которую попытался сходить игрок
     */
    private void checkDoors(PointOnTheField point) {

        if (doors.contains(point) && protagonist.getNumberOfKeys() > 0 && !isItPassableCells(point)) {

            protagonist.useKey();
            gameField.openDoor(point.getX(), point.getY());
            analyzerOfOpenDoor.doorIsOpen(point);
        }
    }

    @Override
    public ArrayDeque<PointOnTheField> movesOfProtagonist(final int x, final  int y) {

        checkDoors(new PointOnTheField(x,y));

        ArrayDeque<PointOnTheField> way = analyzerOfWay.getWay(getPositionOfProtagonist(), new PointOnTheField(x,y));

        if (way == null) return null;

        protagonist.movesOfProtagonist(x,y);

        noticeAfterMotion();

        return way;
    }

    /**
     * Метод, который сообщает необходимым классам, что состояние системы изменилось
     * Вызывается после хода игрока
     */
    private void noticeAfterMotion() {

        analyzerOfGameOver.messageAboutChangingSystem();
        analyzerOfFoundKey.messageAboutChangingSystem();
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


    @Override
    public void addListenerOfGameOver(GameOverListener listener) {

        analyzerOfGameOver.addListener(listener);
    }

    @Override
    public void removeListenerOfGameOver(GameOverListener listener) {

        analyzerOfGameOver.removeListener(listener);
    }

    @Override
    public void addListenerOfFoundKey(FoundKeyListener listener) {

        analyzerOfFoundKey.addListener(listener);
    }

    @Override
    public void removeListenerOfFoundKey(FoundKeyListener listener) {

        analyzerOfFoundKey.removeListener(listener);
    }

    @Override
    public void addListenerOfOpenDoor(OpenDoorListener listener) {

        analyzerOfOpenDoor.addListener(listener);
    }

    @Override
    public void removeListenerOfOpenDoor(OpenDoorListener listener) {

        analyzerOfOpenDoor.removeListener(listener);
    }

    @Override
    public int getNumberOfKeys() {

        return protagonist.getNumberOfKeys();
    }

    @Override
    public List<PointOnTheField> getTrees() {

        return gameField.getTrees();
    }

    @Override
    public List<PointOnTheField> getGrass() {

        return gameField.getGrass();
    }
}