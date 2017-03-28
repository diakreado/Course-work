package com.maltsev.labyrinth.view.game.drawers;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.utils.Disposable;
import com.maltsev.labyrinth.presenter.interfaces.IFieldDrawer;
import com.maltsev.labyrinth.presenter.tempdata.PointOnTheScreen;
import com.maltsev.labyrinth.presenter.tempdata.SizeOfTexture;

/**
 * Отрисовщик игрового поля
 *
 * Отрисовывает проходимые клетки и декорирующие элементы
 *
 * Обладает разными типами блоков для поля, чтобы разнообразить внешний вид поля (с этим выглядит чуть лучше, возможно в будущем когда
 * появятся другие текстуры для игрового поля станет ярче выраженно)
 *
 * Реализует интерфейс Disposable, следовательно требует, вызова метода dispose() по завершению работы для освобождения памяти
 */
public class FieldDrawer implements IFieldDrawer, Disposable {

    private SpriteBatch batch;

    private TextureAtlas atlas;

    private TextureAtlas.AtlasRegion topBottomCells;
    private TextureAtlas.AtlasRegion leftRightCells;
    private TextureAtlas.AtlasRegion leftTopRightBottomCells;

    private TextureAtlas.AtlasRegion leftTopCells;
    private TextureAtlas.AtlasRegion rightTopCells;
    private TextureAtlas.AtlasRegion leftBottomCells;
    private TextureAtlas.AtlasRegion rightBottomCells;

    private TextureAtlas.AtlasRegion leftTopRightCells;
    private TextureAtlas.AtlasRegion bottomLeftTopCells;
    private TextureAtlas.AtlasRegion rightBottomLeftCells;
    private TextureAtlas.AtlasRegion topRightBottomCells;

    private TextureAtlas.AtlasRegion leftCells;
    private TextureAtlas.AtlasRegion rightCells;
    private TextureAtlas.AtlasRegion bottomCells;
    private TextureAtlas.AtlasRegion topCells;

    private Texture tree;
    private Texture grass;

    private SizeOfTexture sizeOfBlock;

    /**
     * @param batch - то с помощью чего будет прооисходить отрисовка
     */
    public FieldDrawer(SpriteBatch batch) {

        this.batch = batch;

        atlas = new TextureAtlas("game_ui/atlasForField.pack");

        divideAtlas();

        tree = new Texture("game_ui/tree02.png");
        grass = new Texture("game_ui/grass4.png");

        sizeOfBlock = new SizeOfTexture(topBottomCells.getRegionWidth(), topBottomCells.getRegionHeight());
    }

    /**
     * Записываем регионы атласа по отдельным ссылкам, для упрощения доступа
     */
    private void divideAtlas() {

        topBottomCells = atlas.findRegion("top_bottom_cel");
        leftRightCells = atlas.findRegion("left_right_cell");
        leftTopRightBottomCells = atlas.findRegion("left_top_right_bottom_cell");

        leftTopCells = atlas.findRegion("left_top_cell");
        rightTopCells = atlas.findRegion("right_top_cell");
        leftBottomCells = atlas.findRegion("left_bottom_cell");
        rightBottomCells = atlas.findRegion("right_bottom_cell");

        leftCells = atlas.findRegion("left_cell");
        rightCells = atlas.findRegion("right_cell");
        bottomCells = atlas.findRegion("bottom_cell");
        topCells = atlas.findRegion("top_cell");

        leftTopRightCells = atlas.findRegion("left_top_right_cell");
        rightBottomLeftCells = atlas.findRegion("right_bottom_left_cell");
        bottomLeftTopCells = atlas.findRegion("bottom_left_top_cell");
        topRightBottomCells = atlas.findRegion("top_right_bottom_cell");
    }

    @Override
    public SizeOfTexture getSizeOfBlock() {

        return sizeOfBlock;
    }

    @Override
    public void drawGrass(PointOnTheScreen point) {

        batch.draw(grass, point.getX(), point.getY());
    }

    @Override
    public void drawTree(PointOnTheScreen point) {

        batch.draw(tree, point.getX(), point.getY());
    }

    @Override
    public void drawTopBottomCells(PointOnTheScreen point) {

        batch.draw(topBottomCells, point.getX(), point.getY());
    }

    @Override
    public void drawLeftRightCells(PointOnTheScreen point) {

        batch.draw(leftRightCells, point.getX(), point.getY());
    }

    @Override
    public void drawLeftTopRightBottomCells(PointOnTheScreen point) {

        batch.draw(leftTopRightBottomCells, point.getX(), point.getY());
    }

    @Override
    public void drawLeftTopCells(PointOnTheScreen point) {

        batch.draw(leftTopCells, point.getX(), point.getY());
    }

    @Override
    public void drawRightTopCells(PointOnTheScreen point) {

        batch.draw(rightTopCells, point.getX(), point.getY());
    }

    @Override
    public void drawLeftBottomCells(PointOnTheScreen point) {

        batch.draw(leftBottomCells, point.getX(), point.getY());
    }

    @Override
    public void drawRightBottomCells(PointOnTheScreen point) {

        batch.draw(rightBottomCells, point.getX(), point.getY());
    }

    @Override
    public void drawLeftCells(PointOnTheScreen point) {

        batch.draw(leftCells, point.getX(), point.getY());
    }

    @Override
    public void drawRightCells(PointOnTheScreen point) {

        batch.draw(rightCells, point.getX(), point.getY());
    }

    @Override
    public void drawBottomCells(PointOnTheScreen point) {

        batch.draw(bottomCells, point.getX(), point.getY());
    }

    @Override
    public void drawTopCells(PointOnTheScreen point) {

        batch.draw(topCells, point.getX(), point.getY());
    }

    @Override
    public void drawLeftTopRightCells(PointOnTheScreen point) {

        batch.draw(leftTopRightCells, point.getX(), point.getY());
    }

    @Override
    public void drawBottomLeftTopCells(PointOnTheScreen point) {

        batch.draw(bottomLeftTopCells, point.getX(), point.getY());
    }

    @Override
    public void drawRightBottomLeftCells(PointOnTheScreen point) {

        batch.draw(rightBottomLeftCells, point.getX(), point.getY());
    }

    @Override
    public void drawTopRightBottomCells(PointOnTheScreen point) {

        batch.draw(topRightBottomCells, point.getX(), point.getY());
    }

    @Override
    public void dispose() {

        grass.dispose();
        tree.dispose();


        atlas.dispose();
    }
}
