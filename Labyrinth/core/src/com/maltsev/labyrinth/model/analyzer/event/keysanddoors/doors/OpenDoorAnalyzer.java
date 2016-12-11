package com.maltsev.labyrinth.model.analyzer.event.keysanddoors.doors;


import com.maltsev.labyrinth.model.Model;
import com.maltsev.labyrinth.model.analyzer.event.EventAnalyzer;
import com.maltsev.labyrinth.model.field.PointOnTheField;

import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

public class OpenDoorAnalyzer extends EventAnalyzer{

    private Queue<OpenDoorListener> queue;

    List<PointOnTheField> doors;




    public OpenDoorAnalyzer(Model model) {

        super(model);

        queue = new PriorityQueue<OpenDoorListener>(DEFAULT_SIZE_OF_QUEUE);

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
     * @param listener
     */
    public void deleteListener(OpenDoorListener listener) {

        if (queue.contains(listener))
            queue.remove(listener);
    }


    public void doorIsOpen() {

        alertListener();
    }

    /**
     * Оповещание слушателей об окончание игры
     */
    private void alertListener() {

        OpenDoorListener item;

        for (int i = 0; i < queue.size(); i++) {

            item = queue.poll();
            item.doorIsOpen();
            queue.add(item);
        }
    }
}
