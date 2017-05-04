package se.snrn.brytoutbox;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector3;
import se.snrn.brytoutbox.ball.BallManager;
import se.snrn.brytoutbox.paddle.Paddle;

class InputHandler implements InputProcessor{

    private Paddle paddle;
    private BallManager balls;
    private OrthographicCamera orthographicCamera;
    private Vector3 mouseCoords;


    public InputHandler(Paddle paddle, BallManager balls, OrthographicCamera orthographicCamera) {

        this.paddle = paddle;
        this.balls = balls;
        this.orthographicCamera = orthographicCamera;
        mouseCoords = new Vector3();

    }

    @Override
    public boolean keyDown(int keycode) {
        if(keycode == Input.Keys.LEFT || keycode == Input.Keys.A){
            paddle.setMovingLeft(true);
        }
        if(keycode == Input.Keys.RIGHT || keycode == Input.Keys.D){
            paddle.setMovingRight(true);
        }
        if(keycode == Input.Keys.SPACE){
            paddle.release();
        }

        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        if(keycode == Input.Keys.LEFT || keycode == Input.Keys.A){
            paddle.setMovingLeft(false);
        }
        if(keycode == Input.Keys.RIGHT || keycode == Input.Keys.D){
            paddle.setMovingRight(false);
        }
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        mouseCoords.set(screenX, screenY, 0);
        orthographicCamera.unproject(mouseCoords);
        if(paddle.body.getFixtureList().get(0).testPoint(mouseCoords.x, mouseCoords.y)){
            paddle.release();
            return true;
        }


        if(screenX < Gdx.graphics.getWidth()/2){
            System.out.println("left");
            paddle.setMovingLeft(true);
        }
        if(screenX > Gdx.graphics.getWidth()/2){
            System.out.println("right");
            paddle.setMovingRight(true);
        }
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        paddle.setMovingLeft(false);
        paddle.setMovingRight(false);
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }
}
