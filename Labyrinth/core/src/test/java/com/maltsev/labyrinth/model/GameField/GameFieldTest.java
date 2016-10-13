package com.maltsev.labyrinth.model.GameField;

import org.junit.Test;
import static org.junit.Assert.*;

public class GameFieldTest {
    @Test
    public void isItPossibleWay() throws Exception {

        GameField gameField = new GameField();

        assertEquals(true ,gameField.isItPossibleWay(0,0));
        assertEquals(true ,gameField.isItPossibleWay(4,4));
        assertEquals(true ,gameField.isItPossibleWay(3,0));
        assertEquals(false ,gameField.isItPossibleWay(1,1));
        assertEquals(false ,gameField.isItPossibleWay(2,4));
        assertEquals(false ,gameField.isItPossibleWay(0,3));
    }

}