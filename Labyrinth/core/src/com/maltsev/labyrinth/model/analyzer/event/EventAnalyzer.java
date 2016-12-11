package com.maltsev.labyrinth.model.analyzer.event;


import com.maltsev.labyrinth.model.Model;

public abstract class EventAnalyzer {

    protected final int DEFAULT_SIZE_OF_QUEUE = 16;

    protected Model model;

    protected EventAnalyzer(Model model) {

        this.model = model;
    }

}
