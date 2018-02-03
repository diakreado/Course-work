package com.maltsev.labyrinth.model.protagonist;


/**
 *  Главный герой
 */
public class Protagonist implements com.maltsev.labyrinth.model.analyzer.event.keysanddoors.keys.FoundKeyListener {

    /**
     *  Позиция главного героя на карте
     */
    private com.maltsev.labyrinth.model.field.PointOnTheField locationOfProtagonist;

    /**
     * Количество ключей, которые имеются у протагониста
     */
    private int numberOfKeys = 0;

    @Override
    public void keyIsFound(com.maltsev.labyrinth.model.field.PointOnTheField positionOfKey) {

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
    public void movesOfProtagonist(final com.maltsev.labyrinth.model.field.PointOnTheField newPoint) {

        locationOfProtagonist = new com.maltsev.labyrinth.model.field.PointOnTheField(newPoint);
    }

    /**
     *  Перемещения протагониста на новую позицию
     * @param x координата точки по оси X, в которую нужно переместить протагониста
     * @param y координата точки по оси Y, в которую нужно переместить протагониста
     */
    public void movesOfProtagonist(final int x, final  int y) {

        locationOfProtagonist = new com.maltsev.labyrinth.model.field.PointOnTheField(x,y);
    }

    /**
     * Конструктор, с установкой начального положения героя
     * @param startPoint точка - начальное положение героя
     */
    public Protagonist(final com.maltsev.labyrinth.model.field.PointOnTheField startPoint) {

        locationOfProtagonist = new com.maltsev.labyrinth.model.field.PointOnTheField(startPoint);
    }

    /**
     * Конструктор, с установкой начального положения героя
     * @param x координата точки по оси X
     * @param y координата точки по оси Y
     */
    public Protagonist(final int x, final  int y) {

        locationOfProtagonist = new com.maltsev.labyrinth.model.field.PointOnTheField(x, y);
    }

    /**
     * @return точка, положения героя
     */
    public com.maltsev.labyrinth.model.field.PointOnTheField getLocationOfProtagonist() {

        return locationOfProtagonist;
    }
}