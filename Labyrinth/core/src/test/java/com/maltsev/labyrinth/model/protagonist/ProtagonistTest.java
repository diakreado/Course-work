package com.maltsev.labyrinth.model.protagonist;

import com.maltsev.labyrinth.model.field.PointOnTheField;
import org.junit.Test;

import static org.junit.Assert.*;


public class ProtagonistTest {

    @Test
    public void movesProtagonist() throws Exception {

        Protagonist protagonist = new Protagonist(1,1);

        PointOnTheField firstPoint = new PointOnTheField(53,86);
        protagonist.movesOfProtagonist(firstPoint);

        assertTrue(firstPoint.equals(protagonist.getLocationOfProtagonist()));

        PointOnTheField secondPoint = new PointOnTheField(10,3);
        protagonist.movesOfProtagonist(10,3);

        assertTrue(secondPoint.equals(protagonist.getLocationOfProtagonist()));

        PointOnTheField thirdPoint = new PointOnTheField(34,19);
        protagonist.movesOfProtagonist(5,5);

        assertFalse(thirdPoint.equals(protagonist.getLocationOfProtagonist()));
    }

    @Test
    public void createProtagonistAndCheckHisLocation() throws Exception {

        Protagonist protagonist = new Protagonist(0,0);
        PointOnTheField startPoint = new PointOnTheField(0,0);

        assertTrue(startPoint.equals(protagonist.getLocationOfProtagonist()));
    }


}