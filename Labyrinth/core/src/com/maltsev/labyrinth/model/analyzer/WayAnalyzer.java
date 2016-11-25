package com.maltsev.labyrinth.model.analyzer;


import com.maltsev.labyrinth.model.Model;
import com.maltsev.labyrinth.model.field.PointOnTheField;

import java.util.ArrayList;

/**
 * Класс, главная задача которого проведение маршрута из одной точки в другую
 */
public class WayAnalyzer {

    private Model model;

    private int[][] fieldForWave;

    private final int DEFAULT_RANGE = 5;                // По факту эти поля вынесены сюда, чтобы было удобнее делигировать
                                                       //  выолнение различных частей алгоритма другим методам
    private int range;
    private PointOnTheField startPoint;
    private PointOnTheField finishPoint;

    private int sizeOfFieldX;
    private int sizeOfFieldY;

    /**
     * Может быть это кому-то интересно
     * @return дефолтная длина шага
     */
    public int getDEFAULT_RANGE() {

        return DEFAULT_RANGE;
    }

    /**
     * Метод по умолчанию, вызывает метод поиска пути с дефолтной длинной шага
     */
    @org.jetbrains.annotations.Nullable
    public ArrayList<PointOnTheField> getWay(final PointOnTheField startPoint, final PointOnTheField finishPoint) {

        return getWay(startPoint, finishPoint, DEFAULT_RANGE);
    }

    /**
     * Построение пути из одной точки поля в другую
     * @param startPoint стартовая точка пути
     * @param finishPoint финишная точка пути
     * @return возвращяет либо путь в виде массива точек, либо ноль, если путь содержит более range шагов или он невозможен
     */
    @org.jetbrains.annotations.Nullable
    public ArrayList<PointOnTheField> getWay(final PointOnTheField startPoint, final PointOnTheField finishPoint, int range) {

        model = Model.getInstance();

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

        ArrayList<PointOnTheField> wayBack = returnWave();

        if (wayBack == null) return null;

        ArrayList<PointOnTheField> way = new ArrayList<PointOnTheField>();

        for (int i = wayBack.size() - 1; i >=0 ; i--) {

            way.add(wayBack.get(i));
        }  // переворачиваем

        return way;
    }

    /**
     * Возвращение волны
     * @return либо путь возвращения в виде коллекции точек, либо null
     */
    @org.jetbrains.annotations.Nullable
    private ArrayList<PointOnTheField> returnWave() {

        ArrayList<PointOnTheField> wayBack = new ArrayList<PointOnTheField>();

        PointOnTheField bufferPoint = new PointOnTheField(finishPoint);

        wayBack.add(bufferPoint);

        int weightOfTheWave;
        int noMoreThanFewStrokes = 0;

        while (!bufferPoint.equals(startPoint)) {

            int bufferPointX = bufferPoint.getX();
            int bufferPointY = bufferPoint.getY();
            weightOfTheWave = fieldForWave[bufferPointX][bufferPointY];

            if (bufferPointX + 1 < sizeOfFieldX && fieldForWave[bufferPointX + 1][bufferPointY] == weightOfTheWave - 1) {

                bufferPoint = new PointOnTheField(bufferPointX + 1, bufferPointY);
                wayBack.add(bufferPoint);
            } else
            if (bufferPointY + 1 < sizeOfFieldY && fieldForWave[bufferPointX][bufferPointY + 1] == weightOfTheWave - 1) {

                bufferPoint = new PointOnTheField(bufferPointX, bufferPointY + 1);
                wayBack.add(bufferPoint);
            } else
            if (bufferPointX - 1 >= 0 && fieldForWave[bufferPointX - 1][bufferPointY] == weightOfTheWave - 1) {

                bufferPoint = new PointOnTheField(bufferPointX - 1, bufferPointY);
                wayBack.add(bufferPoint);
            } else
            if (bufferPointY - 1 >= 0 && fieldForWave[bufferPointX][bufferPointY - 1] == weightOfTheWave - 1) {

                bufferPoint = new PointOnTheField(bufferPointX, bufferPointY - 1);
                wayBack.add(bufferPoint);
            }

            noMoreThanFewStrokes++;

            if (noMoreThanFewStrokes > range)  return null;

        } // проходим от конца к началу, запоминая путь

        return wayBack;
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
