package com.maltsev.labyrinth.model.field;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class GameFieldTest {

    @Test
    public void createFiledAndCheckPossible() throws Exception {

        String newField = "1000s\n" +
                          "10000\n" +
                          "10000\n" +
                          "10000\n" +
                          "11f11";

        GameField gameField = new GameField(newField);

        assertEquals(true ,gameField.isItPassableCell(0,4));

        assertTrue(gameField.isItPassableCell(0,0));
        assertTrue(gameField.isItPassableCell(4,4));
        assertTrue(gameField.isItPassableCell(3,0));
        assertFalse(gameField.isItPassableCell(1,1));
        assertFalse(gameField.isItPassableCell(2,4));
        assertFalse(gameField.isItPassableCell(0,3));

        assertEquals(5, gameField.getSizeX());
        assertEquals(5, gameField.getSizeY());

        assertTrue(gameField.getStartingPoint().equals(0,4));
        assertTrue(gameField.getFinishingPoint().equals(4,2));
    }

    @Test
    public void getArrayOfPassableCells() throws Exception {

        String newField = "100000\n" +
                          "000011\n" +
                          "110000";

        GameField gameField = new GameField(newField);

        ArrayList<PointOnTheField> passableCells = new ArrayList<PointOnTheField>(gameField.getPassableCells());

        PointOnTheField firstPoint = new PointOnTheField(passableCells.get(0));
        assertEquals(true ,firstPoint.equals(0,0));

        PointOnTheField secondPoint = new PointOnTheField(passableCells.get(1));
        assertEquals(true ,secondPoint.equals(1,4));

        PointOnTheField thirdPoint = new PointOnTheField(passableCells.get(3));
        assertEquals(true ,thirdPoint.equals(2,0));

        assertTrue(gameField.getStartingPoint().equals(0,0));
        assertTrue(gameField.getFinishingPoint().equals(2,1));
    }

    @Test
    public void throwsExceptionInConstructor() throws Exception {

        String newField = "1000111\n" +
                          "1000011\n" +
                          "10000111\n" +
                          "1000011\n" +
                          "1111111";

        GameField gameField = new GameField(newField);

        try {

            gameField.isItPassableCell(2,8);
        }
        catch (OutOfBoundaryOfTheFieldException ex) {

            assertEquals("Illegal request of possible way",ex.getInfoAboutException());
            assertEquals(6 ,ex.getMaximumAllowableValueOfParam());
            assertEquals("y" ,ex.getNameOfParam());
            assertEquals(8 ,ex.getValueOfParam());
        }

        try {

            gameField.isItPassableCell(5,1);
        }
        catch (OutOfBoundaryOfTheFieldException ex) {

            assertEquals("Illegal request of possible way",ex.getInfoAboutException());
            assertEquals(4 ,ex.getMaximumAllowableValueOfParam());
            assertEquals("x" ,ex.getNameOfParam());
            assertEquals(5 ,ex.getValueOfParam());
        }

        try {

            gameField.isItPassableCell(2,7);         // тот самый лишний 0
        }
        catch (OutOfBoundaryOfTheFieldException ex) {

            assertEquals("Illegal request of possible way",ex.getInfoAboutException());
            assertEquals(6 ,ex.getMaximumAllowableValueOfParam());
            assertEquals("y" ,ex.getNameOfParam());
            assertEquals(7 ,ex.getValueOfParam());
        }

        String emptyField1 = "0000000\n" +
                          "000000\n" +
                "0000000\n" +
                "0000000\n" +
                "0000000";

        try {

            GameField emptyGameField1 = new GameField(emptyField1);
        }
        catch (FieldIsEmptyException ex) {

            assertEquals(ex.getMessage(), "This field is empty dude");
        }

        String emptyField2 = "0000000\n" +
                                "000000\n" +
                                "000100\n" +
                                "0000000\n" +
                                "0000000";

        try {

            GameField emptyGameField2 = new GameField(emptyField2);
        }
        catch (FieldIsEmptyException ex) {

            assertEquals(ex.getMessage(), "This field is empty dude");
        }
    }

    @Test
    public void doorCheck() throws Exception {

        String field = "110D11\n" +
                       "010010\n" +
                       "ddd11k";

        GameField gameField = new GameField(field);

        List<PointOnTheField> doors = gameField.getDoors();

        assertEquals(doors.size(), 4);

        assertTrue(doors.get(0).equals(0,3));
        assertTrue(doors.get(1).equals(2,0));
        assertTrue(doors.get(2).equals(2,1));
        assertTrue(doors.get(3).equals(2,2));
    }

    @Test
    public void doorKeys() throws Exception {

        String field = "110k11\n" +
                "010010\n" +
                "kKk11d";

        GameField gameField = new GameField(field);

        List<PointOnTheField> keys = gameField.getKeys();

        assertEquals(keys.size(), 4);

        assertTrue(keys.get(0).equals(0,3));
        assertTrue(keys.get(1).equals(2,0));
        assertTrue(keys.get(2).equals(2,1));
        assertTrue(keys.get(3).equals(2,2));
    }
}