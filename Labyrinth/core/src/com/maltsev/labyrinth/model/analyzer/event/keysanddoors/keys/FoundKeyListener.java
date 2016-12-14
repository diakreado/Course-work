package com.maltsev.labyrinth.model.analyzer.event.keysanddoors.keys;


import com.maltsev.labyrinth.model.field.PointOnTheField;

/**
 * Хочешь получить сообщение о получение ключа - реализуй интерфейс и вставь в очередь(без интерфейса не принимаем)
 */
public interface FoundKeyListener {

    /**
     * Метод, вызывающийся у слушателей, после обнаружения ключа
     * @param keyPosition позиция ключа
     */
    void keyIsFound(PointOnTheField keyPosition);
}
