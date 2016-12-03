package com.maltsev.labyrinth.view.screens;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.freetype.*;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.StretchViewport;
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

        stage = new Stage(new StretchViewport(Labyrinth.V_WIDTH, Labyrinth.V_HEIGHT));

        fon = new Texture("menu_ui/fon.jpg");

        atlasUi = new TextureAtlas("menu_ui/menu.pack");

        skin = new Skin(atlasUi);

        buttonStyle = new ImageTextButton.ImageTextButtonStyle();

        buttonStyle.up = skin.getDrawable("blue_button04");
        buttonStyle.down = skin.getDrawable("blue_button04_down");

        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("font/some_font.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = 65;
        font = generator.generateFont(parameter);
        generator.dispose();

        buttonStyle.font = font;

        final MainMenuScreen i = this;

        play = new ImageTextButton("Start", buttonStyle);
        play.addListener(new ClickListener() {

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {

                game.setGameScreen();
                dispose();
            }
        });

        stage.addActor(play);
        play.setPosition((float)0.625 * Labyrinth.V_WIDTH, (float)0.4375 * Labyrinth.V_HEIGHT);

        Gdx.input.setInputProcessor(stage);
        Gdx.input.setCatchBackKey(true);
    }

    @Override
    public void render(float delta) {

        Gdx.gl.glClearColor(0.4f, 0.439f, 1f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        game.spriteBatch.begin();
        game.spriteBatch.draw(fon, 0, 0);
        game.spriteBatch.end();

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