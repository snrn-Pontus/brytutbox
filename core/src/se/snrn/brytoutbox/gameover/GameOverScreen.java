package se.snrn.brytoutbox.gameover;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import se.snrn.brytoutbox.BrytUtBox;

import static se.snrn.brytoutbox.BrytUtBox.*;


public class GameOverScreen implements Screen {



    private OrthographicCamera orthographicCamera;
    private OrthographicCamera uiCamera;

    private Batch batch;
    private Batch uiBatch;
    private BrytUtBox brytUtBox;
    private GameOverUi gameOverUi;



    private GameOverInputHandler inputHandler;

    private int level;


    public GameOverScreen(Batch batch, SpriteBatch uiBatch, BrytUtBox brytUtBox, int level) {


        this.batch = batch;
        this.uiBatch = uiBatch;
        this.brytUtBox = brytUtBox;
        this.level = level;


        gameOverUi = new GameOverUi(this);

        orthographicCamera = new OrthographicCamera(WIDTH / PPM, HEIGHT / PPM);
        uiCamera = new OrthographicCamera(WIDTH, HEIGHT);




        inputHandler = new GameOverInputHandler(this);

    }

    @Override
    public void show() {
        orthographicCamera.position.set(orthographicCamera.viewportWidth / 2f, orthographicCamera.viewportHeight / 2f, 0);
        uiCamera.position.set(uiCamera.viewportWidth / 2f, uiCamera.viewportHeight / 2f, 0);

        orthographicCamera.update();
        uiCamera.update();
        Gdx.input.setInputProcessor(inputHandler);


    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);


        orthographicCamera.update();
        uiCamera.update();

        gameOverUi.update(delta);


        batch.setProjectionMatrix(orthographicCamera.combined);
        batch.begin();

        batch.end();


        uiBatch.setProjectionMatrix(uiCamera.combined);
        uiBatch.begin();
        gameOverUi.render(uiBatch);
        uiBatch.end();


    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }

    public int getLevel() {
        return level;
    }
}
