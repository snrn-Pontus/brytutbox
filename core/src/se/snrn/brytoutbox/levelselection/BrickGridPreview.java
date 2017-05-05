package se.snrn.brytoutbox.levelselection;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import se.snrn.brytoutbox.Renderable;
import se.snrn.brytoutbox.Updateable;

import static se.snrn.brytoutbox.BrytUtBox.PPM;

public class BrickGridPreview implements Updateable, Renderable {

    private Sprite[][] bricks;
    private int[][] map;
    private int number;


    public BrickGridPreview(int[][] map) {


        bricks = new Sprite[map.length][map[0].length];
        this.map = map;
        this.number = number;

        int bottom = 0;
        for (int x = 0; x < map.length; x++) {
            for (int y = 0; y < map[x].length; y++) {
                if (map[x][y] != 0) {
                    bricks[x][y] = new Sprite(new Texture(Gdx.files.internal("gfx/bricks/white_brick.png")));
                    bricks[x][y].setSize(64 / PPM, 32 / PPM);

                    //bricks[x][y].setPosition((x * 64), y * 32);
                    bricks[x][y].setPosition((x*64) / PPM, (y*32) / PPM);

                    bricks[x][y].setAlpha(map[x][y] * 0.2f);
                }
            }
        }
    }

    @Override
    public void update(float delta) {
        for (int x = 0; x < bricks.length; x++) {
            for (int y = 0; y < bricks[x].length; y++) {
                if (bricks[x] != null && bricks[x][y] != null) {
                    bricks[x][y].setPosition((x*64) / PPM, (y*32) / PPM);

                }
            }
        }
    }

    @Override
    public void render(Batch batch) {
        for (Sprite[] brick : bricks) {
            for (Sprite aBrick : brick) {
                if (aBrick != null) {
                    aBrick.draw(batch);
                }
            }
        }
    }


    public int getNumber() {
        return number;
    }
}
