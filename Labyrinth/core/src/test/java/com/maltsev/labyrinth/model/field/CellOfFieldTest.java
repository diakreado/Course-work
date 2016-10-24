package com.maltsev.labyrinth.model.field;

import org.junit.Test;

import static org.junit.Assert.*;


public class CellOfFieldTest {

    @Test
    public void createCellWithSetPatencyAndgetInfoAboutIt() throws Exception {

        CellOfField firstCell = new CellOfField(true);
        CellOfField secondCell = new CellOfField(false);

        assertTrue(firstCell.getInfoAboutPatencyOfCell());
        assertFalse(secondCell.getInfoAboutPatencyOfCell());
    }

}