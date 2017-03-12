package com.maltsev.labyrinth.model;

import com.maltsev.labyrinth.model.analyzer.event.gameover.GameOverListener;
import com.maltsev.labyrinth.model.analyzer.event.keysanddoors.doors.OpenDoorListener;
import com.maltsev.labyrinth.model.analyzer.event.keysanddoors.keys.FoundKeyListener;
import com.maltsev.labyrinth.model.field.PointOnTheField;
import org.junit.Test;

import java.util.ArrayDeque;
import java.util.ArrayList;

import static org.junit.Assert.*;


public class ModelTest {

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

        IModel IModel = new Model();

        String newField = "1s0000\n" +
                          "01111f\n" +
                          "110001";

        IModel.setGameField(newField);
        IModel.setValueOfRangeOfStep(5);

        GameEndListener listener = new GameEndListener();
        IModel.addListenerOfGameOver(listener);

        assertTrue(new PointOnTheField(0,1).equals(IModel.getPositionOfProtagonist()));

        assertEquals(3, IModel.getSizeOfFieldX());
        assertEquals(6, IModel.getSizeOfFieldY());

        ArrayList<PointOnTheField> arrayOfPoint = new ArrayList<PointOnTheField>(IModel.getPassableCells());

        assertTrue(new PointOnTheField(0,0).equals(arrayOfPoint.get(0)));
        assertTrue(new PointOnTheField(0,1).equals(arrayOfPoint.get(1)));
        assertTrue(new PointOnTheField(1,1).equals(arrayOfPoint.get(2)));
        assertTrue(new PointOnTheField(1,5).equals(arrayOfPoint.get(6)));
        assertTrue(new PointOnTheField(2,5).equals(arrayOfPoint.get(9)));

        assertTrue(IModel.isItPassableCells(0,0));
        assertTrue(IModel.isItPassableCells(1,1));
        assertTrue(IModel.isItPassableCells(2,5));

        assertFalse(listener.isTestOfGameOverSuccess());                   // Так как мы ещё не на финише

        IModel.movesOfProtagonist(1,1);
        assertTrue(new PointOnTheField(1,1).equals(IModel.getPositionOfProtagonist()));

        assertTrue(IModel.movesOfProtagonist(0,5) == null);

        assertTrue(new PointOnTheField(1,1).equals(IModel.getPositionOfProtagonist()));

        assertFalse(listener.isTestOfGameOverSuccess());

        ArrayDeque<PointOnTheField> way = IModel.movesOfProtagonist(1,5);
        assertTrue(way.poll().equals(1,1));
        assertTrue(way.poll().equals(1,2));
        assertTrue(way.poll().equals(1,3));
        assertTrue(way.poll().equals(1,4));
        assertTrue(way.poll().equals(1,5));
        assertTrue(new PointOnTheField(1,5).equals(IModel.getPositionOfProtagonist()));    // Переход на финишную точку

        assertTrue(listener.isTestOfGameOverSuccess());  // Финиш

        IModel.movesOfProtagonist(2,5);
        assertTrue(new PointOnTheField(2,5).equals(IModel.getPositionOfProtagonist()));
        assertTrue(IModel.movesOfProtagonist(0,0) == null);
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

        IModel IModel = new Model();
        DoorsListener doorsListener = new DoorsListener();
        KeysListener keysListener = new KeysListener();

        String newField =   "s11d11\n" +
                            "100001\n" +
                            "k00001";

        IModel.setGameField(newField);
        IModel.setValueOfRangeOfStep(20);    // Не важно насколько делако будем ходить, тут интереснее, что через дверь мы не пройдём

        IModel.addListenerOfOpenDoor(doorsListener);
        IModel.addListenerOfFoundKey(keysListener);

        assertFalse(doorsListener.isTestSuccess());
        assertFalse(keysListener.isTestSuccess());

        assertTrue(IModel.getPositionOfProtagonist().equals(0,0));

        IModel.movesOfProtagonist(0,2);
        assertTrue(IModel.getPositionOfProtagonist().equals(0,2));

        IModel.movesOfProtagonist(0,3);
        assertFalse(IModel.getPositionOfProtagonist().equals(0,3));

        IModel.movesOfProtagonist(0,4);
        assertFalse(IModel.getPositionOfProtagonist().equals(0,4));

        assertFalse(doorsListener.isTestSuccess());
        assertFalse(keysListener.isTestSuccess());
        IModel.movesOfProtagonist(2,0);
        assertTrue(keysListener.isTestSuccess());

        IModel.movesOfProtagonist(0,3);
        assertTrue(doorsListener.isTestSuccess());
        assertTrue(IModel.getPositionOfProtagonist().equals(0,3));
    }
}