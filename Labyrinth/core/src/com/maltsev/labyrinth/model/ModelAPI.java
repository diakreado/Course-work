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
     */
    boolean isItPassableCells(int x, int y);

    /**
     * Возможно ли поместить протигониста в определённую ячейку
     * @param point точка указывающая на ячейку
     * @return возвращяет значение логического типа, если true, то протагонист может находится в этой ячейке, иначе false
     */
    boolean isItPassableCells(PointOnTheField point);

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
     * Перемещаяет протагониста в определённыю точку пространства, если это возможно, иначе оставляет на прежнем месте.
     * Работает только если конечная точка пути находитсся не дальше 5 клеток от начальной
     * @param x координата ячейки по оси Х
     * @param y координата ячейки по оси Y
     * @return маршрут(массив точек) перемещения из одной точки в другую, если он возможен, иначе пустой массив
     */
    ArrayList<PointOnTheField> movesOfProtagonist(int x, int y) throws OutOfBoundaryOfTheField;

    /**
     * @return Окончена ли игра
     */
    boolean isGameEnded();                         // TODO планируется заменить шаблоном Наблюдатель

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
