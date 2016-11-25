package com.maltsev.labyrinth.view;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.maltsev.labyrinth.presenter.Presenter;

public class GameScreen implements Screen {


    private SpriteBatch batch;
    private OrthographicCamera camera;

    private Vector3 touchPos;

    private Texture protagonistImg;
    private Rectangle protagonistPosition;

    private Texture block;
    private Texture finishingBlock;

    private Texture grass;

    private Presenter presenter;

    private int protagonistWigth;
    private int protagonistHight;

    private int blockWigth;
    private int blockHight;

    final Labyrinth game;

    public GameScreen(final Labyrinth gam) {
        this.game = gam;

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
        grass = new Texture("rpgTile019.png");



        blockWigth = block.getWidth();
        blockHight = block.getHeight();

        protagonistPosition.x = presenter.getPositionOfProtagonist().getX();
        protagonistPosition.y = presenter.getPositionOfProtagonist().getY();
    }

    @Override
    public void show() {

    }

    @Override
    public void render (float delta) {

        Gdx.gl.glClearColor(255, 255, 255, 255);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.setProjectionMatrix(camera.combined);
        batch.begin();

        for(int x = -500; x < 1000; x+=64) {

            for(int y = -500; y < 2000; y+=64) {

                batch.draw(grass,x,y);
            }
        }

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

    public void drawBlock(float x, float y) {

        batch.draw(block, x, y);
    }

    public void drawFinishingBlock(float x, float y) {

        batch.draw(finishingBlock, x, y);
    }

    @Override
    public void dispose () {

        //super.dispose();
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
