package com.maltsev.labyrinth.presenter;

import com.maltsev.labyrinth.model.analyzer.gameover.GameOverListener;
import com.maltsev.labyrinth.model.field.PointOnTheField;
import com.maltsev.labyrinth.model.Model;
import com.maltsev.labyrinth.presenter.tempdata.PointOnTheScreen;
import com.maltsev.labyrinth.presenter.tempdata.SizeOfTexture;

import java.util.ArrayDeque;
import java.util.ArrayList;

/**
 * Presenter выступает в качестве посредника между View и Model.
 * Он извлекает данные из модели и передает их во View. Также решает,
 * что нужно делать, когда вы взаимодействуете с View.
 */
public class Presenter implements GameOverListener {

    private Model model;
    private View view;

    private SizeOfTexture sizeOfBlock;
    private ArrayList<PointOnTheField> passableCells;

    private ArrayDeque<PointOnTheField> way;

    private PointOnTheScreen pointBeforeMovement;
    private PointOnTheScreen pointOfMovement;
    private double timer = 0;
    private double rateOfProtagonist = 0.2;

    private int rangeOfStep = 5;

    private boolean isGameOver = false;

    /**
     * Следует вызвать последним, так как он интересуется полями View (может оказаться, что поля ещё не инициализированы)
     * @param view отрисовщик ui, с которым будет работать presenter
     */
    public Presenter(View view) {

        this.view = view;

        model = Model.getInstance();

        String newField = FileReader.read("gameField.txt");

        model.setGameField(newField);
        model.addListenerOfGameOver(this);
        model.setValueOfRangeOfStep(rangeOfStep);

        sizeOfBlock = new SizeOfTexture(view.getSizeOfBlock());
        passableCells = new ArrayList<PointOnTheField>(model.getPassableCells());

        pointBeforeMovement = getPositionOfProtagonist();
    }

    /**
     * Отрисовывает проходимые клетки и клетку, являющуюся финишной
     * Вызывает метод drawBlock() у View
     */
    public void drawField() {

        drawPassableCells();
        drawExit();
    }

    /**
     * Отрисовка клеток, по которым можно передвигаться протагонисту
     * Вызывает метод drawBlock() у View
     */
    public void drawPassableCells() {

        for (PointOnTheField point : passableCells) {

            view.drawBlock(translatePointFieldToScreen(point));
        }
    }

    /**
     * Отрисовывает финишную клетку
     * Вызывает метод drawBlock() у View
     */
    public void drawExit() {

        view.drawExit(translatePointFieldToScreen(model.getFinishingPositionOfField()));
    }

    public void moveProtagonist(float screenX, float screenY) {

        int x = (int) screenX / sizeOfBlock.getWidth();
        int y = (int) screenY / sizeOfBlock.getHeight();

        way = model.movesOfProtagonist(x, y);

        if (way != null)
            startMovement();
    }

    private void startMovement() {

        view.lockInput();
        view.startMovement();
        timer = 0;
        pointOfMovement = pointBeforeMovement;
    }

    private void finishMovement() {

        view.unlockInput();
        view.finishMovement();
        timer = 0;
        pointBeforeMovement = getPositionOfProtagonist();

        if (isGameOver) {

            view.lockInput();
            view.messageOfGameOver();
        }
    }

    private void movementOfProtagonist(float deltaTime) {

        timer += deltaTime;

        if(timer > rateOfProtagonist) {

            timer = 0;
            pointOfMovement = translatePointFieldToScreen(way.poll());
        }


        if(way.isEmpty())
            finishMovement();
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

    private PointOnTheScreen translatePointFieldToScreen(PointOnTheField pointOnTheField) {

        return new PointOnTheScreen(pointOnTheField.getX() * sizeOfBlock.getWidth(),
                pointOnTheField.getY() * sizeOfBlock.getHeight());
    }

    @Override
    public void gameIsOver() {

        isGameOver = true;
    }
}