package com.maltsev.labyrinth.presenter;

import com.maltsev.labyrinth.model.field.PointOnTheField;
import com.maltsev.labyrinth.presenter.tempdata.PointOnTheScreen;
import com.maltsev.labyrinth.presenter.tempdata.SizeOfTexture;
import org.junit.Test;
import static com.maltsev.labyrinth.presenter.transkatorofcoordinate.TranslatorOfCoordinate.*;

import static org.junit.Assert.*;


public class TranslatorOfCoordinateTest {

    @Test
    public void verifyTranslator() throws Exception {

        try {
            translatePointFieldToScreen(new PointOnTheField(30,40));
        }
        catch (RuntimeException ex) {
            assertEquals(ex.toString(),"com.maltsev.test.TranslatorIsNotInitialize:" +
                    " Нельзя пользоватся ПереводчикомКоординат без его инициализации");
        }

        try {
            translatePointScreenToField(new PointOnTheScreen(30,40));
        }
        catch (RuntimeException ex) {
            assertEquals(ex.toString(),"com.maltsev.test.TranslatorIsNotInitialize:" +
                    " Нельзя пользоватся ПереводчикомКоординат без его инициализации");
        }
        
        assertTrue(initializeOfTranslator(new SizeOfTexture(10,10)));
        assertFalse(initializeOfTranslator(new SizeOfTexture(100,100)));

        PointOnTheField pointOnTheField = new PointOnTheField(34,61);
        PointOnTheScreen pointOnTheScreen = new PointOnTheScreen(340,610);

        assertEquals(translatePointScreenToField(translatePointFieldToScreen(pointOnTheField)),pointOnTheField);
        assertEquals(translatePointFieldToScreen(pointOnTheField), pointOnTheScreen);
        assertEquals(translatePointScreenToField(pointOnTheScreen), pointOnTheField);

    }
}