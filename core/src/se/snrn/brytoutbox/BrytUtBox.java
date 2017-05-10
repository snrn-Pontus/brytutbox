package se.snrn.brytoutbox;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import se.snrn.brytoutbox.gameover.GameOverScreen;
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
    public static Settings settings;

    @Override
    public void create() {
        settings = new Settings();
        gameState = new GameState();
        WIDTH = Gdx.graphics.getWidth();
        HEIGHT = Gdx.graphics.getHeight();
        batch = new SpriteBatch();
        uiBatch = new SpriteBatch();
        levelSelection = new LevelSelection(batch, uiBatch, this);
        //setScreen(levelSelection);
        setScreen(new GameOverScreen(batch,uiBatch,this,1));

    }

    public void selectLevel(int levelNumber) {
        gameBoard = new GameBoard(batch, uiBatch, levelNumber);
        setScreen(gameBoard);
        gameState.setState(States.PLAYING);
    }

    public void gameOver(int levelNumber){
            setScreen(new GameOverScreen(batch,uiBatch,this, levelNumber));
    }

    @Override
    public void render() {
        super.render();
        gameState.update(Gdx.graphics.getDeltaTime());
        if(gameState.getState() == States.GAME_OVER && (getScreen() instanceof GameBoard)){
            gameOver(gameState.getLevel());
        }
    }

    @Override
    public void dispose() {
        batch.dispose();
    }
}
