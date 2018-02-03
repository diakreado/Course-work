package com.maltsev.labyrinth.view.game.scenes;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.maltsev.labyrinth.view.Labyrinth;
import com.maltsev.labyrinth.view.utils.Resizable;

/**
 * Класс, который отвечает за отрисовку фона
 *
 * Фон делается статическим, т.е. когда протагонист ходит по миру фон остаётся картинкой,
 * которая просто висит сзади игрового поля, по-моему это упрощает отрисовку
 */
public class Fon implements Disposable, Resizable{

    public Stage stage;
    private ExtendViewport viewport;

    private Image img;
    private Texture fon;

    private OrthographicCamera camera;

    public Fon(SpriteBatch spriteBatch) {
        camera = new OrthographicCamera();
        camera.setToOrtho(false, Labyrinth.V_WIDTH, Labyrinth.V_HEIGHT);

        viewport = new ExtendViewport(Labyrinth.V_WIDTH, Labyrinth.V_HEIGHT, camera);

        stage = new Stage(viewport, spriteBatch);

        fon = new Texture("fon/grass.png");
        img = new Image(fon);

        img.setPosition(0,0);

        stage.addActor(img);
    }

    @Override
    public void dispose() {
        fon.dispose();
        stage.dispose();
    }

    @Override
    public void resize(int width, int height) {

    }
}
