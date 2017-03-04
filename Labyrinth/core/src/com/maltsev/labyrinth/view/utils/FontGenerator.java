package com.maltsev.labyrinth.view.utils;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.utils.Disposable;

/**
 * Установка шрифта, под названием Roboto
 * метод создаёт два шрифта, по ссылкам font и fontForLabel
 * для fontForLabel дополнительно делаем шрифт жирным
 *
 * Такой способ задания шрифта продиктован используемым фреймворком
 */
public class FontGenerator implements Disposable {

    private FreeTypeFontGenerator generator;
    private FreeTypeFontGenerator.FreeTypeFontParameter parameter;

    public FontGenerator() {

        generator = new FreeTypeFontGenerator(Gdx.files.internal("font/some_font.ttf"));
        parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
    }

    public BitmapFont getFont() {

        parameter.size = 65;
        parameter.borderWidth = 0;
        return generator.generateFont(parameter);
    }

    public BitmapFont getSmallFont() {

        parameter.size = 50;
        parameter.borderWidth = 0;
        return generator.generateFont(parameter);
    }

    public BitmapFont getFontForLabel() {

        parameter.size = 65;
        parameter.borderWidth = 2;
        return generator.generateFont(parameter);                    //Делаем шрифт для Метки жирным
    }

    @Override
    public void dispose() {

        generator.dispose();
    }
}