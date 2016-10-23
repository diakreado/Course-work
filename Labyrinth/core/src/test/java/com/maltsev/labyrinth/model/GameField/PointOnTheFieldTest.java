package com.maltsev.labyrinth.model.GameField;

import org.junit.Test;

import static org.junit.Assert.*;

public class PointOnTheFieldTest {
    @Test
    public void constructorTest() throws Exception {

        PointOnTheField pointFirst = new PointOnTheField(7,4);

        assertEquals(7 ,pointFirst.getX());
        assertEquals(4 ,pointFirst.getY());

        PointOnTheField pointSecond = new PointOnTheField(0,1);

        assertEquals(0 ,pointSecond.getX());
        assertEquals(1 ,pointSecond.getY());

        System.out.println("\nКонструктор, принимающий int, и метод, возвращающий координаты работает правильно");
    }

    @Test
    public void copyConstructorTest() throws Exception {

        PointOnTheField pointFirst = new PointOnTheField(7,4);

        PointOnTheField pointSecond = new PointOnTheField(pointFirst);

        assertEquals(7 ,pointSecond .getX());
        assertEquals(4 ,pointSecond.getY());

        System.out.println("Констркутор копирования работает правильно");
    }

    @Test
    public void equalsTest() throws Exception {

        PointOnTheField pointFirst = new PointOnTheField(100,54);
        PointOnTheField pointSecond = new PointOnTheField(100,54);
        PointOnTheField pointThird = new PointOnTheField(0,0);

        assertEquals(true, pointFirst.equals(100,54));
        assertEquals(true, pointFirst.equals(pointSecond));
        assertEquals(false, pointFirst.equals(pointThird));

        System.out.println("Стравнение точек работает правильно");
    }


}