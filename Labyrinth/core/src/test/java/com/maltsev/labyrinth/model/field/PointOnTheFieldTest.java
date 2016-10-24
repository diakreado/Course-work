package com.maltsev.labyrinth.model.field;

import org.junit.Test;

import static org.junit.Assert.*;

public class PointOnTheFieldTest {
    @Test
    public void constructorOfPointTest() throws Exception {

        PointOnTheField pointFirst = new PointOnTheField(7,4);

        assertEquals(7 ,pointFirst.getX());
        assertEquals(4 ,pointFirst.getY());

        PointOnTheField pointSecond = new PointOnTheField(0,1);

        assertEquals(0 ,pointSecond.getX());
        assertEquals(1 ,pointSecond.getY());
    }

    @Test
    public void copyConstructorOfPointTest() throws Exception {

        PointOnTheField pointFirst = new PointOnTheField(7,4);

        PointOnTheField pointSecond = new PointOnTheField(pointFirst);

        assertEquals(7 ,pointSecond .getX());
        assertEquals(4 ,pointSecond.getY());
    }

    @Test
    public void equalsOfPointTest() throws Exception {

        PointOnTheField pointFirst = new PointOnTheField(100,54);
        PointOnTheField pointSecond = new PointOnTheField(100,54);
        PointOnTheField pointThird = new PointOnTheField(0,0);

        assertTrue(pointFirst.equals(100,54));
        assertTrue(pointFirst.equals(pointSecond));
        assertFalse(pointFirst.equals(pointThird));
    }


}