package com.maltsev.labyrinth.view.game.drawers;


import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Disposable;
import com.maltsev.labyrinth.presenter.tempdata.PointOnTheScreen;

/**
 * Отрисовщик протагониста
 *
 * Создан, чтобы было возможно менять направление движения протагониста и менять текстуру его обозначающую
 */
public class ProtagonistDrawer implements com.maltsev.labyrinth.presenter.interfaces.IProtagonistDrawer,Disposable {

    private Texture protagonist;
    /**
     * Используется Vector3 чтобы упроситить передачу данных для центрирования в OrthographicCamera
     */
    private Vector3 positionOfProtagonist;

    private SpriteBatch spriteBatch;

    /**
     * @param batch - средство отрисовки
     */
    public ProtagonistDrawer(SpriteBatch batch) {

        this.spriteBatch = batch;

        positionOfProtagonist = new Vector3();

        protagonist = new Texture("game_ui/protagonist.png");
    }

    @Override
    public void setPositionOfProtagonist(PointOnTheScreen pointOfNewLocationOfProtagonist) {

        positionOfProtagonist.x = pointOfNewLocationOfProtagonist.getX();
        positionOfProtagonist.y = pointOfNewLocationOfProtagonist.getY();
    }

    public Vector3 getPositionOfProtagonist() {

        return positionOfProtagonist;
    }

    @Override
    public void draw() {

        spriteBatch.draw(protagonist, positionOfProtagonist.x, positionOfProtagonist.y);
    }

    @Override
    public void dispose() {

        protagonist.dispose();
    }
}
