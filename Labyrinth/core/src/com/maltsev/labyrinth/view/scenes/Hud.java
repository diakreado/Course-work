package com.maltsev.labyrinth.view.scenes;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.viewport.*;
import com.maltsev.labyrinth.view.Labyrinth;
import com.maltsev.labyrinth.view.utils.Resizable;
import com.maltsev.labyrinth.view.screens.GameScreen;

/**
 * Класс, который отвечает за отрисовку обёртки(вывод информирующих лейлбов поверх игрового экрана)
 */
public class Hud implements Disposable, Resizable {

    public Stage stage;
    private BitmapFont font;

    private double timeCount =0;

    private Label keys;
    private Label timer;

    private Table tableTop;

    private ImageButton pauseButton;
    private TextureAtlas atlasUi;
    private Skin skin;

    private ClickListener pauseListener;

    private Texture fon;

    private GameScreen gameScreen;

    private ExtendViewport viewport;
    private OrthographicCamera camera;

    private boolean isStopTimer = false;


    public Hud(SpriteBatch spriteBatch, GameScreen gameScreen) {

        this.gameScreen = gameScreen;

        generateFont();

        camera = new OrthographicCamera();
        camera.setToOrtho(false, Labyrinth.V_WIDTH, Labyrinth.V_HEIGHT);

        viewport = new ExtendViewport(Labyrinth.V_WIDTH, Labyrinth.V_HEIGHT, camera);

        stage = new Stage(viewport, spriteBatch);

        Label.LabelStyle labelStyle = new Label.LabelStyle(font, Color.WHITE);

        timer  = new Label("",labelStyle);
        keys = new Label("", labelStyle);

        fon = new Texture("hud_gui/fon.png");
        final Image img = new Image(fon);

        atlasUi = new TextureAtlas("hud_gui/hud_ui.pack");
        skin = new Skin(atlasUi);
        pauseButton = new ImageButton(skin.getDrawable("yellow_button09"), skin.getDrawable("yellow_button090"));

        registeredListenerAgain();

        tableTop = new Table();
        tableTop.top();
        tableTop.setFillParent(true);

        tableTop.add(timer).width(100).expandX().top();
        tableTop.add(keys).expandX().top();
        tableTop.add(pauseButton).expandX();


        stage.addActor(tableTop);
    }

    private void registeredListenerAgain(){
        pauseListener = new ClickListener() {

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {

                gameScreen.close();
                registeredListenerAgain();
            }
        };
        pauseButton.addListener(pauseListener);
    }

    public void setTime(float delta) {

        if(!isStopTimer)
            timeCount += delta;
        timer.setText("time: " + String.format("%.0f%n", timeCount));
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
        parameter.size = 60;
        font = generator.generateFont(parameter);
        generator.dispose();
    }

    /**
     * Использовать при окончание работы с объектом
     */
    @Override
    public void dispose() {

        skin.dispose();
        atlasUi.dispose();
        stage.dispose();
        fon.dispose();
        font.dispose();
    }

    @Override
    public void resize(int width, int height) {

        stage.getViewport().update(width, height, true);
    }
}
