package com.maltsev.labyrinth.view.screens;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.maltsev.labyrinth.presenter.Presenter;
import com.maltsev.labyrinth.presenter.interfaces.View;
import com.maltsev.labyrinth.presenter.tempdata.PointOnTheScreen;
import com.maltsev.labyrinth.view.Labyrinth;
import com.maltsev.labyrinth.view.scenes.Fon;
import com.maltsev.labyrinth.view.scenes.Hud;
import com.maltsev.labyrinth.presenter.tempdata.SizeOfTexture;

/**
 * Игровой экран
 *
 * Здесь происходит основное действо
 *
 * Контактирует с классами Fon  и Hud, используя из для удобной отрисовки, с классом Presenter, выступает в качестве
 * View для него и как следствие обладает методами для отрисовки по запросу, а так же может обратиться к Labyrinth для того,
 * чтобы закончить игру и перевести управление к другому экрану, в частности к MainMenuScreen
 */
public class GameScreen implements Screen, View {

    private Labyrinth game;
    private Presenter presenter;
    private Hud hud;
    private Fon fonGameScreen;

    private SpriteBatch batch;
    private OrthographicCamera camera;

    private Texture block;
    private Texture horizontalCells;
    private Texture centerCells;

    private Texture leftTopCells;
    private Texture rightTopCells;
    private Texture leftBottomCells;
    private Texture rightBottomCells;

    private Texture leftTopRightCells;          //TODO ну тут TextureAtlas сам просится
    private Texture bottomLeftTopCells;
    private Texture rightBottomLeftCells;
    private Texture topRightBottomCells;

    private Texture rightEndCells;
    private Texture leftEndCells;
    private Texture topEndCells;
    private Texture bottomEndCells;

    private SizeOfTexture sizeOfBlock;
    private Texture exit;
    private Texture doorClose;
    private Texture doorOpen;       //TODO следовало бы объеденить их в один TextureAtlas, но пока так, потому что планируется замена текстур
    private Texture key;
    private Texture protagonist;    //TODO как вариант подгружать их всего один раз, при первом создании, а дальше уже только переисользовать
    private Texture infoGameEnd;

    private Vector3 touchPos;
    private Vector3 positionOfProtagonist;

    private boolean lockInput = false;
    private boolean isGameEnd = false;
    private boolean isItPause = false;
    private boolean isInMotion = false;

    private float defaultWidth;
    private float defaultHeight;

    private Stage stage;

    private boolean typeOfControl = false;

    public GameScreen(final Labyrinth game) {

        this.game = game;
        batch = game.spriteBatch;

        defaultHeight = game.getDefaultHeight();
        defaultWidth = game.getDefaultWidth();

        camera = new OrthographicCamera();
        camera.setToOrtho(false, Labyrinth.V_WIDTH, Labyrinth.V_HEIGHT);

        loadingOfTextures();

        typeOfControl = game.infoOfSettings.getTypeOfTheControl();

        touchPos = new Vector3();

        positionOfProtagonist = new Vector3();

        presenter = new Presenter(this, game.infoOfSettings.getNumberOfGameField());

        hud = new Hud(game, this, presenter);
        fonGameScreen = new Fon(batch);

        changePositionOfProtagonist(presenter.getPositionOfProtagonist());

        Gdx.input.setInputProcessor(hud.hudStage);

        camera.position.set(positionOfProtagonist);
        camera.update();

        stage = new Stage();
    }

    /**
     * Загрузка текстур
     * В это методе происходи создание объектов класса Texture, т.е. после завершения работы этого метода
     * каждой ссылке на объект будет поставленно в соответствие определённое изображение
     *
     * Здесь фиксируется размер Блока, из которого будет конструироваться игровое поле (дорога, по которой идёт протагонист)
     */
    private  void loadingOfTextures() {

        block = new Texture("game_ui/block.png");
        horizontalCells = new Texture("game_ui/horizontal_cell.png");
        centerCells = new Texture("game_ui/center_cell.png");

        leftTopCells = new Texture("game_ui/left_top_cell.png");
        rightTopCells = new Texture("game_ui/right_top_cell.png");
        leftBottomCells = new Texture("game_ui/left_bottom_cell.png");
        rightBottomCells = new Texture("game_ui/right_bottom_cell.png");

        rightEndCells = new Texture("game_ui/right_end_cell.png");
        leftEndCells = new Texture("game_ui/left_end_cell.png");
        topEndCells = new Texture("game_ui/top_end_cell.png");
        bottomEndCells = new Texture("game_ui/bottom_end_cell.png");

        leftTopRightCells = new Texture("game_ui/left_top_right_cell.png");
        rightBottomLeftCells = new Texture("game_ui/right_bottom_left_cell.png");
        bottomLeftTopCells = new Texture("game_ui/bottom_left_top_cell.png");
        topRightBottomCells = new Texture("game_ui/top_right_bottom_cell.png");

        sizeOfBlock = new SizeOfTexture(block.getWidth(), block.getHeight());
        exit = new Texture("game_ui/exit.png");
        doorClose = new Texture("game_ui/doorClose.png");
        doorOpen = new Texture("game_ui/doorOpen.png");
        key = new Texture("game_ui/key.png");
        protagonist = new Texture("game_ui/protagonist.png");
        infoGameEnd = new Texture("game_ui/grey_panel.png");
    }

