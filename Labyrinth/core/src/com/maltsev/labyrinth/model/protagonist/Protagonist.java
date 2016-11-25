package com.maltsev.labyrinth.model.protagonist;


import com.maltsev.labyrinth.model.field.PointOnTheField;

/**
 *  Главный герой
 */
public class Protagonist {

    /**
     *  Позиция главного героя на карте
     */
    private PointOnTheField locationOfProtagonist;


    /**
     *  Перемещения протагониста на новую позицию
     * @param newPoint - точка - новая позиция расположения героя
     */
    public void movesOfProtagonist(final PointOnTheField newPoint) {

        locationOfProtagonist = new PointOnTheField(newPoint);
    }

    /**
     *  Перемещения протагониста на новую позицию
     * @param x координата точки по оси X, в которую нужно переместить протагониста
     * @param y координата точки по оси Y, в которую нужно переместить протагониста
     */
    public void movesOfProtagonist(final int x, final  int y) {

        locationOfProtagonist = new PointOnTheField(x,y);
    }

    /**
     * Конструктор, с установкой начального положения героя
     * @param startPoint точка - начальное положение героя
     */
    public Protagonist(final PointOnTheField startPoint) {

        locationOfProtagonist = new PointOnTheField(startPoint);
    }

    /**
     * Конструктор, с установкой начального положения героя
     * @param x координата точки по оси X
     * @param y координата точки по оси Y
     */
    public Protagonist(final int x, final  int y) {

        locationOfProtagonist = new PointOnTheField(x, y);
    }

    /**
     * @return точка, положения героя
     */
    public PointOnTheField getLocationOfProtagonist() {

        return locationOfProtagonist;
    }
}