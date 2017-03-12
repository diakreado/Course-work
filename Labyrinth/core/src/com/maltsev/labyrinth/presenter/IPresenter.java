package com.maltsev.labyrinth.presenter;


import com.maltsev.labyrinth.presenter.interfaces.IGameScreen;

/**
 * Presenter выступает в качестве посредника между View и Model.
 * Он извлекает данные из модели и передает их во View. Также решает,
 * что нужно делать, когда вы взаимодействуете с View.
 */
public interface IPresenter {

    /**
     * Начать игру
     * @param gameScreen экран с игровым процессом, должен уметь воплнять ряд действий описанных в интерфейсе IGameScreen
     * @param numberOfTheField номер игрового поля
     * @return объект отвечающий служащий посредником между GameScreen и Model, обеспечивающий логику отрисовки и правила игры
     */
    IPresenterOfGameProcess startGame(IGameScreen gameScreen, int numberOfTheField);
}
