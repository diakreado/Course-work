package com.maltsev.labyrinth.view.screens;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.maltsev.labyrinth.presenter.Presenter;
import com.maltsev.labyrinth.presenter.interfaces.View;
import com.maltsev.labyrinth.presenter.tempdata.PointOnTheScreen;
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

    private SpriteBatch batch;
    private OrthographicCamera camera;

    private Texture block;
    private SizeOfTexture sizeOfBlock;
    private Texture exit;
    private Texture protagonist;
    private Texture infoGameEnd;

    private Vector3 touchPos;
    private Vector3 positionOfProtagonist;

    private boolean lockInput = false;
    private boolean isGameEnd = false;

    private boolean isInMotion = false;

    public GameScreen(final Labyrinth game) {

        this.game = game;

        batch = game.spriteBatch;

        camera = new OrthographicCamera();
        camera.setToOrtho(false, Labyrinth.V_WIDTH, Labyrinth.V_HEIGHT);

        hud = new Hud(batch);
        fon = new Fon(batch);

        touchPos = new Vector3();

        positionOfProtagonist = new Vector3();

        block = new Texture("game_ui/block.png");
        sizeOfBlock = new SizeOfTexture(block.getWidth(), block.getHeight());

        exit = new Texture("game_ui/exit.png");

        protagonist = new Texture("game_ui/protagonist.png");

        infoGameEnd = new Texture("game_ui/grey_panel.png");

        presenter = new Presenter(this);

        changePositionOfProtagonist(presenter.getPositionOfProtagonist());

        camera.position.set(positionOfProtagonist);
        camera.update();
    }

    @Override
    public SizeOfTexture getSizeOfBlock() {

        return sizeOfBlock;
    }

    @Override
    public void lockInput() {

        lockInput = true;
    }

    @Override
    public void unlockInput() {

        lockInput = false;
    }

    @Override
    public void startMovement() {

        isInMotion = true;
    }

    @Override
    public void finishMovement() {

        isInMotion = false;
    }

    private void changePositionOfProtagonist(PointOnTheScreen pointOfNewLocationOfProtagonist) {

        positionOfProtagonist.x = pointOfNewLocationOfProtagonist.getX();
        positionOfProtagonist.y = pointOfNewLocationOfProtagonist.getY();
    }

    private void handelInput(float delta) {

        if (Gdx.input.justTouched() && !lockInput) {

            touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);

            camera.unproject(touchPos);

            presenter.moveProtagonist(touchPos.x, touchPos.y);
        }
    }

    private void update(float delta) {

        handelInput(delta);

        if (isInMotion)
            changePositionOfProtagonist(presenter.getPositionOfMovingProtagonist(delta));

        hud.setTime(delta);
    }

    /**
     * Здесь происходит вся отрисовка
     */
    private void draw(float delta) {

        presenter.drawField();
        batch.draw(protagonist, positionOfProtagonist.x, positionOfProtagonist.y);
        update(delta);

        if(isGameEnd) {

            batch.draw(infoGameEnd, positionOfProtagonist.x - infoGameEnd.getWidth()/2,
                    positionOfProtagonist.y - infoGameEnd.getHeight()/2);

            waitingAction();
        }

        camera.position.set(positionOfProtagonist);
        camera.update();
    }

    private void waitingAction() {

        if (Gdx.input.justTouched()) {

            this.close();
        }
    }

    @Override
    public void render (float delta) {

        fon.stage.draw();

        batch.setProjectionMatrix(camera.combined);

        // Следует вызывать методы отрисовки этого экрана только в пределах begin~end
        batch.begin();
        draw(delta);
        batch.end();

        hud.stage.draw();
    }

    @Override
    public void drawBlock(PointOnTheScreen point) {

        batch.draw(block, point.getX(), point.getY());
    }

    @Override
    public void drawExit(PointOnTheScreen point) {

        batch.draw(exit, point.getX(), point.getY());
    }

    private void close() {

        dispose();
        game.setMainMenuScreen();
    }

    @Override
    public void messageOfGameOver() {

        isGameEnd = true;
    }

    @Override
    public void dispose () {

        hud.dispose();
        fon.dispose();
        block.dispose();
        exit.dispose();
        protagonist.dispose();
        infoGameEnd.dispose();
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
