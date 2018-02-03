package com.maltsev.labyrinth.model.analyzer.event;


/**
 * Обобщающий класс для тех, кто следит за событиями и оповещает других, если оно произошло
 */
public abstract class EventAnalyzer {

    protected com.maltsev.labyrinth.model.IModel IModel;

    protected EventAnalyzer(com.maltsev.labyrinth.model.IModel IModel) {

        this.IModel = IModel;
    }

}
