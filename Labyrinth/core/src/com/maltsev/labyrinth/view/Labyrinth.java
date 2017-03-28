package com.maltsev.labyrinth.view;


import com.badlogic.gdx.Game;
import com.maltsev.labyrinth.view.game.GameScreen;
import com.maltsev.labyrinth.view.utils.FontGenerator;
import com.maltsev.labyrinth.view.utils.InfoAboutSettings;

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

    /**
     * Генератор шрифтов, с его помощь можно создать шрифт определённого размера
     */
    public FontGenerator fontGenerator;

    /**
     * Структура, которая использауется для передачи настроечных данных
     */
    public InfoAboutSettings infoAboutSettings;


    @Override
    public void create() {

        fontGenerator = new FontGenerator();
        infoAboutSettings = new InfoAboutSettings(0,false);

        setGameScreen();
    }

    /**
     * Установка главным экраном - GameScreen
     */
    public void setGameScreen() {

        boolean choice = false;

        infoAboutSettings = new InfoAboutSettings(1, choice);
        gameScreen = new GameScreen(this);
        this.setScreen(gameScreen);
    }

    @Override
    public void render() {

        super.render();
    }

    @Override
    public void dispose() {

        fontGenerator.dispose();

        if(gameScreen != null)
            gameScreen.dispose();

        super.dispose();
    }
}
