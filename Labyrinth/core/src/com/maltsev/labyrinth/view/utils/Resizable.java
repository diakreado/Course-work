package com.maltsev.labyrinth.view.utils;

/**
 * Функциональный интерфейс, обозначающий что размер объекта можно менять
 */
public interface Resizable {

    /**
     * Изменить размер объекта
     * @param width - дельта ширины
     * @param height - дельта высоты
     */
    void resize(int width, int height);
}
