package se.snrn.brytoutbox.mainmenuscreen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import se.snrn.brytoutbox.Renderable;
import se.snrn.brytoutbox.Updateable;



public class MainMenuUi implements Updateable, Renderable {


    private BitmapFont bitmapFont;
    private NinePatch uiBackground;
    private MainMenu mainMenu;

    public MainMenuUi(MainMenu mainMenu) {
        this.mainMenu = mainMenu;
        uiBackground = new NinePatch(new Texture(Gdx.files.internal("gfx/ui/selection.png")), 4, 4, 4, 4);
        bitmapFont = new BitmapFont();
    }

    @Override
    public void update(float delta) {

    }

    @Override
    public void render(Batch batch) {
        uiBackground.draw(batch,0,0, Gdx.graphics.getWidth(), 320);
        bitmapFont.draw(batch, "Press space or click to start", 32,32);

    }
}
