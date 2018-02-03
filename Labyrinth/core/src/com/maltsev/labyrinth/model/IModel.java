package com.maltsev.labyrinth.model;


import com.maltsev.labyrinth.model.analyzer.event.keysanddoors.doors.OpenDoorListener;
import com.maltsev.labyrinth.model.field.FieldIsEmptyException;

import java.util.ArrayDeque;
import java.util.List;

/**
 * Model рассматривается в качестве поставщика данных,
 * которые будут отображаться во View.
 */
public interface IModel {

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
    boolean isItPassableCells(final com.maltsev.labyrinth.model.field.PointOnTheField point);

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
    List<com.maltsev.labyrinth.model.field.PointOnTheField> getPassableCells();

    /**
     * Установка игрового поля
     * @param newField игровое поле, строка-матрица, где 1,s,f - проходимые элементы, а 0 - нет, s - начальная точка поля, f - конечная, а новая строчка задаётся \n
     * @throws FieldIsEmptyException выбрасывается, когда поле задано лишь 0, т.е. ходить некуда
     */
    void setGameField(final String newField)  throws FieldIsEmptyException;

    /**
     * Перемещаяет протагониста в определённыю точку пространства, если это возможно, иначе оставляет на прежнем месте.
     * Работает только если конечная точка пути находитсся не дальше 5 клеток от начальной
     * @param x координата ячейки по оси Х
     * @param y координата ячейки по оси Y
     * @return маршрут(массив точек) перемещения из одной точки в другую, если он возможен, иначе null
     */
    ArrayDeque<com.maltsev.labyrinth.model.field.PointOnTheField> movesOfProtagonist(final int x, final int y);

    /**
     * Добавляет слушателя на событие окончание игры
     * @param listener объект-слушатель
     */
    void addListenerOfGameOver(com.maltsev.labyrinth.model.analyzer.event.gameover.GameOverListener listener);

    /**
     * Отписка слушателя от раассылки на Конец игры
     * @param listener объект-слушатель
     */
    void removeListenerOfGameOver(com.maltsev.labyrinth.model.analyzer.event.gameover.GameOverListener listener);

    /**
     * Добавляет слушателя на событие Найден ключ
     * @param listener объект-слушатель
     */
    void addListenerOfFoundKey(com.maltsev.labyrinth.model.analyzer.event.keysanddoors.keys.FoundKeyListener listener);

    /**
     * Отписка слушателя от раассылки на Найден ключ
     * @param listener объект-слушатель
     */
    void removeListenerOfFoundKey(com.maltsev.labyrinth.model.analyzer.event.keysanddoors.keys.FoundKeyListener listener);

    /**
     * Добавляет слушателя на событие Открытие двери
     * @param listener объект-слушатель
     */
    void addListenerOfOpenDoor(OpenDoorListener listener);

    /**
     * Отписка слушателя от раассылки на Открытие двери
     * @param listener объект-слушатель
     */
    void removeListenerOfOpenDoor(OpenDoorListener listener);

    /**
     * @return Точка, местоположение героя
     */
    com.maltsev.labyrinth.model.field.PointOnTheField getPositionOfProtagonist();

    /**
     * @return начальная точка поля
     *
     * Та точка, куда помещается протагонист, с самого начала игры
     */
    com.maltsev.labyrinth.model.field.PointOnTheField getStartPosition();

    /**
     * @return конечная точка поля
     *
     * Точка, куда нужно пройти протагонисту, чтобы окончить игру
     */
    com.maltsev.labyrinth.model.field.PointOnTheField getFinishPosition();

    /**
     * Установить значение дальности шага протагониста
     *
     * В общем, шаги были введены, чтобы игрок не мог пройти
     * от начала карты до конца в один клик
     * @param valueOfRangeOfStep дальность шага
     */
    void setValueOfRangeOfStep(int valueOfRangeOfStep);

    /**
     * @return Массив с координатами дверей
     * Изначально двери закрыты, но если есть ключ, то дверь откроется, а
     * количество ключей уменьшится на 1
     */
    List<com.maltsev.labyrinth.model.field.PointOnTheField> getDoors();

    /**
     * @return Массив с координатами ключей
     * Ключи нужны чтобы открывать двери
     */
    List<com.maltsev.labyrinth.model.field.PointOnTheField> getKeys();

    /**
     * @return Число ключей, собраных игроком
     */
    int getNumberOfKeys();

    /**
     * @return Массив с координатами деревьев
     * по факту это лишь дектор
     */
    List<com.maltsev.labyrinth.model.field.PointOnTheField> getTrees();

    /**
     * @return Массив с координатами травы на игровом поле
     * по факту это лишь дектор
     *
     * Возможно перейдёт в нечто другое, т.к. трава не справляется со своей задачей, удачно декорировать лабиринт
     */
    List<com.maltsev.labyrinth.model.field.PointOnTheField> getGrass();
}
