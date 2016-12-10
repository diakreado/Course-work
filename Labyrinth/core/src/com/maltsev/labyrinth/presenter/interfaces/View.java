package com.maltsev.labyrinth.presenter.interfaces;


import com.maltsev.labyrinth.presenter.tempdata.PointOnTheScreen;
import com.maltsev.labyrinth.presenter.tempdata.SizeOfTexture;

/**
 * Интерфейс для взаимодействия с Presenter
 */
public interface View {

    /**
     * @return размер блока текстуры
     */
    SizeOfTexture getSizeOfBlock();

    /**
     * Отрисовка блока текстуры
     * @param point точка, в которой следует отрисовать блок
     */
    void drawBlock(PointOnTheScreen point);

    /**
     * Отрисовка финишной клетки
     * Если финишная клетка не нужна, то можно ничего не делать
     * @param point точка, в которой следует отрисовать финишную клетку
     */
    void drawExit(PointOnTheScreen point);

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
}
