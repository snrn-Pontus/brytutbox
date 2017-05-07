package se.snrn.brytoutbox.bricks;


import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.Pool;
import se.snrn.brytoutbox.*;

public class BrickGrid implements Updateable, Renderable, Debuggable {

    private Brick[][] bricks;
    private int bottom;
    private Pool<Brick> brickPool;
    private int bricksLeft;


    public BrickGrid(int[][] map, BrickPool brickPool) {
        this.brickPool = brickPool;


        bricks = new Brick[map.length][map[0].length];

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

        bricksLeft = 0;

        for (int x = 0; x < bricks.length; x++) {
            for (int y = 0; y < bricks[x].length; y++) {
                if (bricks[x] != null && bricks[x][y] != null) {

                    bricks[x][y].update(delta);
                    bricksLeft++;
                    if (bricks[x][y].isDestroyed()) {

                        GameBoard.world.destroyBody(bricks[x][y].body);
                        bricks[x][y] = null;
                    }
                }
            }
        }
        BrytUtBox.gameState.setBricksLeft(bricksLeft);
    }

    @Override
    public void render(Batch batch) {
        for (Brick[] brick : bricks) {
            for (Brick aBrick : brick) {
                if (aBrick != null) {
                    aBrick.render(batch);
                }
            }
        }
    }

    @Override
    public void drawDebug(ShapeRenderer shapeRenderer) {
        for (Brick[] brick : bricks) {
            for (Brick aBrick : brick) {
                if (aBrick != null) {
                    aBrick.drawDebug(shapeRenderer);
                }
            }
        }
    }

}
