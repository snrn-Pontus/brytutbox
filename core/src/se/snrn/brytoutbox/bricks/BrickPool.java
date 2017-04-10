package se.snrn.brytoutbox.bricks;


import com.badlogic.gdx.utils.Pool;

public class BrickPool extends Pool<Brick> {

    @Override
    protected Brick newObject() {
        return new Brick();
    }
}
