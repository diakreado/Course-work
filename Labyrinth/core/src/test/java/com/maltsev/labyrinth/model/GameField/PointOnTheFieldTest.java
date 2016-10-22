package com.maltsev.labyrinth.model.GameField;

import org.junit.Test;

import static org.junit.Assert.*;

public class PointOnTheFieldTest {
    @Test
    public void performanceOfPointOnTheField() throws Exception {

        PointOnTheField pointFirst = new PointOnTheField(7,4);

        assertEquals(7 ,pointFirst.getX());
        assertEquals(4 ,pointFirst.getY());

        PointOnTheField pointSecond = new PointOnTheField(0,1);

        assertEquals(0 ,pointSecond.getX());
        assertEquals(1 ,pointSecond.getY());

        System.out.println("\n 1.Конструктор, принимающий int, и метод, возвращающий координаты работает правильно");

        PointOnTheField pointThird = new PointOnTheField(pointFirst);

        assertEquals(7 ,pointThird .getX());
        assertEquals(4 ,pointThird .getY());

        System.out.println(" 2.Констркутор копирования работает правильно");

        System.out.println("\n Класс Точка на плоскости работает правильно");
    }

}