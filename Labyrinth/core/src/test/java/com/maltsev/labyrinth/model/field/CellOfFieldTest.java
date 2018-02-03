package com.maltsev.labyrinth.model.field;


import org.junit.Test;

import static org.junit.Assert.*;


public class CellOfFieldTest {

    /**
     * Проверка правильности создания ячейки
     * @throws Exception
     */
    @Test
    public void createCell() throws Exception {

        CellOfField firstCell = new CellOfField(true);
        CellOfField secondCell = new CellOfField(false);

        assertTrue(firstCell.getInfoAboutPatencyOfCell());
        assertFalse(secondCell.getInfoAboutPatencyOfCell());
    }

    /**
     * Проверка на то, как на ячейке создаётся и открывается дверь
     * @throws Exception
     */
    @Test
    public void doorsOnCell() throws Exception {

        CellOfField firstCell = new CellOfField(true);
        CellOfField secondCell = new CellOfField(true);
        CellOfField thirdCell = new CellOfField(false);

        firstCell.createDoor();
        thirdCell.createDoor();

        assertTrue(firstCell.isItDoor());
        assertFalse(secondCell.isItDoor());     // Установленны ли на ячейках двери
        assertFalse(thirdCell.isItDoor());


        assertFalse(firstCell.getInfoAboutPatencyOfCell());
        assertTrue(secondCell.getInfoAboutPatencyOfCell());   // Проходимы ли ячейки
        assertFalse(thirdCell.getInfoAboutPatencyOfCell());

        firstCell.openDoor();
        firstCell.openDoor();      // Пытаемся всё открыть
        firstCell.openDoor();

        assertTrue(firstCell.isItDoor());
        assertFalse(secondCell.isItDoor());  // Дверь должны остаться дверьми
        assertFalse(thirdCell.isItDoor());

        assertTrue(firstCell.getInfoAboutPatencyOfCell());   // Должна изменится проходимость первой ячейки
        assertTrue(secondCell.getInfoAboutPatencyOfCell());
        assertFalse(thirdCell.getInfoAboutPatencyOfCell());
    }
}