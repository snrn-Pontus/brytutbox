package se.snrn.brytoutbox.gameover;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.NinePatchDrawable;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
import se.snrn.brytoutbox.Renderable;
import se.snrn.brytoutbox.Updateable;

import static se.snrn.brytoutbox.BrytUtBox.gameState;

public class GameOverUi implements Updateable, Renderable{
    private BitmapFont bitmapFont;
    private NinePatch uiBackground;
    private GameOverScreen gameOverScreen;
    private TextField textField;
    Stage stage;

    Drawable cursor;
    Drawable selection;
    private Label label;


    public GameOverUi(GameOverScreen gameOverScreen) {
        this.gameOverScreen = gameOverScreen;

        uiBackground = new NinePatch(new Texture(Gdx.files.internal("gfx/ui/selection.png")), 4, 4, 4, 4);
        cursor = new SpriteDrawable(new Sprite(new Texture("gfx/ui/cursor.png")));

        selection = new SpriteDrawable(new Sprite(new Texture("gfx/ui/white.png")));
        bitmapFont = new BitmapFont();
        NinePatchDrawable drawable = new NinePatchDrawable();
        drawable.setPatch(uiBackground);
        textField = new TextField("", new TextField.TextFieldStyle(new BitmapFont(), Color.BLACK, cursor,selection,drawable));


        textField.setTextFieldListener(new TextField.TextFieldListener() {
            @Override
            public void keyTyped(TextField textField, char c) {
                if ((c == '\r' || c == '\n')){
                    System.out.println(textField.getText());
                }
            }
        });

        stage = new Stage();
        stage.addActor(textField);

        label = new Label("Enter name:",new Label.LabelStyle(new BitmapFont(),Color.BLACK));
        stage.addActor(label);
        label.setPosition(128,160);
        textField.setPosition(128,128);

    }

    public void setInputFocus(){
        stage.setKeyboardFocus(textField);
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void update(float delta) {
    }

    @Override
    public void render(Batch batch) {

        uiBackground.draw(batch,0,0, Gdx.graphics.getWidth(), 320);
        bitmapFont.draw(batch, "Level: "+gameOverScreen.getLevel(), 32,32);
        bitmapFont.draw(batch, "ScoreState: "+gameState.getScoreState().getScore(), 128,32);
        textField.draw(batch, 1);
        label.draw(batch,1);
    }
}
