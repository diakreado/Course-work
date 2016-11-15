package com.maltsev.labyrinth.model.analyzer;

import com.maltsev.labyrinth.model.Model;
import com.maltsev.labyrinth.model.field.PointOnTheField;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;


public class WayAnalyzerTest {

    @Test
    public void motionInStraightLine() throws Exception {

        Model model = Model.getInstance();

        String gameField = "11111\n" +
                           "11s11\n" +
                           "10000\n" +
                           "1f111\n" +
                           "11111";

        model.setGameField(gameField);

        PointOnTheField startPoint1 = new PointOnTheField(0,0);
        PointOnTheField finishPoint1 = new PointOnTheField(0,4);

        ArrayList<PointOnTheField> way1 = WayAnalyzer.getWay(startPoint1, finishPoint1);

        assertTrue(way1.get(0).equals(0,0));
        assertTrue(way1.get(1).equals(0,1));
        assertTrue(way1.get(2).equals(0,2));
        assertTrue(way1.get(3).equals(0,3));
        assertTrue(way1.get(4).equals(0,4));

        PointOnTheField startPoint2 = new PointOnTheField(1,2);
        PointOnTheField finishPoint2 = new PointOnTheField(3,1);

        ArrayList<PointOnTheField> way2 = WayAnalyzer.getWay(startPoint2, finishPoint2);

        assertTrue(way2.get(0).equals(1,2));
        assertTrue(way2.get(1).equals(1,1));
        assertTrue(way2.get(2).equals(1,0));
        assertTrue(way2.get(3).equals(2,0));
        assertTrue(way2.get(4).equals(3,0));
        assertTrue(way2.get(5).equals(3,1));

        PointOnTheField startPoint3 = new PointOnTheField(0,0);
        PointOnTheField finishPoint3 = new PointOnTheField(4,4);

        ArrayList<PointOnTheField> way3 = WayAnalyzer.getWay(startPoint3, finishPoint3);

        assertTrue(way3.isEmpty());
        assertEquals(way3.size(),0);

        PointOnTheField startPoint4 = new PointOnTheField(1,1);
        PointOnTheField finishPoint4 = new PointOnTheField(1,5);

        ArrayList<PointOnTheField> way4 = WayAnalyzer.getWay(startPoint4, finishPoint4);

        assertTrue(way4.isEmpty());
        assertEquals(way4.size(),0);

        PointOnTheField startPoint5 = new PointOnTheField(5,5);
        PointOnTheField finishPoint5 = new PointOnTheField(3,3);

        ArrayList<PointOnTheField> way5 = WayAnalyzer.getWay(startPoint5, finishPoint5);

        assertTrue(way5.isEmpty());
        assertEquals(way5.size(),0);
    }

}