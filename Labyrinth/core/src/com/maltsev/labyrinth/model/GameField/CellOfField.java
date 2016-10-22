package com.maltsev.labyrinth.model.GameField;



class CellOfField {

    /**
     *  Не все ячейки пригодны для помещения в них игрока
     */
    public  boolean isItPossibleWay;

    /**
     * @param - является ли эта ячейка проходимой
     */
    CellOfField(boolean isItPossibleWay) {

        this.isItPossibleWay = isItPossibleWay;
    }
}
