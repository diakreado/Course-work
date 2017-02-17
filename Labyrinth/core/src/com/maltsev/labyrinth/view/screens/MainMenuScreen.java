package com.maltsev.labyrinth.view.screens;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.freetype.*;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.maltsev.labyrinth.view.Labyrinth;

/**
 * Главное меню игры
 */
public class MainMenuScreen implements Screen{

    private Skin skinForButton;
    private TextureAtlas atlasUiForButton;
    private ImageTextButton.ImageTextButtonStyle buttonStyle;
    private Stage stage;
    private ImageTextButton play;
    private Labyrinth game;
    private BitmapFont font;
    private Texture fon;
    private Table table;
    private ScrollPane scrollPane;
    private List list;
    private int numberOfGameField;

    public MainMenuScreen(final Labyrinth game) {

        this.game = game;

        numberOfGameField = 1;

        stage = new Stage(new ExtendViewport(Labyrinth.V_WIDTH, Labyrinth.V_HEIGHT));
        // ExtendViewport - сохраняет соотношение сторон
        // Сначала масштабируется мир по размеру окна просмотра,
        // затем короткий размер удлиняется до заполнения окна просмотра.

        table = new Table();

        fon = new Texture("menu_ui/fon.jpg");

        atlasUiForButton = new TextureAtlas("menu_ui/menu.pack");

        skinForButton = new Skin(atlasUiForButton);

        buttonStyle = new ImageTextButton.ImageTextButtonStyle();

        buttonStyle.up = skinForButton.getDrawable("blue_button04");
        buttonStyle.down = skinForButton.getDrawable("blue_button04_down");

        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("font/some_font.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = 65;
        font = generator.generateFont(parameter);
        generator.dispose();

        buttonStyle.font = font;

        play = new ImageTextButton("Start", buttonStyle);
        play.addListener(new ClickListener() {

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {

                game.setGameScreen(numberOfGameField);    //TODO Подумать о том, что бы не удалять главный экран
                dispose();
            }
        });

        table.center();
        table.setFillParent(true);

        Drawable drawable = new Drawable() {
            @Override
            public void draw(Batch batch, float x, float y, float width, float height) {

            }

            @Override
            public float getLeftWidth() {
                return 0;
            }

            @Override
            public void setLeftWidth(float leftWidth) {

            }

            @Override
            public float getRightWidth() {
                return 0;
            }

            @Override
            public void setRightWidth(float rightWidth) {

            }

            @Override
            public float getTopHeight() {
                return 0;
            }

            @Override
            public void setTopHeight(float topHeight) {

            }

            @Override
            public float getBottomHeight() {
                return 0;
            }

            @Override
            public void setBottomHeight(float bottomHeight) {

            }

            @Override
            public float getMinWidth() {
                return 0;
            }

            @Override
            public void setMinWidth(float minWidth) {

            }

            @Override
            public float getMinHeight() {
                return 0;
            }

            @Override
            public void setMinHeight(float minHeight) {

            }
        };

        List.ListStyle listStyle = new List.ListStyle(font,new Color(1,1,1,1),
                new Color(0,0,0,1),drawable);
        list = new List(listStyle);


        String[] inputData = {"one","two","three"};

        list.setItems(inputData);

        ScrollPane.ScrollPaneStyle scrollPaneStyle = new ScrollPane.ScrollPaneStyle(drawable,drawable,drawable,drawable,drawable);
        scrollPane= new ScrollPane(list, scrollPaneStyle);


        //table.add(scrollPane);
        table.add(play);

        //stage.addActor(new Image(fon));
        stage.addActor(table);

        Gdx.input.setInputProcessor(stage);
        Gdx.input.setCatchBackKey(true);
    }

    @Override
    public void render(float delta) {

        Gdx.gl.glClearColor(1f, 1f, 1f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        game.spriteBatch.begin();
        game.spriteBatch.draw(fon, 0, 0);
        game.spriteBatch.end();

        stage.act(delta);
        stage.draw();

        //System.out.println(list.getSelected());
    }

    @Override
    public void dispose() {

        skinForButton.dispose();
        stage.dispose();
        fon.dispose();
        font.dispose();
        atlasUiForButton.dispose();

        System.out.println();    //Временная бесполезная строка, созданая чтобы Idea не говорила о дублирование кода
                                // Просто аналогичным образом освобождаются ресурсы для Hud
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