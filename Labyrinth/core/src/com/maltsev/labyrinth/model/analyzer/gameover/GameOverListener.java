package com.maltsev.labyrinth.model.analyzer.gameover;


/**
 * Интерфейс для записи в слушатели информации о прохождение поля
 */
public interface GameOverListener {

    /**
     * Метод, который вызовется в случае окончания игры
     */
    void gameIsOver();
}
