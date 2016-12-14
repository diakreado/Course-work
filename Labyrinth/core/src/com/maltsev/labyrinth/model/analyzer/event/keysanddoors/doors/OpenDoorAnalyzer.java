package com.maltsev.labyrinth.model.analyzer.event.keysanddoors.doors;


import com.maltsev.labyrinth.model.Model;
import com.maltsev.labyrinth.model.analyzer.event.EventAnalyzer;
import com.maltsev.labyrinth.model.field.PointOnTheField;

import java.util.*;

/**
 * Извещает интересующихся об открытие двери
 */
public class OpenDoorAnalyzer extends EventAnalyzer{

    private Queue<OpenDoorListener> queue;

    List<PointOnTheField> doors;


    public OpenDoorAnalyzer(Model model) {

        super(model);

        queue = new LinkedList<OpenDoorListener>();

        doors = model.getDoors();
    }

    /**
     * Добавить слушателя
     * @param listener объект-слушатель
     */
    public void addListener(OpenDoorListener listener) {

        queue.add(listener);
    }

    /**
     * Используется, чтобы отписаться от рассылки
     * @param listener - кого удалить
     */
    public void removeListener(OpenDoorListener listener) {

        if (queue.contains(listener))
            queue.remove(listener);
    }

    /**
     * Вызывается при открытие двери, чтобы оповестить об этом слушателей
     */
    public void doorIsOpen(PointOnTheField doorPosition) {

        alertListener(doorPosition);
    }

    /**
     * Оповещание слушателей об окончание игры
     */
    private void alertListener(PointOnTheField doorPosition) {

        OpenDoorListener item;

        for (int i = 0; i < queue.size(); i++) {

            item = queue.poll();
            item.doorIsOpen(doorPosition);
            queue.add(item);
        }
    }
}
