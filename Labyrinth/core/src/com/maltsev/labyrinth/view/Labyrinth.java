package com.maltsev.labyrinth.view;


import com.badlogic.gdx.Game;
import com.maltsev.labyrinth.presenter.IPresenter;
import com.maltsev.labyrinth.view.screens.Game.GameScreen;
import com.maltsev.labyrinth.view.screens.MainMenu.MainMenuScreen;
import com.maltsev.labyrinth.view.screens.MainMenu.SettingsScreen;
import com.maltsev.labyrinth.view.utils.FontGenerator;
import com.maltsev.labyrinth.view.utils.InfoOfSettings;
import com.maltsev.labyrinth.view.screens.MainMenu.MenuButtonStyle;

/**
 * Главный класс из пакета view
 *
 * Общее определение IGameScreen из шаблона проектирования MVP:
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


    // Размеры экрана, на которые ориентируются при отрисовке, далее если размеры реального экрана
    // не совпадают с ними происходит  масштабирование
    public static final int V_WIDTH = 1920;
    public static final int V_HEIGHT = 1080;


    // Реальниые размеры экрана, которые устанавливаются, как размеры первого открывшегося окна
    private float defaultWidth = 0;
    private float defaultHeight = 0;

    // Игровые экраны

    /**
     * Главный экран, изначально открывается он
     */
    private MainMenuScreen mainMenuScreen;

    /**
     * Экран на котором представлен непосредственно лабиринт
     */
    private GameScreen gameScreen;

    /**
     * Экран с настройками вызывается из меню и служит для настройки приложения под прихоти пользователя
     */
    private SettingsScreen settingsScreen;

    /**
     * Генератор шрифтов, с его помощь можно создать шрифт определённого размера
     */
    public FontGenerator fontGenerator;

    /**
     * Класс определяющий стиль кнопки, созданый для удобного создания идентичных кнопок
     */
    public MenuButtonStyle menuButtonStyle;


    /**
     * Структура, которая использауется для передачи настроечных данных
     */
    public InfoOfSettings infoOfSettings;


    /**
     * Главный
     */
    private IPresenter IPresenter;

    @Override
    public void create() {

        fontGenerator = new FontGenerator();
        menuButtonStyle = new MenuButtonStyle(this);
        settingsScreen = new SettingsScreen(this);
        infoOfSettings = new InfoOfSettings(0,false, 0);

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

        if(settingsScreen.getChoiceAboutControl() == 1) {
            choice = true;
        }

        infoOfSettings = new InfoOfSettings(numberOfGameField, choice, settingsScreen.getChoiceAboutTexture());
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

        fontGenerator.dispose();
        menuButtonStyle.dispose();

        if(gameScreen != null)
            gameScreen.dispose();

        if(mainMenuScreen != null)
            mainMenuScreen.dispose();

        super.dispose();
    }
}
