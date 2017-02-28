package com.maltsev.labyrinth.view.screens;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.freetype.*;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.maltsev.labyrinth.view.Labyrinth;
import com.maltsev.labyrinth.view.utils.DrawableObject;

/**
 * Главное меню игры
 *
 * Из главного меню можно выбрать уровень и начать игру
 *
 * При нажатие кнопки Start  начинается игра и с помощью Labyrinth управление передаётся экрану GameScreen
 */
public class MainMenuScreen implements Screen{

    //TODO Планируется добавить возможность просматривать результаты  прошедших игр

    private Skin skinForButton;
    private TextureAtlas atlasUiForButton;
    private ImageTextButton.ImageTextButtonStyle buttonStyle;
    private Stage stage;
    private ImageTextButton play;
    private Labyrinth game;
    private BitmapFont font;
    private BitmapFont fontForLabel;
    private Table table;
    private ScrollPane scrollPane;
    private List list;

    public MainMenuScreen(final Labyrinth game) {

        this.game = game;

        stage = new Stage(new ExtendViewport(Labyrinth.V_WIDTH, Labyrinth.V_HEIGHT));
        // ExtendViewport - сохраняет соотношение сторон
        // Сначала масштабируется мир по размеру окна просмотра,
        // затем короткий размер удлиняется до заполнения окна просмотра.


        atlasUiForButton = new TextureAtlas("menu_ui/menu.pack");

        skinForButton = new Skin(atlasUiForButton);

        buttonStyle = new ImageTextButton.ImageTextButtonStyle();
        buttonStyle.up = skinForButton.getDrawable("blue_button04");
        buttonStyle.down = skinForButton.getDrawable("blue_button04_down");

        setUpFont();
        buttonStyle.font = font;

        play = new ImageTextButton("Start", buttonStyle);
        play.addListener(new ClickListener() {

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {



                game.setGameScreen(list.getSelectedIndex());
                dispose();
            }
        });

        //TODO Этот список надо получать из файла с игровыми полями, скорее всего от Presenter'a, но это не точно
        String[] inputData = {"one","two","three","four","five","six","seven","eight","nine","ten",
                "eleven","twelve","thirteen","fourteen","fifteen","sixteen","seventeen","eighteen"};

        List.ListStyle listStyle = new List.ListStyle(font,new Color(0.96f,0.0f,0.16f,1),
                new Color(0,0,0,1), new DrawableObject());
        list = new List(listStyle);

        list.setItems(inputData);

        ScrollPane.ScrollPaneStyle scrollPaneStyle = new ScrollPane.ScrollPaneStyle();
        scrollPane= new ScrollPane(list, scrollPaneStyle);


        Label label = new Label("Select the level:", new Label.LabelStyle(fontForLabel,new Color(0,0,0,1)));


        table = new Table();

        table.center();
        table.setFillParent(true);

        table.add(label).padRight(400);                     //Размещени объектов на сцене
        table.row();
        table.add(scrollPane).padRight(300);
        table.add(play);
        stage.addActor(table);

        Gdx.input.setInputProcessor(stage);
    }

    /**
     * Установка шрифта, под названием Roboto
     * метод создаёт два шрифта, по ссылкам font и fontForLabel
     * для fontForLabel дополнительно делаем шрифт жирным
     *
     * Такой способ задания шрифта продиктован используемым фреймворком
     */
    private void setUpFont() {

        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("font/some_font.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();

        parameter.size = 65;
        font = generator.generateFont(parameter);

        parameter.borderWidth = 2;
        fontForLabel = generator.generateFont(parameter);                    //Делаем шрифт для Метки жирным

        generator.dispose();
    }

    /**
     * Закраска фона в Белый цвет
     */
    private void fillingBackground() {

        Gdx.gl.glClearColor(1f, 1f, 1f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

    }

    @Override
    public void render(float delta) {

        fillingBackground();

        stage.act(delta);
        stage.draw();
    }

    @Override
    public void dispose() {

        skinForButton.dispose();
        stage.dispose();
        font.dispose();
        atlasUiForButton.dispose();
    }

    @Override
    public void resize(int width, int height) {

        stage.getViewport().update(width, height, true);
    }

    @Override
    public void show() {
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