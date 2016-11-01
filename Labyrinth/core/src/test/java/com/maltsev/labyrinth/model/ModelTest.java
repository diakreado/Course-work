package com.maltsev.labyrinth.model;

import com.maltsev.labyrinth.model.field.OutOfBoundaryOfTheField;
import com.maltsev.labyrinth.model.field.PointOnTheField;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;


public class ModelTest {

    @Test
    public void testingOfModel() throws Exception {

        Model model = new Model();

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
        assertTrue(new PointOnTheField(1,3).equals(arrayOfPoint.get(4)));
        assertTrue(new PointOnTheField(1,5).equals(arrayOfPoint.get(6)));
        assertTrue(new PointOnTheField(2,5).equals(arrayOfPoint.get(9)));

        assertTrue(model.isItPossibleWay(0,0));
        assertTrue(model.isItPossibleWay(1,3));
        assertTrue(model.isItPossibleWay(2,5));

        boolean isFirstTestWithExceptionPass = false;
        boolean isSecondTestWithExceptionPass = false;

        try {

            model.isItPossibleWay(6,3);

        } catch (OutOfBoundaryOfTheField ex) {

            isFirstTestWithExceptionPass = true;

            assertEquals("Illegal request of possible way",ex.getInfoAboutPlaceFromThrowingException());
            assertEquals(6,ex.getValueOfParam());
            assertEquals("x",ex.getNameOfParam());
            assertEquals(2,ex.getMaximumAllowableValueOfParam());
        }

        try {

            model.isItPossibleWay(0,10);

        } catch (OutOfBoundaryOfTheField ex) {

            isSecondTestWithExceptionPass = true;

            assertEquals("Illegal request of possible way",ex.getInfoAboutPlaceFromThrowingException());
            assertEquals(10,ex.getValueOfParam());
            assertEquals("y",ex.getNameOfParam());
            assertEquals(5,ex.getMaximumAllowableValueOfParam());
        }

        assertTrue(isFirstTestWithExceptionPass);
        assertTrue(isSecondTestWithExceptionPass);


        model.movesOfProtagonist(1,1);
        assertTrue(new PointOnTheField(1,1).equals(model.getLocationOfProtagonist()));


        try {

            model.movesOfProtagonist(-1,5);
        } catch (OutOfBoundaryOfTheField ex) {

            assertEquals(2,ex.getMaximumAllowableValueOfParam());
        }

        model.movesOfProtagonist(0,2);
        assertTrue(new PointOnTheField(0,2).equals(model.getLocationOfProtagonist()));

        assertFalse(model.isGameEnded());

        model.movesOfProtagonist(1,5);
        assertTrue(new PointOnTheField(1,5).equals(model.getLocationOfProtagonist()));

        assertTrue(model.isGameEnded());
    }
}