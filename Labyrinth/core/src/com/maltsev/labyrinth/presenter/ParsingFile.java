package com.maltsev.labyrinth.presenter;


/**
 * Класс, созданый для того, чтобы парсить файл с полем,
 * получать из него игровое поле по номеру
 */
public class ParsingFile {

    public static String getFieldOnTheNumber(int numberOfTheField) {

        String fileData = FileReader.read("gameField.txt");

        return fileData;
    }
}
