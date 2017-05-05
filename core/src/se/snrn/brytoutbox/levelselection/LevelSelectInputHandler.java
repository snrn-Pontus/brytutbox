package se.snrn.brytoutbox.levelselection;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;


public class LevelSelectInputHandler implements InputProcessor {

    private LevelSelection levelSelection;

    public LevelSelectInputHandler(LevelSelection levelSelection) {

        this.levelSelection = levelSelection;
    }

    @Override
    public boolean keyDown(int keycode) {
        if(keycode == Input.Keys.LEFT || keycode == Input.Keys.A){
            levelSelection.nextLevel();
        }
        if(keycode == Input.Keys.RIGHT || keycode == Input.Keys.D){
            levelSelection.previousLevel();
        }
        if(keycode == Input.Keys.SPACE){
            levelSelection.selectLevel();
        }
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
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
