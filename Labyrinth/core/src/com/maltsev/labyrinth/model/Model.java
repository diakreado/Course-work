package com.maltsev.labyrinth.model;


import com.maltsev.labyrinth.model.GameField.GameField;

/**
 * Model рассматривается в качестве поставщика данных,
 * которые будут отображаться во View.
 */
public class Model {

    Protagonist protagonist;
    public GameField gameField;

    public Model() {

        gameField = new GameField();
        protagonist = new Protagonist();
    }
}
