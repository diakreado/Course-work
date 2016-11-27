package com.maltsev.labyrinth.view.screens;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.maltsev.labyrinth.view.Labyrinth;

/**
 * Главное меню игры
 */
public class MainMenuScreen implements Screen{

    private Skin skin;
    private TextureAtlas atlasUi;
    private ImageTextButton.ImageTextButtonStyle buttonStyle;
    private Stage stage;
    private ImageTextButton play;
    private Labyrinth game;
    private BitmapFont font;
    private Texture fon;


    public MainMenuScreen(final Labyrinth game) {

        this.game = game;

        stage = new Stage(new ScreenViewport());

        fon = new Texture("menu_ui/fon.jpg");

        atlasUi = new TextureAtlas("menu_ui/menu.pack");

        skin = new Skin(atlasUi);

        buttonStyle = new ImageTextButton.ImageTextButtonStyle();

        buttonStyle.up = skin.getDrawable("blue_button04");
        buttonStyle.down = skin.getDrawable("blue_button04_down");
        font = new BitmapFont();
        buttonStyle.font = font;

        final MainMenuScreen i = this;

        play = new ImageTextButton("Start", buttonStyle);
        play.addListener(new ClickListener() {

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {

                game.setScreen(new GameScreen(game, i));
            }
        });    // На кнопку "Start" запускаем игру

        stage.addActor(play);
        play.setPosition((float)0.625 * Labyrinth.V_WIDTH, (float)0.4375 * Labyrinth.V_HEIGHT);

        Gdx.input.setInputProcessor(stage);  // Устанавливаем нашу сцену основным процессором для ввода (нажатия, касания, клавиатура etc.)
        Gdx.input.setCatchBackKey(true); // Это нужно для того, чтобы пользователь возвращался назад, в случае нажатия на кнопку Назад на своем устройстве
    }

    @Override
    public void render(float delta) {

        Gdx.gl.glClearColor(0.4f, 0.439f, 1f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        game.spriteBatch.begin();
        game.spriteBatch.draw(fon, 0, 0);
        game.spriteBatch.end();

        // Рисуем сцену
        stage.act(delta);
        stage.draw();
    }

    @Override
    public void dispose() {

        stage.dispose();
        atlasUi.dispose();
        skin.dispose();
        font.dispose();
    }

    public void turnOn() {

        game.startAgain();
        dispose();
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