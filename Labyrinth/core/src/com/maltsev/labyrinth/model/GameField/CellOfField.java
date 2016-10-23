package com.maltsev.labyrinth.model.GameField;


/**
 *  Ячейка поля, содержит информацию, о том возможно ли на неё встать или нет
 */
class CellOfField {

    /**
     *  Хранит информацию о проходимости клетки
     */
    private boolean isItPossibleWay;

    /**
     * @return проходим ли клетка
     */
    public boolean getInfoAboutPatencyOfCell() {

        return isItPossibleWay;
    }

    /**
     * @param - является ли эта ячейка проходимой
     */
    CellOfField(boolean isItPossibleWay) {

        this.isItPossibleWay = isItPossibleWay;
    }
}