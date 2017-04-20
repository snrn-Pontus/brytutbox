package se.snrn.brytoutbox;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;


public class ResourceManager {
    public static Sprite getNumSprite(int number) {
        Sprite sprite = new Sprite(new Texture(Gdx.files.internal("gfx/num_"+number+".png")));
        sprite.setSize(1,1);
        return sprite;
    }
}
