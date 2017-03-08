package com.maltsev.labyrinth.presenter;

import com.maltsev.labyrinth.model.Model;
import com.maltsev.labyrinth.model.ModelOfLabyrinth;
import com.maltsev.labyrinth.model.analyzer.event.gameover.GameOverListener;
import com.maltsev.labyrinth.model.analyzer.event.keysanddoors.doors.OpenDoorListener;
import com.maltsev.labyrinth.model.analyzer.event.keysanddoors.keys.FoundKeyListener;
import com.maltsev.labyrinth.model.field.FieldIsEmptyException;
import com.maltsev.labyrinth.model.field.PointOnTheField;
import com.maltsev.labyrinth.presenter.interfaces.View;
import com.maltsev.labyrinth.presenter.tempdata.PointOnTheScreen;
import com.maltsev.labyrinth.presenter.tempdata.SizeOfTexture;
import static com.maltsev.labyrinth.presenter.ParsingFile.getFieldOnTheNumber;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;

/**
 * Presenter выступает в качестве посредника между View и ModelOfLabyrinth.
 * Он извлекает данные из модели и передает их во View. Также решает,
 * что нужно делать, когда вы взаимодействуете с View.
 */
public class Presenter implements GameOverListener, FoundKeyListener, OpenDoorListener {

    private Model model;
    private View view;

    private SizeOfTexture sizeOfBlock;
    private List<PointOnTheField> doorsClosed;
    private List<PointOnTheField> doorsOpened;
    private List<PointOnTheField> keys;

    private ArrayDeque<PointOnTheField> way;

    private PassableCellsDrawer passableCellsDrawer;

    private PointOnTheScreen pointOfMovement;
    private PointOnTheField pointOfMovementInTheFiledCoordinate;
    private double timer = 0;
    private double timerForStep = 0;
    private double rateOfProtagonist = 0.185;
    private float frequencyOfIntermediateSteps = 10.88f;

    private int rangeOfStep = 7;

    /**
     * Аналогично isKeyFound, создано чтобы отрисовка поля происходила после того, как протагонист закончит движение
     */
    private boolean isGameOver = false;

    /**
     * Поле, созданное, чтобы ключ исчезал с карты после окончания движения протагониста, а не при нажатие на ключ
     */
    private boolean isKeyFound = false;

    private List<PointOnTheField> trees;
    private List<PointOnTheField> grass;


    /**
     * Следует вызвать последним, так как он интересуется полями View (может оказаться, что поля ещё не инициализированы)
     * @param view отрисовщик ui, с которым будет работать presenter
     */
    public Presenter(View view, int numberOfTheField) {

        this.view = view;

        model = new ModelOfLabyrinth();


        String newField = getFieldOnTheNumber(numberOfTheField);

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

        model.addListenerOfGameOver(this);
        model.addListenerOfFoundKey(this);
        model.addListenerOfOpenDoor(this);
        model.setValueOfRangeOfStep(rangeOfStep);

        sizeOfBlock = new SizeOfTexture(view.getSizeOfBlock());
        doorsClosed = model.getDoors();
        doorsOpened = new ArrayList<>();
        keys = model.getKeys();

        trees = model.getTrees();
        grass = model.getGrass();

        passableCellsDrawer = new PassableCellsDrawer(view, this);
    }

    /**
     * Отрисовывает поле
     * Вызывает метод drawBlock() drawExit() drawKeys() drawClosedDoors() у View
     */
    public void drawField() {

        drawPassableCells();
        drawExit();

        drawKeys();
        drawClosedDoors();
        drawOpenedDoors();

        drawTrees();
        drawGrass();
    }

    public void drawGrass() {

        for (PointOnTheField point : grass) {

            view.drawGrass(translatePointFieldToScreen(point));
        }
    }

    public void drawTrees() {

        for (PointOnTheField point : trees) {

            view.drawTree(translatePointFieldToScreen(point));
        }
    }

