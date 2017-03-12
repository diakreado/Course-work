package com.maltsev.labyrinth.model.analyzer.event;


import com.maltsev.labyrinth.model.IModel;

/**
 * Обобщающий класс для тех, кто следит за событиями и оповещает других, если оно произошло
 */
public abstract class EventAnalyzer {

    protected IModel IModel;

    protected EventAnalyzer(IModel IModel) {

        this.IModel = IModel;
    }

}
