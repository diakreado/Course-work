package com.maltsev.labyrinth.model.field;


import org.junit.Test;

import static org.junit.Assert.*;


public class CellOfFieldTest {

    @Test
    public void createCell() throws Exception {

        CellOfField firstCell = new CellOfField(true);
        CellOfField secondCell = new CellOfField(false);

        assertTrue(firstCell.getInfoAboutPatencyOfCell());
        assertFalse(secondCell.getInfoAboutPatencyOfCell());
    }

    @Test
    public void doorsOnCell() throws Exception {

        CellOfField firstCell = new CellOfField(true);
        CellOfField secondCell = new CellOfField(false);

        firstCell.createDoor();
        secondCell.createDoor();

        assertFalse(firstCell.getInfoAboutPatencyOfCell());
        assertFalse(secondCell.getInfoAboutPatencyOfCell());

        firstCell.openDoor();

        assertTrue(firstCell.getInfoAboutPatencyOfCell());
    }
}