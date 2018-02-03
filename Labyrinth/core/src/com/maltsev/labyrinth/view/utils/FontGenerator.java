package com.maltsev.labyrinth.view.utils;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.utils.Disposable;


/**
 * Установка шрифта, под названием Roboto
 *
 * Такой способ задания шрифта продиктован используемым фреймворком
 */
public class FontGenerator implements Disposable {

    private FreeTypeFontGenerator generator;
    private FreeTypeFontGenerator.FreeTypeFontParameter parameter;

    public FontGenerator() {
        generator = new FreeTypeFontGenerator(Gdx.files.internal("font/font_for_title.ttf"));
        parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
    }

    public BitmapFont getFont() {
        parameter.size = 65;
        parameter.borderWidth = 0;
        return generator.generateFont(parameter);
    }

    public BitmapFont getBigFont() {
        parameter.size = 150;
        parameter.borderWidth = 0;
        return generator.generateFont(parameter);
    }

    @Override
    public void dispose() {
        generator.dispose();
    }
}
