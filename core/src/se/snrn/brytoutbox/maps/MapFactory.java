package se.snrn.brytoutbox.maps;


import java.util.Random;

public class MapFactory {

    public static int[][] createRandomMap() {
        Random random = new Random();

        int height = 20;
        int width = 10;
        int[][] map = new int[height][width];

        for (int x = 0; x < map.length; x++) {
            for (int y = 0; y < map[x].length; y++) {

                map[x][y] = random.nextInt();

            }

        }
        return map;
    }
}
