package com.maltsev.labyrinth.model.GameField;

import java.util.ArrayList;

/**
 *  Игровое поле
 */
public class GameField {

    /**
     *  Матрица ячеек, т.е. само поле
     */
    private ArrayList< ArrayList< CellOfField>> matrixOfCell;

    /**
     *  Массив координат ячеек, которые являются проходимыми
     */
    private ArrayList<PointOnTheField> passableCells;

    /**
     *  Размер поля по X
     */
    private int sizeOfFieldX;

    /**
     *  Размер поля по Y
     */
    private int sizeOfFieldY;


    /**
     * Конструктор, в которм задаётся игровое поле
     * @param newField - объязательно матрица, где 0 - проходимый элемент, а 1 - нет
     */
    public GameField(int newField[][]) {

        passableCells = new ArrayList<PointOnTheField>();
        matrixOfCell = new ArrayList<ArrayList<CellOfField>>();

        int lengthX = newField.length;
        int lengthY = newField[newField.length - 1].length;

        for(int x = 0; x < lengthX; x++) {                  // Проверка на то, чтобы матрица была прямоугольной,
                                                           // если это не так, то шириной берётся минимальное значение
            if (lengthY != newField[x].length) {          // остальное отбрасывается

                if (lengthY > newField[x].length) {

                    lengthY = newField[x].length;
                }
            }
        }

        for(int x = 0; x < lengthX; x++) {

            ArrayList< CellOfField> arrayOfCell = new ArrayList<CellOfField>();

            for(int y = 0; y < lengthY; y++) {

                boolean isItPossibleWay = false;
                if (newField[x][y] == 0) {

                    isItPossibleWay = true;

                    passableCells.add(new PointOnTheField(x,y));
                }
                arrayOfCell.add(new CellOfField(isItPossibleWay));
            }
            matrixOfCell.add(arrayOfCell);
        }
        setTheValuesOfSizeOfField();
    }

    /**
     *  Фиксирование размера поля
     */
    private void setTheValuesOfSizeOfField() {

        sizeOfFieldX = matrixOfCell.size();
        sizeOfFieldY = matrixOfCell.get(0).size();
    }

    /**
     * @param x - координата точки по оси X
     * @param y - координата точки по оси Y
     * @return является ли эта ячейка проходимой
     * @throws OutOfBoundaryOfTheField - воход за граниуц поля
     */
    public boolean isItPossibleWay(int x, int y) throws OutOfBoundaryOfTheField {

        if (x < 0 || x >= sizeOfFieldX) throw new OutOfBoundaryOfTheField("x", x, sizeOfFieldX - 1);
        if (y < 0 || y >= sizeOfFieldY) throw new OutOfBoundaryOfTheField("y", y, sizeOfFieldY - 1);

        return matrixOfCell.get(x).get(y).getInfoAboutPatencyOfCell();
    }

    /**
     * @return размер поля по оси X
     */
    public int getSizeOfFieldX() {

        return sizeOfFieldX;
    }

    /**
     * @return размер поля по оси Y
     */
    public int getSizeOfFieldY() {

        return sizeOfFieldY;
    }

    /**
     * @return Массив координат проходимых ячеек
     */
    public ArrayList<PointOnTheField> getPassableCells() {

        return passableCells;
    }
}