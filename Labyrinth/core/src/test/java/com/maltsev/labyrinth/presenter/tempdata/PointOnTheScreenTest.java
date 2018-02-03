package com.maltsev.labyrinth.presenter.tempdata;

import com.maltsev.labyrinth.model.field.PointOnTheField;
import org.junit.Test;

import static org.junit.Assert.*;


public class PointOnTheScreenTest {
    @Test
    public void verifyPointOnTheScreen() throws Exception {

        int pointX = 100;
        int pointY = 300;

        PointOnTheScreen point = new PointOnTheScreen(pointX,pointY);

        assertEquals((int)point.getX(),pointX);
        assertEquals((int)point.getY(),pointY);

        assertEquals(point.toString(), "x= " + (float)pointX + ";  y= " + (float)pointY + ";");

        assertFalse(point.equals(null));
        assertTrue(point.equals(point));
        assertFalse(point.equals(new PointOnTheField(pointX, pointY)));

        PointOnTheScreen equalsPoint = new PointOnTheScreen(pointX, pointY);
        PointOnTheScreen notEqualsPoint = new PointOnTheScreen(pointX + 1,pointY);

        assertFalse(point.equals(notEqualsPoint));
        assertTrue(point.equals(equalsPoint));
    }
}