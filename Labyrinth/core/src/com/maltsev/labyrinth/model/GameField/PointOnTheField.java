package com.maltsev.labyrinth.model.GameField;


public class PointOnTheField {

    int x;
    int y;


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

    public int getX() {

        return x;
    }

    public int getY() {

        return y;
    }
}
