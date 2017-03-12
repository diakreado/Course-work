package com.maltsev.labyrinth.view.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;

/**
 *  Обработка данных из файла
 */
public class FileReader {

    /**
     * Чтение из файла
     * @param fileName имя файла
     * @return полученная информация из файла, в виде одной строки
     */
    public static String read(String fileName) {

        FileHandle file = Gdx.files.internal(fileName);

        return file.readString();
    }
}