package com.maltsev.labyrinth.presenter;


import com.maltsev.labyrinth.presenter.transkatorofcoordinate.TranslatorOfCoordinate;

import java.util.ArrayList;
import java.util.List;

/**
 * Отвечает за логику отрисовки игрового мира, проходимые ячейки и декорации
 */
class GameFieldDrawer {

    private List<com.maltsev.labyrinth.model.field.PointOnTheField> passableCells;

    private ArrayList<com.maltsev.labyrinth.model.field.PointOnTheField> cellsTopBottom;
    private ArrayList<com.maltsev.labyrinth.model.field.PointOnTheField> cellsLeftRight;
    private ArrayList<com.maltsev.labyrinth.model.field.PointOnTheField> cellsLeftTopRightBottom;

    private ArrayList<com.maltsev.labyrinth.model.field.PointOnTheField> cellsLeftTop;
    private ArrayList<com.maltsev.labyrinth.model.field.PointOnTheField> cellsRightTop;
    private ArrayList<com.maltsev.labyrinth.model.field.PointOnTheField> cellsLeftBottom;
    private ArrayList<com.maltsev.labyrinth.model.field.PointOnTheField> cellsRightBottom;

    private ArrayList<com.maltsev.labyrinth.model.field.PointOnTheField> cellsBottom;
    private ArrayList<com.maltsev.labyrinth.model.field.PointOnTheField> cellsTop;
    private ArrayList<com.maltsev.labyrinth.model.field.PointOnTheField> cellsLeft;
    private ArrayList<com.maltsev.labyrinth.model.field.PointOnTheField> cellsRight;

    private ArrayList<com.maltsev.labyrinth.model.field.PointOnTheField> cellsLeftTopRight;
    private ArrayList<com.maltsev.labyrinth.model.field.PointOnTheField> cellsBottomLeftTop;
    private ArrayList<com.maltsev.labyrinth.model.field.PointOnTheField> cellsRightBottomLeft;
    private ArrayList<com.maltsev.labyrinth.model.field.PointOnTheField> cellsTopRightBottom;

    private List<com.maltsev.labyrinth.model.field.PointOnTheField> trees;
    private List<com.maltsev.labyrinth.model.field.PointOnTheField> grass;

    private com.maltsev.labyrinth.presenter.interfaces.IFieldDrawer fieldDrawer;

    /**
     * @param fieldDrawer отрисовщик
     * @param model ссылка на Model, для извлечения данных необходимых для отрисовки
     */
    GameFieldDrawer(com.maltsev.labyrinth.presenter.interfaces.IFieldDrawer fieldDrawer, com.maltsev.labyrinth.model.IModel model) {

        this.passableCells = model.getPassableCells();
        this.fieldDrawer = fieldDrawer;

        trees = model.getTrees();
        grass = model.getGrass();

        initializeTheArraysOfPassableCells();
    }

    /**
     * Отрисовка декоративных элементов
     */
    void drawDecoration(){

        for (com.maltsev.labyrinth.model.field.PointOnTheField point : grass) {

            fieldDrawer.drawGrass(TranslatorOfCoordinate.translatePointFieldToScreen(point));
        }

        for (com.maltsev.labyrinth.model.field.PointOnTheField point : trees) {

            fieldDrawer.drawTree(TranslatorOfCoordinate.translatePointFieldToScreen(point));
        }
    }

