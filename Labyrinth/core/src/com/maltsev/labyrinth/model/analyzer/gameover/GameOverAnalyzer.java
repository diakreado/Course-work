package com.maltsev.labyrinth.model.analyzer.gameover;

//Мне кажется довольно странным, что GameOverAnalyzer содержит поле ModelOfLabyrinth, а ModelOfLabyrinth содержит поле GameOverAnalyzer
//todo это вполне нормально, ведь GameOverAnalyzer должен получать информацию про игровое поле и ему не сильно интересно, что там внутри, следовательно ему удобнее работать с  ModelOfLabyrinth

import com.maltsev.labyrinth.model.Model;
import com.maltsev.labyrinth.model.field.PointOnTheField;

import java.util.PriorityQueue;

public class GameOverAnalyzer {

    private PriorityQueue<GameOverListener> queue;

    private Model model;

    private final int DELAULT_SIZE_OF_QUEUE = 16;

    private PointOnTheField finishPoint;


    public GameOverAnalyzer(Model model) {

        this.model = model;

        finishPoint = new PointOnTheField(model.getFinishingPositionOfField());

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

        if (finishPoint.equals(model.getPositionOfProtagonist())) {

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
