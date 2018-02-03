package com.maltsev.labyrinth.model.field;

/**
 * Точка на поле
 *
 * Используется как структура для хранения информации о позиции чего-либо
 *
 * Вполне может хранить отрицательные значения
 */
public class PointOnTheField {

    /**
     *  Координата точки по оси X
     */
    private int x;

    /**
     *  Координата точки по оси Y
     */
    private int y;


    /**
     * Конструктор точки
     * @param x координата по оси X
     * @param y координата по оси Y
     */
    public PointOnTheField(final int x, final int y) {

        this.x = x;
        this.y = y;
    }

    /**
     * Конструктор копирования
     * @param point - объект, который копируют
     */
    public PointOnTheField(final PointOnTheField point) {

        this.x = point.x;
        this.y = point.y;
    }

    /**
     * @return координату по оси X
     */
    public int getX() {

        return x;
    }

    /**
     * @return координату по оси Y
     */
    public int getY() {

        return y;
    }

    @Override
    public boolean equals(final Object obj) {

        if (obj == this) {

            return true;
        }

        if (obj == null || obj.getClass() != this.getClass()) {

            return false;
        }

        PointOnTheField comparePoint = (PointOnTheField) obj;

        return this.x == comparePoint.x && this.y == comparePoint.y;
    }

    /**
     * Сгенерировала IDEA
     */
    @Override
    public int hashCode() {

        int result = x;
        result = 31 * result + y;
        return result;
    }

    /**
     * @param x координата точки по оси X
     * @param y координата точки по оси Y
     * @return одинаковые ли точки
     */
    public boolean equals(final int x, int y) {

        return this.x == x && this.y == y;
    }

    /**
     * Сгенерировала IDEA
     */
    @Override
    public String toString() {

        return "PointOnTheField{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}