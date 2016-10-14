package com.maltsev.labyrinth.view;


import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

import com.maltsev.labyrinth.presenter.Presenter;


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

    Texture block;

    Presenter presenter;


    @Override
    public void create () {

        presenter = new Presenter();

        camera = new OrthographicCamera();
        camera.setToOrtho(false,800,480);

        touchPos = new Vector3();

        batch = new SpriteBatch();

        heroImg = new Texture("protagonist.png");
        heroPosition = new Rectangle();

        block = new Texture("block.png");

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

            batch.draw(block, 0, 0);
            batch.draw(block, presenter.getSize()*70, presenter.getSize()*70);

            batch.draw(heroImg,heroPosition.x,heroPosition.y);

        batch.end();

        if (Gdx.input.isTouched()){

            touchPos.set(Gdx.input.getX(),Gdx.input.getY(),0);
            camera.unproject(touchPos);

            float heroPositionStartX = heroPosition.x;
            float heroPositionStartY = heroPosition.y;

            heroPosition.x = ((int)touchPos.x / 70)*70;
            heroPosition.y = ((int)touchPos.y / 70)*70;

            if (heroPosition.x < 0) heroPosition.x = 0;
            if (heroPosition.x > presenter.getSize()*70) heroPosition.x = presenter.getSize()*70;
            if (heroPosition.y < 0) heroPosition.y = 0;
            if (heroPosition.y > presenter.getSize()*70) heroPosition.y = presenter.getSize()*70;

            int x = (int)(heroPosition.x / 70);
            int y = (int)(heroPosition.y / 70);

            if( !(presenter.canIWalkHere(x, y))) {

                heroPosition.x = heroPositionStartX;
                heroPosition.y = heroPositionStartY;
            }
        }
    }

    @Override
    public void dispose () {

        super.dispose();
        batch.dispose();
        heroImg.dispose();
    }

}
