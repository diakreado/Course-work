package com.maltsev.labyrinth.model;


import com.maltsev.labyrinth.model.analyzer.gameover.GameOverListener;
import com.maltsev.labyrinth.model.field.OutOfBoundaryOfTheField;
import com.maltsev.labyrinth.model.field.PointOnTheField;

import java.util.ArrayDeque;
import java.util.ArrayList;

/**
 * Model рассматривается в качестве поставщика данных,
 * которые будут отображаться во View.
 */
public interface ModelAPI {                  //TODO Бесполезный интерфейс

    /**
     * Возможно ли поместить протигониста в определённую ячейку
     * @param x координата ячейки по оси Х
     * @param y координата ячейки по оси Y
     * @return возвращяет значение логического типа, если true, то протагонист может находится в этой ячейке, иначе false
     */
    boolean isItPassableCells(final int x, final int y);

    /**
     * Возможно ли поместить протигониста в определённую ячейку
     * @param point точка указывающая на ячейку
     * @return возвращяет значение логического типа, если true, то протагонист может находится в этой ячейке, иначе false
     */
    boolean isItPassableCells(final PointOnTheField point);

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
    void setGameField(final String newField);

    /**
     * Перемещаяет протагониста в определённыю точку пространства, если это возможно, иначе оставляет на прежнем месте.
     * Работает только если конечная точка пути находитсся не дальше 5 клеток от начальной
     * @param x координата ячейки по оси Х
     * @param y координата ячейки по оси Y
     * @return маршрут(массив точек) перемещения из одной точки в другую, если он возможен, иначе null
     */
    @org.jetbrains.annotations.Nullable
    ArrayDeque<PointOnTheField> movesOfProtagonist(final int x, final int y) throws OutOfBoundaryOfTheField;

    /**
     * Добавляет слушателя на событие окончание игры
     * @param listener объект-слушатель
     */
    void addListenerOfGameOver(GameOverListener listener);

    /**
     * @return Точка, местоположение героя
     */
    PointOnTheField getPositionOfProtagonist();

    /**
     * @return начальная точка поля
     */
    PointOnTheField getStartingPositionOfField();

    /**
     * @return конечная точка поля
     */
    PointOnTheField getFinishingPositionOfField();

    /**
     * Установить значение дальности шага протагониста
     * @param valueOfRangeOfStep дальность шага
     */
    void setValueOfRangeOfStep(int valueOfRangeOfStep);
}
