package com.maltsev.labyrinth.view;

import com.badlogic.gdx.Game;
import com.maltsev.labyrinth.view.game.GameScreen;
import com.maltsev.labyrinth.view.menu.MenuScreen;
import com.maltsev.labyrinth.view.utils.FontGenerator;
import com.maltsev.labyrinth.view.utils.InfoAboutSettings;

/**
 * Class organize correct interaction between screens
 */
public class Labyrinth extends Game {

    // Default size of the screen
    // If you have different size, screen will be resized
    public static final int V_WIDTH = 1920;
    public static final int V_HEIGHT = 1080;

    private GameScreen gameScreen;
    private MenuScreen menuScreen;

    public FontGenerator fontGenerator;
    public InfoAboutSettings infoAboutSettings = new InfoAboutSettings(
            "11111111111d11111f\n" +
                      "1111111111110g0t0t\n" +
                      "111111111111k111s1\n" +
                      "tttttttttttttttttt\n" +
                      "tttttttttttttttttt\n" +
                      "ttttttttgggttttttt\n" +
                      "tttttttttttttttttt\n" +
                      "tttttttttttttttttt",true);

    public Labyrinth() {
    }

    @Override
    public void create() {
        fontGenerator = new FontGenerator();
        menuScreen = new MenuScreen(this);

        setMenuScreen();
    }

    public void setGameScreen() {
        gameScreen = new GameScreen(this);

        this.setScreen(gameScreen);
    }

    public void closeGameScreen() {
        gameScreen.dispose();

        setMenuScreen();
    }

    public void setMenuScreen() {
        setScreen(menuScreen);
        menuScreen.activateScreen();
    }

    public void closeMenuScreen() {
        menuScreen.dispose();
    }

    @Override
    public void render() {
        super.render();
    }

    @Override
    public void dispose() {
        closeMenuScreen();

        fontGenerator.dispose();

        super.dispose();
    }
}
