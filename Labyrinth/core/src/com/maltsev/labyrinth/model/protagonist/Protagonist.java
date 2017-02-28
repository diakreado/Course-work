package com.maltsev.labyrinth.model.protagonist;


import com.maltsev.labyrinth.model.analyzer.event.keysanddoors.keys.FoundKeyListener;
import com.maltsev.labyrinth.model.field.PointOnTheField;

/**
 *  Главный герой
 */
public class Protagonist implements FoundKeyListener {

    /**
     *  Позиция главного героя на карте
     */
    private PointOnTheField locationOfProtagonist;

    /**
     * Количество ключей, которые имеются у протагониста
     */
    private int numberOfKeys = 0;

    @Override
    public void keyIsFound(PointOnTheField positionOfKey) {

        numberOfKeys++;
    }

    /**
     * Ключ используется, следовательно количество ключей умменьшается
     */
    public void useKey() {

        numberOfKeys--;
    }

    /**
     * @return количество ключей на данный момент
     */
    public int getNumberOfKeys() {

        return numberOfKeys;
    }

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
    // уж если создал класс, представляющий позицию на поле, то его только и используй. Так что этот метод мне кажется лишним
    //TODO иногда удобнее одно, иногда другое, по смыслу и то, и то допустимо
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
    //Этот тоже лишний
    //TODO мне не сложно написать два варианта, они вполне допустимы, ты можешь сказать о позиции двумя параметрами или точкой, там где это используется улушчается понятность
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