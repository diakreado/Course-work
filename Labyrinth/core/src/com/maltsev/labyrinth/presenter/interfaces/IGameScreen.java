package com.maltsev.labyrinth.presenter.interfaces;


import com.maltsev.labyrinth.presenter.tempdata.PointOnTheScreen;

/**
 * Интерфейс для взаимодействия с Presenter
 */
public interface IGameScreen {

    /**
     * @return размер блока текстуры
     */
    com.maltsev.labyrinth.presenter.tempdata.SizeOfTexture getSizeOfBlock();

    /**
     * Отрисовка финишной клетки
     * @param point точка, в которой следует отрисовать финишную клетку
     */
    void drawExit(PointOnTheScreen point);

    /**
     * Отрисовка ключа
     * @param point точка, в которой следует отрисовать ключ
     */
    void drawKey(PointOnTheScreen point);

    /**
     * Отрисовка закрытой двери
     * @param point точка, в которой следует отрисовать дверь
     */
    void drawCloseDoor(PointOnTheScreen point);

    /**
     * Отрисовка открытой двери
     * @param point точка, в которой следует отрисовать дверь
     */
    void drawOpenDoor(PointOnTheScreen point);

    /**
     * Запрет на ввод
     */
    void lockInput();

    /**
     * Отключить запрет на ввод
     */
    void unlockInput();

    /**
     * Начать движение
     */
    void startMovement();

    /**
     * Закончить движение
     */
    void finishMovement();

    /**
     * Сообщение об окончание игры
     */
    void messageOfGameOver();



    IProtagonistDrawer getProtagonistDrawer();

    IFieldDrawer getFieldDrawer();

}
