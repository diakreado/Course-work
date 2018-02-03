package com.maltsev.labyrinth.presenter;


import com.maltsev.labyrinth.model.field.PointOnTheField;
import com.maltsev.labyrinth.presenter.tempdata.PointOnTheScreen;

public interface IPresenter {

    /**
     * Отрисовывает поле
     * Вызывает метод drawTopBottomCells() drawExit() drawKeys() drawClosedDoors() у gameScreen
     */
    void drawField();

    /**
     * Движение протагониста
     * Если движение в указаную точку невозможно, то никаких действий совершенно не будет
     * @param screenX координата экрана по оси Х
     * @param screenY координата экрана по оси Y
     */
    void moveProtagonist(float screenX, float screenY);

    void moveProtagonistInTheFieldCoordinate(int fieldX, int fieldY);

    /**
     * Вызывается, чтобы узнать где находится протагонист во время передвижения
     * @param deltaTime промежуток времени между кадрами
     * @return Точка, в которой находится двигающийся протагонист в данный момент
     */
    PointOnTheScreen getPositionOfMovingProtagonist(float deltaTime);

    PointOnTheField getPositionOfProtagonistInTheFieldCoordinate();

    /**
     * @return число ключей, которые собрал игрок
     */
    int getNumberOfKeys();
}
