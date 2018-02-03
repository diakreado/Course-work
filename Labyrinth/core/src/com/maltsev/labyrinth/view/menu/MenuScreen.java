package com.maltsev.labyrinth.view.menu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.maltsev.labyrinth.view.Labyrinth;

public class MenuScreen implements Screen {

    /**  <Disposable   */
    private Stage stage;

    private TextureAtlas atlasUi;
    private Skin skin;

    private Texture textureOfProtagonist;
    /**  disposable>     */

    private Table table;
    private Table tableForTitle;
    private Table tableForButtons;

    private final Labyrinth labyrinth;


    public MenuScreen(final Labyrinth labyrinth) {
        this.labyrinth = labyrinth;

        initializeStage();
    }

    private void initializeTableForButtons(){
        atlasUi = new TextureAtlas("menu_ui/menu.pack");
        skin = new Skin(atlasUi);

        ImageTextButton.ImageTextButtonStyle imageTextButtonStyle = new ImageTextButton.ImageTextButtonStyle(
                skin.getDrawable("blue_button04"), skin.getDrawable("blue_button04_down"),
                skin.getDrawable("blue_button04"), labyrinth.fontGenerator.getFont());

        ImageTextButton startButton = new ImageTextButton("Start", imageTextButtonStyle);
        ImageTextButton settingsButton = new ImageTextButton("Settings", imageTextButtonStyle);

        ClickListener startListener = new ClickListener() {
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                labyrinth.setGameScreen();
            }
        };
        ClickListener settingsListener = new ClickListener() {
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                System.out.println("Settings");
            }
        };

        startButton.addListener(startListener);
        settingsButton.addListener(settingsListener);

        tableForButtons = new Table();
        tableForButtons.left();
        tableForButtons.padLeft(75);
        final int bottomPadding = 20;
        tableForButtons.add(startButton).padBottom(bottomPadding);
        tableForButtons.row();
        tableForButtons.add(settingsButton).padBottom(bottomPadding + 100);
    }

    private void initializeTableForTitle() {
        Label.LabelStyle titleLabelStyle = new Label.LabelStyle(labyrinth.fontGenerator.getBigFont(), Color.WHITE);
        Label titleLabelPartOne = new Label("Escape from",titleLabelStyle);
        Label titleLabelPartTwo = new Label("the maze",titleLabelStyle);

        textureOfProtagonist = new Texture("game_ui/protagonist.png");
        Image imageProtagonist = new Image(textureOfProtagonist);

        tableForTitle = new Table();
        tableForTitle.setFillParent(true);
        tableForTitle.padRight(400);

        tableForTitle.add(titleLabelPartOne);
        tableForTitle.row();
        tableForTitle.add(titleLabelPartTwo);
        tableForTitle.row();
        tableForTitle.add(imageProtagonist).width(imageProtagonist.getWidth()*2)
                .height(imageProtagonist.getHeight()*2).padTop(25);
    }

    private void initializeTable() {
        initializeTableForButtons();
        initializeTableForTitle();

        table = new Table();
        table.setFillParent(true);
        table.left().bottom();
        table.add(tableForButtons);
        table.add(tableForTitle).expandX();
    }

    private void initializeStage() {
        initializeTable();

        OrthographicCamera camera = new OrthographicCamera();
        camera.setToOrtho(false, Labyrinth.V_WIDTH, Labyrinth.V_HEIGHT);
        Viewport viewport = new ExtendViewport(Labyrinth.V_WIDTH, Labyrinth.V_HEIGHT, camera);

        stage = new Stage(viewport);
        stage.addActor(table);
        stage.getViewport().setScreenSize(Labyrinth.V_WIDTH, Labyrinth.V_HEIGHT);

        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void show() {
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0.3f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
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

    @Override
    public void dispose() {
        stage.dispose();

        atlasUi.dispose();
        skin.dispose();

        textureOfProtagonist.dispose();
    }
}
