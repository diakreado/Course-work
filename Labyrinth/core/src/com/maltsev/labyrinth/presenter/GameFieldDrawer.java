package com.maltsev.labyrinth.presenter;


import com.maltsev.labyrinth.model.field.PointOnTheField;
import com.maltsev.labyrinth.presenter.transkatorofcoordinate.TranslatorOfCoordinate;

import java.util.ArrayList;
import java.util.List;

/**
 * Отвечает за логику отрисовки игрового мира, проходимые ячейки и декорации
 */
class GameFieldDrawer {

    private List<PointOnTheField> passableCells;

    private ArrayList<PointOnTheField> cellsTopBottom;
    private ArrayList<PointOnTheField> cellsLeftRight;
    private ArrayList<PointOnTheField> cellsLeftTopRightBottom;

    private ArrayList<PointOnTheField> cellsLeftTop;
    private ArrayList<PointOnTheField> cellsRightTop;
    private ArrayList<PointOnTheField> cellsLeftBottom;
    private ArrayList<PointOnTheField> cellsRightBottom;

    private ArrayList<PointOnTheField> cellsBottom;
    private ArrayList<PointOnTheField> cellsTop;
    private ArrayList<PointOnTheField> cellsLeft;
    private ArrayList<PointOnTheField> cellsRight;

    private ArrayList<PointOnTheField> cellsLeftTopRight;
    private ArrayList<PointOnTheField> cellsBottomLeftTop;
    private ArrayList<PointOnTheField> cellsRightBottomLeft;
    private ArrayList<PointOnTheField> cellsTopRightBottom;

