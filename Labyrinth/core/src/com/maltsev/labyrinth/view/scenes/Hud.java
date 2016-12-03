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
    private Viewport viewport;

    private Integer worldTimer;
    private double timeCount =0;
    private Integer score;

    private BitmapFont font;
    private Label.LabelStyle labelStyle;

    Label countdownLabel;
    Label scoreLabel;
    Label timeLabel;
    Label levelLabel;
    Label worldLabel;
    Label labyrinthLabel;

    Label timer;

    public Hud(SpriteBatch spriteBatch) {

        worldTimer = 0;
        score = 0;


        viewport = new ScreenViewport(new OrthographicCamera());
        stage = new Stage(viewport, spriteBatch);

        Table table = new Table();
        table.top();
        table.setFillParent(true);

        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("font/abc.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = 60;
        font = generator.generateFont(parameter);
        generator.dispose();

        labelStyle = new Label.LabelStyle(font, Color.WHITE);


        timeLabel = new Label("Time", labelStyle);
        worldLabel = new Label("write here", labelStyle);
        labyrinthLabel = new Label("Time", labelStyle);

        timer  = new Label("0000000",labelStyle);

        table.add(timer);



        stage.addActor(table);
    }

    public void setTime(float delta) {

        timeCount += delta;

        timer.setText(String.format("%.0f%n", timeCount));
    }

    /**
     * Использовать при окончание работы с объектом
     */
    public void dispose() {

        font.dispose();
        stage.dispose();
    }
}