    /**
     * Выделение памяти и заполнение массивов проходимых ячеек
     */
    private void initializeTheArraysOfPassableCells() {

        cellsTopBottom = new ArrayList<>();
        cellsLeftRight = new ArrayList<>();

        cellsLeftTopRightBottom = new ArrayList<>();

        cellsLeftTop = new ArrayList<>();
        cellsRightTop = new ArrayList<>();
        cellsLeftBottom = new ArrayList<>();
        cellsRightBottom = new ArrayList<>();

        cellsBottom = new ArrayList<>();
        cellsTop = new ArrayList<>();
        cellsLeft = new ArrayList<>();
        cellsRight = new ArrayList<>();

        cellsLeftTopRight = new ArrayList<>();
        cellsBottomLeftTop = new ArrayList<>();
        cellsRightBottomLeft = new ArrayList<>();
        cellsTopRightBottom = new ArrayList<>();

        for (com.maltsev.labyrinth.model.field.PointOnTheField point : passableCells) {

            if (passableCells.contains(new com.maltsev.labyrinth.model.field.PointOnTheField(point.getX() + 1, point.getY())) &&
                    passableCells.contains(new com.maltsev.labyrinth.model.field.PointOnTheField(point.getX() - 1, point.getY())) &&
                    passableCells.contains(new com.maltsev.labyrinth.model.field.PointOnTheField(point.getX(), point.getY() + 1)) &&
                    passableCells.contains(new com.maltsev.labyrinth.model.field.PointOnTheField(point.getX(), point.getY() - 1))) {

                cellsLeftTopRightBottom.add(point);
                continue;
            }

            if (passableCells.contains(new com.maltsev.labyrinth.model.field.PointOnTheField(point.getX() + 1, point.getY())) &&
                    passableCells.contains(new com.maltsev.labyrinth.model.field.PointOnTheField(point.getX() - 1, point.getY())) &&
                    passableCells.contains(new com.maltsev.labyrinth.model.field.PointOnTheField(point.getX(), point.getY() + 1))) {

                cellsLeftTopRight.add(point);
                continue;
            }

            if (passableCells.contains(new com.maltsev.labyrinth.model.field.PointOnTheField(point.getX() - 1, point.getY())) &&
                    passableCells.contains(new com.maltsev.labyrinth.model.field.PointOnTheField(point.getX(), point.getY() + 1)) &&
                    passableCells.contains(new com.maltsev.labyrinth.model.field.PointOnTheField(point.getX(), point.getY() - 1))) {

                cellsBottomLeftTop.add(point);
                continue;
            }

            if (passableCells.contains(new com.maltsev.labyrinth.model.field.PointOnTheField(point.getX() + 1, point.getY())) &&
                    passableCells.contains(new com.maltsev.labyrinth.model.field.PointOnTheField(point.getX() - 1, point.getY())) &&
                    passableCells.contains(new com.maltsev.labyrinth.model.field.PointOnTheField(point.getX(), point.getY() - 1))) {

                cellsRightBottomLeft.add(point);
                continue;
            }

            if (passableCells.contains(new com.maltsev.labyrinth.model.field.PointOnTheField(point.getX() + 1, point.getY())) &&
                    passableCells.contains(new com.maltsev.labyrinth.model.field.PointOnTheField(point.getX(), point.getY() + 1)) &&
                    passableCells.contains(new com.maltsev.labyrinth.model.field.PointOnTheField(point.getX(), point.getY() - 1))) {

                cellsTopRightBottom.add(point);
                continue;
            }


            if (passableCells.contains(new com.maltsev.labyrinth.model.field.PointOnTheField(point.getX() + 1, point.getY())) &&
                    passableCells.contains(new com.maltsev.labyrinth.model.field.PointOnTheField(point.getX() - 1, point.getY()))){

                cellsLeftRight.add(point);
                continue;
            }

            if (passableCells.contains(new com.maltsev.labyrinth.model.field.PointOnTheField(point.getX(), point.getY() + 1)) &&
                    passableCells.contains(new com.maltsev.labyrinth.model.field.PointOnTheField(point.getX(), point.getY() - 1))){

                cellsTopBottom.add(point);
                continue;
            }


            if (passableCells.contains(new com.maltsev.labyrinth.model.field.PointOnTheField(point.getX() - 1, point.getY())) &&
                    passableCells.contains(new com.maltsev.labyrinth.model.field.PointOnTheField(point.getX(), point.getY() - 1))){

                cellsLeftBottom.add(point);
                continue;
            }


            if (passableCells.contains(new com.maltsev.labyrinth.model.field.PointOnTheField(point.getX() + 1, point.getY())) &&
                    passableCells.contains(new com.maltsev.labyrinth.model.field.PointOnTheField(point.getX(), point.getY() - 1))){

                cellsRightBottom.add(point);
                continue;
            }


            if (passableCells.contains(new com.maltsev.labyrinth.model.field.PointOnTheField(point.getX() - 1, point.getY())) &&
                    passableCells.contains(new com.maltsev.labyrinth.model.field.PointOnTheField(point.getX(), point.getY() + 1))){

                cellsLeftTop.add(point);
                continue;
            }


            if (passableCells.contains(new com.maltsev.labyrinth.model.field.PointOnTheField(point.getX() + 1, point.getY())) &&
                    passableCells.contains(new com.maltsev.labyrinth.model.field.PointOnTheField(point.getX(), point.getY() + 1))){

                cellsRightTop.add(point);
                continue;
            }

            if(passableCells.contains(new com.maltsev.labyrinth.model.field.PointOnTheField(point.getX() + 1, point.getY()))){

                cellsRight.add(point);
                continue;
            }

            if(passableCells.contains(new com.maltsev.labyrinth.model.field.PointOnTheField(point.getX() - 1, point.getY()))){

                cellsLeft.add(point);
                continue;
            }

            if(passableCells.contains(new com.maltsev.labyrinth.model.field.PointOnTheField(point.getX(), point.getY() + 1))){

                cellsTop.add(point);
                continue;
            }

            if(passableCells.contains(new com.maltsev.labyrinth.model.field.PointOnTheField(point.getX(), point.getY() - 1))){

                cellsBottom.add(point);
            }

        }
    }

