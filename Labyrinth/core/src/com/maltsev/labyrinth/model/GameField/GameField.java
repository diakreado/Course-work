package com.maltsev.labyrinth.model.GameField;


import java.util.ArrayList;

/**
 *  Класс отвечающий за описание игрового поля
 */
public class GameField {

    /**
     *  Матрица ячеек, т.е. само поле
     */
    public ArrayList< ArrayList< CellOfField>> matrixOfCell;

    /**
     *  Конструктор по умолчанию, создаёт поле 5*5, где точки, по которым можно ходить имеют индексы
     *  либо y = 0, либо x = 4
     */
    public GameField() {

        matrixOfCell = new ArrayList<ArrayList<CellOfField>>();

        for(int x = 0; x < 5; x++) {

            ArrayList< CellOfField> arrayOfCell = new ArrayList<CellOfField>();

            for(int y = 0; y < 5; y++) {

                boolean isItPossibleWay = false;
                if (y == 0 || x == 4) isItPossibleWay = true;

                arrayOfCell.add(new CellOfField(isItPossibleWay));
            }

            matrixOfCell.add(arrayOfCell);
        }
    }

    /**
     *
     * @param x - координата точки по оси X
     * @param y - координата точки по оси Y
     * @return является ли эта точка проходимой
     */
    public boolean isItPossibleWay(int x, int y) {

        ArrayList< CellOfField> arrayOfCell = matrixOfCell.get(x);

        CellOfField cellOfField = arrayOfCell.get(y);

        return cellOfField.isItPossibleWay;
    }

    /**
     *
     * @param point - точка, информация о которой требуется
     * @return информация о точке
     */
    public boolean isItPossibleWay(PointOnTheField point) {

        ArrayList< CellOfField> arrayOfCell = matrixOfCell.get(point.x);

        CellOfField cellOfField = arrayOfCell.get(point.y);

        return cellOfField.isItPossibleWay;
    }
}