    /**
     * Отрисовка клеток, по которым можно передвигаться протагонисту
     * Вызывает метод drawBlock() у View
     */
    public void drawPassableCells() {

        passableCellsDrawer.drawCells();
    }

    /**
     * Отрисовывает финишную клетку
     * Вызывает метод drawExit() у View
     */
    public void drawExit() {

        view.drawExit(translatePointFieldToScreen(model.getFinishPosition()));
    }

    /**
     * Отрисовывает ключи на карте
     * Вызывает метод drawKey() у View
     */
    public void drawKeys() {

        for (PointOnTheField point : keys) {

            view.drawKey(translatePointFieldToScreen(point));
        }
    }

    /**
     * Отрисовывает закрытые двери на карте
     * Вызывает метод drawCloseDoor() у View
     */
    public void drawClosedDoors() {

        for (PointOnTheField point : doorsClosed) {


            view.drawCloseDoor(translatePointFieldToScreen(point));
        }
    }

    /**
     * Отрисовывает двери на карте
     * Вызывает метод drawOpenDoor() у View
     */
    public void drawOpenedDoors() {

        for (PointOnTheField point : doorsOpened) {

            view.drawOpenDoor(translatePointFieldToScreen(point));
        }
    }

    /**
     * Движение протагониста
     * Если движение в указаную точку невозможно, то никаких действий совершенно не будет
     * @param screenX координата экрана по оси Х
     * @param screenY координата экрана по оси Y
     */
    public void moveProtagonist(float screenX, float screenY) {

        int x = (int) screenX / sizeOfBlock.getWidth();
        int y = (int) screenY / sizeOfBlock.getHeight();

        way = model.movesOfProtagonist(x, y);

        if (way != null)
            startMovement();
    }

    public void moveProtagonistInTheFieldCoordinate(int fieldX, int fieldY) {

        way = model.movesOfProtagonist(fieldX, fieldY);

        if (way != null)
            startMovement();
    }

    /**
     * Метод, который вызывается в начале движения
     * Вызывает методы lockInput() и startMovement() у View
     */
    private void startMovement() {

        view.lockInput();
        view.startMovement();
        timer = 0;
        timerForStep = 0;
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

        view.unlockInput();
        view.finishMovement();
        timer = 0;

        if (isKeyFound) {

            keys = model.getKeys();
            isKeyFound = false;
        }

        if (isGameOver) {

            view.lockInput();
            view.messageOfGameOver();
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

        checkDirection(pointOfMovementInTheFiledCoordinate, way.peek());


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

    private void checkDirection(PointOnTheField firstPoint, PointOnTheField secondPoint) {

        if (secondPoint.getX() > firstPoint.getX()) {

            view.changeDirectionToRight();
        }

        if (secondPoint.getX() < firstPoint.getX()) {

            view.changeDirectionToLeft();
        }

        if (secondPoint.getY() > firstPoint.getY()) {

            view.changeDirectionToBack();
        }

        if (secondPoint.getY() < firstPoint.getY()) {

            view.changeDirectionToRight();
        }
    }

    /**
     * Вызывается, чтобы узнать где находится протагонист во время передвижения
     * @param deltaTime промежуток времени между кадрами
     * @return Точка, в которой находится двигающийся протагонист в данный момент
     */
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

    public PointOnTheField getPositionOfProtagonistInTheFieldCoordinate() {

        return  model.getPositionOfProtagonist();
    }


    /**
     * Перевод координат точки из координат поля в координаты экрана
     * @param pointOnTheField точка с координатами поля
     * @return точка с координатами экрана
     */
    public PointOnTheScreen translatePointFieldToScreen(PointOnTheField pointOnTheField) {

        return new PointOnTheScreen(pointOnTheField.getX() * sizeOfBlock.getWidth(),
                pointOnTheField.getY() * sizeOfBlock.getHeight());
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

    /**
     * @return число ключей, которые собрал игрок
     */
    public int getNumberOfKeys() {

        return model.getNumberOfKeys();
    }

    public List<PointOnTheField> getPassableCells() {

        return model.getPassableCells();
    }
}