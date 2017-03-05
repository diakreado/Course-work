package com.maltsev.labyrinth.view;


import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.maltsev.labyrinth.view.screens.GameScreen;
import com.maltsev.labyrinth.view.screens.MainMenuScreen;
import com.maltsev.labyrinth.view.screens.SettingsScreen;
import com.maltsev.labyrinth.view.utils.FontGenerator;
import com.maltsev.labyrinth.view.utils.InfoOfSettings;
import com.maltsev.labyrinth.view.utils.MenuButtonStyle;

/**
 * Главный класс из пакета view
 *
 * Общее определение View из шаблона проектирования MVP:
 * Представление, как правило, реализуется в Activity,
 * которая содержит ссылку на презентер.
 * Единственное, что делает представление,
 * это вызывает методы презентера при каком-либо действии пользователя
 *
 * Данный класс выступает в качестве управляющего экранами, т.е. чтобы переключиться между экранами вызываются его методы
 *
 * На данный момент существует два экрана:
 * экран с игровым меню - MainMenuScreen
 * экран с игровым процессом - GameScreen
 *
 * Предпологается, что если один экран создан, то второго быть не должно,
 * а как поступает запрос на смену экрана, то ссоздаётся новый, а старый удаляется
 */
public class Labyrinth extends Game {

    //TODO можно добавить автоматическую генерацию лабиринта с парметрами, а двери и ключи раскидывать по примитивному алгориитму
    //TODO выводить результат таймера на панель конца игры и возможно писать их в файл, а потом выводить в общую таблицу результатов
    //TODO добавить экран паузы
    //TODO сделать отрисовку передвижени плавной, возможно использовать спрайты движения
    //TODO сменить игровые текстуры

    public static final int V_WIDTH = 1920;
    public static final int V_HEIGHT = 1080;

    private float defaultWidth = 0;
    private float defaultHeight = 0;

    private MainMenuScreen mainMenuScreen;
    private GameScreen gameScreen;
    private SettingsScreen settingsScreen;

    public FontGenerator fontGenerator;

    public MenuButtonStyle menuButtonStyle;

    public SpriteBatch spriteBatch;

    public InfoOfSettings infoOfSettings;

    @Override
    public void create() {

        spriteBatch = new SpriteBatch();
        fontGenerator = new FontGenerator();
        menuButtonStyle = new MenuButtonStyle(this);
        settingsScreen = new SettingsScreen(this);
        infoOfSettings = new InfoOfSettings(0,false);

        setMainMenuScreen();
    }


    /**
     * Установка главным экраном - GameScreen
     * @param numberOfGameField - номер игрового поля выбранного пользователем
     */
    public void setGameScreen(int numberOfGameField) {

        if (defaultWidth + defaultHeight < 1) {

            defaultHeight = mainMenuScreen.getDefaultHeight();
            defaultWidth = mainMenuScreen.getDefaultWidth();
        }

        boolean choice = false;

        if(settingsScreen.getChoice() == 1) {
            choice = true;
        }

        infoOfSettings = new InfoOfSettings(numberOfGameField, choice);
        gameScreen = new GameScreen(this);
        this.setScreen(gameScreen);

        mainMenuScreen = null;
    }

    /**
     * Установка главным экраном MainMenuScreen
     */
    public void setMainMenuScreen() {

        mainMenuScreen = new MainMenuScreen(this);
        this.setScreen(mainMenuScreen);

        gameScreen = null;
    }

    public float getDefaultHeight() {

        return defaultHeight;
    }

    public float getDefaultWidth() {

        return defaultWidth;
    }

    public void openSettingsScreen() {

        this.setScreen(settingsScreen);
    }

    @Override
    public void render() {

        super.render();
    }

    @Override
    public void dispose() {

        spriteBatch.dispose();
        fontGenerator.dispose();
        menuButtonStyle.dispose();

        if(gameScreen != null)
            gameScreen.dispose();

        if(mainMenuScreen != null)
            mainMenuScreen.dispose();

        super.dispose();
    }
}
