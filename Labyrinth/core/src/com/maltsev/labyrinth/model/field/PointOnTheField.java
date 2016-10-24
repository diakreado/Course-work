package com.maltsev.labyrinth.model.field;

/**
 * Точка на поле
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
    public PointOnTheField(int x, int y) {

        this.x = x;
        this.y = y;
    }

    /**
     *  Конструктор копирования
     * @param point - объект, который копируют
     */
    public PointOnTheField(PointOnTheField point) {

        this.x = point.getX();
        this.y = point.getY();
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

    /**
     * @param point точка, с которой производится сравнение
     * @return одинаковые ли точки
     */
    public boolean equals(PointOnTheField point) {

        if (this.x == point.x && this.y == point.y) return true;
        else return false;
    }

    /**
     * @param x координата точки по оси X
     * @param y координата точки по оси Y
     * @return одинаковые ли точки
     */
    public boolean equals(int x, int y) {

        if (this.x == x && this.y == y) return true;
        else return false;
    }
}