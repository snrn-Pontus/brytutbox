package se.snrn.brytoutbox;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import se.snrn.brytoutbox.ball.Ball;
import se.snrn.brytoutbox.ball.BallManager;
import se.snrn.brytoutbox.bricks.BrickGrid;
import se.snrn.brytoutbox.bricks.BrickPool;
import se.snrn.brytoutbox.effects.EffectManager;
import se.snrn.brytoutbox.maps.MapReader;
import se.snrn.brytoutbox.paddle.Paddle;
import se.snrn.brytoutbox.physics.Box2DFactory;
import se.snrn.brytoutbox.physics.CollisionHandler;
import se.snrn.brytoutbox.ui.Ui;


import static se.snrn.brytoutbox.BrytUtBox.HEIGHT;
import static se.snrn.brytoutbox.BrytUtBox.PPM;
import static se.snrn.brytoutbox.BrytUtBox.WIDTH;


public class GameBoard implements Screen {


    private Batch batch;

    public static Paddle paddle;
    private InputHandler inputHandler;

    public static BrickGrid brickGrid;
    private CollisionHandler collisionHandler;
    public static World world;
    private OrthographicCamera orthographicCamera;
    private OrthographicCamera uiCamera;

    public static EffectManager effectManager;
    private Batch uiBatch;
    private BallManager ballManager;
    private Ui ui;
    public static Score score;
    private MapReader mapReader;
    private BrickPool brickPool;
    private Vector2 gravity;


    public GameBoard(Batch batch, SpriteBatch uiBatch) {

        score = new Score();
        brickPool = new BrickPool();


        this.batch = batch;
        this.uiBatch = uiBatch;


        gravity = new Vector2(0, 0);
        world = new World(gravity, true);


        paddle = new Paddle();

        ballManager = new BallManager();
        Ball ball = new Ball();
        ballManager.addBall(ball);
        ball.setStuck(true);
        paddle.setStuckBall(ball);


        ui = new Ui(score, ballManager);


        effectManager = new EffectManager();



        Box2DFactory.createRectangleBody(-4, HEIGHT / 2, 8, HEIGHT, new Object());
        Box2DFactory.createRectangleBody(WIDTH + 4, HEIGHT / 2, 8, HEIGHT, new Object());

        Box2DFactory.createRectangleBody(WIDTH / 2, HEIGHT + 4, WIDTH, 8, new Object());
        Box2DFactory.createRectangleBody(WIDTH / 2, -4, WIDTH, 8, new Object());


        orthographicCamera = new OrthographicCamera(WIDTH / PPM, HEIGHT / PPM);
        uiCamera = new OrthographicCamera(WIDTH, HEIGHT);
        batch.setProjectionMatrix(orthographicCamera.combined);

        collisionHandler = new CollisionHandler(world);

        mapReader = new MapReader();
        brickGrid = new BrickGrid(mapReader.readMapImage(1), brickPool);



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

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);


        orthographicCamera.update();
        uiCamera.update();

        world.step(1 / 60f, 6, 2);


        paddle.update(delta);
        ballManager.update(delta);
        brickGrid.update(delta);


        effectManager.update(delta);

        ui.update(delta);


        batch.setProjectionMatrix(orthographicCamera.combined);



        batch.begin();
        paddle.render(batch);
        brickGrid.render(batch);
        ballManager.render(batch);
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
