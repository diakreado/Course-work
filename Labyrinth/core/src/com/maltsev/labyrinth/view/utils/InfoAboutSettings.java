package com.maltsev.labyrinth.view.utils;


public class InfoAboutSettings {

    private String gameField = "";

    /**
     * true - контроллер
     * false - касание
     */
    private boolean typeOfTheControl = false;

    public InfoAboutSettings(String gameField, boolean typeOfTheControl) {

        this.gameField = gameField;
        this.typeOfTheControl = typeOfTheControl;
    }

    public String getGameField() {
        return gameField;
    }

    public boolean getTypeOfTheControl() {
        return typeOfTheControl;
    }

}