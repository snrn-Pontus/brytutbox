package se.snrn.brytoutbox;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import se.snrn.brytoutbox.levelselection.LevelSelection;

public class BrytUtBox extends Game {
    private SpriteBatch batch;
    private GameBoard gameBoard;
    private LevelSelection levelSelection;
    private ShapeRenderer shapeRenderer;

    @Override
    public void create() {
        shapeRenderer = new ShapeRenderer();
        batch = new SpriteBatch();
        gameBoard = new GameBoard(batch, shapeRenderer);
        levelSelection = new LevelSelection(batch);
        setScreen(levelSelection);
    }

    @Override
    public void render() {
        super.render();
    }

    @Override
    public void dispose() {
        batch.dispose();
    }
}
