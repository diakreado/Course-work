package com.maltsev.labyrinth.model;


import com.badlogic.gdx.math.Vector2;
import com.maltsev.labyrinth.model.GameField.PointOnTheField;

import java.util.ArrayList;

/**
 * Model рассматривается в качестве поставщика данных,
 * которые будут отображаться во View.
 */
public interface ModelAPI {

    /**
     * Возможно ли поместить протигониста в определённую ячейку
     * @param x координата ячейкки по оси Х
     * @param y координата ячейкки по оси Y
     * @return возвращяет значение логического типа, если true, то протагонист может находится в этой ячейке, иначе false
     */
    boolean isItPossibleWay(int x, int y);

    /**
     * @return Размер поля по оси Х
     */
    int getSizeOfFieldX();

    /**
     * @return Размер поля по оси Y
     */
    int getSizeOfFieldY();

    /**
     * @return Массив объектов Точка на плоскости, который содержит номера ячеек, которые являются проходимыми
     */
    ArrayList<PointOnTheField> getPassableCells();

    /**
     * Установка игрового поля
     * @param gameField - игровое поле
     * @param startPoint - начальная точка поля
     * @param finalPoint - конечная точка поля
     */
    void setGameField(int gameField[][] ,PointOnTheField startPoint ,PointOnTheField finalPoint);

    /**
     * Перемещаяет протагониста в определённыю точку пространства
     * @param x координата ячейкки по оси Х
     * @param y координата ячейкки по оси Y
     */
    void moveProtagonist(int x, int y);
}
