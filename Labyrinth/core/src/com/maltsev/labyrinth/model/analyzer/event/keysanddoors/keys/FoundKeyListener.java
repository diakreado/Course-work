package com.maltsev.labyrinth.model.analyzer.event.keysanddoors.keys;


/**
 * Хочешь получить сообщение о получение ключа - реализуй интерфейс и вставь в очередь(без интерфейса не принимаем)
 */
public interface FoundKeyListener {

    /**
     * Метод, вызывающийся у слушателей, после обнаружения ключа
     */
    void keyIsFound();
}
