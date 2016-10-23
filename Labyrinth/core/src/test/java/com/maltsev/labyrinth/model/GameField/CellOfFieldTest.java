package com.maltsev.labyrinth.model.GameField;

import org.junit.Test;

import static org.junit.Assert.*;


public class CellOfFieldTest {

    @Test
    public void getInfoAboutPatencyOfCell() throws Exception {

        CellOfField firstCell = new CellOfField(true);
        CellOfField secondCell = new CellOfField(false);

        assertEquals(firstCell.getInfoAboutPatencyOfCell() ,true);
        assertEquals(secondCell.getInfoAboutPatencyOfCell() ,false);
    }

}