package com.maltsev.labyrinth.view.utils;


public class InfoOfSettings {

    private int numberOfGameField = 0;

    /**
     * true - контроллер
     * false - касание
     */
    private boolean typeOfTheControl = false;

    public InfoOfSettings(int numberOfGameField, boolean typeOfTheControl) {

        this.numberOfGameField = numberOfGameField;
        this.typeOfTheControl = typeOfTheControl;
    }

    public int getNumberOfGameField() {

        return numberOfGameField;
    }

    public boolean getTypeOfTheControl() {

        return typeOfTheControl;
    }
}