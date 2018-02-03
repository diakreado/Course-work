package com.maltsev.labyrinth.model.field;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;


@RunWith(Suite.class)
@Suite.SuiteClasses({

        CellOfFieldTest.class,
        com.maltsev.labyrinth.model.field.GameFieldTest.class,
        PointOnTheFieldTest.class  })

/**
 *  Запуск всех тестов свзяанных с игровым полем
 */
public class FieldTestSuit {}
