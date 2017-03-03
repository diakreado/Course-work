package com.maltsev.labyrinth.presenter;


import com.maltsev.labyrinth.model.field.PointOnTheField;
import com.maltsev.labyrinth.presenter.interfaces.View;

import java.util.ArrayList;
import java.util.List;


class PassableCellsDrawer {

    private ArrayList<PointOnTheField> cellsVertical;
    private ArrayList<PointOnTheField> cellsHorizontal;

    private ArrayList<PointOnTheField> cellsCenter;

    private ArrayList<PointOnTheField> cellsLeftTop;
    private ArrayList<PointOnTheField> cellsRightTop;
    private ArrayList<PointOnTheField> cellsLeftBottom;
    private ArrayList<PointOnTheField> cellsRightBottom;

    private ArrayList<PointOnTheField> cellsEndTop;
    private ArrayList<PointOnTheField> cellsEndBottom;
    private ArrayList<PointOnTheField> cellsEndRight;
    private ArrayList<PointOnTheField> cellsEndLeft;

    private ArrayList<PointOnTheField> cellsLeftTopRight;
    private ArrayList<PointOnTheField> cellsBottomLeftTop;
    private ArrayList<PointOnTheField> cellsRightBottomLeft;
    private ArrayList<PointOnTheField> cellsTopRightBottom;

    private View view;
    private Presenter presenter;
    private List<PointOnTheField> passableCells;

    PassableCellsDrawer(View view, Presenter presenter) {

        this.view = view;
        this.presenter = presenter;
        this.passableCells = presenter.getPassableCells();

        cellsVertical = new ArrayList<>();
        cellsHorizontal = new ArrayList<>();

        cellsCenter = new ArrayList<>();

        cellsLeftTop = new ArrayList<>();
        cellsRightTop = new ArrayList<>();
        cellsLeftBottom = new ArrayList<>();
        cellsRightBottom = new ArrayList<>();

        cellsEndTop = new ArrayList<>();
        cellsEndBottom = new ArrayList<>();
        cellsEndRight = new ArrayList<>();
        cellsEndLeft = new ArrayList<>();

        cellsLeftTopRight = new ArrayList<>();
        cellsBottomLeftTop = new ArrayList<>();
        cellsRightBottomLeft = new ArrayList<>();
        cellsTopRightBottom = new ArrayList<>();

        for (PointOnTheField point : passableCells) {

            if (passableCells.contains(new PointOnTheField(point.getX() + 1, point.getY())) &&
                    passableCells.contains(new PointOnTheField(point.getX() - 1, point.getY())) &&
                    passableCells.contains(new PointOnTheField(point.getX(), point.getY() + 1)) &&
                    passableCells.contains(new PointOnTheField(point.getX(), point.getY() - 1))) {

                cellsCenter.add(point);
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

                cellsHorizontal.add(point);
                continue;
            }

            if (passableCells.contains(new PointOnTheField(point.getX(), point.getY() + 1)) &&
                    passableCells.contains(new PointOnTheField(point.getX(), point.getY() - 1))){

                cellsVertical.add(point);
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

                cellsEndLeft.add(point);
                continue;
            }

            if(passableCells.contains(new PointOnTheField(point.getX() - 1, point.getY()))){

                cellsEndRight.add(point);
                continue;
            }

            if(passableCells.contains(new PointOnTheField(point.getX(), point.getY() + 1))){

                cellsEndBottom.add(point);
                continue;
            }

            if(passableCells.contains(new PointOnTheField(point.getX(), point.getY() - 1))){

                cellsEndTop.add(point);
            }

        }

        // Проверка, что всё поле покрыто
//        System.out.println(passableCells.size());
//        System.out.println(cellsCenter.size() + cellsEndBottom.size() + cellsEndLeft.size()
//                + cellsEndRight.size() + cellsEndTop.size() + cellsHorizontal.size() + cellsLeftBottom.size()
//                + cellsLeftTop.size() + cellsRightBottom.size() + cellsRightTop.size() + cellsVertical.size());
    }

    void drawCells() {

        for (PointOnTheField point : cellsCenter) {

            view.drawCenterCells(presenter.translatePointFieldToScreen(point));
        }

        for (PointOnTheField point : cellsEndBottom) {

            view.drawBottomEndCells(presenter.translatePointFieldToScreen(point));
        }

        for (PointOnTheField point : cellsEndLeft) {

            view.drawLeftEndCells(presenter.translatePointFieldToScreen(point));
        }

        for (PointOnTheField point : cellsEndRight) {

            view.drawRightEndCells(presenter.translatePointFieldToScreen(point));
        }

        for (PointOnTheField point : cellsEndTop) {

            view.drawTopEndCells(presenter.translatePointFieldToScreen(point));
        }

        for (PointOnTheField point : cellsLeftTop) {

            view.drawLeftTopCells(presenter.translatePointFieldToScreen(point));
        }

        for (PointOnTheField point : cellsRightBottom) {

            view.drawRightBottomCells(presenter.translatePointFieldToScreen(point));
        }

        for (PointOnTheField point : cellsHorizontal) {

            view.drawHorizontalCells(presenter.translatePointFieldToScreen(point));
        }

        for (PointOnTheField point : cellsLeftBottom) {

            view.drawLeftBottomCells(presenter.translatePointFieldToScreen(point));
        }

        for (PointOnTheField point : cellsRightTop) {

            view.drawRightTopCells(presenter.translatePointFieldToScreen(point));
        }

        for (PointOnTheField point : cellsVertical) {

            view.drawBlock(presenter.translatePointFieldToScreen(point));
        }

        for (PointOnTheField point : cellsLeftTopRight) {

            view.drawLeftTopRightCells(presenter.translatePointFieldToScreen(point));
        }

        for (PointOnTheField point : cellsBottomLeftTop) {

            view.drawBottomLeftTopCells(presenter.translatePointFieldToScreen(point));
        }

        for (PointOnTheField point : cellsRightBottomLeft) {

            view.drawRightBottomLeftCells(presenter.translatePointFieldToScreen(point));
        }

        for (PointOnTheField point : cellsTopRightBottom) {

            view.drawTopRightBottomCells(presenter.translatePointFieldToScreen(point));
        }
    }
}
