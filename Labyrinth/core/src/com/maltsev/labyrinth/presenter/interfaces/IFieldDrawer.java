package com.maltsev.labyrinth.presenter.interfaces;

import com.maltsev.labyrinth.presenter.tempdata.PointOnTheScreen;
import com.maltsev.labyrinth.presenter.tempdata.SizeOfTexture;

/**
 * Отрисовщик ирового поля
 *
 * Как следстиве должен уметь рисовать сотовляющие игрового поля. В них ходят проходимые клетки(дорожка)
 * и декорирующие элементы
 *
 * Тот кто реализует этот интерфейс должен реализовать методы отрисовки различных типов
 * блоков(изображений из которых конструируется игровое поле) и метод возвращяющий размер блока
 *
 * В качестве параметра принимается текстура указывающая на то, на какой позиции нужно отрисовать блок
 *
 * Каждый метод отрисовывает свой тип блоков, например, drawRightBottomLeftCells блок, который следует разместить
 * в такой точке соединения, в которую пути сходятся слева, справа и снизу, остальные по аналогии, т.е.
 * если к блоку запланировано подведение пути с права и слева, то чтобы его отрисовать нужно будет вызвать drawLeftRight
 * Перечисление сторон происходи по часовой стрелке
 */
public interface IFieldDrawer {

    /**
     * @return структура данных содержащая в себе размер текстуры единичного блока, из которых составляется поле
     */
    SizeOfTexture getSizeOfBlock();


    void drawTree(PointOnTheScreen point);
    void drawGrass(PointOnTheScreen point);                         //Декорации


    void drawTopBottomCells(PointOnTheScreen point);
    void drawLeftRightCells(PointOnTheScreen point);                //Вертикальный, горизонтальный, центральный блок
    void drawLeftTopRightBottomCells(PointOnTheScreen point);

    void drawLeftTopCells(PointOnTheScreen point);
    void drawRightTopCells(PointOnTheScreen point);
    void drawLeftBottomCells(PointOnTheScreen point);               //Блоки двумя входами
    void drawRightBottomCells(PointOnTheScreen point);

    void drawLeftCells(PointOnTheScreen point);
    void drawRightCells(PointOnTheScreen point);
    void drawBottomCells(PointOnTheScreen point);                   //Тупиковые блоки, с один входом
    void drawTopCells(PointOnTheScreen point);

    void drawLeftTopRightCells(PointOnTheScreen point);
    void drawBottomLeftTopCells(PointOnTheScreen point);
    void drawRightBottomLeftCells(PointOnTheScreen point);          //Блоки объединения трёх путей
    void drawTopRightBottomCells(PointOnTheScreen point);
}
