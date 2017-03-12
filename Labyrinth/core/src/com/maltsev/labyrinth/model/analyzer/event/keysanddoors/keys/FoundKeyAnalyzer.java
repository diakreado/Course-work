package com.maltsev.labyrinth.model.analyzer.event.keysanddoors.keys;


import com.maltsev.labyrinth.model.IModel;
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

    private PointOnTheField keyPosition;


    public FoundKeyAnalyzer(IModel IModel) {

        super(IModel);

        queue = new LinkedList<FoundKeyListener>();

        keys = IModel.getKeys();
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

        keyPosition = IModel.getPositionOfProtagonist();    //Не всегда на этой позиции есть ключь, но мы его там ожидаем

        if(keys.contains(keyPosition))
            alertListener();
    }

    /**
     * Оповещание слушателей об окончание игры
     */
    private void alertListener() {

        FoundKeyListener item;

        for (int i = 0; i < queue.size(); i++) {

            item = queue.poll();
            item.keyIsFound(keyPosition);        // Здесь keyPosition уже всегда позиция ключа на карте
            queue.add(item);
        }

        keys = IModel.getKeys();
    }

}
