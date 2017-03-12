package com.maltsev.labyrinth.presenter.tempdata;

public class PointOnTheScreen {

    private float x;
    private float y;

    public PointOnTheScreen(float x, float y) {

        this.x = x;
        this.y = y;
    }

    public float getX() {

        return x;
    }

    public float getY() {

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

        PointOnTheScreen comparePoint = (PointOnTheScreen) obj;

        return this.x == comparePoint.x && this.y == comparePoint.y;
    }

    @Override
    public String toString() {

        return "x= " + x + ";  y= " + y + ";";
    }
}
