package com.maltsev.labyrinth.model.field;

//Странный класс. По сути же это boolean
//TODO это сделано для расшииряемости

/**
 *  Ячейка поля, содержит информацию, о том возможно ли на неё поместить протагониста или нет
 */
class CellOfField {

    /**
     * Поле хранящее информацию о проходимости клетки
     */
    private boolean isThisCellPossibleForMove;

    /**
     * Есть ли дверь в клетке
     */
    private boolean isDoor = false;

    /**
     * Открыта ли дверь
     */
    private boolean doorIsOpen = false;

    /**
     * @return проходима ли клетка
     */
    boolean getInfoAboutPatencyOfCell() {

        if(!isDoor)
            return isThisCellPossibleForMove;
        else
            return doorIsOpen;
    }

    /**
     * @param isThisCellPossibleForMove является ли эта ячейка проходимой
     */
    CellOfField(final boolean isThisCellPossibleForMove) {

        this.isThisCellPossibleForMove = isThisCellPossibleForMove;
    }

    /**
     * Установить дверь в клетку
     */
    void createDoor() {

        if(isThisCellPossibleForMove)
            isDoor = true;
    }

    /**
     * Открытие двери
     */
    void openDoor() {

        if(isDoor)
            doorIsOpen = true;
    }
}