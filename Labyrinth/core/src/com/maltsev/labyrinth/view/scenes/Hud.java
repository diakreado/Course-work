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
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

/**
 * Класс, который отвечает за отрисовку обёртки(вывод информирующих лейлбов поверх игрового экрана)
 */
public class Hud {    //TODO много бесполезного надо почистить и решить, что вообще туда нужно выводить

    public Stage stage;
    private Viewport viewport;

    private Integer worldTimer;
    private float timeCount;
    private Integer score;

    private BitmapFont font;
    private Label.LabelStyle labelStyle;

    Label countdownLabel;
    Label scoreLabel;
    Label timeLabel;
    Label levelLabel;
    Label worldLabel;
    Label labyrinthLabel;

    public Hud(SpriteBatch spriteBatch) {

        worldTimer = 0;
        timeCount = 0;
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


        timeLabel = new Label("some info", labelStyle);
        worldLabel = new Label("write here", labelStyle);
        labyrinthLabel = new Label("I can", labelStyle);

        table.add(labyrinthLabel).expandX().padTop(10);
        table.add(worldLabel).expandX().padTop(10);
        table.add(timeLabel).expandX().padTop(10);

        stage.addActor(table);
    }

    /**
     * Использовать при окончание работы с объектом
     */
    public void dispose() {

        font.dispose();
        stage.dispose();
    }
}
