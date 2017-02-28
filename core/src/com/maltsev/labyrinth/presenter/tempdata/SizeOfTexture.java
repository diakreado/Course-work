package com.maltsev.labyrinth.presenter.tempdata;


public class SizeOfTexture {

    private int width;
    private int height;

    public SizeOfTexture(int width, int height) {

        this.width = width;
        this.height = height;
    }

    public SizeOfTexture(SizeOfTexture newObj) {

        this.width = newObj.getWidth();
        this.height = newObj.getHeight();
    }

    public int getWidth() {

        return width;
    }

    public int getHeight() {

        return height;
    }
}