    /**
     * Отрисовка проходимых ячеек
     */
    void drawPassableCells() {

        for (com.maltsev.labyrinth.model.field.PointOnTheField point : cellsLeftTopRightBottom)
            fieldDrawer.drawLeftTopRightBottomCells(TranslatorOfCoordinate.translatePointFieldToScreen(point));

        for (com.maltsev.labyrinth.model.field.PointOnTheField point : cellsTop)
            fieldDrawer.drawTopCells(TranslatorOfCoordinate.translatePointFieldToScreen(point));

        for (com.maltsev.labyrinth.model.field.PointOnTheField point : cellsRight)
            fieldDrawer.drawRightCells(TranslatorOfCoordinate.translatePointFieldToScreen(point));

        for (com.maltsev.labyrinth.model.field.PointOnTheField point : cellsLeft)
            fieldDrawer.drawLeftCells(TranslatorOfCoordinate.translatePointFieldToScreen(point));

        for (com.maltsev.labyrinth.model.field.PointOnTheField point : cellsBottom)
            fieldDrawer.drawBottomCells(TranslatorOfCoordinate.translatePointFieldToScreen(point));

        for (com.maltsev.labyrinth.model.field.PointOnTheField point : cellsLeftTop)
            fieldDrawer.drawLeftTopCells(TranslatorOfCoordinate.translatePointFieldToScreen(point));

        for (com.maltsev.labyrinth.model.field.PointOnTheField point : cellsRightBottom)
            fieldDrawer.drawRightBottomCells(TranslatorOfCoordinate.translatePointFieldToScreen(point));

        for (com.maltsev.labyrinth.model.field.PointOnTheField point : cellsLeftRight)
            fieldDrawer.drawLeftRightCells(TranslatorOfCoordinate.translatePointFieldToScreen(point));

        for (com.maltsev.labyrinth.model.field.PointOnTheField point : cellsLeftBottom)
            fieldDrawer.drawLeftBottomCells(TranslatorOfCoordinate.translatePointFieldToScreen(point));

        for (com.maltsev.labyrinth.model.field.PointOnTheField point : cellsRightTop)
            fieldDrawer.drawRightTopCells(TranslatorOfCoordinate.translatePointFieldToScreen(point));

        for (com.maltsev.labyrinth.model.field.PointOnTheField point : cellsTopBottom)
            fieldDrawer.drawTopBottomCells(TranslatorOfCoordinate.translatePointFieldToScreen(point));

        for (com.maltsev.labyrinth.model.field.PointOnTheField point : cellsLeftTopRight)
            fieldDrawer.drawLeftTopRightCells(TranslatorOfCoordinate.translatePointFieldToScreen(point));

        for (com.maltsev.labyrinth.model.field.PointOnTheField point : cellsBottomLeftTop)
            fieldDrawer.drawBottomLeftTopCells(TranslatorOfCoordinate.translatePointFieldToScreen(point));

        for (com.maltsev.labyrinth.model.field.PointOnTheField point : cellsRightBottomLeft)
            fieldDrawer.drawRightBottomLeftCells(TranslatorOfCoordinate.translatePointFieldToScreen(point));

        for (com.maltsev.labyrinth.model.field.PointOnTheField point : cellsTopRightBottom)
            fieldDrawer.drawTopRightBottomCells(TranslatorOfCoordinate.translatePointFieldToScreen(point));
    }
}
