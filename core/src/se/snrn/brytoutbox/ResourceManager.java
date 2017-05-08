package se.snrn.brytoutbox;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import se.snrn.brytoutbox.powerups.PowerUpType;

public class ResourceManager {


    public static Sprite getEffect(PowerUpType type) {
        return new Sprite(new Texture(Gdx.files.internal("gfx/powerups/split_ball.png")));
    }

    public static Sprite getPowerUpBackground() {
        return new Sprite(new Texture(Gdx.files.internal("gfx/powerups/base.png")));
    }

}
