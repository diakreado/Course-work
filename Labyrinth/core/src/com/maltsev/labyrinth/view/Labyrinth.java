package com.maltsev.labyrinth.view;


import com.badlogic.gdx.Game;
import com.maltsev.labyrinth.view.game.GameScreen;
import com.maltsev.labyrinth.view.utils.FontGenerator;
import com.maltsev.labyrinth.view.utils.InfoAboutSettings;
import com.maltsev.labyrinth.view.utils.ViewSwitcher;

/**
 * Главный класс из пакета view
 *
 * Общее определение IGameScreen из шаблона проектирования MVP:
 * Представление, как правило, реализуется в Activity,
 * которая содержит ссылку на презентер.
 * Единственное, что делает представление,
 * это вызывает методы презентера при каком-либо действии пользователя
 */
public class Labyrinth extends Game {

    // Размеры экрана, на которые ориентируются при отрисовке, далее если размеры реального экрана
    // не совпадают с ними происходит  масштабирование
    public static final int V_WIDTH = 1920;
    public static final int V_HEIGHT = 1080;

    private GameScreen gameScreen;
    private ViewSwitcher switcher;
    public FontGenerator fontGenerator;
    public InfoAboutSettings infoAboutSettings;

    public Labyrinth(ViewSwitcher switcher,InfoAboutSettings infoAboutSettings) {
        this.infoAboutSettings = infoAboutSettings;
        this.switcher = switcher;
    }

    @Override
    public void create() {
        fontGenerator = new FontGenerator();
        setGameScreen();
    }

    public void setGameScreen() {
        gameScreen = new GameScreen(this);
        this.setScreen(gameScreen);
    }

    public void closeGameScreen() {
        gameScreen.dispose();
        switcher.exitFromGame();
    }

    @Override
    public void render() {
        super.render();
    }

    @Override
    public void dispose() {
        fontGenerator.dispose();
        super.dispose();
    }
}
