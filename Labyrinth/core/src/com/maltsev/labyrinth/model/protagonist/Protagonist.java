package com.maltsev.labyrinth.model.protagonist;


import com.maltsev.labyrinth.model.field.PointOnTheField;

/**
 *  Главный герой
 *  Использовался шаблон Singleton, т.к. главный герой один
 */
public class Protagonist {

    /**
     * Ссылка на единственный объект главного героя
     */
    private static Protagonist ourInstance;

    /**
     *  Позиция главного героя на карте
     */
    private PointOnTheField locationOfProtagonist;


    /**
     *  Перемещения протагониста на новую позицию
     * @param newPoint - точка - новая позиция расположения героя
     */
    public void moveProtagonist(PointOnTheField newPoint) {

        locationOfProtagonist = new PointOnTheField(newPoint);
    }

    /**
     *  Перемещения протагониста на новую позицию
     * @param x координата точки по оси X, в которую нужно переместить протагониста
     * @param y координата точки по оси Y, в которую нужно переместить протагониста
     */
    public void moveProtagonist(int x, int y) {

        locationOfProtagonist = new PointOnTheField(x,y);
    }

    /**
     *  Установка экземпляра главный герой
     * @param startPoint точка, в которой герой создаётся
     * @throws ObjectAlreadyExists бросается, когда экземпляр этого класса уже существует
     */
    public static void setInstance(PointOnTheField startPoint) throws ObjectAlreadyExists {

        if (ourInstance != null) {

            throw new ObjectAlreadyExists();
        }

        ourInstance = new Protagonist(startPoint);
    }

    /**
     *  Установка экземпляра главный герой
     * @param x координата точки по оси X, в которой создаётся герой
     * @param y координата точки по оси Y, в которой создаётся герой
     * @throws ObjectAlreadyExists бросается, когда экземпляр этого класса уже существует
     */
    public static void setInstance(int x, int y) throws ObjectAlreadyExists {

        setInstance(new PointOnTheField(x,y));
    }

    /**
     * @return экземпляр этого класса
     */
    public static Protagonist getInstance() {

        return ourInstance;
    }

    /**
     *  Закрытый конструктор, с установкой начального положения героя
     * @param startPoint точка - начальное положение героя
     */
    private Protagonist(PointOnTheField startPoint) {

        locationOfProtagonist = new PointOnTheField(startPoint);
    }

    /**
     * @return точка, положения героя
     */
    public PointOnTheField getLocationOfProtagonist() {

        return locationOfProtagonist;
    }
}