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
public class View extends ApplicationAdapter {                                     //TODO  Разобраться с камерой

    SpriteBatch batch;
    OrthographicCamera camera;

    Vector3 touchPos;

    Texture heroImg;
    Rectangle heroPosition;

    Texture block;

    Presenter presenter;


    @Override
    public void create () {

        presenter = new Presenter(this);

        camera = new OrthographicCamera();
        camera.setToOrtho(false,800,480);

        touchPos = new Vector3();

        batch = new SpriteBatch();

        heroImg = new Texture("protagonist.png");
        heroPosition = new Rectangle();

        block = new Texture("block.png");

        heroPosition.x = 0;
        heroPosition.y = 0;
    }

    @Override
    public void render () {

        Gdx.gl.glClearColor(255, 255, 255, 255);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        camera.update();

        batch.setProjectionMatrix(camera.combined);
        batch.begin();

            presenter.drawPassableCells(block);

            batch.draw(heroImg,heroPosition.x,heroPosition.y);

        batch.end();

        if (Gdx.input.isTouched()){

            touchPos.set(Gdx.input.getX(),Gdx.input.getY(),0);
            camera.unproject(touchPos);

            presenter.moveProtagonist(touchPos, heroPosition, block);
        }
    }

    public void drawBlock(float x, float y) {

         batch.draw(block, x, y);
    }

    @Override
    public void dispose () {

        super.dispose();
        batch.dispose();
        heroImg.dispose();
    }

}
