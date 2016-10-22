package com.maltsev.labyrinth.model.GameField;

import org.junit.Test;
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

        System.out.println("\n 1. Конструктор создания поля и проверка на возможность прохождения ячейки работают правильно");
    }

}