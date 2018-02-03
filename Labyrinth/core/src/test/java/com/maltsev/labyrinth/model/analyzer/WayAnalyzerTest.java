package com.maltsev.labyrinth.model.analyzer;

import com.maltsev.labyrinth.model.IModel;
import com.maltsev.labyrinth.model.Model;
import com.maltsev.labyrinth.model.field.PointOnTheField;
import org.junit.Test;

import java.util.ArrayDeque;

import static org.junit.Assert.*;


public class WayAnalyzerTest {

    @Test
    public void motionInStraightLine() throws Exception {

        IModel IModel = new Model();

        String gameField = "11111\n" +
                           "11s11\n" +
                           "10000\n" +
                           "1f111\n" +
                           "11111";

        IModel.setGameField(gameField);

        WayAnalyzer analyzer = new WayAnalyzer(IModel);
        analyzer.setDefaultRange(5);

        PointOnTheField startPoint1 = new PointOnTheField(0,0);
        PointOnTheField finishPoint1 = new PointOnTheField(0,4);

        ArrayDeque<PointOnTheField> way1 = analyzer.getWay(startPoint1, finishPoint1);

        assertTrue(way1.poll().equals(0,0));
        assertTrue(way1.poll().equals(0,1));
        assertTrue(way1.poll().equals(0,2));
        assertTrue(way1.poll().equals(0,3));
        assertTrue(way1.poll().equals(0,4));

        PointOnTheField startPoint2 = new PointOnTheField(1,2);
        PointOnTheField finishPoint2 = new PointOnTheField(3,1);

        ArrayDeque<PointOnTheField> way2 = analyzer.getWay(startPoint2, finishPoint2);

        assertTrue(way2.poll().equals(1,2));
        assertTrue(way2.poll().equals(1,1));
        assertTrue(way2.poll().equals(1,0));
        assertTrue(way2.poll().equals(2,0));
        assertTrue(way2.poll().equals(3,0));
        assertTrue(way2.poll().equals(3,1));

        PointOnTheField startPoint3 = new PointOnTheField(0,0);
        PointOnTheField finishPoint3 = new PointOnTheField(4,4);

        ArrayDeque<PointOnTheField> way3 = analyzer.getWay(startPoint3, finishPoint3);
        assertTrue(way3 == null);

        PointOnTheField startPoint4 = new PointOnTheField(1,1);
        PointOnTheField finishPoint4 = new PointOnTheField(4,4);

        ArrayDeque<PointOnTheField> way41 = analyzer.getWay(startPoint4, finishPoint4, 8);
        assertTrue(way41 != null);

        ArrayDeque<PointOnTheField> way42 = analyzer.getWay(startPoint4, finishPoint4, 7);
        assertTrue(way42 == null);


        PointOnTheField startPoint5 = new PointOnTheField(4,4);
        PointOnTheField finishPoint5 = new PointOnTheField(2,0);

        ArrayDeque<PointOnTheField> way51 = analyzer.getWay(startPoint5, finishPoint5);
        assertTrue(way51 == null);

        analyzer.setDefaultRange(6);

        ArrayDeque<PointOnTheField> way52 = analyzer.getWay(startPoint5, finishPoint5);
        assertTrue(way52 != null);


        PointOnTheField startPoint6 = new PointOnTheField(5,4);
        PointOnTheField finishPoint6 = new PointOnTheField(3,3);

        ArrayDeque<PointOnTheField> way6 = analyzer.getWay(startPoint6, finishPoint6);
        assertTrue(way6 == null);
    }

}