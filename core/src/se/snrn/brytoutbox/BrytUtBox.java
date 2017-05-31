package se.snrn.brytoutbox;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import se.snrn.brytoutbox.gameover.GameOverScreen;
import se.snrn.brytoutbox.levelselection.LevelSelection;
import se.snrn.brytoutbox.maps.Map;
import se.snrn.brytoutbox.maps.MapDownloader;

import java.util.ArrayList;

public class BrytUtBox extends Game {
    private SpriteBatch batch;
    private SpriteBatch uiBatch;
    private GameBoard gameBoard;
    private LevelSelection levelSelection;
    public static int PPM = 32;
    public static int BRICK_WIDTH = 64;
    public static int BRICK_HEIGHT = 32;
    public static GameState gameState;

    public static int WIDTH;
    public static int HEIGHT;
    public static Settings settings;
    private static ArrayList<Map> maps;
    MapDownloader mapDownloader;
    @Override
    public void create() {


        settings = new Settings();
        gameState = new GameState(this);
        WIDTH = Gdx.graphics.getWidth();
        HEIGHT = Gdx.graphics.getHeight();
        batch = new SpriteBatch();
        uiBatch = new SpriteBatch();
        levelSelection = new LevelSelection(batch, uiBatch, this);
        //setScreen(new GameOverScreen(batch,uiBatch,this,1));

        mapDownloader = new MapDownloader();
        maps = mapDownloader.getMaps();

    }

    public void selectLevel(Map map) {
        setScreen(new GameBoard(batch, uiBatch, map));
        gameState.setState(States.PLAYING);
    }

    public void gameOver(int levelNumber){
            setScreen(new GameOverScreen(batch,uiBatch,this, levelNumber));
            gameState.setState(States.GAME_OVER);
    }

    @Override
    public void render() {
        super.render();
        if(!mapDownloader.getMaps().isEmpty() && gameState.getState() == States.LOADING){
            gameState.setState(States.LEVEL_SELECTION);
            setScreen(levelSelection);
        }
        gameState.update(Gdx.graphics.getDeltaTime());
        if(gameState.getState() == States.GAME_OVER && (getScreen() instanceof GameBoard)){
            gameOver(gameState.getLevel());
        }
    }

    @Override
    public void dispose() {
        batch.dispose();
    }

    public static ArrayList<Map> getMaps() {
        return maps;
    }
}
