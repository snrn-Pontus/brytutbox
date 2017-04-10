package se.snrn.brytoutbox.bricks;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;


public class BrickSpriteFactory {


    public static Sprite getBrickSprite(int strength) {
        switch (strength) {
            case 0: {
                return new Sprite(new Texture(Gdx.files.internal("gfx/brick_0.png")));

            }
            case 1: {
                return new Sprite(new Texture(Gdx.files.internal("gfx/brick_1.png")));

            }
            case 2: {
                return new Sprite(new Texture(Gdx.files.internal("gfx/brick_2.png")));

            }
            case 3: {
                return new Sprite(new Texture(Gdx.files.internal("gfx/brick_3.png")));

            }
            case 4: {
                return new Sprite(new Texture(Gdx.files.internal("gfx/brick_4.png")));
            }
            case 5: {
                return new Sprite(new Texture(Gdx.files.internal("gfx/brick_5.png")));
            }

        }
        return null;
    }
}
