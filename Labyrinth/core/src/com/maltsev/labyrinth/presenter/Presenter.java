package com.maltsev.labyrinth.presenter;


import com.maltsev.labyrinth.presenter.interfaces.IGameScreen;

/**
 * Класс отвечающий за логику работы пиложения (не за логику игры Лабиринт)
 *
 * Может начать игру
 */
public class Presenter implements IPresenter {

    private PresenterOfGameProcess presenterOfGameProcess;

    public Presenter() {

    }

    @Override
    public IPresenterOfGameProcess startGame(IGameScreen gameScreen, int numberOfTheField) {

        presenterOfGameProcess = new PresenterOfGameProcess(gameScreen, numberOfTheField);
        return presenterOfGameProcess;
    }


}