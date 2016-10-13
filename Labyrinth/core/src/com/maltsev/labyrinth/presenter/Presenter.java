package com.maltsev.labyrinth.presenter;



import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.maltsev.labyrinth.model.Model;

/**
 * Presenter выступает в качестве посредника между View и Model.
 * Он извлекает данные из модели и передает их во View. Также решает,
 * что нужно делать, когда вы взаимодействуете с View.
 */
public class Presenter {

    private Model model;

    public Rectangle pressOnTheScreen(Vector3 touchPos){

        Rectangle heroPosition = new Rectangle();

        heroPosition.x = touchPos.x - 80;
        heroPosition.y = touchPos.y - 80;

        if (heroPosition.x < 0) heroPosition.x = 0;
        if (heroPosition.x > 657) heroPosition.x = 657;
        if (heroPosition.y < 0) heroPosition.y = 0;
        if (heroPosition.y > 280) heroPosition.y = 280;

        return heroPosition;
    }

}
