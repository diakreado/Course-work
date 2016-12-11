package com.maltsev.labyrinth.model.field;

import java.util.ArrayList;
import java.util.List;

/**
 *  Игровое поле
 */
public class GameField {

    /**
     *  Матрица ячеек, т.е. само поле
     */
    private CellOfField[][] field;

    /**
     *  Массив координат ячеек, которые являются проходимыми
     */
    private List<PointOnTheField> passableCells;

    /**
     *  Массив координат ячеек, в которых находятся двери
     */
    private List<PointOnTheField> doors;

    /**
     *  Массив координат ячеек, в которых находятся ключи
     */
    private List<PointOnTheField> keys;

    /**
     *  Размер поля по оси X
     */
    private int sizeOfFieldX;

    /**
     *  Размер поля по оси Y
     */
    private int sizeOfFieldY;

    /**
     * Стартовая точка поля
     */
    private PointOnTheField startingPoint;

    /**
     * Финишная точка поля
     */
    private PointOnTheField finishingPoint;


    /**
     * Конструктор, в которм создаётся игровое поле
     * @param newField строка-матрица, где 1,s,f - проходимые элементы, а 0 - нет,
     *                 s - начальная точка поля, f - конечная, а новая строчка задаётся \n
     */
    public GameField(final String newField) throws FieldIsEmptyException {

        passableCells = new ArrayList<PointOnTheField>();
        doors = new ArrayList<PointOnTheField>();
        keys = new ArrayList<PointOnTheField>();

        String[] fieldFromString = newField.split("\\n");   // делим полученую строчку на части

        int lengthX = fieldFromString.length;
        int lengthY = fieldFromString[fieldFromString.length - 1].length();


        for(int x = 0; x < lengthX; x++) {                        // Проверка на то, чтобы матрица была прямоугольной,
                                                                 // если это не так, то шириной берётся минимальное значение
            if (lengthY > fieldFromString[x].length()) {        // остальное отбрасывается

                lengthY = fieldFromString[x].length();
            }
        }

        field = new CellOfField[lengthX][lengthY];

        boolean metBeginning = false;
        boolean metEnd = false;

        for(int x = 0; x < lengthX; x++) {

            for(int y = 0; y < lengthY; y++) {

                boolean isItPossibleWay = false;

                if (fieldFromString[x].charAt(y) != '0') {            // Установка проходимости

                    isItPossibleWay = true;

                    passableCells.add(new PointOnTheField(x,y));
                }

                field[x][y] = new CellOfField(isItPossibleWay);

                switch (fieldFromString[x].charAt(y)) {

                    case 's':           // Установка стартовой и финишной точек
                    case 'S': {

                        if (!metBeginning) startingPoint = new PointOnTheField(x,y);
                        metBeginning = true;
                        break;
                    }
                    case 'f':
                    case 'F': {

                        if (!metEnd) finishingPoint = new PointOnTheField(x,y);
                        metEnd = true;
                        break;
                    }

                    case 'd':           // Устновка дверей и ключей
                    case 'D': {

                        doors.add(new PointOnTheField(x,y));
                        field[x][y].createDoor();
                        break;
                    }
                    case 'k':
                    case 'K': {

                        keys.add(new PointOnTheField(x,y));
                        break;
                    }
                }

            }
        }

        setSize();

        if(passableCells.size() < 2)
            throw new FieldIsEmptyException("This field is empty dude");

        if (!metBeginning) {                                             // Если точки начала и конца не обнаружены, то
                                                                        //  они выбираются, из проходимых ячеек,
            PointOnTheField startPoint = passableCells.get(0);         //   как первая и последняя
            this.startingPoint = new PointOnTheField(startPoint);
        }
        if (!metEnd) {

            PointOnTheField finishPoint = passableCells.get(passableCells.size() - 1);
            this.finishingPoint = new PointOnTheField(finishPoint);
        }
    }

    /**
     *  Фиксирование размеров поля
     */
    private void setSize() {

        sizeOfFieldX = field.length;
        sizeOfFieldY = field[0].length;
    }

    /**
     * @param x - координата точки по оси X
     * @param y - координата точки по оси Y
     * @return является ли эта ячейка проходимой
     * @throws OutOfBoundaryOfTheFieldException - воход за граниуц поля
     */
    public boolean isItPassableCell(final int x, final int y) throws OutOfBoundaryOfTheFieldException {

        if (x < 0 || x >= sizeOfFieldX)
            throw new OutOfBoundaryOfTheFieldException("Illegal request of possible way",
                    "x", x, sizeOfFieldX - 1);

        if (y < 0 || y >= sizeOfFieldY)
            throw new OutOfBoundaryOfTheFieldException("Illegal request of possible way",
                    "y", y, sizeOfFieldY - 1);

        return field[x][y].getInfoAboutPatencyOfCell();
    }

    /**
     * @param point точка на поле указывающая на ячейку
     * @return является ли эта ячейка проходимой
     * @throws OutOfBoundaryOfTheFieldException - воход за граниуц поля
     */
    public boolean isItPassableCell(final PointOnTheField point) throws OutOfBoundaryOfTheFieldException {

        int x = point.getX();
        int y = point.getY();

        if (x < 0 || x >= sizeOfFieldX)
            throw new OutOfBoundaryOfTheFieldException("Illegal request of possible way",
                    "x", x, sizeOfFieldX - 1);

        if (y < 0 || y >= sizeOfFieldY)
            throw new OutOfBoundaryOfTheFieldException("Illegal request of possible way",
                    "y", y, sizeOfFieldY - 1);

        return field[x][y].getInfoAboutPatencyOfCell();
    }

    /**
     * @return размер поля по оси X (начиная отсчёт с нуля)
     */
    public int getSizeX() {

        return sizeOfFieldX;
    }

    /**
     * @return размер поля по оси Y (начиная отсчёт с нуля)
     */
    public int getSizeY() {

        return sizeOfFieldY;
    }

    /**
     * @return начальная точка поля
     */
    public PointOnTheField getStartingPoint() {

        return startingPoint;
    }

    /**
     * @return конечная точка поля
     */
    public PointOnTheField getFinishingPoint() {

        return finishingPoint;
    }

    /**
     * @return Массив координат проходимых ячеек
     */
    public List<PointOnTheField> getPassableCells() {

        return new ArrayList<PointOnTheField>(passableCells);
    }

    /**
     * @return Массив координат расположения дверей
     */
    public List<PointOnTheField> getDoors() {

        return new ArrayList<PointOnTheField>(doors);
    }

    /**
     * @return Массив координат расположения ключей
     */
    public List<PointOnTheField> getKeys() {

        return new ArrayList<PointOnTheField>(keys);
    }

    /**
     * Открыть дверь
     * @param x координата по оси Х
     * @param y координата по оси Y
     */
    public void openDoor(int x, int y) {

        field[x][y].openDoor();
    }
}