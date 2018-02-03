package com.maltsev.labyrinth.model.analyzer.event.keysanddoors.doors;


/**
 * Для оповещения об открытие двери, реализуёте этот интерфейс
 */
public interface OpenDoorListener {

    /**
     * Метод, который вызывется у всех слушателей, после открытия двери
     */
    void doorIsOpen(com.maltsev.labyrinth.model.field.PointOnTheField doorPosition);
}
