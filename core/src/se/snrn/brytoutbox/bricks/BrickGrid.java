package se.snrn.brytoutbox.bricks;


import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.Pool;
import se.snrn.brytoutbox.Debuggable;
import se.snrn.brytoutbox.GameBoard;
import se.snrn.brytoutbox.Renderable;
import se.snrn.brytoutbox.Updateable;

import static se.snrn.brytoutbox.GameBoard.PPM;

public class BrickGrid implements Updateable, Renderable, Debuggable {

    private Brick[][] bricks;
    private int bottom;
    Pool<Brick> brickPool;


    public BrickGrid(int[][] map, BrickPool brickPool) {
        this.brickPool = brickPool;



        bricks = new Brick[map.length][map[0].length];

        //bottom = Gdx.graphics.getHeight() - 32*10;
        bottom = 0;
        for (int x = 0; x < map.length; x++) {
            for (int y = 0; y < map[x].length; y++) {
                if (map[x][y] != 0) {
                    bricks[x][y] = brickPool.obtain();
                    bricks[x][y].init((x * 64), y * 32, map[x][y]);
                }
            }
        }
    }

    @Override
    public void update(float delta) {
        for (int x = 0; x < bricks.length; x++) {
            for (int y = 0; y < bricks[x].length; y++) {
                if (bricks[x] != null && bricks[x][y] != null) {

                    bricks[x][y].update(delta);
                    if(bricks[x][y].isDestroyed()) {
                        GameBoard.world.destroyBody(bricks[x][y].body);
                        bricks[x][y] = null;
                    }
                }
            }
        }
    }

    @Override
    public void render(Batch batch) {
        for (int x = 0; x < bricks.length; x++) {
            for (int y = 0; y < bricks[x].length; y++) {
                if (bricks[x] != null && bricks[x][y] != null) {
                    bricks[x][y].render(batch);
                }
            }
        }
    }

    @Override
    public void drawDebug(ShapeRenderer shapeRenderer) {
        for (int x = 0; x < bricks.length; x++) {
            for (int y = 0; y < bricks[x].length; y++) {
                if (bricks[x] != null && bricks[x][y] != null) {
                    bricks[x][y].drawDebug(shapeRenderer);
                }
            }
        }
    }

}
