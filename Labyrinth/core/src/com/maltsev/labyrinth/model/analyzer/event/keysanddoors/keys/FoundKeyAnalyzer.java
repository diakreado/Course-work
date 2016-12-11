package com.maltsev.labyrinth.model.analyzer.event.keysanddoors.keys;


import com.maltsev.labyrinth.model.Model;
import com.maltsev.labyrinth.model.analyzer.event.EventAnalyzer;
import com.maltsev.labyrinth.model.field.PointOnTheField;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Класс, который следит за получением ключей
 */
public class FoundKeyAnalyzer extends EventAnalyzer {

    private Queue<FoundKeyListener> queue;

    private List<PointOnTheField> keys;


    public FoundKeyAnalyzer(Model model) {

        super(model);

        queue = new LinkedList<FoundKeyListener>();

        keys = model.getKeys();
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
    public void removeListener(FoundKeyListener listener) {

        if (queue.contains(listener))
            queue.remove(listener);
    }

    /**
     * Сообщение о том, что состояние системы изменилось и возможно
     * настал момент истины (тот момент, ради которго создан класс)
     */
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
