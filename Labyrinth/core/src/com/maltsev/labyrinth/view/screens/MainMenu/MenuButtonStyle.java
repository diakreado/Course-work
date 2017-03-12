package com.maltsev.labyrinth.view.screens.MainMenu;


import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.ui.ImageTextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.Disposable;
import com.maltsev.labyrinth.view.Labyrinth;

public class MenuButtonStyle implements Disposable {

    private Skin skinForButton;
    private TextureAtlas atlasUiForButton;

    private ImageTextButton.ImageTextButtonStyle buttonStyle;

    private final Labyrinth game;

    public MenuButtonStyle(Labyrinth game) {

        this.game = game;

        atlasUiForButton = new TextureAtlas("menu_ui/menu.pack");

        skinForButton = new Skin(atlasUiForButton);

        buttonStyle = new ImageTextButton.ImageTextButtonStyle();
        buttonStyle.up = skinForButton.getDrawable("blue_button04");
        buttonStyle.down = skinForButton.getDrawable("blue_button04_down");

        buttonStyle.font = game.fontGenerator.getFont();
    }

    public ImageTextButton.ImageTextButtonStyle getButtonStyle() {

        return buttonStyle;
    }


    @Override
    public void dispose() {

        skinForButton.dispose();
        atlasUiForButton.dispose();
    }
}
