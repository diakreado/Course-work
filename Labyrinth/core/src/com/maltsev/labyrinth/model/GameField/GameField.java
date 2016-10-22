package com.maltsev.labyrinth.model.GameField;

import java.util.ArrayList;

/**
 *  Класс отвечающий за описание игрового поля
 */
public class GameField {                   //TODO рассмотреть движение протагониста по полю, т.е. определять сможет он дойти до точки или нет

    /**
     *  Использутся только для конструктора по умолчанию
     */
    static final int newField[][] = {{0,1,1,0,0,0},
                                     {0,0,1,0,1,1},
                                     {1,0,1,0,0,0},
                                     {0,0,1,1,1,0},
                                     {0,1,1,1,0,0},
                                     {0,1,0,0,0,1},
                                     {0,1,0,1,1,1},
                                     {0,1,0,0,0,0},
                                     {0,1,1,1,1,0},
                                     {0,0,0,0,0,0}};
    /**
     *  Матрица ячеек, т.е. само поле
     */
    public ArrayList< ArrayList< CellOfField>> matrixOfCell;

    private ArrayList<PointOnTheField> passableCells;




    public GameField() {

        this(newField);
    }

    /**
     * @param newField - объязательно матрица, где 0 - проходимый элемент, а 1 - нет
     */
    public GameField(int newField[][]) {

        passableCells = new ArrayList<PointOnTheField>();
        matrixOfCell = new ArrayList<ArrayList<CellOfField>>();

        for(int x = 0; x < newField.length; x++) {

            ArrayList< CellOfField> arrayOfCell = new ArrayList<CellOfField>();

            for(int y = 0; y < newField[newField.length - 1].length; y++) {

                boolean isItPossibleWay = false;
                if (newField[x][y] == 0) {

                    isItPossibleWay = true;

                    passableCells.add(new PointOnTheField(x,y));
                }

                arrayOfCell.add(new CellOfField(isItPossibleWay));
            }

            matrixOfCell.add(arrayOfCell);
        }

    }

    /**
     * @param x - координата точки по оси X
     * @param y - координата точки по оси Y
     * @return является ли эта ячейка проходимой
     */
    public boolean isItPossibleWay(int x, int y) {

        return matrixOfCell.get(x).get(y).isItPossibleWay;
    }

    public int getSizeOfFieldX() {

        return matrixOfCell.size();
    }

    public int getSizeOfFieldY() {

        return matrixOfCell.get(0).size();
    }

    public ArrayList<PointOnTheField> getPassableCells() {

        return passableCells;
    }
}
