package se.snrn.brytoutbox.levelselection;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import se.snrn.brytoutbox.Renderable;
import se.snrn.brytoutbox.Updateable;

public class SelectionUi implements Updateable, Renderable{
    private BitmapFont bitmapFont;
    private NinePatch uiBackground;
    private LevelSelection levelSelection;

    public SelectionUi(LevelSelection levelSelection) {
        this.levelSelection = levelSelection;

        uiBackground = new NinePatch(new Texture(Gdx.files.internal("gfx/ui/selection.png")), 4, 4, 4, 4);
        bitmapFont = new BitmapFont();
    }

    @Override
    public void update(float delta) {

    }

    @Override
    public void render(Batch batch) {
        uiBackground.draw(batch,0,0, Gdx.graphics.getWidth(), 320);
        bitmapFont.draw(batch, "Level: "+levelSelection.getMapNumber(), 32,32);

    }
}
