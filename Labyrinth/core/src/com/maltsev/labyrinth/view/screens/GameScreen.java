package com.maltsev.labyrinth.view.screens;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.maltsev.labyrinth.presenter.Presenter;
import com.maltsev.labyrinth.presenter.View;
import com.maltsev.labyrinth.view.Labyrinth;
import com.maltsev.labyrinth.view.scenes.Fon;
import com.maltsev.labyrinth.view.scenes.Hud;
import com.maltsev.labyrinth.presenter.tempdata.SizeOfTexture;

/**
 * Игровой экран
 * Здесь происходит основное действо
 */
public class GameScreen implements Screen, View {

    private Labyrinth game;
    private Presenter presenter;
    private Hud hud;
    private Fon fon;
    private Vector3 touchPos;
    private SpriteBatch batch;
    private Texture block;
    private SizeOfTexture sizeOfBlock;
    private OrthographicCamera camera;


    GameScreen(final Labyrinth game) {

        this.game = game;

        presenter = new Presenter(this);

        batch = game.spriteBatch;

        camera = new OrthographicCamera();
        camera.setToOrtho(false, Labyrinth.V_WIDTH, Labyrinth.V_HEIGHT);

        hud = new Hud(batch);
        fon = new Fon(batch);

        touchPos = new Vector3();

        block = new Texture("block.png");
        sizeOfBlock = new SizeOfTexture(block.getWidth(), block.getHeight());
    }

    @Override
    public SizeOfTexture getSizeOfBlock() {

        return sizeOfBlock;
    }

    private void handelInput(float delta) {

        if (Gdx.input.justTouched()) {

            touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);

            camera.unproject(touchPos);

            camera.position.set(touchPos);
        }

    }

    private void update(float delta) {

        handelInput(delta);

        camera.update();
    }

    @Override
    public void render (float delta) {

        update(delta);

        fon.stage.draw();

        batch.setProjectionMatrix(camera.combined);

        batch.begin();
        presenter.drawPassableCells();
        batch.end();

        hud.stage.draw();
    }

    @Override
    public void drawBlock(float x, float y) {

        batch.draw(block, x, y);
    }

    @Override
    public void dispose () {

    }

    @Override
    public void show() {

    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

}
