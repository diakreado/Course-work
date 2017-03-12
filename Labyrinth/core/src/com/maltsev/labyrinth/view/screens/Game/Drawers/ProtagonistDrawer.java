package com.maltsev.labyrinth.view.screens.Game.Drawers;


import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Disposable;
import com.maltsev.labyrinth.presenter.interfaces.IProtagonistDrawer;
import com.maltsev.labyrinth.presenter.tempdata.PointOnTheScreen;

/**
 * Отрисовщик протагониста
 *
 * Создан, чтобы было возможно менять направление движения протагониста и менять текстуру его обозначающую
 */
public class ProtagonistDrawer implements IProtagonistDrawer,Disposable {

    private Texture protagonist;

    private Texture protagonistRight;
    private Texture protagonistLeft;
    private Texture protagonistBack;

    /**
     * Используется Vector3 чтобы упроситить передачу данных для центрирования в OrthographicCamera
     */
    private Vector3 positionOfProtagonist;

    private SpriteBatch spriteBatch;

    /**
     * @param batch - средство отрисовки
     * @param numberOfTexture - номер текстуры, создано для кастомизации игрока
     */
    public ProtagonistDrawer(SpriteBatch batch, int numberOfTexture) {

        this.spriteBatch = batch;

        positionOfProtagonist = new Vector3();

        choiceOfTextures(numberOfTexture);

        protagonist = protagonistRight;  // Пусть по дефолту он идёт на право
    }

    /**
     * Создание текстуры по номеру
     * @param numberOfTexture - номер текстуры
     */
    private void choiceOfTextures(int numberOfTexture) {

        switch (numberOfTexture) {

            case 0: {
                protagonistRight = new Texture("game_ui/1.png");    //Всего одна текстура, но механизм использования
                protagonistLeft = protagonistRight;                            //требует такого задания
                protagonistBack = protagonistRight;
                break;
            }

            case 2: {
                protagonistRight = new Texture("game_ui/33.jpg");
                protagonistLeft = protagonistRight;
                protagonistBack = protagonistRight;
                break;
            }

            case 3: {
                protagonistRight = new Texture("game_ui/wanderer.png");
                protagonistLeft = protagonistRight;
                protagonistBack = protagonistRight;
                break;
            }

            case 4: {
                protagonistRight = new Texture("game_ui/protagonist.png");
                protagonistLeft = protagonistRight;
                protagonistBack = protagonistRight;
                break;
            }

            default: {
                protagonistRight = new Texture("game_ui/BirdRight.png");
                protagonistLeft = new Texture("game_ui/BirdLeft.png");
                protagonistBack = new Texture("game_ui/BirdBackRight.png");
                break;
            }
        }
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
    public void changeDirectionToRight() {

        protagonist = protagonistRight;
    }

    @Override
    public void changeDirectionToLeft() {

        protagonist = protagonistLeft;
    }

    @Override
    public void changeDirectionToBack() {

        protagonist = protagonistBack;
    }

    @Override
    public void draw() {

        spriteBatch.draw(protagonist, positionOfProtagonist.x, positionOfProtagonist.y);
    }

    @Override
    public void dispose() {

        protagonist.dispose();

        protagonistRight.dispose();
        protagonistLeft.dispose();
        protagonistBack.dispose();
    }
}
