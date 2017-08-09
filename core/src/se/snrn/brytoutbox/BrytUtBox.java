package se.snrn.brytoutbox;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import se.snrn.brytoutbox.gameover.GameOverScreen;
import se.snrn.brytoutbox.levelselection.LevelSelection;
import se.snrn.brytoutbox.mainmenuscreen.MainMenu;
import se.snrn.brytoutbox.maps.Map;
import se.snrn.brytoutbox.maps.MapDownloader;
import se.snrn.brytoutbox.maps.MapFactory;

import java.util.ArrayList;

public class BrytUtBox extends Game {
    private SpriteBatch batch;
    private SpriteBatch uiBatch;

    public static GameState gameState;

    public static int PPM = 32;
    public static int BRICK_WIDTH = 64;
    public static int BRICK_HEIGHT = 32;
    public static int WIDTH;
    public static int HEIGHT;

    public static Settings settings;
    private static ArrayList<Map> maps;
    private MapDownloader mapDownloader;

    @Override
    public void create() {

        settings = new Settings();
        gameState = new GameState(this);
        WIDTH = Gdx.graphics.getWidth();
        HEIGHT = Gdx.graphics.getHeight();
        batch = new SpriteBatch();
        uiBatch = new SpriteBatch();
//        mapDownloader = new MapDownloader();
//        maps = mapDownloader.getMaps();
        setScreen(new MainMenu(batch,uiBatch,this));

    }

    public void goToLevelSelect() {
        setScreen(new LevelSelection(batch, uiBatch, this));
        gameState.setState(States.LEVEL_SELECTION);
    }

    public void selectLevel(Map map) {
        setScreen(new GameBoard(batch, uiBatch, map));
        gameState.setState(States.PLAYING);
    }

    public void gameOver(int levelNumber) {
        setScreen(new GameOverScreen(batch, uiBatch, this, levelNumber));
        gameState.setState(States.GAME_OVER);
    }

    @Override
    public void render() {
        super.render();
        gameState.update(Gdx.graphics.getDeltaTime());
        goToLevelSelect();
//        if (!mapDownloader.getMaps().isEmpty() && gameState.getState() == States.LOADING) {
//            goToLevelSelect();
//        }
    }

    @Override
    public void dispose() {
        batch.dispose();
    }

    public static ArrayList<Map> getMaps() {
        return maps;
    }
}
