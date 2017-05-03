package se.snrn.brytoutbox;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import se.snrn.brytoutbox.ball.BallManager;
import se.snrn.brytoutbox.paddle.Paddle;

public class InputHandler implements InputProcessor{

    private Paddle paddle;
    private BallManager balls;

    public InputHandler(Paddle paddle, BallManager balls) {

        this.paddle = paddle;
        this.balls = balls;
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
            GameBoard.paddle.release();
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
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
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
