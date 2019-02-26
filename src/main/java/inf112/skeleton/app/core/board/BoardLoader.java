package inf112.skeleton.app.core.board;

import inf112.skeleton.app.core.tiles.Tile;
import java.io.*;

public class BoardLoader {

    public static final int EMPTY = 0;

    public static final int ROTATE_LEFT = 1;
    public static final int ROTATE_RIGHT = 2;

    public static final int ASSEMBLY_ORANGE_STRAIGHT_DOWN = 3;
    public static final int ASSEMBLY_ORANGE_STRAIGHT_UP = 4;
    public static final int ASSEMBLY_ORANGE_STRAIGHT_RIGHT = 5;
    public static final int ASSEMBLY_ORANGE_STRAIGHT_LEFT = 6;

    public static final int ASSEMBLY_ORANGE_TURN_LEFT_UP = 7;
    public static final int ASSEMBLY_ORANGE_TURN_LEFT_DOWN = 8;
    public static final int ASSEMBLY_ORANGE_TURN_RIGHT_UP = 9;
    public static final int ASSEMBLY_ORANGE_TURN_RIGHT_DOWN = 10;
    public static final int ASSEMBLY_ORANGE_TURN_DOWN_RIGHT = 11;
    public static final int ASSEMBLY_ORANGE_TURN_DOWN_LEFT = 12;
    public static final int ASSEMBLY_ORANGE_TURN_UP_LEFT = 13;
    public static final int ASSEMBLY_ORANGE_TURN_UP_RIGHT = 14;

    /**
     * Creates an ITile based on a text file on the format:
     * (where w, h and n are integers)
     *
     * w h
     * n n n n n
     * n n n n n
     * n n n n n
     * n n n n n
     * n n n n n
     *
     * @param fileName
     * @return
     * @throws IOException
     */
    public static Tile[][] loadBoard(String fileName) throws IOException {
        int[][] file = loadFile(fileName);
        Tile[][] tiles = new Tile[file.length][file[0].length];
        for (int i = 0; i < file.length; i++) {
            for (int j = 0; j < file[i].length; j++) {
                tiles[i][j] = getTile(file[i][j]);
            }
        }
        return tiles;
    }

    /**
     * Reads a file that starts with 2 integers on the first line: width and height.
     * Reads a grid of numbers.
     *
     * @param fileName
     * @return an integer array from a text file
     * @throws IOException
     */
    private static int[][] loadFile(String fileName) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(fileName));
        String line = reader.readLine();
        String[] dimensions = line.split(" ");
        int w = Integer.parseInt(dimensions[0]);
        int h = Integer.parseInt(dimensions[1]);
        int[][] result = new int[w][h];
        for (int i = 0; i < h; i++) {
            String[] numbers = reader.readLine().split(" ");
            for (int j = 0; j < w; j++) {
                result[w][h] = Integer.parseInt(numbers[j]);
            }
        }
        return result;
    }

    /**
     * TODO: add correct return types
     *
     * @param type
     * @return
     */
    private static Tile getTile(int type) {
        switch (type) {
            case EMPTY: return null;
            case ROTATE_LEFT: return null;
            case ROTATE_RIGHT: return null;
            case ASSEMBLY_ORANGE_STRAIGHT_DOWN: return null;
            case ASSEMBLY_ORANGE_STRAIGHT_UP: return null;
            case ASSEMBLY_ORANGE_STRAIGHT_RIGHT: return null;
            case ASSEMBLY_ORANGE_STRAIGHT_LEFT: return null;
            case ASSEMBLY_ORANGE_TURN_LEFT_UP: return null;
            case ASSEMBLY_ORANGE_TURN_LEFT_DOWN: return null;
            case ASSEMBLY_ORANGE_TURN_RIGHT_UP: return null;
            case ASSEMBLY_ORANGE_TURN_RIGHT_DOWN: return null;
            case ASSEMBLY_ORANGE_TURN_DOWN_RIGHT: return null;
            case ASSEMBLY_ORANGE_TURN_DOWN_LEFT: return null;
            case ASSEMBLY_ORANGE_TURN_UP_LEFT: return null;
            case ASSEMBLY_ORANGE_TURN_UP_RIGHT: return null;
            default: throw new IllegalArgumentException("tile-type does not exist");
        }
    }
}
