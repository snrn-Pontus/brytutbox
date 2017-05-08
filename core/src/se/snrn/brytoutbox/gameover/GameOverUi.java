package se.snrn.brytoutbox.gameover;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.NinePatchDrawable;
import se.snrn.brytoutbox.Renderable;
import se.snrn.brytoutbox.Updateable;
import se.snrn.brytoutbox.levelselection.LevelSelection;

import static se.snrn.brytoutbox.BrytUtBox.WIDTH;

public class GameOverUi implements Updateable, Renderable{
    private BitmapFont bitmapFont;
    private NinePatch uiBackground;
    private GameOverScreen gameOverScreen;
    private TextField textField;


    public GameOverUi(GameOverScreen gameOverScreen) {
        this.gameOverScreen = gameOverScreen;

        uiBackground = new NinePatch(new Texture(Gdx.files.internal("gfx/ui/selection.png")), 4, 4, 4, 4);
        bitmapFont = new BitmapFont();
        NinePatchDrawable drawable = new NinePatchDrawable();
        drawable.setPatch(uiBackground);
        textField = new TextField("Text", new TextField.TextFieldStyle(new BitmapFont(), Color.BLACK, drawable,drawable,drawable));

    }

    @Override
    public void update(float delta) {

    }

    @Override
    public void render(Batch batch) {
        uiBackground.draw(batch,0,0, Gdx.graphics.getWidth(), 320);
        bitmapFont.draw(batch, "Level: "+gameOverScreen.getLevel(), 32,32);
        textField.draw(batch, 1);
    }
}
