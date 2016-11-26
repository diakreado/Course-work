package com.maltsev.labyrinth.view.scenes;


import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
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

    Label countdownLabel;
    Label scoreLabel;
    Label timeLabel;
    Label levelLabel;
    Label worldLabel;
    Label labyrinthLabel;
    Integer info1 = 0;

    public Hud(SpriteBatch spriteBatch) {

        worldTimer = 0;
        timeCount = 0;
        score = 0;


        viewport = new ScreenViewport(new OrthographicCamera());
        stage = new Stage(viewport, spriteBatch);

        Table table = new Table();
        table.top();
        table.setFillParent(true);

        countdownLabel = new Label(info1.toString() , new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        scoreLabel = new Label("write here", new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        timeLabel = new Label("How many touch", new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        levelLabel = new Label("demo", new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        worldLabel = new Label("World", new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        labyrinthLabel = new Label("What i should ", new Label.LabelStyle(new BitmapFont(), Color.WHITE));

        table.add(labyrinthLabel).expandX().padTop(10);
        table.add(worldLabel).expandX().padTop(10);
        table.add(timeLabel).expandX().padTop(10);
        table.row();
        table.add(scoreLabel).expandX();
        table.add(levelLabel).expandX();
        table.add(countdownLabel).expandX();

        stage.addActor(table);
    }

    public void setInfo(int info) {

        info1 = info;
    }

    /**
     * Использовать при окончание работы с объектом
     */
    public void dispose() {

        stage.dispose();
    }
}
