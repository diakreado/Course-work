package com.maltsev.labyrinth.presenter;

import com.maltsev.labyrinth.model.IModel;
import com.maltsev.labyrinth.model.Model;
import com.maltsev.labyrinth.model.analyzer.event.gameover.GameOverListener;
import com.maltsev.labyrinth.model.analyzer.event.keysanddoors.doors.OpenDoorListener;
import com.maltsev.labyrinth.model.analyzer.event.keysanddoors.keys.FoundKeyListener;
import com.maltsev.labyrinth.model.field.FieldIsEmptyException;
import com.maltsev.labyrinth.model.field.PointOnTheField;
import com.maltsev.labyrinth.presenter.interfaces.IGameScreen;
import com.maltsev.labyrinth.presenter.interfaces.IProtagonistDrawer;
import com.maltsev.labyrinth.presenter.tempdata.PointOnTheScreen;
import com.maltsev.labyrinth.presenter.tempdata.SizeOfTexture;
import static com.maltsev.labyrinth.presenter.transkatorofcoordinate.TranslatorOfCoordinate.*;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;


public class Presenter implements IPresenter, GameOverListener, FoundKeyListener, OpenDoorListener {

    IModel model;
    private IGameScreen gameScreen;

    private SizeOfTexture sizeOfBlock;
    private List<PointOnTheField> doorsClosed;
    private List<PointOnTheField> doorsOpened;
    private List<PointOnTheField> keys;

    private ArrayDeque<PointOnTheField> way;

    private GameFieldDrawer gameFieldDrawer;

    private PointOnTheScreen pointOfMovement;
    private PointOnTheField pointOfMovementInTheFiledCoordinate;  //TODO сомнительное поле

    private double timer = 0;
    private double rateOfProtagonist = 0.185;
    private float frequencyOfIntermediateSteps = 10.88f;

    private IProtagonistDrawer protagonistDrawer;

    private int rangeOfStep = 7;

    /**
     * Аналогично isKeyFound, создано чтобы отрисовка поля происходила после того, как протагонист закончит движение
     */
    private boolean isGameOver = false;

    /**
     * Поле, созданное, чтобы ключ исчезал с карты после окончания движения протагониста, а не при нажатие на ключ
     */
    private boolean isKeyFound = false;


    public Presenter(IGameScreen view, String newField) {

        this.gameScreen = view;
        model = new Model();

        try {

            model.setGameField(newField);
        }
        catch (FieldIsEmptyException ex){

            System.out.println(ex);

            String defaultField = "11111\n11111";

            try {
                model.setGameField(defaultField);
            }
            catch (FieldIsEmptyException error) {

                throw new Error("Всё очень плохо");
            }
        }

        sizeOfBlock = view.getSizeOfBlock();
        initializeOfTranslator(sizeOfBlock);

        model.addListenerOfGameOver(this);
        model.addListenerOfFoundKey(this);
        model.addListenerOfOpenDoor(this);
        model.setValueOfRangeOfStep(rangeOfStep);

        doorsClosed = model.getDoors();
        doorsOpened = new ArrayList<>();
        keys = model.getKeys();

        gameFieldDrawer = new GameFieldDrawer(view.getFieldDrawer(), model);

        this.protagonistDrawer = view.getProtagonistDrawer();
        protagonistDrawer.setPositionOfProtagonist(translatePointFieldToScreen(model.getPositionOfProtagonist()));
    }


    @Override
    public void drawField() {

        gameFieldDrawer.drawPassableCells();
        gameFieldDrawer.drawDecoration();

        drawExit();

        drawKeys();
        drawClosedDoors();
        drawOpenedDoors();
    }

    /**
     * Отрисовывает финишную клетку
     * Вызывает метод drawExit() у gameScreen
     */
    public void drawExit() {

        gameScreen.drawExit(translatePointFieldToScreen(model.getFinishPosition()));
    }

    /**
     * Отрисовывает ключи на карте
     * Вызывает метод drawKey() у gameScreen
     */
    public void drawKeys() {

        for (PointOnTheField point : keys) {

            gameScreen.drawKey(translatePointFieldToScreen(point));
        }
    }

    /**
     * Отрисовывает закрытые двери на карте
     * Вызывает метод drawCloseDoor() у gameScreen
     */
    public void drawClosedDoors() {

        for (PointOnTheField point : doorsClosed) {


            gameScreen.drawCloseDoor(translatePointFieldToScreen(point));
        }
    }

    /**
     * Отрисовывает двери на карте
     * Вызывает метод drawOpenDoor() у gameScreen
     */
    public void drawOpenedDoors() {

        for (PointOnTheField point : doorsOpened) {

            gameScreen.drawOpenDoor(translatePointFieldToScreen(point));
        }
    }


