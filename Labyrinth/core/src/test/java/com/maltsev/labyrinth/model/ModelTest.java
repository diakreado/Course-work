package com.maltsev.labyrinth.model;

import com.maltsev.labyrinth.model.field.OutOfBoundaryOfTheField;
import com.maltsev.labyrinth.model.field.PointOnTheField;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;


public class ModelTest {

    @Test
    public void testingOfModel() throws Exception {

        Model model = Model.getInstance();

        String newField = "1s0000\n" +
                          "01111f\n" +
                          "110001";

        model.setGameField(newField);

        assertTrue(new PointOnTheField(0,1).equals(model.getLocationOfProtagonist()));

        assertEquals(3, model.getSizeOfFieldX());
        assertEquals(6, model.getSizeOfFieldY());

        assertFalse(model.isGameEnded());

        ArrayList<PointOnTheField> arrayOfPoint = new ArrayList<PointOnTheField>(model.getPassableCells());

        assertTrue(new PointOnTheField(0,0).equals(arrayOfPoint.get(0)));
        assertTrue(new PointOnTheField(0,1).equals(arrayOfPoint.get(1)));
        assertTrue(new PointOnTheField(1,1).equals(arrayOfPoint.get(2)));
        assertTrue(new PointOnTheField(1,5).equals(arrayOfPoint.get(6)));
        assertTrue(new PointOnTheField(2,5).equals(arrayOfPoint.get(9)));

        assertTrue(model.isItPassableCells(0,0));
        assertTrue(model.isItPassableCells(1,1));
        assertTrue(model.isItPassableCells(2,5));


        model.movesOfProtagonist(1,1);
        assertTrue(new PointOnTheField(1,1).equals(model.getLocationOfProtagonist()));

        assertTrue(model.movesOfProtagonist(0,5).isEmpty());

        assertTrue(new PointOnTheField(1,1).equals(model.getLocationOfProtagonist()));

        assertFalse(model.isGameEnded());

        ArrayList<PointOnTheField> way = model.movesOfProtagonist(1,5);
        assertTrue(way.get(0).equals(1,1));
        assertTrue(way.get(1).equals(1,2));
        assertTrue(way.get(2).equals(1,3));
        assertTrue(way.get(3).equals(1,4));
        assertTrue(way.get(4).equals(1,5));
        assertTrue(new PointOnTheField(1,5).equals(model.getLocationOfProtagonist()));

        assertTrue(model.isGameEnded());

        model.movesOfProtagonist(2,5);
        assertTrue(new PointOnTheField(2,5).equals(model.getLocationOfProtagonist()));
        assertTrue(model.movesOfProtagonist(0,0).isEmpty());

    }
}