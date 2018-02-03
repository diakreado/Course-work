package com.maltsev.labyrinth.model;


import org.junit.runner.RunWith;
import org.junit.runners.Suite;



@RunWith(Suite.class)
@Suite.SuiteClasses({
        com.maltsev.labyrinth.model.field.FieldTestSuit.class,
        com.maltsev.labyrinth.model.protagonist.ProtagonistTest.class,
        com.maltsev.labyrinth.model.analyzer.WayAnalyzerTest.class,
        ModelTest.class})

/**
 * Тестирование всего пакета IModel
 */
public class ModelTestSuit {}
