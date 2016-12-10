package com.maltsev.labyrinth.model.analyzer;


import com.maltsev.labyrinth.model.Model;
import com.maltsev.labyrinth.model.field.PointOnTheField;

import java.util.ArrayDeque;

/**
 * Класс, главная задача которого проведение маршрута из одной точки в другую
 */
public class WayAnalyzer {

    private Model model;

    private int[][] fieldForWave;

    private int defaultRange = 5;

    private int range;

    private PointOnTheField startPoint;              // По факту эти поля вынесены сюда, чтобы было удобнее делигировать
    private PointOnTheField finishPoint;            //  выолнение различных частей алгоритма другим методам

    private int sizeOfFieldX;
    private int sizeOfFieldY;

    public WayAnalyzer(Model model) {

        this.model = model;
    }

    /**
     * Установить значение для длины шага
     * @param defaultRange значение, которое вы хотели бы устновить
     */
    public void setDefaultRange(int defaultRange) {

        this.defaultRange = defaultRange;
    }

    /**
     * Может быть это кому-то интересно
     * @return дефолтная длина шага
     */
    public int getDefaultRange() {

        return defaultRange;
    }

    /**
     * Метод по умолчанию, вызывает метод поиска пути с дефолтной длинной шага
     */
    @org.jetbrains.annotations.Nullable
    public ArrayDeque<PointOnTheField> getWay(final PointOnTheField startPoint, final PointOnTheField finishPoint) {

        return getWay(startPoint, finishPoint, defaultRange);
    }

    /**
     * Построение пути из одной точки поля в другую
     * @param startPoint стартовая точка пути
     * @param finishPoint финишная точка пути
     * @return возвращяет либо путь в виде массива точек, либо ноль, если путь содержит более range шагов или он невозможен
     */
    @org.jetbrains.annotations.Nullable
    public ArrayDeque<PointOnTheField> getWay(final PointOnTheField startPoint, final PointOnTheField finishPoint, int range) {

        if (!model.isItPassableCells(startPoint) || !model.isItPassableCells(finishPoint))
            return null;                            // Проверка на правильность задания начальной и конечной точки

        this.startPoint = startPoint;
        this.finishPoint = finishPoint;              // Записываем введённые параметры
        this.range = range;

        sizeOfFieldX = model.getSizeOfFieldX();      // Выписываем из модели значения размера поля
        sizeOfFieldY = model.getSizeOfFieldY();

        fieldForWave = new int[sizeOfFieldX][sizeOfFieldY];

        putMaximumValueInEveryCell();

        if(!motionOfWave()) return null;

        //ArrayList<PointOnTheField> wayBack = returningWave();

        ArrayDeque<PointOnTheField> way = returningWave();

        if (way == null) return null;

        return way;
    }

    /**
     * Возвращение волны
     * @return либо путь возвращения в виде коллекции точек, либо null
     */
    @org.jetbrains.annotations.Nullable
    private ArrayDeque<PointOnTheField> returningWave() {

        ArrayDeque<PointOnTheField> way = new ArrayDeque<PointOnTheField>();

        PointOnTheField bufferPoint = new PointOnTheField(finishPoint);

        way.addFirst(bufferPoint);

        int weightOfTheWave;
        int noMoreThanFewStrokes = 0;

        while (!bufferPoint.equals(startPoint)) {

            int bufferPointX = bufferPoint.getX();
            int bufferPointY = bufferPoint.getY();
            weightOfTheWave = fieldForWave[bufferPointX][bufferPointY];

            if (bufferPointX + 1 < sizeOfFieldX && fieldForWave[bufferPointX + 1][bufferPointY] == weightOfTheWave - 1) {

                bufferPoint = new PointOnTheField(bufferPointX + 1, bufferPointY);
                way.addFirst(bufferPoint);
            } else
            if (bufferPointY + 1 < sizeOfFieldY && fieldForWave[bufferPointX][bufferPointY + 1] == weightOfTheWave - 1) {

                bufferPoint = new PointOnTheField(bufferPointX, bufferPointY + 1);
                way.addFirst(bufferPoint);
            } else
            if (bufferPointX - 1 >= 0 && fieldForWave[bufferPointX - 1][bufferPointY] == weightOfTheWave - 1) {

                bufferPoint = new PointOnTheField(bufferPointX - 1, bufferPointY);
                way.addFirst(bufferPoint);
            } else
            if (bufferPointY - 1 >= 0 && fieldForWave[bufferPointX][bufferPointY - 1] == weightOfTheWave - 1) {

                bufferPoint = new PointOnTheField(bufferPointX, bufferPointY - 1);
                way.addFirst(bufferPoint);
            }

            noMoreThanFewStrokes++;

            if (noMoreThanFewStrokes > range)  return null;

        } // проходим от конца к началу, запоминая путь

        return way;
    }

    /**
     * Распространение волны: в цикле из начальной точки выходит волна и заполняет всё поле
     */
    private boolean motionOfWave() {

        fieldForWave[startPoint.getX()][startPoint.getY()] = 0;    // стартовая точка волны
        fieldForWave[finishPoint.getX()][finishPoint.getY()] = Integer.MAX_VALUE;  // конечная точка волны

        int weightOfTheWave = 0;

        int noMoreThanFewStrokes = 0;

        while (fieldForWave[finishPoint.getX()][finishPoint.getY()] == Integer.MAX_VALUE) {

            for (int i = 0; i < fieldForWave.length; i++) {

                for (int j = 0; j < fieldForWave[i].length; j++) {

                    if (fieldForWave[i][j] == weightOfTheWave) {

                        if (i + 1 < sizeOfFieldX && fieldForWave[i + 1][j] > weightOfTheWave  && model.isItPassableCells(i + 1, j)) {

                            fieldForWave[i + 1][j] = weightOfTheWave + 1;
                        }
                        if (j + 1 < sizeOfFieldY && fieldForWave[i][j + 1] > weightOfTheWave  && model.isItPassableCells(i, j + 1)) {

                            fieldForWave[i][j + 1] = weightOfTheWave + 1;
                        }
                        if (i - 1 >= 0 && fieldForWave[i - 1][j] > weightOfTheWave  && model.isItPassableCells(i - 1, j)) {

                            fieldForWave[i - 1][j] = weightOfTheWave + 1;
                        }
                        if (j - 1 >= 0 && fieldForWave[i][j - 1] > weightOfTheWave && model.isItPassableCells(i, j - 1)) {

                            fieldForWave[i][j - 1] = weightOfTheWave + 1;
                        }
                    }
                }
            }

            weightOfTheWave++;

            noMoreThanFewStrokes++;
            if (noMoreThanFewStrokes > range)  return false;
        }

        return true;
    }

    /**
     * Заполнение поля максимальными значениями
     */
    private void putMaximumValueInEveryCell() {

        for (int i = 0; i < fieldForWave.length; i++) {

            for (int j = 0; j < fieldForWave[i].length; j++) {

                fieldForWave[i][j] = Integer.MAX_VALUE;
            }
        }
    }
}
