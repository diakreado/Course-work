package ru.maltsev.mygame.view;

/**
 * Created by Mikle on 10.10.2016.
 */

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

import ru.maltsev.mygame.presenter.Presenter;

/**
 * View, как правило, реализуется в Activity,
 * которая содержит ссылку на презентер.
 * Единственное, что делает View,
 * это вызывает методы презентера при каком-либо действии пользователя
 */
public class View extends ApplicationAdapter {

    SpriteBatch batch;
    OrthographicCamera camera;

    Vector3 touchPos;

    Texture heroImg;
    Rectangle heroPosition;

    Presenter presenter;


    @Override
    public void create () {

        presenter = new Presenter();

        camera = new OrthographicCamera();
        camera.setToOrtho(false,800,480);

        touchPos = new Vector3();

        batch = new SpriteBatch();

        heroImg = new Texture("hero.png");
        heroPosition = new Rectangle();

        heroPosition.x = 100;
        heroPosition.y = 100;
    }

    @Override
    public void render () {

        Gdx.gl.glClearColor(0, 0, 0, 0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        camera.update();

        batch.setProjectionMatrix(camera.combined);
        batch.begin();

        batch.draw(heroImg,heroPosition.x,heroPosition.y);

        batch.end();

        if (Gdx.input.isTouched()){

            touchPos.set(Gdx.input.getX(),Gdx.input.getY(),0);
            camera.unproject(touchPos);

            heroPosition = presenter.pressOnTheScreen(touchPos);
        }
    }

    @Override
    public void dispose () {

        super.dispose();
        batch.dispose();
        heroImg.dispose();
    }

}
