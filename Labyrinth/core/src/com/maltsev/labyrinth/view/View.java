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
public class View extends ApplicationAdapter {                                     //TODO Использовать TexturePacker

    SpriteBatch batch;
    OrthographicCamera camera;

    Vector3 touchPos;

    Texture protagonistImg;
    Rectangle protagonistPosition;

    Texture block;
    Texture finishingBlock;

    Presenter presenter;

    int protagonistWigth;
    int protagonistHight;

    int blockWigth;
    int blockHight;

    @Override
    public void create () {

        presenter = new Presenter(this);

        camera = new OrthographicCamera();
        camera.setToOrtho(false,800,480);

        touchPos = new Vector3();

        batch = new SpriteBatch();

        protagonistImg = new Texture("protagonist.png");
        protagonistPosition = new Rectangle();

        protagonistWigth = protagonistImg.getWidth();
        protagonistHight = protagonistImg.getHeight();


        block = new Texture("block.png");
        finishingBlock = new Texture("blockFinish.png");

        blockWigth = block.getWidth();
        blockHight = block.getHeight();

        protagonistPosition.x = presenter.getPositionOfProtagonist().getX();
        protagonistPosition.y = presenter.getPositionOfProtagonist().getY();
    }

    @Override
    public void render () {

        Gdx.gl.glClearColor(255, 255, 255, 255);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.setProjectionMatrix(camera.combined);
        batch.begin();

            presenter.drawPassableCells();

            batch.draw(protagonistImg, protagonistPosition.x, protagonistPosition.y);

             if (Gdx.input.justTouched()){

            touchPos.set(Gdx.input.getX(),Gdx.input.getY(),0);
            camera.unproject(touchPos);

            presenter.moveProtagonist(touchPos, protagonistPosition);
            }


        batch.end();

        camera.position.set(protagonistPosition.x, protagonistPosition.y,0);

        camera.update();
    }

    public void drawBlock(float x, float y) {

         batch.draw(block, x, y);
    }

    public void drawFinishingBlock(float x, float y) {

        batch.draw(finishingBlock, x, y);
    }

    @Override
    public void dispose () {

        super.dispose();
        batch.dispose();
        protagonistImg.dispose();
    }

    /**
     * @return ширина изображения для протагониста
     */
    public int getProtagonistWigth() {

        return protagonistWigth;
    }

    /**
     * @return высота изображения для протагониста
     */
    public int getProtagonistHight() {

        return protagonistHight;
    }

    /**
     * @return ширина изображения для клеточки
     */
    public int getBlockWigth() {

        return blockWigth;
    }

    /**
     * @return высота изображения для клеточки
     */
    public int getBlockHight() {

        return blockHight;
    }

}
