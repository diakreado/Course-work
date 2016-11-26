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

        WayAnalyzer analyzer = new WayAnalyzer();
        analyzer.setDefaultRange(5);

        PointOnTheField startPoint1 = new PointOnTheField(0,0);
        PointOnTheField finishPoint1 = new PointOnTheField(0,4);

        ArrayList<PointOnTheField> way1 = analyzer.getWay(startPoint1, finishPoint1);

        assertTrue(way1.get(0).equals(0,0));
        assertTrue(way1.get(1).equals(0,1));
        assertTrue(way1.get(2).equals(0,2));
        assertTrue(way1.get(3).equals(0,3));
        assertTrue(way1.get(4).equals(0,4));

        PointOnTheField startPoint2 = new PointOnTheField(1,2);
        PointOnTheField finishPoint2 = new PointOnTheField(3,1);

        ArrayList<PointOnTheField> way2 = analyzer.getWay(startPoint2, finishPoint2);

        assertTrue(way2.get(0).equals(1,2));
        assertTrue(way2.get(1).equals(1,1));
        assertTrue(way2.get(2).equals(1,0));
        assertTrue(way2.get(3).equals(2,0));
        assertTrue(way2.get(4).equals(3,0));
        assertTrue(way2.get(5).equals(3,1));

        PointOnTheField startPoint3 = new PointOnTheField(0,0);
        PointOnTheField finishPoint3 = new PointOnTheField(4,4);

        ArrayList<PointOnTheField> way3 = analyzer.getWay(startPoint3, finishPoint3);
        assertTrue(way3 == null);

        PointOnTheField startPoint4 = new PointOnTheField(1,1);
        PointOnTheField finishPoint4 = new PointOnTheField(4,4);

        ArrayList<PointOnTheField> way41 = analyzer.getWay(startPoint4, finishPoint4, 8);
        assertTrue(way41 != null);

        ArrayList<PointOnTheField> way42 = analyzer.getWay(startPoint4, finishPoint4, 7);
        assertTrue(way42 == null);


        PointOnTheField startPoint5 = new PointOnTheField(4,4);
        PointOnTheField finishPoint5 = new PointOnTheField(2,0);

        ArrayList<PointOnTheField> way51 = analyzer.getWay(startPoint5, finishPoint5);
        assertTrue(way51 == null);

        analyzer.setDefaultRange(6);

        ArrayList<PointOnTheField> way52 = analyzer.getWay(startPoint5, finishPoint5);
        assertTrue(way52 != null);


        PointOnTheField startPoint6 = new PointOnTheField(5,4);
        PointOnTheField finishPoint6 = new PointOnTheField(3,3);

        ArrayList<PointOnTheField> way6 = analyzer.getWay(startPoint6, finishPoint6);
        assertTrue(way6 == null);
    }

}