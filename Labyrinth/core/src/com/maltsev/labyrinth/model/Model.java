package com.maltsev.labyrinth.model;

import com.maltsev.labyrinth.model.analyzer.event.keysanddoors.doors.OpenDoorListener;

import java.util.ArrayDeque;
import java.util.List;

public class Model implements IModel {

    public Model() {}

    /**
     * Главный герой
     * Здесь хранится информацию о том, где он находится и его
     * определённые параметры (например, сколько у него ключей)
     */
    private com.maltsev.labyrinth.model.protagonist.Protagonist protagonist;

    /**
     * Игровое поле.
     */
    private com.maltsev.labyrinth.model.field.GameField gameField;

    /**
     *  Наблюдатель за концом игры
     *  Оповещает его слушателей о том, что игра закночилась
     */
    private com.maltsev.labyrinth.model.analyzer.event.gameover.GameOverAnalyzer analyzerOfGameOver;

    /**
     * Наблюдатель за открытием дверей
     * Оповещает слушателей о том, что была открыта дверь
     */
    private com.maltsev.labyrinth.model.analyzer.event.keysanddoors.doors.OpenDoorAnalyzer analyzerOfOpenDoor;

    /**
     * Наблюдатель за нахождением ключей
     * Оповещает слушателей о том, что найден ключ
     */
    private com.maltsev.labyrinth.model.analyzer.event.keysanddoors.keys.FoundKeyAnalyzer analyzerOfFoundKey;

    /**
     * Анализатор пути
     * Строит путь определённой длинны между двумя точкам
     * Длинна ограниченна, потому что не хотелось бы, чтобы игрок в одно касание перешёл от начал к концу
     */
    private com.maltsev.labyrinth.model.analyzer.WayAnalyzer analyzerOfWay;

    /**
     * Массив дверей
     * Запоминается, т.к. многократно используется при проверке возможности хода
     */
    private List<com.maltsev.labyrinth.model.field.PointOnTheField> doors;


    @Override
    public void setGameField(final String newField) throws com.maltsev.labyrinth.model.field.FieldIsEmptyException {

        this.gameField = new com.maltsev.labyrinth.model.field.GameField(newField);
        protagonist = new com.maltsev.labyrinth.model.protagonist.Protagonist(gameField.getStartingPoint());
        analyzerOfGameOver = new com.maltsev.labyrinth.model.analyzer.event.gameover.GameOverAnalyzer(this);
        analyzerOfFoundKey = new com.maltsev.labyrinth.model.analyzer.event.keysanddoors.keys.FoundKeyAnalyzer(this);
        analyzerOfOpenDoor = new com.maltsev.labyrinth.model.analyzer.event.keysanddoors.doors.OpenDoorAnalyzer(this);
        analyzerOfWay = new com.maltsev.labyrinth.model.analyzer.WayAnalyzer(this);
        doors = gameField.getDoors();

        analyzerOfFoundKey.addListener(protagonist);
        analyzerOfFoundKey.addListener(gameField);
    }

    /**
     * Вызывается перед тем, как сделать ход, т.к. возможно дверь откроется
     * @param point - точка, в которую попытался сходить игрок
     */
    private void checkDoors(com.maltsev.labyrinth.model.field.PointOnTheField point) {

        if (doors.contains(point) && protagonist.getNumberOfKeys() > 0 && !isItPassableCells(point)) {

            protagonist.useKey();
            gameField.openDoor(point.getX(), point.getY());
            analyzerOfOpenDoor.doorIsOpen(point);
        }
    }

    @Override
    public ArrayDeque<com.maltsev.labyrinth.model.field.PointOnTheField> movesOfProtagonist(final int x, final  int y) {

        checkDoors(new com.maltsev.labyrinth.model.field.PointOnTheField(x,y));

        ArrayDeque<com.maltsev.labyrinth.model.field.PointOnTheField> way = analyzerOfWay.getWay(getPositionOfProtagonist(), new com.maltsev.labyrinth.model.field.PointOnTheField(x,y));

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

        }catch (com.maltsev.labyrinth.model.field.OutOfBoundaryOfTheFieldException ex) {

            return false;
        }
    }

    @Override
    public boolean isItPassableCells(final com.maltsev.labyrinth.model.field.PointOnTheField point) {

        try {

            return gameField.isItPassableCell(point);

        }catch (com.maltsev.labyrinth.model.field.OutOfBoundaryOfTheFieldException ex) {

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
    public List<com.maltsev.labyrinth.model.field.PointOnTheField> getPassableCells() {

        return gameField.getPassableCells();
    }

    @Override
    public com.maltsev.labyrinth.model.field.PointOnTheField getPositionOfProtagonist() {

        return protagonist.getLocationOfProtagonist();
    }

    @Override
    public com.maltsev.labyrinth.model.field.PointOnTheField getStartPosition() {

        return gameField.getStartingPoint();
    }

    @Override
    public com.maltsev.labyrinth.model.field.PointOnTheField getFinishPosition() {

        return gameField.getFinishingPoint();
    }

    @Override
    public void setValueOfRangeOfStep(int valueOfRangeOfStep) {

        analyzerOfWay.setDefaultRange(valueOfRangeOfStep);
    }

    @Override
    public List<com.maltsev.labyrinth.model.field.PointOnTheField> getKeys() {

        return gameField.getKeys();
    }

    @Override
    public List<com.maltsev.labyrinth.model.field.PointOnTheField> getDoors() {

        return gameField.getDoors();
    }


    @Override
    public void addListenerOfGameOver(com.maltsev.labyrinth.model.analyzer.event.gameover.GameOverListener listener) {

        analyzerOfGameOver.addListener(listener);
    }

    @Override
    public void removeListenerOfGameOver(com.maltsev.labyrinth.model.analyzer.event.gameover.GameOverListener listener) {

        analyzerOfGameOver.removeListener(listener);
    }

    @Override
    public void addListenerOfFoundKey(com.maltsev.labyrinth.model.analyzer.event.keysanddoors.keys.FoundKeyListener listener) {

        analyzerOfFoundKey.addListener(listener);
    }

    @Override
    public void removeListenerOfFoundKey(com.maltsev.labyrinth.model.analyzer.event.keysanddoors.keys.FoundKeyListener listener) {

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
    public List<com.maltsev.labyrinth.model.field.PointOnTheField> getTrees() {

        return gameField.getTrees();
    }

    @Override
    public List<com.maltsev.labyrinth.model.field.PointOnTheField> getGrass() {

        return gameField.getGrass();
    }
}