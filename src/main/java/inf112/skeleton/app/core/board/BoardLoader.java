package inf112.skeleton.app.core.board;

import inf112.skeleton.app.core.enums.Direction;
import inf112.skeleton.app.core.enums.DirectionChange;
import inf112.skeleton.app.core.tiles.*;
import inf112.skeleton.app.core.flag.Flag;

import java.io.*;

public class BoardLoader {

    /*
    public static void main(String[] args) {
        BoardLoader bl = new BoardLoader();
        File fl = new File("NewBoard5april.csv");

        try {
            ITile[][] board = bl.loadBoard(fl);
        } catch(IOException e) {
            System.out.println(e.getMessage());
        }
    }
    */

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

        ITile tile;
        for (int i = 0; i < h; i++) {
            String[] rLine = reader.readLine().split(",");
            for (int j = 0; j < w; j++) {
                String[] params = rLine[j].split("|");

                switch (params[0].charAt(0)) {
                    case 'N':
                        tile = new Tile(null, getFlag(params[2]), getWalls(params[1]));
                        break;

                    case 'A':
                        if(params[4].equals("MOVE"))
                            tile = new TileAssemblyLine(null, getFlag(params[2]), getWalls(params[1]), params[5].equals("EXPRESS"), Direction.getFromString(params[3]));

                        else if(params[4].equals("TURN"))
                            tile = new TileAssemblyLineTurn(null, getFlag(params[2]), getWalls(params[1]), params[5].equals("EXPRESS"), Direction.getFromString(params[3]), DirectionChange.getFromString(params[6]));

                        else if(params[4].equals("SPLIT"))
                            tile = new TileAssemblyLineSplit(null, getFlag(params[2]), getWalls(params[1]), params[5].equals("EXPRESS"), Direction.getFromString(params[3]));

                        else if(params[4].equals("MERGE"))
                            tile = new TileAssemblyLineMerge(null, getFlag(params[2]), getWalls(params[1]), params[5].equals("EXPRESS"), Direction.getFromString(params[3]));

                        else
                            throw new IllegalArgumentException("Tile of type AssemblyLine did not have a valid type");

                        break;

                    case 'B':
                        tile = new TileBlackhole(null, getFlag(params[2]), getWalls(params[1]));
                        break;

                    case 'G':
                        tile = new TileGear(null, getFlag(params[2]), getWalls(params[1]), DirectionChange.getFromString(params[3]));
                        break;

                    default:
                        throw new IllegalArgumentException("Tile type is not valid");
                }

                result[i][j] = tile;
            }
        }

        return result;
    }

    /**
     * Returns a list of booleans, indicating wall position
     *
     * @param st
     * @return
     */
    public static boolean[] getWalls(String st) {
        boolean[] res = new boolean[st.length()];
        for(int i = 0; i < st.length(); i++)
            res[i] = st.charAt(i) == '1';
        return res;
    }

    /**
     * Returns a list of booleans, indicating wall position
     *
     * @param st
     * @return
     */
    public static Flag getFlag(String st) {
        return new Flag(Integer.parseInt(st));
    }
}
