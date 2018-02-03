package com.maltsev.labyrinth.presenter.transkatorofcoordinate;


import com.maltsev.labyrinth.presenter.tempdata.PointOnTheScreen;

/**
 * Переводчик координат из системы экрана в систему игрового поля и обратно
 */
public class TranslatorOfCoordinate {

    /**
     * Размер единичного блока (клеточка), из которого составляется игровое поле на экране
     */
    static private com.maltsev.labyrinth.presenter.tempdata.SizeOfTexture sizeOfBlock;

    /**
     *
     * @param sizeOfTexture структура содержащая размер блока
     * @return успешно ли прошла инициализация
     */
    static public boolean initializeOfTranslator(com.maltsev.labyrinth.presenter.tempdata.SizeOfTexture sizeOfTexture) {

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
    static public PointOnTheScreen translatePointFieldToScreen(com.maltsev.labyrinth.model.field.PointOnTheField pointOnTheField) {

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
    static public com.maltsev.labyrinth.model.field.PointOnTheField translatePointScreenToField(PointOnTheScreen pointOnTheScreen) {

        if(sizeOfBlock == null)
            throw new TranslatorIsNotInitialize();

        return new com.maltsev.labyrinth.model.field.PointOnTheField((int) pointOnTheScreen.getX() / sizeOfBlock.getWidth(),
                (int) pointOnTheScreen.getY() / sizeOfBlock.getHeight());
    }

}