    private List<PointOnTheField> trees;
    private List<PointOnTheField> grass;

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
     * Drawing decoration cells
     */
    void drawDecoration(){
        for (PointOnTheField point : grass) {
            fieldDrawer.drawGrass(TranslatorOfCoordinate.translatePointFieldToScreen(point));
        }
//        for (PointOnTheField point : trees) {
//            fieldDrawer.drawTree(TranslatorOfCoordinate.translatePointFieldToScreen(point));
//        }
        for (int i = trees.size() - 1; i > 0; i--) {
            fieldDrawer.drawTree(TranslatorOfCoordinate.translatePointFieldToScreen(trees.get(i)));
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

        for (PointOnTheField point : passableCells) {

            if (passableCells.contains(new PointOnTheField(point.getX() + 1, point.getY())) &&
                    passableCells.contains(new PointOnTheField(point.getX() - 1, point.getY())) &&
                    passableCells.contains(new PointOnTheField(point.getX(), point.getY() + 1)) &&
                    passableCells.contains(new PointOnTheField(point.getX(), point.getY() - 1))) {

                cellsLeftTopRightBottom.add(point);
                continue;
            }

            if (passableCells.contains(new PointOnTheField(point.getX() + 1, point.getY())) &&
                    passableCells.contains(new PointOnTheField(point.getX() - 1, point.getY())) &&
                    passableCells.contains(new PointOnTheField(point.getX(), point.getY() + 1))) {

                cellsLeftTopRight.add(point);
                continue;
            }

            if (passableCells.contains(new PointOnTheField(point.getX() - 1, point.getY())) &&
                    passableCells.contains(new PointOnTheField(point.getX(), point.getY() + 1)) &&
                    passableCells.contains(new PointOnTheField(point.getX(), point.getY() - 1))) {

                cellsBottomLeftTop.add(point);
                continue;
            }

            if (passableCells.contains(new PointOnTheField(point.getX() + 1, point.getY())) &&
                    passableCells.contains(new PointOnTheField(point.getX() - 1, point.getY())) &&
                    passableCells.contains(new PointOnTheField(point.getX(), point.getY() - 1))) {

                cellsRightBottomLeft.add(point);
                continue;
            }

            if (passableCells.contains(new PointOnTheField(point.getX() + 1, point.getY())) &&
                    passableCells.contains(new PointOnTheField(point.getX(), point.getY() + 1)) &&
                    passableCells.contains(new PointOnTheField(point.getX(), point.getY() - 1))) {

                cellsTopRightBottom.add(point);
                continue;
            }


            if (passableCells.contains(new PointOnTheField(point.getX() + 1, point.getY())) &&
                    passableCells.contains(new PointOnTheField(point.getX() - 1, point.getY()))){

                cellsLeftRight.add(point);
                continue;
            }

            if (passableCells.contains(new PointOnTheField(point.getX(), point.getY() + 1)) &&
                    passableCells.contains(new PointOnTheField(point.getX(), point.getY() - 1))){

                cellsTopBottom.add(point);
                continue;
            }


            if (passableCells.contains(new PointOnTheField(point.getX() - 1, point.getY())) &&
                    passableCells.contains(new PointOnTheField(point.getX(), point.getY() - 1))){

                cellsLeftBottom.add(point);
                continue;
            }


            if (passableCells.contains(new PointOnTheField(point.getX() + 1, point.getY())) &&
                    passableCells.contains(new PointOnTheField(point.getX(), point.getY() - 1))){

                cellsRightBottom.add(point);
                continue;
            }


            if (passableCells.contains(new PointOnTheField(point.getX() - 1, point.getY())) &&
                    passableCells.contains(new PointOnTheField(point.getX(), point.getY() + 1))){

                cellsLeftTop.add(point);
                continue;
            }


            if (passableCells.contains(new PointOnTheField(point.getX() + 1, point.getY())) &&
                    passableCells.contains(new PointOnTheField(point.getX(), point.getY() + 1))){

                cellsRightTop.add(point);
                continue;
            }

            if(passableCells.contains(new PointOnTheField(point.getX() + 1, point.getY()))){

                cellsRight.add(point);
                continue;
            }

            if(passableCells.contains(new PointOnTheField(point.getX() - 1, point.getY()))){

                cellsLeft.add(point);
                continue;
            }

            if(passableCells.contains(new PointOnTheField(point.getX(), point.getY() + 1))){

                cellsTop.add(point);
                continue;
            }

            if(passableCells.contains(new PointOnTheField(point.getX(), point.getY() - 1))){

                cellsBottom.add(point);
            }

        }
    }

    /**
     * Drawing passable cells
     */
    void drawPassableCells() {

        for (PointOnTheField point : cellsLeftTopRightBottom)
            fieldDrawer.drawLeftTopRightBottomCells(TranslatorOfCoordinate.translatePointFieldToScreen(point));

        for (PointOnTheField point : cellsTop)
            fieldDrawer.drawTopCells(TranslatorOfCoordinate.translatePointFieldToScreen(point));

        for (PointOnTheField point : cellsRight)
            fieldDrawer.drawRightCells(TranslatorOfCoordinate.translatePointFieldToScreen(point));

        for (PointOnTheField point : cellsLeft)
            fieldDrawer.drawLeftCells(TranslatorOfCoordinate.translatePointFieldToScreen(point));

        for (PointOnTheField point : cellsBottom)
            fieldDrawer.drawBottomCells(TranslatorOfCoordinate.translatePointFieldToScreen(point));

        for (PointOnTheField point : cellsLeftTop)
            fieldDrawer.drawLeftTopCells(TranslatorOfCoordinate.translatePointFieldToScreen(point));

        for (PointOnTheField point : cellsRightBottom)
            fieldDrawer.drawRightBottomCells(TranslatorOfCoordinate.translatePointFieldToScreen(point));

        for (PointOnTheField point : cellsLeftRight)
            fieldDrawer.drawLeftRightCells(TranslatorOfCoordinate.translatePointFieldToScreen(point));

        for (PointOnTheField point : cellsLeftBottom)
            fieldDrawer.drawLeftBottomCells(TranslatorOfCoordinate.translatePointFieldToScreen(point));

        for (PointOnTheField point : cellsRightTop)
            fieldDrawer.drawRightTopCells(TranslatorOfCoordinate.translatePointFieldToScreen(point));

        for (PointOnTheField point : cellsTopBottom)
            fieldDrawer.drawTopBottomCells(TranslatorOfCoordinate.translatePointFieldToScreen(point));

        for (PointOnTheField point : cellsLeftTopRight)
            fieldDrawer.drawLeftTopRightCells(TranslatorOfCoordinate.translatePointFieldToScreen(point));

        for (PointOnTheField point : cellsBottomLeftTop)
            fieldDrawer.drawBottomLeftTopCells(TranslatorOfCoordinate.translatePointFieldToScreen(point));

        for (PointOnTheField point : cellsRightBottomLeft)
            fieldDrawer.drawRightBottomLeftCells(TranslatorOfCoordinate.translatePointFieldToScreen(point));

        for (PointOnTheField point : cellsTopRightBottom)
            fieldDrawer.drawTopRightBottomCells(TranslatorOfCoordinate.translatePointFieldToScreen(point));
    }
}
