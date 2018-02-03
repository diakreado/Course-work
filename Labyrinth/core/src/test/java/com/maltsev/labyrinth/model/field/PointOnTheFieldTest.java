package com.maltsev.labyrinth.model.field;

import org.junit.Test;

import static org.junit.Assert.*;

public class PointOnTheFieldTest {

    /**
     * Проверка корректности создания ячейки
     * @throws Exception
     */
    @Test
    public void constructorOfPointTest() throws Exception {

        int firstPointX = 7;
        int firstPointY = 4;
        verificationPointCreation(firstPointX, firstPointY);

        int secondPointX = 101;
        int secondPointY = -30;
        verificationPointCreation(secondPointX, secondPointY);

        int thirdPointX = 0;
        int thirdPointY = 300;
        verificationPointCreation(thirdPointX, thirdPointY);

        int fourthPointX = 5;
        int fourthPointY = 11;
        verificationPointCreation(fourthPointX, fourthPointY);
    }

    /**
     * Проверка на правильность создания точки
     *
     * Создаётся точка
     * знаения созданой точки сравниваются с ожидаемыми
     * значения точки сравниваются с неожидаемыми
     *
     * @param pointX ожидаемая координата по X
     * @param pointY ожидаемая координата по Y
     * @throws Exception
     */
    private void verificationPointCreation(int pointX, int pointY) throws Exception  {

        PointOnTheField point = new PointOnTheField(pointX, pointY);

        assertEquals(pointX ,point.getX());
        assertEquals(pointY ,point.getY());

        assertNotEquals(pointX + 1, point.getX());
        assertNotEquals(pointY + 1, point.getY());

        assertNotEquals(pointX * 10 + 31, point.getX());
        assertNotEquals(pointY * 31 + 10, point.getY());

        assertNotEquals(pointX + 101, point.getX());
        assertNotEquals(pointY - 30, point.getY());
    }

    /**
     * Проверка работы конструктора копирования
     * @throws Exception
     */
    @Test
    public void copyConstructorOfPointTest() throws Exception {

        int firstPointX = 54;
        int firstPointY = 37;

        PointOnTheField firstPoint = new PointOnTheField(firstPointX,firstPointY);

        PointOnTheField secondPoint = new PointOnTheField(firstPoint);

        assertEquals(firstPointX, secondPoint .getX());
        assertEquals(firstPointY, secondPoint.getY());

        assertNotEquals(firstPointX + 1, secondPoint.getX());
        assertNotEquals(firstPointY - 1, secondPoint.getY());
    }

    /**
     * Проверка сравнения точек
     * @throws Exception
     */
    @Test
    public void equalsOfPointTest() throws Exception {

        int firstPointX = 120;
        int firstPointY = 3;

        int secondPointX = 120;
        int secondPointY = 120;

        int thirdPointX = 120;
        int thirdPointY = 3;

        int fourthPointX = 3;
        int fourthPointY = 120;

        int fifthPointX = 73;
        int fifthPointY = -75;

        PointOnTheField firstPoint = new PointOnTheField(firstPointX,firstPointY);
        PointOnTheField secondPoint = new PointOnTheField(secondPointX,secondPointY);
        PointOnTheField thirdPoint = new PointOnTheField(thirdPointX, thirdPointY);
        PointOnTheField fourthPoint = new PointOnTheField(fourthPointX, fourthPointY);
        PointOnTheField fifthPoint = new PointOnTheField(fifthPointX, fifthPointY);

        assertTrue(firstPoint.equals(firstPointX,firstPointY));
        assertTrue(secondPoint.equals(secondPointX,secondPointY));
        assertTrue(thirdPoint.equals(thirdPointX,thirdPointY));
        assertTrue(fourthPoint.equals(fourthPointX,fourthPointY));
        assertTrue(fifthPoint.equals(fifthPointX,fifthPointY));

        assertFalse(firstPoint.equals(firstPointX + 1,firstPointY - 1));
        assertFalse(secondPoint.equals(secondPointX * 31 + 43,secondPointY + 21));
        assertFalse(thirdPoint.equals(thirdPointX - 12,thirdPointY + 1));
        assertFalse(fourthPoint.equals(fourthPointX - 2,fourthPointY + 3));
        assertFalse(fifthPoint.equals(fifthPointX * 2 + 2,fifthPointY));

        assertTrue(firstPoint.equals(thirdPoint));
        assertFalse(firstPoint.equals(secondPoint));
        assertFalse(firstPoint.equals(fourthPoint));
        assertFalse(firstPoint.equals(fifthPoint));

        assertTrue(firstPoint.equals(firstPoint));
        assertFalse(firstPoint.equals(null));

        String firstPointActualString = "PointOnTheField{" + "x=" + firstPointX + ", y=" + firstPointY + '}';

        assertEquals(firstPointActualString, firstPoint.toString());
    }

    @Test
    public void hashOfPointTest() throws Exception {

        int firstPointX = 37;
        int firstPointY = 4;

        int secondPointX = 423;
        int secondPointY = 175;

        int thirdPointX = 37;
        int thirdPointY = 4;

        PointOnTheField firstPoint = new PointOnTheField(firstPointX,firstPointY);
        PointOnTheField secondPoint = new PointOnTheField(secondPointX,secondPointY);
        PointOnTheField thirdPoint = new PointOnTheField(thirdPointX, thirdPointY);

        assertEquals(firstPoint.hashCode(), thirdPoint.hashCode());

        assertNotEquals(firstPoint.hashCode(), secondPoint.hashCode());
    }


}