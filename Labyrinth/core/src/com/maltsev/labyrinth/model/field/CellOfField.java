package com.maltsev.labyrinth.model.field;


/**
 *  Ячейка поля, содержит информацию, о том возможно ли на неё поместить протагониста или нет
 */
class CellOfField {

    /**
     * Поле хранящее информацию о проходимости клетки
     */
    private boolean isThisCellPossibleForMove;

    /**
     * @return проходима ли клетка
     */
    public boolean getInfoAboutPatencyOfCell() {

        return isThisCellPossibleForMove;
    }

    /**
     * @param isThisCellPossibleForMove является ли эта ячейка проходимой
     */
    CellOfField(boolean isThisCellPossibleForMove) {

        this.isThisCellPossibleForMove = isThisCellPossibleForMove;
    }
}