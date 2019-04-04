package inf112.skeleton.app.core.board;

import inf112.skeleton.app.core.tiles.ITile;
import inf112.skeleton.app.core.tiles.Tile;
import java.io.*;

public class BoardLoader {

    private static final int EMPTY = 0;

    private static final int ROTATE_LEFT = 1;
    private static final int ROTATE_RIGHT = 2;

    private static final int ASSEMBLY_ORANGE_STRAIGHT_DOWN = 3;
    private static final int ASSEMBLY_ORANGE_STRAIGHT_UP = 4;
    private static final int ASSEMBLY_ORANGE_STRAIGHT_RIGHT = 5;
    private static final int ASSEMBLY_ORANGE_STRAIGHT_LEFT = 6;

    private static final int ASSEMBLY_ORANGE_TURN_LEFT_UP = 7;
    private static final int ASSEMBLY_ORANGE_TURN_LEFT_DOWN = 8;
    private static final int ASSEMBLY_ORANGE_TURN_RIGHT_UP = 9;
    private static final int ASSEMBLY_ORANGE_TURN_RIGHT_DOWN = 10;
    private static final int ASSEMBLY_ORANGE_TURN_DOWN_RIGHT = 11;
    private static final int ASSEMBLY_ORANGE_TURN_DOWN_LEFT = 12;
    private static final int ASSEMBLY_ORANGE_TURN_UP_LEFT = 13;
    private static final int ASSEMBLY_ORANGE_TURN_UP_RIGHT = 14;

    public static void main(String[] args) {
        BoardLoader bl = new BoardLoader();
        File fl = new File("Testboard 28mars.csv");

        try {
            ITile[][] board = bl.loadBoard(fl);
        } catch(IOException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Creates an ITile based on a text file on the CSV format:
     * https://docs.google.com/spreadsheets/d/16Zcec6W2YBtkaVKUAjNydJ6Xspv5uP9LYe6uhFLXc_c/
     *
     * @param fl
     * @return
     * @throws IOException
     */
    public static ITile[][] loadBoard(File fl) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(fl));

        String line = reader.readLine();
        String[] dimensions = line.split(",");

        int w = Integer.parseInt(dimensions[0]);
        int h = Integer.parseInt(dimensions[1]);
        ITile[][] result = new ITile[w][h];

        for (int i = 0; i < h; i++) {
            String[] rLine = reader.readLine().split(",");
            for (int j = 0; j < w; j++) {
                String[] params = rLine[j].split("|");

                switch (params[0].charAt(0)) {
                    case 'N':
                        break;

                    case 'A':
                        break;

                    case 'B':
                        break;

                    case 'G':
                        break;

                    default:
                        break;
                }
            }
        }

        return result;
    }



























    /**
     * Reads a file that starts with 2 integers on the first line: width and height.
     * Reads a grid of numbers.
     *
     * @param file
     * @return an integer array from a text file
     * @throws IOException
     */
    public static int[][] loadFile(File file) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(file));
        String line = reader.readLine();
        String[] dimensions = line.split(",");
        int w = Integer.parseInt(dimensions[0]);
        int h = Integer.parseInt(dimensions[1]);
        int[][] result = new int[w][h];
        for (int i = 0; i < h; i++) {
            String[] numbers = reader.readLine().split(",");
            for (int j = 0; j < w; j++) {
                result[i][j] = Integer.parseInt(numbers[j]);
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
    private static ITile getTile(int type) {
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
