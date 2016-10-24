package com.maltsev.labyrinth.model.protagonist;


public class ObjectAlreadyExists extends Exception {

    ObjectAlreadyExists() {

        super("\n\nОбъект этого класса уже существует, создание ещё одного - невозможно" +
                "\nВоспльзуйтесь методом getInstance(), для получения существующего объекта");
    }
}
