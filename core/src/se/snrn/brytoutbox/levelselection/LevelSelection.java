package se.snrn.brytoutbox.levelselection;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import se.snrn.brytoutbox.BrytUtBox;
import se.snrn.brytoutbox.maps.Map;

import java.util.ArrayList;

import static se.snrn.brytoutbox.BrytUtBox.*;


public class LevelSelection implements Screen {


    private ArrayList<Map> maps;
    private BrickGridPreview brickGridPreview;

    private OrthographicCamera orthographicCamera;
    private OrthographicCamera uiCamera;

    private Batch batch;
    private Batch uiBatch;
    private BrytUtBox brytUtBox;


    private SelectionUi selectionUi;
    private LevelSelectInputHandler inputHandler;
    private int mapNumber;


    public LevelSelection(Batch batch, SpriteBatch uiBatch, BrytUtBox brytUtBox) {

        mapNumber = 0;

        this.batch = batch;
        this.uiBatch = uiBatch;
        this.brytUtBox = brytUtBox;

        selectionUi = new SelectionUi(this);

        orthographicCamera = new OrthographicCamera(WIDTH / PPM, HEIGHT / PPM);
        uiCamera = new OrthographicCamera(WIDTH, HEIGHT);


        inputHandler = new LevelSelectInputHandler(this);

    }

    @Override
    public void show() {
        orthographicCamera.position.set(orthographicCamera.viewportWidth / 2f, orthographicCamera.viewportHeight / 2f, 0);
        uiCamera.position.set(uiCamera.viewportWidth / 2f, uiCamera.viewportHeight / 2f, 0);

        orthographicCamera.update();
        uiCamera.update();
        Gdx.input.setInputProcessor(inputHandler);

        maps = BrytUtBox.getMaps();

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);


        orthographicCamera.update();
        uiCamera.update();
        if (brickGridPreview != null) {
            brickGridPreview.update(delta);
        }
        batch.setProjectionMatrix(orthographicCamera.combined);
        batch.begin();
        if (brickGridPreview != null) {
            brickGridPreview.render(batch);
        }
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

    public void selectLevel() {
        gameState.setLevel(mapNumber);
        brytUtBox.selectLevel(maps.get(mapNumber));
    }


    public void nextLevel() {
        int nextMap = mapNumber + 1;
        if (maps.get(nextMap) != null) {
            mapNumber++;
            brickGridPreview = new BrickGridPreview(maps.get(mapNumber).getIntArray());
        }
    }

    public void previousLevel() {
        int prevMap = mapNumber - 1;
        if (maps.get(prevMap) != null) {
            mapNumber--;
            brickGridPreview = new BrickGridPreview(maps.get(mapNumber).getIntArray());
        }
    }

    public int getMapNumber() {
        return mapNumber;
    }

    public String getMapName() {
        return maps.get(mapNumber).getMapName();
    }
}
