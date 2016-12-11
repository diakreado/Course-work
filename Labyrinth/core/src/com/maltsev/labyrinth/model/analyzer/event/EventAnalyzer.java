package com.maltsev.labyrinth.model.analyzer.event;


import com.maltsev.labyrinth.model.Model;

/**
 * Обобщающий класс для тех, кто следит за событиями и оповещает других, если оно произошло
 */
public abstract class EventAnalyzer {

    protected Model model;

    protected EventAnalyzer(Model model) {

        this.model = model;
    }

}
