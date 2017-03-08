package com.maltsev.labyrinth.view.utils;


public class InfoOfSettings {

    private int numberOfGameField = 0;

    private int numberOfTexture = 0;

    /**
     * true - контроллер
     * false - касание
     */
    private boolean typeOfTheControl = false;

    public InfoOfSettings(int numberOfGameField, boolean typeOfTheControl, int numberOfTexture) {

        this.numberOfGameField = numberOfGameField;
        this.typeOfTheControl = typeOfTheControl;
        this.numberOfTexture = numberOfTexture;
    }

    public int getNumberOfTexture() {

        return numberOfTexture;
    }

    public int getNumberOfGameField() {

        return numberOfGameField;
    }

    public boolean getTypeOfTheControl() {

        return typeOfTheControl;
    }
}