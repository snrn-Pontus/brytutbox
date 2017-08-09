package se.snrn.brytoutbox.mainmenuscreen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import se.snrn.brytoutbox.BrytUtBox;

import static se.snrn.brytoutbox.BrytUtBox.*;


public class MainMenu implements Screen {


    private MainMenuUi mainMenuUi;
    private MainMenuInputHandler inputHandler;
    private OrthographicCamera orthographicCamera;
    private OrthographicCamera uiCamera;

    private Batch batch;
    private Batch uiBatch;
    private BrytUtBox brytUtBox;


    public MainMenu(Batch batch, SpriteBatch uiBatch, BrytUtBox brytUtBox) {


        this.batch = batch;
        this.uiBatch = uiBatch;
        this.brytUtBox = brytUtBox;


        orthographicCamera = new OrthographicCamera(WIDTH / PPM, HEIGHT / PPM);
        uiCamera = new OrthographicCamera(WIDTH, HEIGHT);

        mainMenuUi = new MainMenuUi(this);

        inputHandler = new MainMenuInputHandler(this);

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

        batch.setProjectionMatrix(orthographicCamera.combined);
        batch.begin();

        batch.end();


        uiBatch.setProjectionMatrix(uiCamera.combined);
        uiBatch.begin();
        mainMenuUi.render(uiBatch);

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

    public void start() {
        brytUtBox.goToLevelSelect();
    }
}
