package com.maltsev.labyrinth.view.screens.MainMenu;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.maltsev.labyrinth.view.Labyrinth;


public class SettingsScreen implements Screen {

    private Stage stage;

    private CheckBox checkBoxOfTouch;
    private CheckBox checkBoxController;

    private CheckBox checkBoxOfCat;
    private CheckBox checkBoxOfBird;
    private CheckBox checkBoxOfKnight;
    private CheckBox checkBoxOfWanderer;
    private CheckBox checkBoxOfTurtle;

    private ButtonGroup<CheckBox> boxButtonGroupOfControl;
    private ButtonGroup<CheckBox> boxButtonGroupOfTexture;


    private CheckBox.CheckBoxStyle checkBoxStyle;

    private ImageTextButton.ImageTextButtonStyle buttonStyle;

    private BitmapFont font;
    private BitmapFont fontForLabel;
    private BitmapFont smallFont;

    private ClickListener backToMenuListener;

    private ImageTextButton backToMenu;

    private Table table;
    private Table subTable;

    private Texture checkBoxOn;
    private Texture checkBoxOff;

    private Labyrinth game;

    public SettingsScreen(final Labyrinth game) {

        this.game = game;

        checkBoxOn = new Texture("settings_ui/blue_boxTick.png");
        checkBoxOff = new Texture("settings_ui/grey_circle.png");

        font = game.fontGenerator.getFont();
        smallFont = game.fontGenerator.getSmallFont();

        fontForLabel = game.fontGenerator.getFontForLabel();

        checkBoxStyle = new CheckBox.CheckBoxStyle(new TextureRegionDrawable(new TextureRegion(checkBoxOff)),
                new TextureRegionDrawable(new TextureRegion(checkBoxOn)), smallFont, new Color(0,0,0,1));

        checkBoxOfTouch = new CheckBox("Touch",checkBoxStyle);
        checkBoxController = new CheckBox("Controller", checkBoxStyle);

        boxButtonGroupOfControl = new ButtonGroup<>(checkBoxOfTouch, checkBoxController);

        Label selectionOfControl = new Label("Select the type of control:", new Label.LabelStyle(fontForLabel,new Color(0,0,0,1)));

        Label selectionOfTheTextureOfProtagonist = new Label("Select the texture of protagonist:", new Label.LabelStyle(fontForLabel,new Color(0,0,0,1)));


        checkBoxOfCat = new CheckBox("Cat",checkBoxStyle);
        checkBoxOfBird = new CheckBox("Bird",checkBoxStyle);
        checkBoxOfTurtle = new CheckBox("Turtle",checkBoxStyle);
        checkBoxOfWanderer = new CheckBox("Wanderer",checkBoxStyle);
        checkBoxOfKnight = new CheckBox("Knight",checkBoxStyle);

        boxButtonGroupOfTexture = new ButtonGroup<>(checkBoxOfCat,checkBoxOfBird, checkBoxOfTurtle,checkBoxOfWanderer,checkBoxOfKnight);

        checkBoxOfCat.getCells().get(0).size(100,100);
        checkBoxOfCat.getImage().setWidth(90);
        checkBoxOfBird.getCells().get(0).size(200,200);

        checkBoxOfTurtle.getCells().get(0).size(100,100);
        checkBoxOfTurtle.getImage().setWidth(90);
        checkBoxOfWanderer.getCells().get(0).size(200,200);

        checkBoxOfKnight.setChecked(true);

        checkBoxOfTouch.getCells().get(0).size(100,100);
        checkBoxOfTouch.getImage().setWidth(90);
        checkBoxOfTouch.setChecked(true);
        checkBoxController.getCells().get(0).size(200,200);

        buttonStyle = game.menuButtonStyle.getButtonStyle();

        backToMenu = new ImageTextButton("Back to menu", buttonStyle);

        registeredListenerAgain();

        table = new Table();
        subTable = new Table();

        table.top();
        table.setFillParent(true);

        table.add(backToMenu).padLeft(1300).padTop(20);
        table.row();

        subTable.add(selectionOfControl).padBottom(10);
        subTable.row();
        subTable.add(checkBoxOfTouch).padRight(300);
        subTable.add(checkBoxController).padRight(100);

        subTable.row();
        subTable.add(selectionOfTheTextureOfProtagonist).padBottom(10);
        subTable.row();
        subTable.add(checkBoxOfCat).padRight(370);
        subTable.add(checkBoxOfBird).padRight(210);
        subTable.row();
        subTable.add(checkBoxOfTurtle).padRight(310);
        subTable.add(checkBoxOfWanderer).padRight(80);
        subTable.row();
        subTable.add(checkBoxOfKnight).padLeft(500);

        table.add(subTable).padTop(50);

        stage = new Stage(new ExtendViewport(Labyrinth.V_WIDTH, Labyrinth.V_HEIGHT));

        stage.addActor(table);
    }

    private void registeredListenerAgain(){

        backToMenuListener = new ClickListener() {

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {

                game.setMainMenuScreen();
                registeredListenerAgain();
            }
        };

        backToMenu.addListener(backToMenuListener);
    }

    public int getChoiceAboutControl() {

        return boxButtonGroupOfControl.getCheckedIndex();
    }

    public int getChoiceAboutTexture() {

        return boxButtonGroupOfTexture.getCheckedIndex();
    }

    @Override
    public void show() {

        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void render(float delta) {

        Gdx.gl.glClearColor(1f, 1f, 1f, 1);
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

        font.dispose();
        fontForLabel.dispose();
        stage.dispose();
        checkBoxOn.dispose();
        checkBoxOff.dispose();
    }
}
