package se.snrn.brytoutbox.maps;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;

public class MapReader {

    public int[][] readMapImage(int mapNumber) {
        int[][] map = new int[8][29];


        Pixmap pixmap = new Pixmap(Gdx.files.internal("maps/map_"+mapNumber+".png"));


        int width = pixmap.getWidth();
        int height = pixmap.getHeight();


        Color color = new Color();


        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {

                int val = pixmap.getPixel(i, pixmap.getHeight() - j - 1);
                Color.rgba8888ToColor(color, val);


                if (color.equals(Color.WHITE)) {
                    map[i][j] = 0;
                }
                if (color.equals(Color.BLACK)) {
                    map[i][j] = 1;
                }
                if (color.equals(Color.RED)) {
                    map[i][j] = 2;
                }
                if (color.equals(Color.BLUE)) {
                    map[i][j] = 3;
                }


            }
        }


        pixmap.dispose();
        return map;


    }


}
