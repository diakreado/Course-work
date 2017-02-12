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
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.maltsev.labyrinth.view.screens.GameScreen;

/**
 * Класс, который отвечает за отрисовку обёртки(вывод информирующих лейлбов поверх игрового экрана)
 */
public class Hud {

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


    public Hud(SpriteBatch spriteBatch, GameScreen gameScreen) {

        this.gameScreen = gameScreen;

        generateFont();

        Viewport viewport = new ScreenViewport(new OrthographicCamera());
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

        timeCount += delta;
        timer.setText("time: " + String.format("%.0f%n", timeCount));
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
    public void dispose() {

        skin.dispose();
        atlasUi.dispose();
        font.dispose();
        stage.dispose();
        fon.dispose();
    }
}
