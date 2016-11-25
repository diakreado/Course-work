package com.maltsev.labyrinth.model.analyzer.gameover;


import com.maltsev.labyrinth.model.Model;
import com.maltsev.labyrinth.model.field.PointOnTheField;

import java.util.PriorityQueue;

public class GameOverAnalyzer {

    private PriorityQueue<GameOverListener> queue;

    private Model model;

    private final int DELAULT_SIZE_OF_QUEUE = 16;

    private PointOnTheField finishPoint;


    public GameOverAnalyzer() {

        model = Model.getInstance();
        finishPoint = new PointOnTheField(model.getFinishingPositionOnTheField());

        queue = new PriorityQueue<GameOverListener>(DELAULT_SIZE_OF_QUEUE);
    }

    /**
     * Добавить слушателя
     * @param listener объект-слушатель
     */
    public void addListener(GameOverListener listener) {

        queue.add(listener);
    }

    /**
     * Проверка на окончание игры
     */
    public void messageAboutChangingSystem() {

        if (finishPoint.equals(model.getFinishingPositionOnTheField())) {

            alertListener();
        }
    }

    /**
     * Оповещание слушателей об окончание игры
     */
    private void alertListener() {

        GameOverListener item;

        while (!queue.isEmpty()) {

            item = queue.poll();
            item.gameIsOver();
        }
    }
}
