package com.maltsev.labyrinth.model.GameField;

import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class GameFieldTest {
    @Test
    public void isItPossibleWay() throws Exception {

        int newField[][] = {{0,1,1,1,0},
                {0,1,1,1,1},
                {0,1,1,1,1},
                {0,1,1,1,1},
                {0,0,0,0,0}};

        GameField gameField = new GameField(newField);

        assertEquals(true ,gameField.isItPossibleWay(0,4));

        assertEquals(true ,gameField.isItPossibleWay(0,0));
        assertEquals(true ,gameField.isItPossibleWay(4,4));
        assertEquals(true ,gameField.isItPossibleWay(3,0));
        assertEquals(false ,gameField.isItPossibleWay(1,1));
        assertEquals(false ,gameField.isItPossibleWay(2,4));
        assertEquals(false ,gameField.isItPossibleWay(0,3));

        assertEquals(5, gameField.getSizeOfFieldX());
        assertEquals(5, gameField.getSizeOfFieldY());

        System.out.println("Тест на создание поля правильного размера и проверку проходимости ячеек - пройден.");
    }

    @Test
    public void getPassableCells() throws Exception {

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

        System.out.println("Тест на получеие массива проходимых ячеек - пройден.");
    }

    @Test
    public void exceptionInConstructor() throws Exception {

        int newField[][] = {{0,1,1,1,0,0,0},
                {0,1,1,1,1,0,0},
                {0,1,1,1,1,0,0,0},
                {0,1,1,1,1,0,0},
                {0,0,0,0,0,0,0}};

        GameField gameField = new GameField(newField);

        try {

            gameField.isItPossibleWay(2,8);
        }
        catch (OutOfBoundaryOfTheField ex) {

            assertEquals(6 ,ex.maximumAllowableValueOfParam);
            assertEquals("y" ,ex.nameOfParam);
            assertEquals(8 ,ex.valueOfParam);
        }

        try {

            gameField.isItPossibleWay(5,1);
        }
        catch (OutOfBoundaryOfTheField ex) {

            assertEquals(4 ,ex.maximumAllowableValueOfParam);
            assertEquals("x" ,ex.nameOfParam);
            assertEquals(5 ,ex.valueOfParam);
        }

        try {

            gameField.isItPossibleWay(2,7);         // тот самый лишний 0
        }
        catch (OutOfBoundaryOfTheField ex) {

            assertEquals(6 ,ex.maximumAllowableValueOfParam);
            assertEquals("y" ,ex.nameOfParam);
            assertEquals(7 ,ex.valueOfParam);
        }

        System.out.println("Тест на выбрасываение исключений при выходе за границы поля - пройден.");
    }
}