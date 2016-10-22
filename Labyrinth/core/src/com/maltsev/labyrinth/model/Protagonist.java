package com.maltsev.labyrinth.model;


import com.maltsev.labyrinth.model.GameField.PointOnTheField;

class Protagonist {

    PointOnTheField locationOfProtagonist;

    Protagonist(PointOnTheField startPoint) {

        locationOfProtagonist = new PointOnTheField(startPoint);
    }
}
