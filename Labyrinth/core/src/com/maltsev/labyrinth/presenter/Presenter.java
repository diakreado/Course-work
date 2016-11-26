package com.maltsev.labyrinth.presenter;

import com.maltsev.labyrinth.model.analyzer.gameover.GameOverListener;
import com.maltsev.labyrinth.model.field.PointOnTheField;
import com.maltsev.labyrinth.model.Model;
import com.maltsev.labyrinth.presenter.tempdata.PointOnTheScreen;
import com.maltsev.labyrinth.presenter.tempdata.SizeOfTexture;

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

    private final int RANGE_OF_STEP = 6;

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
        model.setValueOfRangeOfStep(RANGE_OF_STEP);

        sizeOfBlock = new SizeOfTexture(view.getSizeOfBlock());
        passableCells = new ArrayList<PointOnTheField>(model.getPassableCells());
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

        model.movesOfProtagonist(x, y);
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

        view.lockInput();

        view.messageOfGameOver();

        //view.close();
    }
}