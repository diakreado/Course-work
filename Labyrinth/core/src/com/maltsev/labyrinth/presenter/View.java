package com.maltsev.labyrinth.presenter;


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
     * @param x координата по оси Х, позиции в которой следует отрисовать блок
     * @param y координата по оси Y, позиции в которой следует отрисовать блок
     */
    void drawBlock(float x, float y);
}
