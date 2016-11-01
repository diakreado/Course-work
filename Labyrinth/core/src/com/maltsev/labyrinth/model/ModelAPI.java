package com.maltsev.labyrinth.model;


import com.maltsev.labyrinth.model.field.OutOfBoundaryOfTheField;
import com.maltsev.labyrinth.model.field.PointOnTheField;

import java.util.ArrayList;

/**
 * Model рассматривается в качестве поставщика данных,
 * которые будут отображаться во View.
 */
public interface ModelAPI {

    /**
     * Возможно ли поместить протигониста в определённую ячейку
     * @param x координата ячейки по оси Х
     * @param y координата ячейки по оси Y
     * @return возвращяет значение логического типа, если true, то протагонист может находится в этой ячейке, иначе false
     * @throws OutOfBoundaryOfTheField бросается в том случае, когда координаты выходят за поле
     */
    boolean isItPossibleWay(int x, int y) throws OutOfBoundaryOfTheField;

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
     * @param newField игровое поле, строка-матрица, где 1,s,f - проходимые элементы, а 0 - нет, s - начальная точка поля, f - конечная, а новая строчка задаётся \n
     */
    void setGameField(String newField);

    /**
     * Перемещаяет протагониста в определённыю точку пространства
     * @param x координата ячейки по оси Х
     * @param y координата ячейки по оси Y
     * @throws OutOfBoundaryOfTheField выбрасывается, когда запрашиваемые координаты некорректны
     */
    void movesOfProtagonist(int x, int y) throws OutOfBoundaryOfTheField;

    /**
     * @return Окончена ли игра
     */
    boolean isGameEnded();

    /**
     * @return Точка, местоположение героя
     */
    PointOnTheField getLocationOfProtagonist();

    /**
     * @return начальная точка поля
     */
    PointOnTheField getStartingPositionOnTheField();

    /**
     * @return конечная точка поля
     */
    PointOnTheField getFinishingPositionOnTheField();
}