    @Override
    public void moveProtagonist(float screenX, float screenY) {

        int x = (int) screenX / sizeOfBlock.getWidth();
        int y = (int) screenY / sizeOfBlock.getHeight();

        way = model.movesOfProtagonist(x, y);

        if (way != null)
            startMovement();
    }

    @Override
    public void moveProtagonistInTheFieldCoordinate(int fieldX, int fieldY) {

        way = model.movesOfProtagonist(fieldX, fieldY);

        if (way != null)
            startMovement();
    }

    /**
     * Метод, который вызывается в начале движения
     * Вызывает методы lockInput() и startMovement() у gameScreen
     */
    private void startMovement() {

        gameScreen.lockInput();
        gameScreen.startMovement();
        timer = 0;
        pointOfMovementInTheFiledCoordinate = way.poll();
        pointOfMovement = translatePointFieldToScreen(pointOfMovementInTheFiledCoordinate);
    }

    /**
     * Действия совершаемые, по окончаанию движения протагониста
     * Например, открытие ввода
     *
     * Отрисовка изменений внесённых ходом
     * Например, исчезновение ключа или сообщение об окончание игры
     */
    private void finishMovement() {

        gameScreen.unlockInput();
        gameScreen.finishMovement();
        timer = 0;

        if (isKeyFound) {

            keys = model.getKeys();
            isKeyFound = false;
        }

        if (isGameOver) {

            gameScreen.lockInput();
            gameScreen.messageOfGameOver();
        }
    }

    /**
     * Отрисовка движения протагониста
     * По очереди отрисовываются все клетки пути между начальной точкой и конечной
     * А между ними отрисовывается движение прогагониста с определённой частотой
     *
     * @param deltaTime сколько времени прошло относительно прошлой отрисовки, добавленно, чтобы регулировать скорость
     *                  передвижения протагониста
     */
    private void movementOfProtagonist(float deltaTime) {

        timer += deltaTime;

        if(timer > rateOfProtagonist) {

            timer = 0;
            pointOfMovementInTheFiledCoordinate = way.poll();
            pointOfMovement = translatePointFieldToScreen(pointOfMovementInTheFiledCoordinate);

        }
        if(timer > rateOfProtagonist/frequencyOfIntermediateSteps) {

            pointOfMovement = new PointOnTheScreen(
                    pointOfMovement.getX() +
                            (way.peek().getX()*sizeOfBlock.getWidth()-pointOfMovement.getX())*
                                    (int)(timer*frequencyOfIntermediateSteps/rateOfProtagonist)/frequencyOfIntermediateSteps,
                    pointOfMovement.getY() +
                            (way.peek().getY()*sizeOfBlock.getHeight()-pointOfMovement.getY())*
                                    (int)(timer*frequencyOfIntermediateSteps/rateOfProtagonist)/frequencyOfIntermediateSteps);
        }

        if(way.isEmpty())
            finishMovement();
    }

    @Override
    public PointOnTheScreen getPositionOfMovingProtagonist(float deltaTime) {

        movementOfProtagonist(deltaTime);

        return pointOfMovement;
    }

    /**
     * @return позиция протагониста, в координатах экрана
     */
    public PointOnTheScreen getPositionOfProtagonist() {

        return  translatePointFieldToScreen(model.getPositionOfProtagonist());
    }

    @Override
    public PointOnTheField getPositionOfProtagonistInTheFieldCoordinate() {

        return  model.getPositionOfProtagonist();
    }


    @Override
    public void gameIsOver() {

        isGameOver = true;
    }

    @Override
    public void keyIsFound(PointOnTheField positionOfKey) {

        isKeyFound = true;
    }

    @Override
    public void doorIsOpen(PointOnTheField doorPosition) {

        doorsClosed.remove(doorPosition);
        doorsOpened.add(voidFieldNearPoint(doorPosition));

        keys = model.getKeys();
    }

    /**
     * Используется для отрисовки открытой двери
     * @param point точка на игровом поле
     * @return ближайшая клетка, на которую не может сходить протигонист
     */
    private PointOnTheField voidFieldNearPoint(PointOnTheField point) {

        int x = point.getX();
        int y = point.getY();

        if(!model.isItPassableCells(x + 1, y))  return new PointOnTheField(x + 1, y);
        if(!model.isItPassableCells(x, y + 1))  return new PointOnTheField(x, y + 1);
        if(!model.isItPassableCells(x - 1, y))  return new PointOnTheField(x - 1, y);
        if(!model.isItPassableCells(x, y - 1))  return new PointOnTheField(x, y - 1);

        return point;
    }

    @Override
    public int getNumberOfKeys() {

        return model.getNumberOfKeys();
    }
}