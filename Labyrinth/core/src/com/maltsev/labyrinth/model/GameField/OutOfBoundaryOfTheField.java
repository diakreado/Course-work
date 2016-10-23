package com.maltsev.labyrinth.model.GameField;


public class OutOfBoundaryOfTheField extends Exception{

    String nameOfParam;
    int valueOfParam;
    int maximumAllowableValueOfParam;

    OutOfBoundaryOfTheField(String nameOfParam, int valueOfParam, int maximumAllowableValueOfParam) {

        super("Неправильно задан " + nameOfParam + " : " + valueOfParam +
                "\nДопустимое значение : [0;" + maximumAllowableValueOfParam + "]");

        this.nameOfParam = nameOfParam;
        this.valueOfParam = valueOfParam;
        this.maximumAllowableValueOfParam = maximumAllowableValueOfParam;
    }
}
