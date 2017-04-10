package se.snrn.brytoutbox;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.viewport.FitViewport;
import se.snrn.brytoutbox.bricks.BrickGrid;
import se.snrn.brytoutbox.bricks.BrickPool;
import se.snrn.brytoutbox.maps.MapLoader;


public class GameBoard implements Screen {

    private Batch batch;
    private ShapeRenderer shapeRenderer;

    private Ball ball;
    public static Paddle paddle;
    private InputHandler inputHandler;
    private Box2DDebugRenderer box2DDebugRenderer;

    public static BrickGrid brickGrid;
    private CollisionHandler collisionHandler;
    public static World world;
    private OrthographicCamera orthographicCamera;
    private FitViewport viewPort;
    BrickPool brickPool;

    public GameBoard(Batch batch, ShapeRenderer shapeRenderer) {

        brickPool = new BrickPool();


        this.batch = batch;
        this.shapeRenderer = shapeRenderer;


        Vector2 gravity = new Vector2(0, -1);
        world = new World(gravity, true);

        paddle = new Paddle();
        ball = new Ball();


        box2DDebugRenderer = new Box2DDebugRenderer();

        new Wall(0 - 12, 0 - Gdx.graphics.getHeight() / 2, 8, Gdx.graphics.getHeight());
        new Wall(Gdx.graphics.getWidth(), 0 - Gdx.graphics.getHeight() / 2, 8, Gdx.graphics.getHeight());
        new Wall(0 - Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() - 4, Gdx.graphics.getWidth(), 8);


        orthographicCamera = new OrthographicCamera();
        viewPort = new FitViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), orthographicCamera);
        batch.setProjectionMatrix(orthographicCamera.combined);
        shapeRenderer.setProjectionMatrix(orthographicCamera.combined);

        collisionHandler = new CollisionHandler(world);

        brickGrid = new BrickGrid(MapLoader.getLevel(2), brickPool);

//        collisionHandler = new CollisionHandler(ball, paddle, brickGrid);

        inputHandler = new InputHandler(paddle, ball);

        Gdx.input.setInputProcessor(inputHandler);
    }

    @Override
    public void show() {
        orthographicCamera.position.set(Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 2, 0);
        orthographicCamera.update();
        viewPort.setCamera(orthographicCamera);
        viewPort.setScreenPosition(Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 2);
        viewPort.apply();
        shapeRenderer.setProjectionMatrix(orthographicCamera.combined);


    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);


        orthographicCamera.update();
        viewPort.update(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        viewPort.apply();

        world.step(1 / 60f, 6, 2);


        paddle.update(delta);
        ball.update(delta);
//        collisionHandler.update(delta);
        brickGrid.update(delta);


        if(ball.body.getPosition().y < -10){
            System.out.println("game over");
        }

        batch.setProjectionMatrix(orthographicCamera.combined);


        box2DDebugRenderer.render(world, orthographicCamera.combined);

        batch.begin();
        paddle.render(batch);
        brickGrid.render(batch);
        ball.render(batch);
        batch.end();

        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        paddle.drawDebug(shapeRenderer);
        //brickGrid.drawDebug(shapeRenderer);
        //ball.drawDebug(shapeRenderer);
        shapeRenderer.end();
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
