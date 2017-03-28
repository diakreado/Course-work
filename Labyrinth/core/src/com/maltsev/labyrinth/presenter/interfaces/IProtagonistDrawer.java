package com.maltsev.labyrinth.presenter.interfaces;

import com.maltsev.labyrinth.presenter.tempdata.PointOnTheScreen;

/**
 * Отрисовщик протагониста
 *
 * Позволяет менять направление движения протагониста, это создано для того чтобы отрисовывался повороты протагониста
 */
public interface IProtagonistDrawer {

    /**
     * Отрисовывает протагониста на экране
     */
    void draw();

    /**
     * @param pointOfNewLocationOfProtagonist точка, в которой следует отрисовать протагониста
     */
    void setPositionOfProtagonist(PointOnTheScreen pointOfNewLocationOfProtagonist);
}
