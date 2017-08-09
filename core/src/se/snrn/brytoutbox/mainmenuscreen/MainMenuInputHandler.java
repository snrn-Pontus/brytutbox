package se.snrn.brytoutbox.mainmenuscreen;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;

public class MainMenuInputHandler implements InputProcessor {
    private MainMenu mainMenu;

    public MainMenuInputHandler(MainMenu mainMenu) {

        this.mainMenu = mainMenu;
    }

    @Override
    public boolean keyDown(int keycode) {
        if(keycode == Input.Keys.SPACE) {
            System.out.println("hurfadurf");
            mainMenu.start();
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
