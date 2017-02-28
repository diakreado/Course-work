package com.maltsev.labyrinth.model.field;

/**
 * Исключение, которое бросается, если поле пустое и ходить некуда
 */
public class FieldIsEmptyException extends Exception{

    FieldIsEmptyException(String str) {

        super(str);
    }
}
