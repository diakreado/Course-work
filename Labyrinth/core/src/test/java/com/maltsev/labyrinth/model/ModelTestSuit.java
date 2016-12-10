package com.maltsev.labyrinth.model;


import com.maltsev.labyrinth.model.field.FieldTestSuit;
import com.maltsev.labyrinth.model.protagonist.ProtagonistTest;
import com.maltsev.labyrinth.model.analyzer.WayAnalyzerTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;



@RunWith(Suite.class)
@Suite.SuiteClasses({
        FieldTestSuit.class,
        ProtagonistTest.class,
        WayAnalyzerTest.class,
        ModelOfLabyrinthTest.class})

/**
 * Тестирование всего пакета model
 */
public class ModelTestSuit {}
