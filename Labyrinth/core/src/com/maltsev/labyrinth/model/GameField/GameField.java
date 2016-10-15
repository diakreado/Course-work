package com.maltsev.labyrinth.model.GameField;

import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;

/**
 *  Класс отвечающий за описание игрового поля
 */
public class GameField {

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

    private ArrayList<Vector2> passableCells;


    public GameField() {

        this(newField);
    }

    public GameField(int newField[][]) {

        passableCells = new ArrayList<Vector2>();
        matrixOfCell = new ArrayList<ArrayList<CellOfField>>();

        for(int x = 0; x < newField.length; x++) {

            ArrayList< CellOfField> arrayOfCell = new ArrayList<CellOfField>();

            for(int y = 0; y < newField[newField.length - 1].length; y++) {

                boolean isItPossibleWay = false;
                if (newField[x][y] == 0) {

                    isItPossibleWay = true;

                    passableCells.add(new Vector2(x,y));
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

    public ArrayList<Vector2> getPassableCells() {

        return passableCells;
    }
}
