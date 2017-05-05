package se.snrn.brytoutbox.levelselection;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import se.snrn.brytoutbox.Renderable;
import se.snrn.brytoutbox.Updateable;

import static se.snrn.brytoutbox.BrytUtBox.WIDTH;

public class SelectionUi implements Updateable, Renderable{
    private BitmapFont bitmapFont;
    private NinePatch uiBackground;
    private LevelSelection levelSelection;
    private Sprite leftArrow;
    private Sprite rightArrow;

    public SelectionUi(LevelSelection levelSelection) {
        this.levelSelection = levelSelection;

        uiBackground = new NinePatch(new Texture(Gdx.files.internal("gfx/ui/selection.png")), 4, 4, 4, 4);
        bitmapFont = new BitmapFont();
        leftArrow = new Sprite(new Texture("gfx/ui/arrow.png"));
        rightArrow = new Sprite(new Texture("gfx/ui/arrow.png"));
        rightArrow.flip(true,false);

        leftArrow.setPosition(0, 160);
        rightArrow.setPosition(WIDTH-rightArrow.getWidth(), 160);
    }

    @Override
    public void update(float delta) {
        leftArrow.setPosition(0, 160);
        rightArrow.setPosition(WIDTH-rightArrow.getWidth(), 160);
    }

    @Override
    public void render(Batch batch) {
        uiBackground.draw(batch,0,0, Gdx.graphics.getWidth(), 320);
        bitmapFont.draw(batch, "Level: "+levelSelection.getMapNumber(), 32,32);
        leftArrow.draw(batch);
        rightArrow.draw(batch);

    }
}
