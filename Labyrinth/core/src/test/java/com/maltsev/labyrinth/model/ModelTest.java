package com.maltsev.labyrinth.model;

import com.maltsev.labyrinth.model.analyzer.gameover.GameOverListener;
import com.maltsev.labyrinth.model.field.PointOnTheField;
import org.junit.Test;

import java.util.ArrayDeque;
import java.util.ArrayList;

import static org.junit.Assert.*;


public class ModelTest {

    class TestListener implements GameOverListener {

        private boolean testOfGameOverSuccess = false;

        boolean isTestOfGameOverSuccess() {

            return testOfGameOverSuccess;
        }

        @Override
        public void gameIsOver() {

            testOfGameOverSuccess = true;
        }
    }

    @Test
    public void testingOfModel() throws Exception {

        Model model = Model.getInstance();

        String newField = "1s0000\n" +
                          "01111f\n" +
                          "110001";

        model.setGameField(newField);
        model.setValueOfRangeOfStep(5);

        TestListener listener = new TestListener();
        model.addListenerOfGameOver(listener);

        assertTrue(new PointOnTheField(0,1).equals(model.getPositionOfProtagonist()));

        assertEquals(3, model.getSizeOfFieldX());
        assertEquals(6, model.getSizeOfFieldY());

        ArrayList<PointOnTheField> arrayOfPoint = new ArrayList<PointOnTheField>(model.getPassableCells());

        assertTrue(new PointOnTheField(0,0).equals(arrayOfPoint.get(0)));
        assertTrue(new PointOnTheField(0,1).equals(arrayOfPoint.get(1)));
        assertTrue(new PointOnTheField(1,1).equals(arrayOfPoint.get(2)));
        assertTrue(new PointOnTheField(1,5).equals(arrayOfPoint.get(6)));
        assertTrue(new PointOnTheField(2,5).equals(arrayOfPoint.get(9)));

        assertTrue(model.isItPassableCells(0,0));
        assertTrue(model.isItPassableCells(1,1));
        assertTrue(model.isItPassableCells(2,5));

        assertFalse(listener.isTestOfGameOverSuccess());                   // Так как мы ещё не на финише

        model.movesOfProtagonist(1,1);
        assertTrue(new PointOnTheField(1,1).equals(model.getPositionOfProtagonist()));

        assertTrue(model.movesOfProtagonist(0,5) == null);

        assertTrue(new PointOnTheField(1,1).equals(model.getPositionOfProtagonist()));

        assertFalse(listener.isTestOfGameOverSuccess());

        ArrayDeque<PointOnTheField> way = model.movesOfProtagonist(1,5);
        assertTrue(way.poll().equals(1,1));
        assertTrue(way.poll().equals(1,2));
        assertTrue(way.poll().equals(1,3));
        assertTrue(way.poll().equals(1,4));
        assertTrue(way.poll().equals(1,5));
        assertTrue(new PointOnTheField(1,5).equals(model.getPositionOfProtagonist()));    // Переход на финишную точку

        assertTrue(listener.isTestOfGameOverSuccess());  // Финиш

        model.movesOfProtagonist(2,5);
        assertTrue(new PointOnTheField(2,5).equals(model.getPositionOfProtagonist()));
        assertTrue(model.movesOfProtagonist(0,0) == null);

    }
}