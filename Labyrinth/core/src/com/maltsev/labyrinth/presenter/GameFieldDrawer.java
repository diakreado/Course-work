package com.maltsev.labyrinth.presenter;


import com.maltsev.labyrinth.model.IModel;
import com.maltsev.labyrinth.model.field.PointOnTheField;
import com.maltsev.labyrinth.presenter.interfaces.IFieldDrawer;
import static com.maltsev.labyrinth.presenter.transkatorofcoordinate.TranslatorOfCoordinate.translatePointFieldToScreen;

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

    private IFieldDrawer fieldDrawer;

    /**
     * @param fieldDrawer отрисовщик
     * @param model ссылка на Model, для извлечения данных необходимых для отрисовки
     */
    GameFieldDrawer(IFieldDrawer fieldDrawer, IModel model) {

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

        for (PointOnTheField point : grass) {

            fieldDrawer.drawGrass(translatePointFieldToScreen(point));
        }

        for (PointOnTheField point : trees) {

            fieldDrawer.drawTree(translatePointFieldToScreen(point));
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
     * Отрисовка проходимых ячеек
     */
    void drawPassableCells() {

        for (PointOnTheField point : cellsLeftTopRightBottom)
            fieldDrawer.drawLeftTopRightBottomCells(translatePointFieldToScreen(point));

        for (PointOnTheField point : cellsTop)
            fieldDrawer.drawTopCells(translatePointFieldToScreen(point));

        for (PointOnTheField point : cellsRight)
            fieldDrawer.drawRightCells(translatePointFieldToScreen(point));

        for (PointOnTheField point : cellsLeft)
            fieldDrawer.drawLeftCells(translatePointFieldToScreen(point));

        for (PointOnTheField point : cellsBottom)
            fieldDrawer.drawBottomCells(translatePointFieldToScreen(point));

        for (PointOnTheField point : cellsLeftTop)
            fieldDrawer.drawLeftTopCells(translatePointFieldToScreen(point));

        for (PointOnTheField point : cellsRightBottom)
            fieldDrawer.drawRightBottomCells(translatePointFieldToScreen(point));

        for (PointOnTheField point : cellsLeftRight)
            fieldDrawer.drawLeftRightCells(translatePointFieldToScreen(point));

        for (PointOnTheField point : cellsLeftBottom)
            fieldDrawer.drawLeftBottomCells(translatePointFieldToScreen(point));

        for (PointOnTheField point : cellsRightTop)
            fieldDrawer.drawRightTopCells(translatePointFieldToScreen(point));

        for (PointOnTheField point : cellsTopBottom)
            fieldDrawer.drawTopBottomCells(translatePointFieldToScreen(point));

        for (PointOnTheField point : cellsLeftTopRight)
            fieldDrawer.drawLeftTopRightCells(translatePointFieldToScreen(point));

        for (PointOnTheField point : cellsBottomLeftTop)
            fieldDrawer.drawBottomLeftTopCells(translatePointFieldToScreen(point));

        for (PointOnTheField point : cellsRightBottomLeft)
            fieldDrawer.drawRightBottomLeftCells(translatePointFieldToScreen(point));

        for (PointOnTheField point : cellsTopRightBottom)
            fieldDrawer.drawTopRightBottomCells(translatePointFieldToScreen(point));
    }
}