    @Override
    public void dispose () {

        hud.dispose();
        fonGameScreen.dispose();
        block.dispose();
        exit.dispose();
        protagonist.dispose();
        infoGameEnd.dispose();

        infoGameEnd.dispose();
        protagonist.dispose();
        key.dispose();
        doorOpen.dispose();
        doorClose.dispose();
        horizontalCells.dispose();
        centerCells.dispose();
        leftTopCells.dispose();
        rightTopCells.dispose();
        leftBottomCells.dispose();
        rightBottomCells.dispose();
        leftTopRightCells.dispose();
        rightBottomLeftCells.dispose();
        topEndCells.dispose();
        bottomEndCells.dispose();
        leftTopRightCells.dispose();
        rightBottomLeftCells.dispose();
        bottomLeftTopCells.dispose();
        topRightBottomCells.dispose();
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

        if (Gdx.input.justTouched() && !lockInput && !typeOfControl) {

            touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);

            camera.unproject(touchPos);

            presenter.moveProtagonist(touchPos.x, touchPos.y);
        }

        if(typeOfControl && !lockInput)
            hud.handleInput();
    }

    public void setPause() {

        lockInput();
        Gdx.input.setInputProcessor(hud.pauseStage);
        isItPause = true;
    }

    private void update(float delta) {

        handelInput(delta);

        if (isInMotion)
            changePositionOfProtagonist(presenter.getPositionOfMovingProtagonist(delta));

        hud.setTime(delta);

        hud.setKeys(presenter.getNumberOfKeys());
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

        fonGameScreen.stage.draw();

        batch.setProjectionMatrix(camera.combined);

        // Следует вызывать методы отрисовки этого экрана только в пределах begin~end
        batch.begin();
        draw(delta);
        batch.end();

        hud.hudStage.draw();
        if(isItPause)
            hud.pauseStage.draw();
    }

    @Override
    public void drawExit(PointOnTheScreen point) {

        batch.draw(exit, point.getX(), point.getY());
    }

    @Override
    public void drawKey(PointOnTheScreen point) {

        batch.draw(key, point.getX(), point.getY());
    }

    @Override
    public void drawCloseDoor(PointOnTheScreen point) {

        batch.draw(doorClose, point.getX(), point.getY());
    }

    @Override
    public void drawOpenDoor(PointOnTheScreen point) {

        batch.draw(doorOpen, point.getX(), point.getY());
    }

    public void close() {

        dispose();
        game.setMainMenuScreen();
    }

    @Override
    public void messageOfGameOver() {

        isGameEnd = true;
        hud.stopTimer();

        Gdx.input.setInputProcessor(stage);  //Костыль, чтобы убрать ввыод с Hud, без этого при конце игры нажатие на кнопку выхода в меню ломает приложение
    }

    @Override
    public void resize(int width, int height) {

        fonGameScreen.resize(width,height);
        hud.resize(width, height);

        camera.viewportWidth = Labyrinth.V_WIDTH * (width/defaultWidth);
        camera.viewportHeight = Labyrinth.V_HEIGHT * (height/defaultHeight);

        camera.update();
    }

    public boolean isLockInput() {

        return lockInput;
    }

    @Override
    public void show() {

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

    @Override
    public void drawHorizontalCells(PointOnTheScreen point) {

        batch.draw(horizontalCells, point.getX(), point.getY());
    }

    @Override
    public void drawCenterCells(PointOnTheScreen point) {

        batch.draw(centerCells, point.getX(), point.getY());
    }

    @Override
    public void drawLeftTopCells(PointOnTheScreen point) {

        batch.draw(leftTopCells, point.getX(), point.getY());
    }

    @Override
    public void drawRightTopCells(PointOnTheScreen point) {

        batch.draw(rightTopCells, point.getX(), point.getY());
    }

    @Override
    public void drawLeftBottomCells(PointOnTheScreen point) {

        batch.draw(leftBottomCells, point.getX(), point.getY());
    }

    @Override
    public void drawRightBottomCells(PointOnTheScreen point) {

        batch.draw(rightBottomCells, point.getX(), point.getY());
    }

    @Override
    public void drawRightEndCells(PointOnTheScreen point) {

        batch.draw(rightEndCells, point.getX(), point.getY());
    }

    @Override
    public void drawLeftEndCells(PointOnTheScreen point) {

        batch.draw(leftEndCells, point.getX(), point.getY());
    }

    @Override
    public void drawTopEndCells(PointOnTheScreen point) {

        batch.draw(topEndCells, point.getX(), point.getY());
    }

    @Override
    public void drawBottomEndCells(PointOnTheScreen point) {

        batch.draw(bottomEndCells, point.getX(), point.getY());
    }

    @Override
    public void drawLeftTopRightCells(PointOnTheScreen point) {

        batch.draw(leftTopRightCells, point.getX(), point.getY());
    }

    @Override
    public void drawBottomLeftTopCells(PointOnTheScreen point) {

        batch.draw(bottomLeftTopCells, point.getX(), point.getY());
    }

    @Override
    public void drawRightBottomLeftCells(PointOnTheScreen point) {

        batch.draw(rightBottomLeftCells, point.getX(), point.getY());
    }

    @Override
    public void drawTopRightBottomCells(PointOnTheScreen point) {

        batch.draw(topRightBottomCells, point.getX(), point.getY());
    }

    @Override
    public void drawBlock(PointOnTheScreen point) {

        batch.draw(block, point.getX(), point.getY());
    }
}
