package com.maltsev.labyrinth.presenter.tempdata;


public class PointOnTheScreen {

    private float x;
    private float y;

    public PointOnTheScreen(float x, float y) {

        this.x = x;
        this.y = y;
    }

    public PointOnTheScreen(SizeOfTexture obj) {

        this.x = obj.getWidth();
        this.y = obj.getHeight();
    }

    public float getX() {

        return x;
    }

    public float getY() {

        return y;
    }
}
