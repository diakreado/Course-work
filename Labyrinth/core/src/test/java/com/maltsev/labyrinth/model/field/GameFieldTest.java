package com.maltsev.labyrinth.model.field;

import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class GameFieldTest {
    @Test
    public void createFiledAndCheckPossible() throws Exception {

        int newField[][] = {{0,1,1,1,0},
                {0,1,1,1,1},
                {0,1,1,1,1},
                {0,1,1,1,1},
                {0,0,0,0,0}};

        GameField gameField = new GameField(newField);

        assertEquals(true ,gameField.isItPossibleWay(0,4));

        assertTrue(gameField.isItPossibleWay(0,0));
        assertTrue(gameField.isItPossibleWay(4,4));
        assertTrue(gameField.isItPossibleWay(3,0));
        assertFalse(gameField.isItPossibleWay(1,1));
        assertFalse(gameField.isItPossibleWay(2,4));
        assertFalse(gameField.isItPossibleWay(0,3));

        assertEquals(5, gameField.getSizeOfFieldX());
        assertEquals(5, gameField.getSizeOfFieldY());
    }

    @Test
    public void getArrayOfPassableCells() throws Exception {

        int newField[][] = {{0,1,1,1,1,1},
                            {1,1,1,1,0,0},
                            {0,0,1,1,1,1}};

        GameField gameField = new GameField(newField);

        ArrayList<PointOnTheField> passableCells = new ArrayList<PointOnTheField>(gameField.getPassableCells());

        PointOnTheField firstPoint = new PointOnTheField(passableCells.get(0));
        assertEquals(true ,firstPoint.equals(0,0));

        PointOnTheField secondPoint = new PointOnTheField(passableCells.get(1));
        assertEquals(true ,secondPoint.equals(1,4));

        PointOnTheField thirdPoint = new PointOnTheField(passableCells.get(3));
        assertEquals(true ,thirdPoint.equals(2,0));
    }

    @Test
    public void throwsExceptionInConstructor() throws Exception {

        int newField[][] = {{0,1,1,1,0,0,0},
                {0,1,1,1,1,0,0},
                {0,1,1,1,1,0,0,0},
                {0,1,1,1,1,0,0},
                {0,0,0,0,0,0,0}};

        GameField gameField = new GameField(newField);

        try {

            gameField.isItPossibleWay(2,8);
        }
        catch (com.maltsev.labyrinth.model.field.OutOfBoundaryOfTheField ex) {

            assertEquals(6 ,ex.getMaximumAllowableValueOfParam());
            assertEquals("y" ,ex.getNameOfParam());
            assertEquals(8 ,ex.getValueOfParam());
        }

        try {

            gameField.isItPossibleWay(5,1);
        }
        catch (com.maltsev.labyrinth.model.field.OutOfBoundaryOfTheField ex) {

            assertEquals(4 ,ex.getMaximumAllowableValueOfParam());
            assertEquals("x" ,ex.getNameOfParam());
            assertEquals(5 ,ex.getValueOfParam());
        }

        try {

            gameField.isItPossibleWay(2,7);         // тот самый лишний 0
        }
        catch (com.maltsev.labyrinth.model.field.OutOfBoundaryOfTheField ex) {

            assertEquals(6 ,ex.getMaximumAllowableValueOfParam());
            assertEquals("y" ,ex.getNameOfParam());
            assertEquals(7 ,ex.getValueOfParam());
        }
    }
}