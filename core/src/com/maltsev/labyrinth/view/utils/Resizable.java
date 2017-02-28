package com.maltsev.labyrinth.view.utils;

/**
 * Функциональный интерфейс, обозначающий что размер объекта можно менять
 */
public interface Resizable {

    /**
     * Изменить размер объекта
     * @param width - новая ширина
     * @param height - новая высота
     */
    void resize(int width, int height);
}
