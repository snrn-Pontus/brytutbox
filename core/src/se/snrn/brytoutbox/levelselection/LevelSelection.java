package se.snrn.brytoutbox.levelselection;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import se.snrn.brytoutbox.levelselection.BrickGridPreview;
import se.snrn.brytoutbox.maps.MapReader;

import static se.snrn.brytoutbox.GameBoard.PPM;


public class LevelSelection implements Screen {




    private BrickGridPreview brickGridPreview;

    private OrthographicCamera orthographicCamera;
    private OrthographicCamera uiCamera;

    private Batch batch;
    private Batch uiBatch;

    private static final int WIDTH = Gdx.graphics.getWidth();
    private static final int HEIGHT = Gdx.graphics.getHeight();

    private MapReader mapReader;
    private SelectionUi selectionUi;


    public LevelSelection(Batch batch) {



        this.batch = batch;
        uiBatch = new SpriteBatch();

        selectionUi = new SelectionUi();

        orthographicCamera = new OrthographicCamera(WIDTH / PPM, HEIGHT / PPM);
        uiCamera = new OrthographicCamera(WIDTH, HEIGHT);


        mapReader = new MapReader();
        brickGridPreview = new BrickGridPreview(mapReader.readMapImage(1));


    }

    @Override
    public void show() {
        orthographicCamera.position.set(orthographicCamera.viewportWidth / 2f, orthographicCamera.viewportHeight / 2f, 0);
        uiCamera.position.set(uiCamera.viewportWidth / 2f, uiCamera.viewportHeight / 2f, 0);

        orthographicCamera.update();
        uiCamera.update();


    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);


        orthographicCamera.update();
        uiCamera.update();







        batch.setProjectionMatrix(orthographicCamera.combined);


        brickGridPreview.update(delta);

        batch.begin();
        brickGridPreview.render(batch);
        batch.end();


        uiBatch.setProjectionMatrix(uiCamera.combined);
        uiBatch.begin();
        selectionUi.render(uiBatch);
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


    public void changeLevel(int i) {
        brickGridPreview = new BrickGridPreview(mapReader.readMapImage(i));
    }
}
