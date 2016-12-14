package com.maltsev.labyrinth.view.scenes;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.StringBuilder;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.maltsev.labyrinth.view.Labyrinth;

/**
 * Класс, который отвечает за отрисовку обёртки(вывод информирующих лейлбов поверх игрового экрана)
 */
public class Hud {    //TODO много бесполезного надо почистить и решить, что вообще туда нужно выводить

    public Stage stage;
    private BitmapFont font;

    private double timeCount =0;

    private Label keys;
    private Label timer;


    public Hud(SpriteBatch spriteBatch) {

        generateFont();

        Viewport viewport = new ScreenViewport(new OrthographicCamera());
        stage = new Stage(viewport, spriteBatch);

        Label.LabelStyle labelStyle = new Label.LabelStyle(font, Color.WHITE);

        timer  = new Label("",labelStyle);
        keys = new Label("", labelStyle);


        Table table = new Table();
        table.top();
        table.setFillParent(true);

        table.add(timer).width(100).expandX().top();
        table.add(keys).expandX().top();

        stage.addActor(table);
    }

    public void setTime(float delta) {

        timeCount += delta;

        timer.setText("time: " + String.format("%.0f%n", timeCount));
    }

    public void setKeys(int numberOfKeys) {

        keys.setText("keys: " + numberOfKeys);
    }

    private void generateFont() {

        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("font/abc.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = 60;
        font = generator.generateFont(parameter);
        generator.dispose();
    }

    /**
     * Использовать при окончание работы с объектом
     */
    public void dispose() {

        font.dispose();
        stage.dispose();
    }
}
