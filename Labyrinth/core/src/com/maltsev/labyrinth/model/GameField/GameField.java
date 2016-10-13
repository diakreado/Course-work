package com.maltsev.labyrinth.model.GameField;


import java.util.ArrayList;

public class GameField {

    private ArrayList< ArrayList< CellOfField>> matrixOfCell;

    GameField() {

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

    public boolean isItPossibleWay(int x, int y) {

        ArrayList< CellOfField> arrayOfCell = matrixOfCell.get(x);

        CellOfField cellOfField = arrayOfCell.get(y);

        return cellOfField.isItPossibleWay;
    }
}
