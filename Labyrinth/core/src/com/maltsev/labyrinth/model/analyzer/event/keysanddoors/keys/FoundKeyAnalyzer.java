package com.maltsev.labyrinth.model.analyzer.event.keysanddoors.keys;


import com.maltsev.labyrinth.model.Model;
import com.maltsev.labyrinth.model.analyzer.event.EventAnalyzer;
import com.maltsev.labyrinth.model.field.PointOnTheField;

import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

public class FoundKeyAnalyzer extends EventAnalyzer {

    private Queue<FoundKeyListener> queue;

    List<PointOnTheField> keys;


    public FoundKeyAnalyzer(Model model) {

        super(model);

        keys = model.getKeys();

        queue = new PriorityQueue<FoundKeyListener>(DEFAULT_SIZE_OF_QUEUE);
    }

    /**
     * Добавить слушателя
     * @param listener объект-слушатель
     */
    public void addListener(FoundKeyListener listener) {

        queue.add(listener);
    }

    /**
     * Используется, чтобы отписаться от рассылки
     * @param listener - кого удалить
     */
    public void deleteListener(FoundKeyListener listener) {

        if (queue.contains(listener))
            queue.remove(listener);
    }


    public void messageAboutChangingSystem() {

        if(keys.contains(model.getPositionOfProtagonist()))
            alertListener();
    }

    /**
     * Оповещание слушателей об окончание игры
     */
    private void alertListener() {

        FoundKeyListener item;

        for (int i = 0; i < queue.size(); i++) {

            item = queue.poll();
            item.keyIsFound();
            queue.add(item);
        }
    }

}
