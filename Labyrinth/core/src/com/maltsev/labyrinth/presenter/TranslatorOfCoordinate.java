package com.maltsev.labyrinth.presenter;


import com.maltsev.labyrinth.model.field.PointOnTheField;
import com.maltsev.labyrinth.presenter.tempdata.PointOnTheScreen;
import com.maltsev.labyrinth.presenter.tempdata.SizeOfTexture;

/**
 * Переводчик координат из системы экрана в систему игрового поля и обратно
 */
class TranslatorOfCoordinate {

    /**
     * Размер единичного блока (клеточка), из которого составляется игровое поле на экране
     */
    static private SizeOfTexture sizeOfBlock;

    /**
     *
     * @param sizeOfTexture структура содержащая размер блока
     * @return успешно ли прошла инициализация
     */
    static boolean initializeOfTranslator(SizeOfTexture sizeOfTexture) {

        if(sizeOfBlock != null)
            return false;

        sizeOfBlock = sizeOfTexture;
        return true;
    }

    /**
     * Перевод координат точки из координат поля в координаты экрана
     * @param pointOnTheField точка с координатами поля
     * @return точка с координатами экрана
     */
    static PointOnTheScreen translatePointFieldToScreen(PointOnTheField pointOnTheField) {

        if(sizeOfBlock == null)
            throw new TranslatorIsNotInitialize();

        return new PointOnTheScreen(pointOnTheField.getX() * sizeOfBlock.getWidth(),
                pointOnTheField.getY() * sizeOfBlock.getHeight());
    }

    /**
     * Перевод координат точки из координат экрана в координаты игрового поля
     * @param pointOnTheScreen точка с координатами экрана
     * @return точка с координатами игрового поля
     */
    static PointOnTheField translatePointScreenToField(PointOnTheScreen pointOnTheScreen) {

        if(sizeOfBlock == null)
            throw new TranslatorIsNotInitialize();

        return new PointOnTheField((int) pointOnTheScreen.getX() / sizeOfBlock.getWidth(),
                (int) pointOnTheScreen.getY() / sizeOfBlock.getHeight());
    }

}
