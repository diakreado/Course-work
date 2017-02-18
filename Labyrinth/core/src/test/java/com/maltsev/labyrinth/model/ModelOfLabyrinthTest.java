package com.maltsev.labyrinth.model;

import com.maltsev.labyrinth.model.analyzer.event.gameover.GameOverListener;
import com.maltsev.labyrinth.model.analyzer.event.keysanddoors.doors.OpenDoorListener;
import com.maltsev.labyrinth.model.analyzer.event.keysanddoors.keys.FoundKeyListener;
import com.maltsev.labyrinth.model.field.PointOnTheField;
import org.junit.Test;

import java.util.ArrayDeque;
import java.util.ArrayList;

import static org.junit.Assert.*;


public class ModelOfLabyrinthTest {

    private class GameEndListener implements GameOverListener {

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

        Model model = new ModelOfLabyrinth();

        String newField = "1s0000\n" +
                          "01111f\n" +
                          "110001";

        model.setGameField(newField);
        model.setValueOfRangeOfStep(5);

        GameEndListener listener = new GameEndListener();
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


    private class KeysListener implements FoundKeyListener {

        private boolean isTestSuccess = false;

        boolean isTestSuccess() {
            return isTestSuccess;
        }

        @Override
        public void keyIsFound(PointOnTheField positionOfkey) {
            isTestSuccess = true;
        }
    }

    private class DoorsListener implements OpenDoorListener {

        private boolean isTestSuccess = false;

        boolean isTestSuccess() {
            return isTestSuccess;
        }

        @Override
        public void doorIsOpen(PointOnTheField doorPosition) {
            isTestSuccess = true;
        }
    }


    @Test
    public void testOfKeysAndDoors() throws Exception {

        Model model = new ModelOfLabyrinth();
        DoorsListener doorsListener = new DoorsListener();
        KeysListener keysListener = new KeysListener();

        String newField =   "s11d11\n" +
                            "100001\n" +
                            "k00001";

        model.setGameField(newField);
        model.setValueOfRangeOfStep(20);    // Не важно насколько делако будем ходить, тут интереснее, что через дверь мы не пройдём

        model.addListenerOfOpenDoor(doorsListener);
        model.addListenerOfFoundKey(keysListener);

        assertFalse(doorsListener.isTestSuccess());
        assertFalse(keysListener.isTestSuccess());

        assertTrue(model.getPositionOfProtagonist().equals(0,0));

        model.movesOfProtagonist(0,2);
        assertTrue(model.getPositionOfProtagonist().equals(0,2));

        model.movesOfProtagonist(0,3);
        assertFalse(model.getPositionOfProtagonist().equals(0,3));

        model.movesOfProtagonist(0,4);
        assertFalse(model.getPositionOfProtagonist().equals(0,4));

        assertFalse(doorsListener.isTestSuccess());
        assertFalse(keysListener.isTestSuccess());
        model.movesOfProtagonist(2,0);
        assertTrue(keysListener.isTestSuccess());

        model.movesOfProtagonist(0,3);
        assertTrue(doorsListener.isTestSuccess());
        assertTrue(model.getPositionOfProtagonist().equals(0,3));
    }
}