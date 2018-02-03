package com.maltsev.labyrinth.view.game.scenes;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.viewport.*;
import com.maltsev.labyrinth.presenter.IPresenter;
import com.maltsev.labyrinth.view.Labyrinth;
import com.maltsev.labyrinth.view.utils.Resizable;
import com.maltsev.labyrinth.view.game.GameScreen;

/**
 * Класс, который отвечает за отрисовку обёртки(вывод информирующих лейлбов поверх игрового экрана)
 */
public class Hud implements Disposable, Resizable {

    private final Labyrinth labyrinth;

    public Stage hudStage;
    public Stage pauseStage;

    private BitmapFont font;
    private double timeCounter =0;

    private Label keys;
    private Label timer;

    private Table tableTop;

    private TextureAtlas atlasUi;
    private Skin skin;
    private ImageButton pauseButton;

    private Texture backPartOfControl;
    private Texture forwardPartOfControl;

    private ClickListener pauseListener;
    private GameScreen gameScreen;
    private ExtendViewport viewport;
    private OrthographicCamera camera;

    private boolean isStopTimer = false;
    private boolean typeOfControl;

    private Touchpad touchpad;
    private IPresenter presenter;
    private Texture border;
    private Image image;
    private boolean isItPasuse = false;

    public Hud(final Labyrinth labyrinth, final GameScreen gameScreen, com.maltsev.labyrinth.presenter.IPresenter presenter) {

        this.labyrinth = labyrinth;
        this.gameScreen = gameScreen;
        this.typeOfControl = labyrinth.infoAboutSettings.getTypeOfTheControl();
        this.presenter = presenter;

        generateFont();

        camera = new OrthographicCamera();
        camera.setToOrtho(false, Labyrinth.V_WIDTH, Labyrinth.V_HEIGHT);

        viewport = new ExtendViewport(Labyrinth.V_WIDTH, Labyrinth.V_HEIGHT, camera);

        hudStage = new Stage(viewport);

        Label.LabelStyle labelStyle = new Label.LabelStyle(font, Color.WHITE);

        timer  = new Label("",labelStyle);
        keys = new Label("", labelStyle);

        atlasUi = new TextureAtlas("hud_gui/hud_gui.pack");
        skin = new Skin(atlasUi);
        pauseButton = new ImageButton(skin.getDrawable("pause"), skin.getDrawable("pause1"));

        //registeredListenerAgain();

        backPartOfControl = new Texture("hud_gui/shadedDark07.png");
        forwardPartOfControl = new Texture("hud_gui/shadedDark01.png");

        tableTop = new Table();
        tableTop.top();
        tableTop.setFillParent(true);

        tableTop.add(timer).width(100).expandX().top();
        tableTop.add(keys).expandX().top();
        tableTop.add(pauseButton).expandX();

        hudStage.addActor(tableTop);

        if(typeOfControl) {

            touchpad = new Touchpad(10, new Touchpad.TouchpadStyle(
                    new TextureRegionDrawable(new TextureRegion(backPartOfControl)),
                    new TextureRegionDrawable(new TextureRegion(forwardPartOfControl))));

            touchpad.setPosition(100,100);

            hudStage.addActor(touchpad);
        }

        pauseStage = new Stage(viewport);

        border = new Texture("pause_ui/pause_screen.png");
        image = new Image(border);
        image.setPosition(375,220);

        Table table = new Table();
        table.setFillParent(true);
        table.center();

        pauseStage.addActor(image);
        pauseStage.addActor(table);
    }

    public void handleInput() {

        double sqrt2 = Math.sqrt(2);

        float coordinateOfTouchpadX = touchpad.getKnobPercentX();
        float coordinateOfTouchpadY = touchpad.getKnobPercentY();

        com.maltsev.labyrinth.model.field.PointOnTheField point = presenter.getPositionOfProtagonistInTheFieldCoordinate();

        if((Math.abs(coordinateOfTouchpadX) > sqrt2/2 || Math.abs(coordinateOfTouchpadY) > sqrt2/2) && !gameScreen.isLockInput()) {

            if(coordinateOfTouchpadX *sqrt2 < coordinateOfTouchpadY){

                if (coordinateOfTouchpadX * (-sqrt2) > coordinateOfTouchpadY) {

                    presenter.moveProtagonistInTheFieldCoordinate(point.getX() - 1, point.getY());
                } else {

                    presenter.moveProtagonistInTheFieldCoordinate(point.getX(), point.getY() + 1);
                }
            } else {

                if (coordinateOfTouchpadX * (-sqrt2) > coordinateOfTouchpadY) {

                    presenter.moveProtagonistInTheFieldCoordinate(point.getX(), point.getY() - 1);
                } else {

                    presenter.moveProtagonistInTheFieldCoordinate(point.getX() + 1, point.getY());
                }
            }
        }
    }

    private void registeredListenerAgain(){
        pauseListener = new ClickListener() {

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                setPause();
                registeredListenerAgain();
            }
        };
        pauseButton.addListener(pauseListener);
    }

    private void setPause() {
        isItPasuse = true;
        gameScreen.setPause();
        stopTimer();
    }

    public void setTime(float delta) {
        if(!isStopTimer)
            timeCounter += delta;
        timer.setText("time: " + String.format("%.0f%n", timeCounter));
    }

    public void stopTimer() {
        isStopTimer = true;
    }

    public void continueTimer() {
        isStopTimer = false;
    }

    public void setKeys(int numberOfKeys) {
        keys.setText("keys: " + numberOfKeys);
    }

    private void generateFont() {
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("font/abc.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = 70;
        font = generator.generateFont(parameter);
        generator.dispose();
    }

    @Override
    public void resize(int width, int height) {
        hudStage.getViewport().update(width, height, true);
    }

    @Override
    public void dispose() {
        skin.dispose();
        atlasUi.dispose();
        hudStage.dispose();
        font.dispose();
        backPartOfControl.dispose();
        forwardPartOfControl.dispose();
        border.dispose();
    }
}
