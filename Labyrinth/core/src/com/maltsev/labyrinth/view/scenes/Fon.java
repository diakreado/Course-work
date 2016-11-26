package com.maltsev.labyrinth.view.scenes;


import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

/**
 * Класс, который отвечает за отрисовку фона
 */
public class Fon {

    public Stage stage;
    private Viewport viewport;

    private Image img;
    private Texture fon;

    public Fon(SpriteBatch spriteBatch) {

        viewport = new ScreenViewport(new OrthographicCamera());
        stage = new Stage(viewport, spriteBatch);

        fon = new Texture("fon/grass.png");
        img = new Image(fon);

        img.setPosition(0,0);

        stage.addActor(img);
    }

    /**
     * Использовать при окончание работы с объектом
     */
    public void dispose() {

        fon.dispose();
        stage.dispose();
    }
}
