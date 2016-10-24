package com.maltsev.labyrinth.model;


import com.maltsev.labyrinth.model.field.FieldTestSuit;
import com.maltsev.labyrinth.model.protagonist.ProtagonistTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;



@RunWith(Suite.class)
@Suite.SuiteClasses({
        FieldTestSuit.class,
        ProtagonistTest.class

})
public class ModelTestSuit {

}
