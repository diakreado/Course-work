package com.maltsev.labyrinth.model.field;


/**
 *  Ячейка поля, содержит информацию, о том возможно ли на неё встать или нет
 */
class CellOfField {

    /**
     * Хранит информацию о проходимости клетки
     */
    private boolean isItPossibleWay;

    /**
     * @return проходима ли клетка
     */
    public boolean getInfoAboutPatencyOfCell() {

        return isItPossibleWay;
    }

    /**
     * Стандартный конструктор
     * @param isItPossibleWay является ли эта ячейка проходимой
     */
    CellOfField(boolean isItPossibleWay) {

        this.isItPossibleWay = isItPossibleWay;
    }
}