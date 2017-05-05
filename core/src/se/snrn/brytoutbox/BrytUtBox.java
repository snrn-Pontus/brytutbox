package se.snrn.brytoutbox;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import se.snrn.brytoutbox.levelselection.LevelSelection;

public class BrytUtBox extends Game {
    private SpriteBatch batch;
    private SpriteBatch uiBatch;
    private GameBoard gameBoard;
    private LevelSelection levelSelection;
    public static int PPM = 32;
    public static GameState gameState;

    public static int WIDTH;
    public static int HEIGHT;

    @Override
    public void create() {
        WIDTH = Gdx.graphics.getWidth();
        HEIGHT = Gdx.graphics.getHeight();
        batch = new SpriteBatch();
        uiBatch = new SpriteBatch();
        levelSelection = new LevelSelection(batch, uiBatch, this);
        setScreen(levelSelection);
    }

    public void selectLevel(int levelNumber) {
        gameBoard = new GameBoard(batch, uiBatch);
        setScreen(gameBoard);
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
