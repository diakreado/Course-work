package com.maltsev.labyrinth.model.field;

/**
 *  Ячейка поля, содержит информацию, о том возможно ли на неё поместить протагониста или нет
 *  Если она является проходимой, может содержать на себе дверь, которую при необходимости разрешается открыть
 */
class CellOfField {

    /**
     * Поле хранящее информацию о проходимости клетки
     * Т.е. может ли протагонист пройти по ней
     */
    private boolean isThisCellPossibleForMove;

    /**
     * Есть ли дверь в клетке
     *
     * Дверь предполагает нечто, что блокирует проходимость клетки до того, как её откроют, на данный момент
     * закрытие двери обратно не предусмотрено
     */
    private boolean isItDoor = false;

    /**
     * Открыта ли дверь
     *
     * Когда дверь открыта она ведёт, то ячейка ведёт себя как проходимая
     * Когда дверь закрыта ячейка является непроходимой
     */
    private boolean doorIsOpen = false;

    /**
     * @return проходима ли клетка
     */
    boolean getInfoAboutPatencyOfCell() {

        if(!isItDoor)
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
     *
     * Дверь можно установить только в проходимую ячейку
     */
    void createDoor() {

        if(isThisCellPossibleForMove)
            isItDoor = true;
    }

    /**
     * Открытие двери
     *
     * Возможно если дверь там была, иначе никакой реакции
     */
    void openDoor() {

        if(isItDoor)
            doorIsOpen = true;
    }

    /**
     * @return находится ли на данной ячейке дверь
     */
    boolean isItDoor() {

        return isItDoor;
    }
}