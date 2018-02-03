package com.maltsev.labyrinth.presenter.tempdata;


public class SizeOfTexture {

    private int width;
    private int height;

    public SizeOfTexture(int width, int height) {

        this.width = width;
        this.height = height;
    }

    public int getWidth() {

        return width;
    }

    public int getHeight() {

        return height;
    }

    @Override
    public String toString() {
        return "width= " + width + ";  height= " + height + ";";
    }
}
