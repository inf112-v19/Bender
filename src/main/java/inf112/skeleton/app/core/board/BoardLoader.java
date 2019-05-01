package inf112.skeleton.app.core.board;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import inf112.skeleton.app.core.enums.Direction;
import inf112.skeleton.app.core.enums.DirectionChange;
import inf112.skeleton.app.core.tiles.*;
import inf112.skeleton.app.core.flag.*;

import java.io.*;

public class BoardLoader {

    /**
     * Creates an ITile based on a text file on the CSV format:
     * https://docs.google.com/spreadsheets/d/16Zcec6W2YBtkaVKUAjNydJ6Xspv5uP9LYe6uhFLXc_c/
     *
     * @param fl
     * @return
     * @throws IOException
     */
    public static ITile[][] loadBoard(String path) throws IOException {
        FileHandle fileHandle = Gdx.files.internal(path);
        String text = fileHandle.readString();
        String[] lines = text.split("\\r?\\n");

        String[] dimensions = lines[0].split(",");

        int w = Integer.parseInt(dimensions[0]);
        int h = Integer.parseInt(dimensions[1]);
        ITile[][] result = new ITile[w][h];

        ITile tile;
        for (int i = 0; i < h; i++) {
            String[] rLine = lines[i + 1].split(",");
            for (int j = 0; j < w; j++) {
                String[] params = rLine[j].split("\\|");

                switch (params[0].charAt(0)) {
                    case 'N':
                        tile = new Tile(null, getFlag(params[1]), getWalls(params[2]));
                        break;

                    case 'A':
                        if(params[3].equals("MOVE"))
                            tile = new TileAssemblyLine(null, getFlag(params[1]), getWalls(params[2]), params[5].equals("EXPRESS"), Direction.getFromString(params[4]));

                        else if(params[3].equals("TURN"))
                            tile = new TileAssemblyLineTurn(null, getFlag(params[1]), getWalls(params[2]), params[6].equals("EXPRESS"), Direction.getFromString(params[4]), DirectionChange.getFromString(params[5]));

                        else if(params[3].equals("SPLIT"))
                            tile = new TileAssemblyLineSplit(null, getFlag(params[1]), getWalls(params[2]), params[5].equals("EXPRESS"), Direction.getFromString(params[4]));

                        else if(params[3].equals("MERGE"))
                            tile = new TileAssemblyLineMerge(null, getFlag(params[1]), getWalls(params[2]), params[5].equals("EXPRESS"), Direction.getFromString(params[4]));

                        else
                            throw new IllegalArgumentException("Tile of type AssemblyLine did not have a valid type");

                        break;

                    case 'B':
                        tile = new TileBlackhole(null, getFlag(params[1]), getWalls(params[2]));
                        break;

                    case 'G':
                        tile = new TileGear(null, getFlag(params[1]), getWalls(params[2]), DirectionChange.getFromString(params[3]));
                        break;

                    case 'R':
                        tile = new TileRepair(null, getFlag(params[1]), getWalls(params[2]), Integer.parseInt(params[3]));
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
    public static IFlag getFlag(String st) {
        int ord = Integer.parseInt(st);
        if(ord != 0)
            return new Flag(ord);
        return null;
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
}
