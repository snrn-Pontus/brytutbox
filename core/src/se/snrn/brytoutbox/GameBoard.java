package se.snrn.brytoutbox;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import se.snrn.brytoutbox.ball.BallManager;
import se.snrn.brytoutbox.bricks.BrickGrid;
import se.snrn.brytoutbox.effects.EffectManager;
import se.snrn.brytoutbox.maps.Map;
import se.snrn.brytoutbox.paddle.Paddle;
import se.snrn.brytoutbox.physics.CollisionHandler;
import se.snrn.brytoutbox.powerups.PowerUpManager;
import se.snrn.brytoutbox.ui.Ui;

import static se.snrn.brytoutbox.BrytUtBox.*;


public class GameBoard implements Screen {


    private Batch batch;

    public static Paddle paddle;
    private InputHandler inputHandler;

    private BrickGrid brickGrid;
    private CollisionHandler collisionHandler;
    private World world;
    private OrthographicCamera orthographicCamera;
    private OrthographicCamera uiCamera;

    private Batch uiBatch;
    public static BallManager ballManager;
    public static EffectManager effectManager;
    public static PowerUpManager powerUpManager;
    private Ui ui;
    private Vector2 gravity;
    private Box2DDebugRenderer box2DDebugRenderer;


    public GameBoard(Batch batch, SpriteBatch uiBatch, Map map) {

        this.batch = batch;
        this.uiBatch = uiBatch;




        box2DDebugRenderer = new Box2DDebugRenderer();
        gravity = new Vector2(0, 0);
        world = new World(gravity, true);

        new CollisionHandler(world);



        ui = new Ui(BrytUtBox.gameState);

        paddle = new Paddle();

        ballManager = new BallManager();
        effectManager = new EffectManager();
        powerUpManager = new PowerUpManager();


        orthographicCamera = new OrthographicCamera(WIDTH / PPM, HEIGHT / PPM);
        uiCamera = new OrthographicCamera(WIDTH, HEIGHT);

        batch.setProjectionMatrix(orthographicCamera.combined);


        brickGrid = new BrickGrid(map);

        inputHandler = new InputHandler(paddle, orthographicCamera);


    }

    @Override
    public void show() {
        orthographicCamera.position.set(orthographicCamera.viewportWidth / 2f, orthographicCamera.viewportHeight / 2f, 0);
        uiCamera.position.set(uiCamera.viewportWidth / 2f, uiCamera.viewportHeight / 2f, 0);

        orthographicCamera.update();
        uiCamera.update();
        Gdx.input.setInputProcessor(inputHandler);


    }


    public void update(float delta) {
        orthographicCamera.update();
        uiCamera.update();

        world.step(1 / 60f, 6, 2);


        paddle.update(delta);
        ballManager.update(delta);
        powerUpManager.update(delta);
        brickGrid.update(delta);


        effectManager.update(delta);

        ui.update(delta);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);


        update(delta);


        box2DDebugRenderer.render(world, orthographicCamera.combined);

        batch.setProjectionMatrix(orthographicCamera.combined);
        batch.begin();
        paddle.render(batch);
        brickGrid.render(batch);
        ballManager.render(batch);
        powerUpManager.render(batch);
        batch.end();

        uiBatch.setProjectionMatrix(uiCamera.combined);
        uiBatch.begin();
        ui.render(uiBatch);
        effectManager.render(uiBatch);
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


}
