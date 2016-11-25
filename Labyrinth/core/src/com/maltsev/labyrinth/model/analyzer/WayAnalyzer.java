package com.maltsev.labyrinth.model.analyzer;


import com.maltsev.labyrinth.model.Model;
import com.maltsev.labyrinth.model.field.PointOnTheField;

import java.util.ArrayList;

/**
 * Класс, главная задача которого проведение маршрута из одной точки в другую
 */
public class WayAnalyzer {

    /**
     * Построение пути из одной точки поля в другую
     * @param startPoint стартовая точка пути
     * @param finishPoint финишная точка пути
     * @return возвращяет либо путь в виде массива точек, либо пустой массив, если путь содержит более 5 шагов или он невозможен
     */
    static public ArrayList<PointOnTheField> getWay(PointOnTheField startPoint, PointOnTheField finishPoint) {

        Model model = Model.getInstance();

        int finishPointX = finishPoint.getX();
        int finishPointY = finishPoint.getY();

        if (finishPointX >= model.getSizeOfFieldX() || finishPointX < 0
                || finishPointY >= model.getSizeOfFieldY() || finishPointY < 0
                || !model.isItPassableCells(finishPoint)
                || startPoint.getX() >= model.getSizeOfFieldX() || startPoint.getX() < 0
                || startPoint.getY() >= model.getSizeOfFieldY() || startPoint.getY() < 0
                || !model.isItPassableCells(startPoint))
            return new ArrayList<PointOnTheField>();      // Проверка на правильность начальной и конечной точки

        int sizeOfFieldX = model.getSizeOfFieldX();
        int sizeOfFieldY = model.getSizeOfFieldY();

        int fieldForWave[][] = new int[sizeOfFieldX][sizeOfFieldY];

        for (int i = 0; i < fieldForWave.length; i++) {

            for (int j = 0; j < fieldForWave[i].length; j++) {

                fieldForWave[i][j] = Integer.MAX_VALUE;
            }
        }   // Заполнение поля максимальными значениями

        fieldForWave[startPoint.getX()][startPoint.getY()] = 0;    // стартовая точка волны
        fieldForWave[finishPointX][finishPointY] = Integer.MAX_VALUE;  // конечная точка волны

        int weightOfTheWave = 0;

        while (fieldForWave[finishPointX][finishPointY] == Integer.MAX_VALUE) {

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
        }   // в цикле из начальной точки выходит волна и заполняет всё поле

        PointOnTheField bufferPoint = new PointOnTheField(finishPoint);

        ArrayList<PointOnTheField> wayBack = new ArrayList<PointOnTheField>();
        wayBack.add(bufferPoint);

        int noMoreThanFiveStrokes = 0;

        while (!bufferPoint.equals(startPoint) && noMoreThanFiveStrokes <= 5) {

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

            noMoreThanFiveStrokes++;

            if (noMoreThanFiveStrokes > 5)  return new ArrayList<PointOnTheField>();

        } // проходим от конца к началу, запоминая путь

        ArrayList<PointOnTheField> way = new ArrayList<PointOnTheField>();

        for (int i = wayBack.size() - 1; i >=0 ; i--) {

            way.add(wayBack.get(i));
        }  // переворачиваем

        return way;
    }
}