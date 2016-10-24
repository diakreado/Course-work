package com.maltsev.labyrinth.model.protagonist;

import com.maltsev.labyrinth.model.field.PointOnTheField;
import org.junit.Test;

import static org.junit.Assert.*;


public class ProtagonistTest {

    @Test (expected = ObjectAlreadyExists.class)                // Всё тестируется в одном тесте, потому что применялся шаблон Singleton,
    public void workWithInstanceOfProtagonist() throws Exception {          // следовательно двух объектов класса не создать, а тесты запускаются
                                                              // в непонятном для меня порядке
        Protagonist.setInstance(0,0);

        Protagonist protagonist = Protagonist.getInstance();
        PointOnTheField startPoint = new PointOnTheField(0,0);

        assertEquals(true, startPoint.equals(protagonist.getLocationOfProtagonist()));

        PointOnTheField firstPoint = new PointOnTheField(53,86);
        protagonist.moveProtagonist(firstPoint);

        assertTrue(firstPoint.equals(protagonist.getLocationOfProtagonist()));

        PointOnTheField secondPoint = new PointOnTheField(10,3);
        protagonist.moveProtagonist(10,3);

        assertTrue(secondPoint.equals(protagonist.getLocationOfProtagonist()));

        PointOnTheField thirdPoint = new PointOnTheField(34,19);
        protagonist.moveProtagonist(5,5);

        assertFalse(thirdPoint.equals(protagonist.getLocationOfProtagonist()));

        Protagonist.setInstance(7,3);
    }
}