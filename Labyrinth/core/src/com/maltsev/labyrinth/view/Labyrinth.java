package com.maltsev.labyrinth.view;


import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.maltsev.labyrinth.view.screens.GameScreen;
import com.maltsev.labyrinth.view.screens.MainMenuScreen;

/**
 * Главный класс из пакета view
 *
 * Представление, как правило, реализуется в Activity,
 * которая содержит ссылку на презентер.
 * Единственное, что делает представление,
 * это вызывает методы презентера при каком-либо действии пользователя
 */
public class Labyrinth extends Game {

    public static final int V_WIDTH = 1920;
    public static final int V_HEIGHT = 1080;

    MainMenuScreen mainMenuScreen;
    GameScreen gameScreen;

    public SpriteBatch spriteBatch;

    @Override
    public void create() {

        spriteBatch = new SpriteBatch();

        setMainMenuScreen();
    }

    public void setGameScreen() {

        gameScreen = new GameScreen(this);
        this.setScreen(gameScreen);

    }

    public void setMainMenuScreen() {

        mainMenuScreen = new MainMenuScreen(this);
        this.setScreen(mainMenuScreen);
    }

    @Override
    public void render() {

        super.render();
    }

    @Override
    public void dispose() {

        spriteBatch.dispose();

        super.dispose();
    }
}
