package com.maltsev.labyrinth.presenter;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;

/**
 *  Обработка данных из файла
 */
class FileReader {

    /**
     * Чтение из файла
     * @param fileName имя файла
     * @return полученная информация из файла, в виде одной строки
     */
    static String read(String fileName) {

        FileHandle file = Gdx.files.internal(fileName);

        return file.readString();
    }
}